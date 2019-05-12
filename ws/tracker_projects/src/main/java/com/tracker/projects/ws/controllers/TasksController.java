package com.tracker.projects.ws.controllers;

import com.tracker.projects.ws.datasource.dtos.tasks.TaskDto;
import com.tracker.projects.ws.datasource.dtos.tasks.TaskPreviewDto;
import com.tracker.projects.ws.datasource.dtos.tasks.TaskStatusDto;
import com.tracker.projects.ws.datasource.dtos.tasks.TaskTypeDto;
import com.tracker.projects.ws.datasource.services.tasks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TasksController {
    @Autowired
    private TaskService tasksService;
    @Autowired
    private TaskPreviewService taskPreviewService;
    @Autowired
    private TaskTypeService taskTypeService;
    @Autowired
    private TaskStatusService taskStatusService;
    @Autowired
    private TaskPriorityService taskPriorityService;

    @GetMapping("/tasks")
    public ResponseEntity getTasks(
            @Nullable @RequestParam(name = "epics", required = false) Boolean onlyEpics,
            @Nullable @RequestParam(name = "projectId", required = false) String forProject,
            @Nullable @RequestParam(name = "sprintId", required = false) String forSprint,
            @Nullable @RequestParam(name = "parentId", required = false) String withParent,
            @Nullable @RequestParam(name = "partOf", required = false) String partOf,
            @Nullable @RequestParam(name = "epicId", required = false) String inEpic,
            @Nullable @RequestParam(name = "subtaskOf", required = false) String subtaskOf,
            @Nullable @RequestParam(name = "blocking", required = false) String blocking
    ) {
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
        if(onlyEpics != null && onlyEpics) {
            if(forProject == null) {
                new Exception(TasksController.class.getSimpleName() + "- getTasks : projectId required for getEpics()").printStackTrace();
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            List<TaskPreviewDto> epics = taskPreviewService.findEpicsForProject(forProject);
            if(epics.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(epics, HttpStatus.OK);
        }

        if(forSprint != null) {
            List<TaskPreviewDto> tasksInSprint = taskPreviewService.findForSprint(forSprint);
            if(tasksInSprint.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tasksInSprint, HttpStatus.OK);
        }

        if(withParent != null) {
            List<TaskPreviewDto> tasksWithParent = taskPreviewService.findTasksWithParent(withParent);
            if(tasksWithParent.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tasksWithParent, HttpStatus.OK);
        }

        if(partOf != null) {
            List<TaskPreviewDto> tasks = taskPreviewService.findTasksPartOF(partOf);
            if(tasks.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }

        if(inEpic != null) {
            List<TaskPreviewDto> tasksInEpic = taskPreviewService.findTasksForEpic(inEpic);
            if(tasksInEpic.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tasksInEpic, HttpStatus.OK);
        }

        if(subtaskOf != null) {
            List<TaskPreviewDto> tasks = taskPreviewService.findSubtasksOf(subtaskOf);
            if(tasks.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }

        if(blocking != null) {
            List<TaskPreviewDto> tasks = taskPreviewService.findTasksBlocking(blocking);
            if(tasks.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }

        new Exception(TasksController.class.getSimpleName() + "- getTasks : no valid args provided").printStackTrace();
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity getTaskDetails(@PathVariable(name = "taskId") String taskId) {
        TaskDto taskDetails = tasksService.getTaskDetails(taskId);
        if(taskDetails != null) {
            return new ResponseEntity<>(taskDetails, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tasks")
    public ResponseEntity createNewTask(@RequestBody TaskDto newTask) {
        if(validatePayload(newTask) != HttpStatus.OK) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(newTask.status == null || newTask.status.value == null) {
            newTask.status = taskStatusService.getByValue("OPEN");
        }
        if(newTask.priority == null || newTask.priority.value == null) {
            newTask.priority = taskPriorityService.findByValue("MEDIUM");
        }
        if(newTask.lastModifiedBy == null) {
            newTask.lastModifiedBy = newTask.createdBy;
        }

        TaskDto storedTask = tasksService.createTask(newTask);
        if(storedTask != null) {
            return new ResponseEntity<>(storedTask, HttpStatus.OK);
        }

        new Exception(TasksController.class.getSimpleName() + "- createNewTask : failed to create task: " + newTask.toString()).printStackTrace();
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/tasks")
    public ResponseEntity updateTask(
            @RequestHeader(value = "userId") String userId,
            @RequestBody TaskDto task
    ) {
        if(validatePayload(task) != HttpStatus.OK) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if(task.status != null && task.status.value != null) {
            task.status = taskStatusService.getByValue(task.status.value);
        }
        if(task.priority != null && task.priority.value != null) {
            task.priority = taskPriorityService.findByValue(task.priority.value);
        }
        if(task.type != null && task.type.value != null) {
            task.type = taskTypeService.getByValue(task.type.value);
        }
        task.lastModifiedBy = userId;
        TaskDto updatedTask = tasksService.updateTask(task);
        if(updatedTask != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        }

        new Exception(TasksController.class.getSimpleName() + "- updateTask : failed to update task: " + task.toString()).printStackTrace();
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity deleteTask(@PathVariable(name = "taskId") String taskId) {
        if(tasksService.deleteById(taskId)) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @NonNull
    private HttpStatus validatePayload(TaskDto task) {
        if(task.title == null) {
            new Exception(TasksController.class.getSimpleName() + "- createNewTask : mandatory field missing: Title").printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
        if(task.type == null || task.type.value == null) {
            new Exception(TasksController.class.getSimpleName() + "- createNewTask : mandatory field missing: Type").printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
        if(task.createdBy == null) {
            new Exception(TasksController.class.getSimpleName() + "- createNewTask : mandatory field missing: CreatedBy").printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
        if(task.project == null) {
            new Exception(TasksController.class.getSimpleName() + "- createNewTask : mandatory field missing: Project").printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }
}
