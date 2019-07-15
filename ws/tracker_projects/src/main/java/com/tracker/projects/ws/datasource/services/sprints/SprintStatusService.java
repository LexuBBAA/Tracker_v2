package com.tracker.projects.ws.datasource.services.sprints;

import com.tracker.projects.ws.datasource.dtos.sprints.SprintStatusDto;

import java.util.List;

public interface SprintStatusService {
    SprintStatusDto getByValue(String value);
    List<SprintStatusDto> getAll();

    boolean deleteByValue(String value);
}
