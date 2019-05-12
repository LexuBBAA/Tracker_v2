package com.tracker.projects.ws.datasource.services.impl.tasks;

import com.tracker.projects.ws.datasource.dtos.tasks.*;
import com.tracker.projects.ws.datasource.entities.tasks.TaskEntity;
import com.tracker.projects.ws.datasource.repositories.tasks.TasksRepository;
import com.tracker.projects.ws.datasource.services.tasks.TaskPriorityService;
import com.tracker.projects.ws.datasource.services.tasks.TaskService;
import com.tracker.projects.ws.datasource.services.tasks.TaskStatusService;
import com.tracker.projects.ws.datasource.services.tasks.TaskTypeService;
import io.micrometer.core.lang.NonNull;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TasksRepository repository;
    @Autowired
    private TaskStatusService taskStatusService;
    @Autowired
    private TaskTypeService taskTypeService;
    @Autowired
    private TaskPriorityService taskPriorityService;

    @Override
    public TaskDto createTask(TaskDto newTask) {
        newTask.taskId = generateTaskId();
        TaskEntity newEntity = new TaskEntity(newTask);
        TaskEntity storedEntity = repository.save(newEntity);
        if(storedEntity != null) {
            return setupData(storedEntity);
        }
        return null;
    }

    @Override
    public TaskDto updateTask(TaskDto task) {
        TaskEntity storedEntity = repository.findByTaskId(task.taskId);
        if(storedEntity == null) {
            return null;
        }
        storedEntity.update(task);
        TaskEntity updatedEntity = repository.save(storedEntity);
        if(updatedEntity != null) {
            return setupData(storedEntity);
        }
        return null;
    }

    @Override
    public TaskDto getTaskDetails(String taskId) {
        TaskEntity storedTask = repository.findByTaskId(taskId);
        if(storedTask != null) {
            return setupData(storedTask);
        }
        return null;
    }

    @Override
    public boolean deleteById(String taskId) {
        if(repository.existsByTaskId(taskId)) {
            repository.deleteByTaskId(taskId);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(TaskDto taskDto) {
        return deleteById(taskDto.taskId);
    }

    @NonNull
    private String generateTaskId() {
        String generatedId = RandomStringUtils.randomAlphanumeric(50);
        if(repository.existsByTaskId(generatedId)) {
            return generateTaskId();
        }
        return generatedId;
    }

    @NonNull
    private TaskDto setupData(@NonNull TaskEntity entity) {
        TaskDto taskDto = setupChildren(entity);
        if(entity.getType() != null) {
            TaskTypeDto type = taskTypeService.getByValue(entity.getType());
            if(type != null) {
                taskDto.type = type;
            }
        }

        if(entity.getStatus() != null) {
            TaskStatusDto status = taskStatusService.getByValue(entity.getStatus());
            if(status != null) {
                taskDto.status = status;
            }
        }

        if(entity.getPriority() != null) {
            TaskPriorityDto priority = taskPriorityService.findByValue(entity.getPriority());
            if(priority != null) {
                taskDto.priority = priority;
            }
        }

        return taskDto;
    }

    @NonNull
    private TaskDto setupChildren(@NonNull TaskEntity sourceEntity) {
        TaskDto taskDto = new TaskDto(sourceEntity);
        if(sourceEntity.getPartOf() != null) {
            TaskEntity entity = repository.findByTaskId(sourceEntity.getPartOf());
            if(entity != null) {
                TaskPreviewDto partOf = new TaskPreviewDto(entity);
                if (partOf != null) {
                    taskDto.partOf = partOf;
                }
            }
        }
        if(sourceEntity.getParent() != null) {
            TaskEntity entity = repository.findByTaskId(sourceEntity.getParent());
            if(entity != null) {
                TaskPreviewDto parent = new TaskPreviewDto(entity);
                if (parent != null) {
                    taskDto.parent = parent;
                }
            }
        }
        if(sourceEntity.getEpic() != null) {
            TaskEntity entity = repository.findByTaskId(sourceEntity.getEpic());
            if(entity != null) {
                TaskPreviewDto epic = new TaskPreviewDto(entity);
                if (epic != null) {
                    taskDto.epic = epic;
                }
            }
        }
        if(sourceEntity.getSubtaskOf() != null) {
            TaskEntity entity = repository.findByTaskId(sourceEntity.getSubtaskOf());
            if(entity != null) {
                TaskPreviewDto subtaskOf = new TaskPreviewDto(entity);
                if (subtaskOf != null) {
                    taskDto.subtaskOf = subtaskOf;
                }
            }
        }
        if(sourceEntity.getBlocks() != null) {
            TaskEntity entity = repository.findByTaskId(sourceEntity.getBlocks());
            if(entity != null) {
                TaskPreviewDto blocks = new TaskPreviewDto(entity);
                if (blocks != null) {
                    taskDto.blocks = blocks;
                }
            }
        }
        return taskDto;
    }
}
