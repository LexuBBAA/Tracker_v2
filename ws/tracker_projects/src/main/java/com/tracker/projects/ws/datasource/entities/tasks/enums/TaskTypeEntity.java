package com.tracker.projects.ws.datasource.entities.tasks.enums;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tasktypevalues")
public class TaskTypeEntity implements Serializable {
    private static final long serialVersionUID = -6156441623962950670L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "type",
            nullable = false,
            unique = true,
            updatable = false,
            length = 25)
    private String type;

    public TaskTypeEntity() {

    }

    public TaskTypeEntity(String value) {
        this.type = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
