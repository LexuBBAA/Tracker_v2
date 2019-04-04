package com.tracker.projects.ws.datasource.dtos;

import java.io.File;
import java.sql.Date;

public class ProjectDto extends ProjectPreviewDto {
    public Integer teamId;
    public String image;
    public Integer clientId;
    public Date startDate;
    public Date dueDate;
    public Integer currentSprintId;
    public Integer sprintDuration;
    public Integer sprintPlanningDay;
    public Integer sprintDemoDay;
    public Integer sprintReviewDay;
    public Double cost;
    public File attachment;
}
