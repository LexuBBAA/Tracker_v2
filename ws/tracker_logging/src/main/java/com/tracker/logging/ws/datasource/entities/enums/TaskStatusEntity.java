package com.tracker.logging.ws.datasource.entities.enums;

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

    @Column(name = "status", length = 25, unique = true)
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
