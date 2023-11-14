package com.book.task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	@NotNull(message = "{NotNull.Task.name}")
	private String name;

	@Column(nullable = false)
	@NotNull(message = "{NotNull.Task.description}")
	private String description;
	
}
