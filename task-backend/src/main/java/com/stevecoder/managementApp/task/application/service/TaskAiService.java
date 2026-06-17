package com.stevecoder.managementApp.task.application.service;

import com.stevecoder.managementApp.task.domain.enums.TaskCategory;
import com.stevecoder.managementApp.task.domain.enums.TaskPriority;
import com.stevecoder.managementApp.task.infrastructure.api.dto.TaskAnalysisDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TaskAiService {

    private final ChatClient chatClient;

    public TaskAiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public TaskAnalysisDTO analyzeTask(String description) {
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
}
