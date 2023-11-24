package com.book.task.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.book.task.dto.TaskDto;
import com.book.task.entity.Task;
import com.book.task.service.TaskService;
import com.book.task.uri.TaskUri;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RepositoryRestController(path = "/todo/")
@RequiredArgsConstructor
public class TaskController {

	private final PagedResourcesAssembler pagedResourcesAssembler;
	private TaskService service;
	
	
	@GetMapping(path = TaskUri.TASKS)
	public ResponseEntity<?> getTasks(TaskDto taskDto, Pageable pageable, PersistentEntityResourceAssembler resourceAssembler) {
		log.info("TaskController: " + taskDto);
		Page<Task> events = service.getTasks(pageable);
		PagedModel<?> resource = pagedResourcesAssembler.toModel(events, resourceAssembler);
		return ResponseEntity.ok(resource);
	}
	
	@GetMapping(path = TaskUri.TASK)
	public ResponseEntity<?> getTask(@PathVariable("id") int taskId, Pageable pageable, PersistentEntityResourceAssembler resourceAssembler) {
		try {
			log.info("TaskController::: " + taskId);
			Task task = service.get(taskId);
			Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(getClass()).getTask(taskId, pageable, resourceAssembler)).withSelfRel();
			Link allLinks = WebMvcLinkBuilder.linkTo(methodOn(getClass())).slash("/tasks").withRel("all tasks");
			EntityModel<Task> entityModel = EntityModel.of(task);
			entityModel.add(selfLink, allLinks);
			return ResponseEntity.ok(entityModel);
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found");
		}
	}
	
	@PostMapping(path = TaskUri.CREATE_TASK)
	public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto, Pageable pageable, PersistentEntityResourceAssembler resourceAssembler) {
		log.info("TaskController:"+taskDto);
		Task events = service.saveTask(taskDto);
		Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(getClass()).createTask(taskDto, pageable, resourceAssembler)).withSelfRel();
		Link allLinks = WebMvcLinkBuilder.linkTo(methodOn(getClass())).slash("/tasks").withRel("all tasks");
		EntityModel<Task> entityModel = EntityModel.of(events);
		entityModel.add(selfLink, allLinks);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("CustomResponseHeader", "Custom Value");
		return new ResponseEntity<EntityModel<Task>>(entityModel, responseHeaders, HttpStatus.CREATED);
	}
}
