package com.tracker.projects.ws.datasource.entities.tasks;

import com.tracker.projects.ws.datasource.dtos.tasks.TaskDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@DynamicUpdate
public class TaskEntity implements Serializable {
    private static final long serialVersionUID = 1455736444141130907L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "taskid", nullable = false, updatable = false, unique = true)
    private String taskId;
    @Column(name = "title", nullable = false, length = 150)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "createddate", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "lastmodifiedat")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(name = "createdby", nullable = false)
    private String createdBy;
    @Column(name = "assignedto")
    private String assignedTo;
    @Column(name = "lastmodifiedby", nullable = false)
    private String lastModifiedBy;
    @Column(name = "duedate")
    private LocalDate dueDate;
    @Column(name = "storypoints")
    private Integer storyPoints = 0;
    @Column(name = "priority", nullable = false)
    private String priority;
    @Column(name = "estimate")
    private Double estimate = 0d;
    @Column(name = "logged")
    private Double logged = 0d;
    @Column(name = "isepic")
    private Boolean isEpic;

    @Column(name = "project", nullable = false)
    private String project;
    @Column(name = "sprintid", nullable = false)
    private String sprint;
    @Column(name = "parent")
    private String parent;
    @Column(name = "partof")
    private String partOf;
    @Column(name = "subtaskof")
    private String subtaskOf;
    @Column(name = "epic")
    private String epic;
    @Column(name = "blocks")
    private String blocks;

    public TaskEntity() {
    }

    public TaskEntity(@NonNull TaskDto task) {
        this.id = task.id;
        this.taskId = task.taskId;
        if(task.title != null && !task.title.equals(this.title)) {
            this.title = task.title;
        }
        if(task.description != null && !task.description.equals(this.description)) {
            this.description = task.description;
        }
        if(task.type != null && !task.type.value.equals(this.type)) {
            this.type = task.type.value;
        }
        if(task.status != null && !task.status.value.equals(this.status)) {
            this.status = task.status.value;
        }
        if(task.createdBy != null && !task.createdBy.equals(this.createdBy)) {
            this.createdBy = task.createdBy;
        }
        if(task.assignedTo != null && !task.assignedTo.equals(this.assignedTo)) {
            this.assignedTo = task.assignedTo;
        }
        if(task.lastModifiedBy != null && !task.lastModifiedBy.equals(this.lastModifiedBy)) {
            this.lastModifiedBy = task.lastModifiedBy;
        }
        if(task.dueDate != null && !task.dueDate.equals(this.dueDate)) {
            this.dueDate = task.dueDate;
        }
        if(task.storyPoints != null && (int) task.storyPoints != this.storyPoints) {
            this.storyPoints = task.storyPoints;
        }
        if(task.priority != null && !task.priority.value.equals(this.priority)) {
            this.priority = task.priority.value;
        }
        if(task.estimate != null && (double) task.estimate != this.estimate) {
            this.estimate = task.estimate;
        }
        if(task.logged != null && (double) task.logged != this.logged) {
            this.logged = task.logged;
        }
        this.isEpic = task.isEpic && task.type != null && task.type.value.equals("Story");
        if(task.project != null && !task.project.equals(this.project)) {
            this.project = task.project;
        }
        this.sprint = task.sprint;
        if(task.parent != null) {
            this.parent = task.parent.taskId;
        } else {
            this.parent = null;
        }
        if(task.partOf != null) {
            this.partOf = task.partOf.taskId;
        } else {
            this.partOf = null;
        }
        if(task.subtaskOf != null) {
            this.subtaskOf = task.subtaskOf.taskId;
        } else {
            this.subtaskOf = null;
        }
        if(task.epic != null) {
            this.epic = task.epic.taskId;
        } else {
            this.epic = null;
        }
        if(task.blocks != null) {
            this.blocks = task.blocks.taskId;
        } else {
            this.blocks = null;
        }
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(@NonNull TaskDto task) {
        this.taskId = task.taskId;
        if(task.title != null && !task.title.equals(this.title)) {
            this.title = task.title;
        }
        if(task.description != null && !task.description.equals(this.description)) {
            this.description = task.description;
        }
        if(task.type != null && !task.type.value.equals(this.type)) {
            this.type = task.type.value;
        }
        if(task.status != null && !task.status.value.equals(this.status)) {
            this.status = task.status.value;
        }
        if(task.createdBy != null && !task.createdBy.equals(this.createdBy)) {
            this.createdBy = task.createdBy;
        }
        if(task.assignedTo != null && !task.assignedTo.equals(this.assignedTo)) {
            this.assignedTo = task.assignedTo;
        }
        if(task.lastModifiedBy != null && !task.lastModifiedBy.equals(this.lastModifiedBy)) {
            this.lastModifiedBy = task.lastModifiedBy;
        }
        this.updatedAt = LocalDateTime.now();
        if(task.dueDate != null && !task.dueDate.equals(this.dueDate)) {
            this.dueDate = task.dueDate;
        }
        if(task.storyPoints != null && (int) task.storyPoints != this.storyPoints) {
            this.storyPoints = task.storyPoints;
        }
        if(task.priority != null && !task.priority.value.equals(this.priority)) {
            this.priority = task.priority.value;
        }
        if(task.estimate != null && (double) task.estimate != this.estimate) {
            this.estimate = task.estimate;
        }
        if(task.logged != null && (double) task.logged != this.logged) {
            this.logged = task.logged;
        }
        this.isEpic = task.isEpic && task.type != null && task.type.value.equals("STORY");
        if(task.project != null && !task.project.equals(this.project)) {
            this.project = task.project;
        }
        this.sprint = task.sprint;
        if(task.parent != null) {
            this.parent = task.parent.taskId;
        } else {
            this.parent = null;
        }
        if(task.partOf != null) {
            this.partOf = task.partOf.taskId;
        } else {
            this.partOf = null;
        }
        if(task.subtaskOf != null) {
            this.subtaskOf = task.subtaskOf.taskId;
        } else {
            this.subtaskOf = null;
        }
        if(task.epic != null) {
            this.epic = task.epic.taskId;
        } else {
            this.epic = null;
        }
        if(task.blocks != null) {
            this.blocks = task.blocks.taskId;
        } else {
            this.blocks = null;
        }
    }

    public Double getLogged() {
        return logged;
    }

    public void setLogged(Double logged) {
        this.logged = logged;
    }

    public boolean isEpic() {
        return this.isEpic;
    }

    public void setEpic(Boolean epic) {
        isEpic = epic;
    }

    public String getSprint() {
        return sprint;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(Integer storyPoints) {
        this.storyPoints = storyPoints;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Double getEstimate() {
        return estimate;
    }

    public void setEstimate(Double estimate) {
        this.estimate = estimate;
    }

    public String getPartOf() {
        return partOf;
    }

    public void setPartOf(String partOf) {
        this.partOf = partOf;
    }

    public String getSubtaskOf() {
        return subtaskOf;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public void setSubtaskOf(String subtaskOf) {
        this.subtaskOf = subtaskOf;
    }

    public String getEpic() {
        return epic;
    }

    public void setEpic(String epic) {
        this.epic = epic;
    }

    public String getBlocks() {
        return blocks;
    }

    public void setBlocks(String blocks) {
        this.blocks = blocks;
    }

}
