package com.tasktogether.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tasktogether.model.Project;
import com.tasktogether.model.Task;
import com.tasktogether.model.User;

public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findByUserCreator(User userCreator); //Posiblemente lo quite
	List<Task> findByProjectAndStatusNot(Project project, Integer status);
	
	@Query("""
		    SELECT DISTINCT t FROM Task t
		    JOIN t.assignedUsers tu
		    JOIN tu.user u
		    JOIN FETCH t.project
		    JOIN FETCH t.userCreator
		    WHERE u.email = :email
		    ORDER BY t.priority DESC
		""")
		List<Task> findTasksByUserEmail(@Param("email") String email);
	/*Esta consulta se hizo para obtener todas las tareas de un usuario dado su email*/

}
