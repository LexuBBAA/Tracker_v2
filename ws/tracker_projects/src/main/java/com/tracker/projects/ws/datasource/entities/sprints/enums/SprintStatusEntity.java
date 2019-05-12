package com.tracker.projects.ws.datasource.entities.sprints.enums;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sprintstatusvalues")
public class SprintStatusEntity implements Serializable {
    private static final long serialVersionUID = 2569858904932221651L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "status",
            nullable = false,
            unique = true,
            updatable = false,
            length = 50)
    private String status;

    public SprintStatusEntity() {
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
