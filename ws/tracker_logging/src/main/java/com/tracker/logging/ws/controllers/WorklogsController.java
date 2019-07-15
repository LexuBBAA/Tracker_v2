package com.tracker.logging.ws.controllers;

import com.tracker.logging.ws.datasource.dtos.WorklogDto;
import com.tracker.logging.ws.datasource.services.WorklogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class WorklogsController {
	@Autowired
	private WorklogsService worklogsService;

	@GetMapping("/")
	public String getStatus() {
		return "Available";
	}

	@GetMapping("/worklogs/{taskId}")
	public ResponseEntity getAllRelatedTo(@PathVariable(name = "taskId") String relatesTo) {
		List<WorklogDto> storedWorklogs = worklogsService.getAllRelatedTo(relatesTo);
		if(storedWorklogs.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(storedWorklogs, HttpStatus.OK);
	}

	@PostMapping("/worklogs")
	public ResponseEntity createNewWorkload(
			@RequestHeader(name = "userId") String userId,
			@RequestBody WorklogDto newWorkload
	) {
		newWorkload.createdBy = userId;
		WorklogDto storedWorkload = worklogsService.create(newWorkload);
		if(storedWorkload != null) {
			return new ResponseEntity<>(storedWorkload, HttpStatus.OK);
		}

		return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/worklogs")
	public ResponseEntity updateWorklog(
			@RequestHeader(name = "userId") String userId,
			@RequestBody WorklogDto worklog
	) {
		worklog.lastModifiedBy = userId;
		worklog.lastModifiedAt = LocalDateTime.now();
		WorklogDto storedWorklog = worklogsService.update(worklog);
		if(storedWorklog != null) {
			return new ResponseEntity<>(storedWorklog, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/worklogs/{worklogId}")
	public ResponseEntity deleteWorklog(@PathVariable(name = "worklogId") String worklogId) {
		if(worklogsService.deleteById(worklogId)) {
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/worklogs")
	public ResponseEntity deleteAllWorklogs(
			@RequestParam(name = "relatedTo") String relatedTo
	) {
		List<WorklogDto> storedWorklogs = worklogsService.getAllRelatedTo(relatedTo);
		if(storedWorklogs.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		worklogsService.deleteAllRelatedTo(relatedTo);
		storedWorklogs = worklogsService.getAllRelatedTo(relatedTo);
		if(storedWorklogs.isEmpty()) {
			return new ResponseEntity(HttpStatus.OK);
		}

		return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
