package com.stevecoder.managementApp.task.application.scheduling;

import com.stevecoder.managementApp.task.domain.port.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateTaskStateSchedule {

    private final TaskRepository taskRepository;

    //    @Scheduled(cron = "0 * * * * *") // cada minuto
    @Scheduled(cron = "0 * * * * *")
    public void updateTaskStates() {

        LocalDateTime now = LocalDateTime.now();

        taskRepository.moveToRunning(
                now.minusMinutes(2),
                now
        );

        taskRepository.moveToDone(
                now.minusMinutes(8)
        );
    }

}
