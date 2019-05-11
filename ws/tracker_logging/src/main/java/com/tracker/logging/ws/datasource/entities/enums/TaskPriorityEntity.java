package com.tracker.logging.ws.datasource.entities.enums;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

public class TaskPriorityEntity implements Serializable {
    private static final long serialVersionUID = -7108623019621145951L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "priority",
            nullable = false,
            unique = true,
            updatable = false)
    private String priority;

    public TaskPriorityEntity() {
    }

    public TaskPriorityEntity(String value) {
        this.priority = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
