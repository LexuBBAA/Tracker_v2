package com.tracker.projects.ws.datasource.entities;

import com.tracker.projects.ws.datasource.dtos.ProjectDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
@DynamicUpdate
public class ProjectEntity implements Serializable {
    private static final long serialVersionUID = 2949374574662312695L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "projectid",
            nullable = false,
            unique = true)
    private String projectId;
    @Column(name = "title",
            nullable = false,
            length = 50)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "createdby",
            nullable = false,
            updatable = false)
    private String createdBy;
    @Column(name = "createddate", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
    @Column(name = "activesprint")
    private String activeSprint;
    @Column(name = "assignedteam")
    private String assignedTeam;

    public ProjectEntity() {
    }

    public ProjectEntity(ProjectDto dto) {
        this.id = dto.id;
        this.projectId = dto.projectId;
        this.title = dto.title;
        this.description = dto.description;
        this.createdBy = dto.createdBy;
        this.activeSprint = dto.activeSprint.sprintId;
        this.assignedTeam = dto.assignedTeam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getActiveSprint() {
        return activeSprint;
    }

    public void setActiveSprint(String activeSprint) {
        this.activeSprint = activeSprint;
    }

    public String getAssignedTeam() {
        return assignedTeam;
    }

    public void setAssignedTeam(String assignedTeam) {
        this.assignedTeam = assignedTeam;
    }
}
