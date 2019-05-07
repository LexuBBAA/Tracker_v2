package com.tracker.projects.ws.datasource.entities;

import com.tracker.projects.ws.datasource.dtos.TeamDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "teams")
@DynamicUpdate
public class TeamEntity implements Serializable {
    private static final long serialVersionUID = -4689602615526544124L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "teamid", nullable = false)
    private String teamId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "createdby", nullable = false)
    private String createdBy;

    @Column(name = "createddate")
    @CreationTimestamp
    private LocalDateTime createdDate;

    public TeamEntity() {
    }

    public TeamEntity(TeamDto dto) {
        this.id = dto.id;
        this.teamId = dto.teamId;
        this.name = dto.name;
        this.description = dto.description;
        if(dto.createdBy != null) {
            this.createdBy = dto.createdBy;
        }
        if(dto.createdDate != null) {
            this.createdDate = dto.createdDate;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
