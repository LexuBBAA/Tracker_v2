package com.tracker.projects.ws.datasource.entities.tasks.enums;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "taskstatusvalues")
public class TaskStatusEntity implements Serializable {
    private static final long serialVersionUID = 9188979525225638887L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "status",
            nullable = false,
            unique = true,
            updatable = false,
            length = 25)
    private String status;

    public TaskStatusEntity() {
    }

    public TaskStatusEntity(String value) {
        this.status = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
