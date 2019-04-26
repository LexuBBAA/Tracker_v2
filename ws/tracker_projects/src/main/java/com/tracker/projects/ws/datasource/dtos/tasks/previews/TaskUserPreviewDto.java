package com.tracker.projects.ws.datasource.dtos.tasks.previews;

import com.tracker.projects.ws.datasource.responses.UserPreviewResponse;

import java.io.Serializable;

public class TaskUserPreviewDto implements Serializable  {
    public Integer userId;
    public String username;
    public String userAvatar;

    public TaskUserPreviewDto(UserPreviewResponse userPreview) {
        this.userId = userPreview.id;
        this.username = userPreview.username;
        this.userAvatar = userPreview.userAvatar;
    }

    public TaskUserPreviewDto(Integer userId) {
        this.userId = userId;
    }
}
