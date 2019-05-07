package com.tracker.logging.ws.datasource.entities.enums;

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

    @Column(name = "type", length = 25, unique = true)
    private String type;

    public TaskTypeEntity() {

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
