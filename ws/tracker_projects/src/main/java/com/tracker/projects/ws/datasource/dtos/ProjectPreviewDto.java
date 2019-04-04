package com.tracker.projects.ws.datasource.dtos;

import java.io.Serializable;

public class ProjectPreviewDto implements Serializable {
    public Integer id;
    public String title;
    public String description;
    public ProjectStatus status;
}
