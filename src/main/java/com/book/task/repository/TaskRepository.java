package com.book.task.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.book.task.entity.Task;

@RepositoryRestResource(path = "task", collectionResourceRel="tasks")
public interface TaskRepository extends JpaRepository<Task, Integer>, JpaSpecificationExecutor<Task>, QuerydslPredicateExecutor<Task>{

	Page<Task> findByIn(@Param(value = "id") List<Integer> eventId, Pageable pageable);
	
	Page<Task> findByNameIn(@Param(value = "name") Collection<String> names, Pageable pageable);
	
	Page<Task> findAll(Pageable pageable);
	
	List<Task> findByName(@Param(value = "name") String name);
	
	@Query(name = "Task.findById", nativeQuery = true)
	@RestResource(exported = false)
	Optional<Task> findById(@Param(value = "id") long id);
}
