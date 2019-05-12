package com.tracker.logging.ws.datasource.entities;

import com.tracker.logging.ws.datasource.dtos.WorklogDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "worklogs")
public class WorklogEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "worklogid",
            nullable = false,
            unique = true)
    private String worklogId;
    @Column(name = "value",
            nullable = false)
    private Double value;
    @Column(name = "comment")
    private String comment;
    @Column(name = "relatesto", nullable = false, updatable = false)
    private String relatesTo;
    @Column(name = "createdat", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "lastmodifiedat", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastModifiedAt;
    @Column(name = "createdby", nullable = false, updatable = false)
    private String createdBy;
    @Column(name = "lastmodifiedby", nullable = false)
    private String lastModifiedBy;

    public WorklogEntity() {
    }

    public WorklogEntity(WorklogDto dto) {
        this.id = dto.id;
        this.worklogId = dto.worklogId;
        this.value = dto.value;
        this.comment = dto.comment;
        this.relatesTo = dto.relatesTo;
        if(dto.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        } else {
            this.createdAt = dto.createdAt;
        }
        this.lastModifiedAt = LocalDateTime.now();
        this.createdBy = dto.createdBy;
        if(dto.lastModifiedBy != null) {
            this.lastModifiedBy = dto.lastModifiedBy;
        } else {
            this.lastModifiedBy = dto.createdBy;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorklogId() {
        return worklogId;
    }

    public void setWorklogId(String worklogId) {
        this.worklogId = worklogId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRelatesTo() {
        return relatesTo;
    }

    public void setRelatesTo(String relatesTo) {
        this.relatesTo = relatesTo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
