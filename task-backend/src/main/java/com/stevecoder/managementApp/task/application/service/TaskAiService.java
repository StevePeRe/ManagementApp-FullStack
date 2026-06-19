package com.stevecoder.managementApp.task.application.service;

import com.stevecoder.managementApp.task.domain.enums.TaskCategory;
import com.stevecoder.managementApp.task.domain.enums.TaskPriority;
import com.stevecoder.managementApp.task.infrastructure.api.dto.TaskAnalysisDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class TaskAiService {

    private final ChatClient chatClient;

    @Value("${spring.ai.fallback.enabled:true}")
    private boolean fallbackEnabled;

    @Value("${spring.ai.timeout-seconds:30}")
    private int timeoutSeconds;

    public TaskAiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public TaskAnalysisDTO analyzeTask(String description) {
        try {
            log.info("Attempting AI analysis with Ollama...");
            return analyzeWithAI(description);
        } catch (Exception e) {
            if (fallbackEnabled) {
                log.warn("AI service unavailable ({}), falling back to rule-based analysis: {}", e.getClass().getSimpleName(), e.getMessage());
                return analyzeWithFallback(description);
            }
            throw new RuntimeException("AI analysis failed and fallback is disabled", e);
        }
    }

    private TaskAnalysisDTO analyzeWithAI(String description) {
        String systemPrompt = """
            You are a task management assistant. Analyze the user's task description and extract:
            - title: A concise task name (max 50 chars)
            - priority: LOW, MEDIUM, HIGH, or URGENT
            - category: WORK, PERSONAL, HEALTH, EDUCATION, FINANCE, or OTHER
            - dueDate: Expected deadline in YYYY-MM-DD format (null if not mentioned)
            - confidence: Your confidence level from 0.0 to 1.0
            
            Return ONLY a valid JSON object with these fields. No explanations.
            Current date: %s
            """.formatted(LocalDate.now());

        return chatClient.prompt()
                .system(systemPrompt)
                .user(description)
                .call()
                .entity(TaskAnalysisDTO.class);
    }

    private TaskAnalysisDTO analyzeWithFallback(String description) {
        String lower = description.toLowerCase();
        String title = extractTitle(description);
        TaskPriority priority = detectPriority(lower);
        TaskCategory category = detectCategory(lower);
        LocalDate dueDate = detectDate(lower);

        TaskAnalysisDTO dto = new TaskAnalysisDTO();
        dto.setTitle(title);
        dto.setPriority(priority);
        dto.setCategory(category);
        dto.setDueDate(dueDate);
        dto.setConfidence(0.5);
        dto.setAiUsed(false);
        return dto;
    }

    private String extractTitle(String description) {
        if (description.length() <= 50) {
            return description;
        }
        String truncated = description.substring(0, 50);
        int lastSpace = truncated.lastIndexOf(' ');
        return lastSpace > 0 ? truncated.substring(0, lastSpace) + "..." : truncated + "...";
    }

    private TaskPriority detectPriority(String lower) {
        if (lower.matches(".*\\b(urgent|urgente|critical|critico|asap|emergency|emergencia|ya|inmediato)\\b.*")) {
            return TaskPriority.URGENT;
        }
        if (lower.matches(".*\\b(high|alta|important|importante|priority|prioridad)\\b.*")) {
            return TaskPriority.HIGH;
        }
        if (lower.matches(".*\\b(low|baja|minor|menor|optional|opcional|when possible|cuando se pueda)\\b.*")) {
            return TaskPriority.LOW;
        }
        return TaskPriority.MEDIUM;
    }

    private TaskCategory detectCategory(String lower) {
        if (lower.matches(".*\\b(work|trabajo|meeting|reunion|client|cliente|project|proyecto|report|informe|code|codigo|deploy|presentation|presentacion)\\b.*")) {
            return TaskCategory.WORK;
        }
        if (lower.matches(".*\\b(health|salud|doctor|medical|medico|exercise|ejercicio|gym|hospital|appointment|cita)\\b.*")) {
            return TaskCategory.HEALTH;
        }
        if (lower.matches(".*\\b(education|educacion|study|estudiar|course|curso|exam|examen|class|clase|homework|tarea)\\b.*")) {
            return TaskCategory.EDUCATION;
        }
        if (lower.matches(".*\\b(finance|finanzas|pay|pagar|budget|presupuesto|invoice|factura|bank|banco|tax|impuesto|money|dinero)\\b.*")) {
            return TaskCategory.FINANCE;
        }
        if (lower.matches(".*\\b(personal|personal|home|casa|family|familia|friend|amigo|buy|comprar|errand|recado|clean|limpiar)\\b.*")) {
            return TaskCategory.PERSONAL;
        }
        return TaskCategory.OTHER;
    }

    private LocalDate detectDate(String lower) {
        Pattern isoPattern = Pattern.compile("\\b(\\d{4}-\\d{2}-\\d{2})\\b");
        Matcher isoMatcher = isoPattern.matcher(lower);
        if (isoMatcher.find()) {
            try {
                return LocalDate.parse(isoMatcher.group(1));
            } catch (DateTimeParseException e) {
                log.warn("Invalid date format in fallback: {}", isoMatcher.group(1));
            }
        }

        LocalDate today = LocalDate.now();
        if (lower.matches(".*\\b(today|hoy)\\b.*")) return today;
        if (lower.matches(".*\\b(tomorrow|manana)\\b.*")) return today.plusDays(1);
        if (lower.matches(".*\\b(weekend|fin de semana)\\b.*")) {
            return today.with(java.time.temporal.TemporalAdjusters.next(java.time.DayOfWeek.SATURDAY));
        }
        if (lower.matches(".*\\b(next week|proxima semana)\\b.*")) return today.plusWeeks(1);
        if (lower.matches(".*\\b(next month|proximo mes)\\b.*")) return today.plusMonths(1);

        String[] dayNames = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday",
                             "lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo"};
        java.time.DayOfWeek[] dayEnums = {
            java.time.DayOfWeek.MONDAY, java.time.DayOfWeek.TUESDAY, java.time.DayOfWeek.WEDNESDAY,
            java.time.DayOfWeek.THURSDAY, java.time.DayOfWeek.FRIDAY, java.time.DayOfWeek.SATURDAY,
            java.time.DayOfWeek.SUNDAY, java.time.DayOfWeek.MONDAY, java.time.DayOfWeek.TUESDAY,
            java.time.DayOfWeek.WEDNESDAY, java.time.DayOfWeek.THURSDAY, java.time.DayOfWeek.FRIDAY,
            java.time.DayOfWeek.SATURDAY, java.time.DayOfWeek.SUNDAY
        };
        for (int i = 0; i < dayNames.length; i++) {
            if (lower.matches(".*\\b(" + dayNames[i] + ")\\b.*")) {
                return today.with(java.time.temporal.TemporalAdjusters.next(dayEnums[i]));
            }
        }

        return null;
    }
}
