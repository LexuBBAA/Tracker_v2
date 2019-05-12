package com.tracker.projects.ws.datasource.entities.sprints;

import com.tracker.projects.ws.datasource.dtos.sprints.SprintDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sprints")
@DynamicUpdate
public class SprintEntity implements Serializable {
    private static final long serialVersionUID = 2260663613179823047L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "sprintid",
            nullable = false,
            unique = true,
            length = 50)
    private String sprintId;
    @Column(name = "title",
            nullable = false,
            length = 50)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "startdate", nullable = false)
    private LocalDate startDate;
    @Column(name = "enddate", nullable = false)
    private LocalDate endDate;
    @Column(name = "createdby", nullable = false, updatable = false)
    private String createdBy;
    @Column(name = "createddate")
    @CreationTimestamp
    private LocalDateTime createdDate;
    @Column(name = "project")
    private String project;
    @Column(name = "status")
    private String status;

    public SprintEntity() {
    }

    public SprintEntity(SprintDto dto) {
        this.sprintId = dto.sprintId;
        this.title = dto.title;
        this.description = dto.description;
        this.startDate = dto.startDate;
        this.endDate = dto.endDate;
        this.createdBy = dto.createdBy;
        this.project = dto.project;
        this.status = dto.status.value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
