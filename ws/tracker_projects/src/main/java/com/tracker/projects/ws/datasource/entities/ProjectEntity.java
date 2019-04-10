package com.tracker.projects.ws.datasource.entities;

import com.tracker.projects.ws.datasource.dtos.ProjectDto;
import com.tracker.projects.ws.datasource.dtos.ProjectStatus;
import com.tracker.projects.ws.datasource.dtos.SprintDto;
import com.tracker.projects.ws.datasource.dtos.TeamDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity(name = "Projects")
public class ProjectEntity {
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
    @Column(name = "client_id")
    private Integer clientId;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "due_date")
    private Date dueDate;
    @Column(name = "current_sprint")
    private Integer currentSprint;
    @Column(name = "sprint_duration")
    private Integer sprintDuration;
    @Column(name = "sprint_planning_day")
    private Integer sprintPlanningDay;
    @Column(name = "sprint_demo_day")
    private Integer sprintDemoDay;
    @Column(name = "sprint_review_day")
    private Integer sprintReviewDay;
    @Column(name = "cost_value")
    private Double costValue;
    @Column(name = "attachment")
    private String attachment;
    @Column(name = "project_status")
    private String projectStatus;

    public ProjectEntity() {
    }

    public ProjectEntity(ProjectDto dto) {
        this.id = dto.id;
        this.title = dto.title;
        this.description = dto.description;

        this.teamId = dto.team.id;

        this.image = dto.image;
        this.clientId = dto.clientId;
        this.startDate = dto.startDate;
        this.dueDate = dto.dueDate;

        this.currentSprint = dto.currentSprint.id;
        this.sprintDuration = dto.sprintDuration;
        this.sprintPlanningDay = dto.sprintPlanningDay;
        this.sprintDemoDay = dto.sprintDemoDay;
        this.sprintReviewDay = dto.sprintReviewDay;

        this.costValue = dto.costValue;
        this.attachment = dto.attachment;

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

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    public Integer getSprintDuration() {
        return sprintDuration;
    }

    public void setSprintDuration(Integer sprintDuration) {
        this.sprintDuration = sprintDuration;
    }

    public Integer getSprintPlanningDay() {
        return sprintPlanningDay;
    }

    public void setSprintPlanningDay(Integer sprintPlanningDay) {
        this.sprintPlanningDay = sprintPlanningDay;
    }

    public Integer getSprintDemoDay() {
        return sprintDemoDay;
    }

    public void setSprintDemoDay(Integer sprintDemoDay) {
        this.sprintDemoDay = sprintDemoDay;
    }

    public Integer getSprintReviewDay() {
        return sprintReviewDay;
    }

    public void setSprintReviewDay(Integer sprintReviewDay) {
        this.sprintReviewDay = sprintReviewDay;
    }

    public Double getCostValue() {
        return costValue;
    }

    public void setCostValue(Double costValue) {
        this.costValue = costValue;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }
}
