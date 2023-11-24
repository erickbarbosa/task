package com.book.task.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.book.task.dto.TaskDto;
import com.book.task.entity.Task;
import com.book.task.repository.TaskRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskService {

	private TaskRepository repository;
	
	public TaskService(TaskRepository repository) {
		this.repository = repository;
	}
	
	public Page<Task> getTasks(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public Task get(long taskId) {
		Optional<Task> task = repository.findById(taskId);
		return task.get();
	}
	
	public Task saveTask(TaskDto taskDto) {
		ModelMapper modelMapper = new ModelMapper();
		Task task = modelMapper.map(taskDto, Task.class);
		
		return repository.save(task);
	}
}
