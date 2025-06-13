package com.tasktogether.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tasktogether.model.Historical;
import com.tasktogether.model.Project;

public interface HistoricalRepository extends JpaRepository<Historical, Long>{

	 @Query("""
		        SELECT h.project
		        FROM Historical h
		        WHERE h.user.id = :userId
		        GROUP BY h.project
		        ORDER BY MAX(h.changeDate) DESC
		        """)
		    List<Project> findTop3RecentProjectsByUserId(@Param("userId") Long userId, Pageable pageable);
	
}
