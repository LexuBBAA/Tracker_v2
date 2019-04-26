package com.tracker.projects.ws.datasource.dtos.tasks;

import com.tracker.projects.ws.datasource.entities.tasks.*;
import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskPriority;
import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskStatus;
import com.tracker.projects.ws.datasource.entities.tasks.enums.TaskType;

import java.io.Serializable;
import java.sql.Date;

public abstract class AbstractTaskDto implements Serializable {
    public Integer id;
    public String title;
    public String description;
    public Integer projectId;
    public Integer sprintId;
    public Integer epicId;
    public Integer storyId;
    public Date startDate;
    public Date dueDate;
    public TaskPriority priority;
    public TaskType type;
    public Double estimate;
    public Double loggedWork;
    public TaskStatus status;
    public String attachment;
    public Integer assignedTo;
    public Integer createdBy;

    protected AbstractTaskDto() {
    }

    protected AbstractTaskDto(TaskEntity taskEntity) {
        this.id = taskEntity.getTaskId();
        this.title = taskEntity.getTitle();
        this.description = taskEntity.getDescription();
        this.projectId = taskEntity.getProjectId();
        this.sprintId = taskEntity.getSprintId();
        this.epicId = taskEntity.getEpicId();
        this.storyId = taskEntity.getStoryId();
        this.startDate = taskEntity.getStartDate();
        this.dueDate = taskEntity.getDueDate();
        this.priority = TaskPriority.valueOf(taskEntity.getPriority());
        this.type = TaskType.valueOf(taskEntity.getType());
        this.estimate = taskEntity.getEstimate();
        this.loggedWork = taskEntity.getLoggedWork();
        this.attachment = taskEntity.getAttachment();
        this.assignedTo = taskEntity.getAssignedTo();
        this.createdBy = taskEntity.getCreatedBy();
        this.status = TaskStatus.valueOf(taskEntity.getStatus());
    }

    protected TaskEntity buildEntity() {
        TaskEntity entity = new TaskEntity();
        entity.setTaskId(this.id);
        entity.setTitle(this.title);
        entity.setDescription(this.description);
        entity.setProjectId(this.projectId);
        entity.setSprintId(this.sprintId);
        entity.setEpicId(this.epicId);
        entity.setStoryId(this.storyId);
        entity.setStartDate(this.startDate);
        entity.setDueDate(this.dueDate);
        entity.setPriority(this.priority.name());
        entity.setType(this.type.name());
        entity.setEstimate(this.estimate);
        entity.setLoggedWork(this.loggedWork);
        entity.setAttachment(this.attachment);
        entity.setAssignedTo(this.assignedTo);
        entity.setCreatedBy(this.createdBy);
        entity.setStatus(this.status.name());
        return entity;
    }

    public static AbstractTaskDto getDto(TaskEntity taskEntity) {
        TaskType type = TaskType.valueOf(taskEntity.getType());
        switch (type) {
            case STORY: return new StoryDto(taskEntity);
            case DOCUMENTATION: return new DocumentationDto(taskEntity);
            case RESEARCH: return new ResearchDto(taskEntity);
            case DESIGN: return new DesignDto(taskEntity);
            case ISSUE: return new IssueDto(taskEntity);
            case TASK:
            case SUBTASK:
            default: return new TaskDto(taskEntity);
        }
    }
}
