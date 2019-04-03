package com.tracker.users.ws.datasource.dto;

import com.tracker.users.ws.datasource.entities.UserEntity;
import com.tracker.users.ws.datasource.entities.UserPreviewEntity;
import com.tracker.users.ws.datasource.responses.roles.UserRoleResponse;

import java.io.Serializable;
import java.sql.Date;

public class UserDto implements Serializable {
    public Integer userId;
    public String firstname;
    public String lastname;
    public String username;
    public String email;
    public Integer createdBy;
    public Integer reportingTo;
    public Double efficiency;
    public Integer projectsCount;
    public Integer projectsCompleted;
    public Date birthdate;
    public Double salary;
    public String phone;
    public Date createdDate;
    public Integer freeDaysCount;
    public String jiraUserId;
    public String githubUserId;
    public String avatar;
    public Integer teamId;
    public UserRoleResponse role;
    public Date lastActivityTime;
    public String password;

    public UserDto(UserEntity entity) {
        this.userId = entity.getUserId();
        this.firstname = entity.getFirstname();
        this.lastname = entity.getLastname();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.createdBy = entity.getCreatedBy();
        this.reportingTo = entity.getReportingTo();
        this.efficiency = entity.getEfficiency();
        this.projectsCount = entity.getProjectsCount();
        this.projectsCompleted = entity.getProjectsCompleted();
        this.birthdate = entity.getBirthdate();
        this.salary = entity.getSalary();
        this.phone = entity.getPhone();
        this.createdDate = entity.getCreatedDate();
        this.freeDaysCount = entity.getFreeDaysCount();
        this.jiraUserId = entity.getJiraUserId();
        this.githubUserId = entity.getGithubUserId();
        this.avatar = entity.getAvatar();
        this.teamId = entity.getTeamId();
        this.role = new UserRoleResponse();
        this.role.id = entity.getRoleId();
        this.lastActivityTime = entity.getLastActivityTime();
    }

    public UserDto(UserPreviewEntity entity) {
        this.userId = entity.getUserId();
        this.firstname = entity.getFirstname();
        this.lastname = entity.getLastname();
        this.username = entity.getUsername();
        this.avatar = entity.getAvatar();
        this.role = new UserRoleResponse();
        this.role.id = entity.getRoleId();
        this.lastActivityTime = entity.getLastActivityTime();
    }

    public boolean validateRequestMandatoryFields() {
        return firstname != null && lastname != null &&
                username != null && email != null &&
                createdBy != null && reportingTo != null &&
                birthdate != null && role != null &&
                password != null;
    }
}
