package com.tasktogether.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tasktogether.model.Task;
import com.tasktogether.model.TaskUser;
import com.tasktogether.model.TaskUserID;

public interface TaskUserRepository extends JpaRepository<TaskUser, TaskUserID>{
	
	@Query("""
		    SELECT tu.task
		    FROM TaskUser tu
		    WHERE tu.task.limitDate BETWEEN :now AND :inThreeDays
		      AND tu.task.status <> 2
		      AND tu.user.id = :userId
		    """)
		List<Task> findTasksExpiringSoon(
		    @Param("userId") Long userId,
		    @Param("now") LocalDate now,
		    @Param("inThreeDays") LocalDate inThreeDays
		);
	
	@Query("""
		    SELECT tu.task
		    FROM TaskUser tu
		    WHERE tu.task.limitDate > :now
		      AND tu.task.status <> 2
		      AND tu.user.id = :userId
		    """)
		List<Task> findUpcomingTasks(
		    @Param("userId") Long userId,
		    @Param("now") LocalDate now
		);

	
}
