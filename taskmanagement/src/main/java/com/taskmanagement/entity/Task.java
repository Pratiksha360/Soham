package com.taskmanagement.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long taskID;
	private String taskName;
	private StringBuffer description;
	private String status;
	private String estimatedTime;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date endDate;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;
	@ManyToOne
	@NotNull
	@JoinColumn(name = "projectId")
	private Project project;
	
}
