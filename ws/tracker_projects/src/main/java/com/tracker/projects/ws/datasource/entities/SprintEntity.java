package com.tracker.projects.ws.datasource.entities;

import com.tracker.projects.ws.datasource.dtos.sprints.SprintDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

@Entity(name = "Sprints")
public class SprintEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "parent_project")
    private Integer parentProjectId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "total_story_points")
    private Integer totalStoryPoints;
    @Column(name = "sprint_status")
    private String sprintStatus;

    public SprintEntity() {
    }

    public SprintEntity(SprintDto dto) {
        this.id = dto.id;
        this.parentProjectId = dto.parentProjectId;
        this.title = dto.title;
        this.description = dto.description;
        this.startDate = dto.startDate;
        this.endDate = dto.endDate;
        this.totalStoryPoints = dto.totalStoryPoints;
        this.sprintStatus = dto.sprintStatus.name();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentProjectId() {
        return parentProjectId;
    }

    public void setParentProjectId(Integer parentProjectId) {
        this.parentProjectId = parentProjectId;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getTotalStoryPoints() {
        return totalStoryPoints;
    }

    public void setTotalStoryPoints(Integer totalStoryPoints) {
        this.totalStoryPoints = totalStoryPoints;
    }

    public String getSprintStatus() {
        return sprintStatus;
    }

    public void setSprintStatus(String sprintStatus) {
        this.sprintStatus = sprintStatus;
    }
}
