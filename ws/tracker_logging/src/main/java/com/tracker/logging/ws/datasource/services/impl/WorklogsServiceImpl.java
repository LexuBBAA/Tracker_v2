package com.tracker.logging.ws.datasource.services.impl;

import com.tracker.logging.ws.datasource.dtos.WorklogDto;
import com.tracker.logging.ws.datasource.entities.WorklogEntity;
import com.tracker.logging.ws.datasource.repositories.WorklogsRepository;
import com.tracker.logging.ws.datasource.services.WorklogsService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorklogsServiceImpl implements WorklogsService {
    @Autowired
    private WorklogsRepository repository;

    @Override
    public WorklogDto create(WorklogDto newWorklog) {
        newWorklog.worklogId = genereateWorklogId();
        WorklogEntity worklogEntity = new WorklogEntity(newWorklog);
        WorklogEntity storedWorklog = repository.save(worklogEntity);
        if(storedWorklog != null) {
            return new WorklogDto(storedWorklog);
        }
        return null;
    }

    @Override
    public WorklogDto update(WorklogDto worklog) {
        WorklogEntity storedWorklog = repository.findByWorklogId(worklog.worklogId);
        if(storedWorklog == null) {
            return null;
        }

        storedWorklog.setValue(worklog.value);
        storedWorklog.setComment(worklog.comment);
        storedWorklog.setLastModifiedBy(worklog.lastModifiedBy);
        storedWorklog.setLastModifiedAt(worklog.lastModifiedAt);

        WorklogEntity updatedWorklog = repository.save(storedWorklog);
        if(updatedWorklog != null) {
            return new WorklogDto(updatedWorklog);
        }
        return null;
    }

    @Override
    public List<WorklogDto> getAllRelatedTo(String relatesTo) {
        List<WorklogEntity> storedEntities = repository.findAllByRelatesTo(relatesTo);
        List<WorklogDto> dtos = new ArrayList<>();
        for(WorklogEntity entity: storedEntities) {
            dtos.add(new WorklogDto(entity));
        }
        return dtos;
    }

    @Override
    public boolean deleteById(String worklogId) {
        if(repository.existsByWorklogId(worklogId)) {
            repository.deleteByWorklogId(worklogId);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAllRelatedTo(String relatesTo) {
        repository.deleteAllByRelatesTo(relatesTo);
    }

    @NonNull
    private String genereateWorklogId() {
        String generatedId = RandomStringUtils.randomAlphanumeric(256);
        if(repository.existsByWorklogId(generatedId)) {
            return genereateWorklogId();
        }
        return generatedId;
    }
}
