package com.stevecoder.managementApp.task.infrastructure.database.repository;

import com.stevecoder.managementApp.task.domain.enums.TaskState;
import com.stevecoder.managementApp.task.infrastructure.database.entity.TaskEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface QueryTaskRepository extends JpaRepository<TaskEntity, Long> {

    @Modifying
    @Transactional
    @Query("""
                UPDATE TaskEntity t 
                SET t.state = :running,
                    t.startedAt = :now
                WHERE t.state = :created
                AND t.createdAt < :createdLimit
            """)
    int moveToRunning(@Param("createdLimit") LocalDateTime createdLimit,
                      @Param("now") LocalDateTime now,
                      @Param("created") TaskState created,
                      @Param("running") TaskState running);

    @Modifying
    @Transactional
    @Query("""
                UPDATE TaskEntity t 
                SET t.state = :done
                WHERE t.state = :running
                AND t.startedAt < :runningLimit
            """)
    int moveToDone(@Param("runningLimit") LocalDateTime runningLimit,
                   @Param("running") TaskState running,
                   @Param("done") TaskState done);

}
