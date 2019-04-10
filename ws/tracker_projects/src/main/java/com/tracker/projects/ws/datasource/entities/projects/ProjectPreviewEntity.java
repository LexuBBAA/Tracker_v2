package com.tracker.projects.ws.datasource.entities.projects;

import com.tracker.projects.ws.datasource.dtos.projects.ProjectPreviewDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

@Entity(name = "Projects")
public class ProjectPreviewEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "team_id")
    private Integer teamId;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "due_date")
    private Date dueDate;
    @Column(name = "current_sprint")
    private Integer currentSprint;
    @Column(name = "project_status")
    private String projectStatus;

    public ProjectPreviewEntity() {
    }

    public ProjectPreviewEntity(ProjectPreviewDto dto) {
        this.id = dto.id;
        this.title = dto.title;
        this.teamId = dto.team.id;
        this.description = dto.description;
        this.image = dto.image;
        this.dueDate = dto.dueDate;
        this.currentSprint = dto.currentSprint.id;
        this.projectStatus = dto.status.name();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getCurrentSprint() {
        return currentSprint;
    }

    public void setCurrentSprint(Integer currentSprint) {
        this.currentSprint = currentSprint;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }
}
