package com.tracker.logging.ws.datasource.services;

import com.tracker.logging.ws.datasource.dtos.WorklogDto;

import java.util.List;

public interface WorklogsService {
    WorklogDto create(WorklogDto newWorklog);
    WorklogDto update(WorklogDto worklog);
    List<WorklogDto> getAllRelatedTo(String relatesTo);
    boolean deleteById(String worklogId);
    void deleteAllRelatedTo(String relatesTo);
}
