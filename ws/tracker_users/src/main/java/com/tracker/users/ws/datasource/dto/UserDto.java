package com.tracker.users.ws.datasource.dto;

import com.tracker.users.ws.datasource.entities.UserEntity;
import com.tracker.users.ws.datasource.entities.UserPreviewEntity;
import com.tracker.users.ws.datasource.responses.roles.UserAccessProfileResponse;
import com.tracker.users.ws.datasource.responses.roles.UserDepartmentResponse;
import com.tracker.users.ws.datasource.responses.roles.UserRoleResponse;
import com.tracker.users.ws.utils.RequestType;

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

    public void formatForRole(UserRoleResponse userRole, RequestType requestType) {
        if(requestType != RequestType.VIEW && userRole.userAccessRights.usersAccess == UserAccessProfileResponse.AccessType.NONE) {
            this.username = null;
            this.email = null;
            this.createdBy = null;
            this.reportingTo = null;
            this.efficiency = null;
            this.projectsCount = null;
            this.projectsCompleted = null;
            this.birthdate = null;
            this.salary = null;
            this.phone = null;
            this.freeDaysCount = null;
            this.jiraUserId = null;
            this.githubUserId = null;
            this.teamId = null;
            this.role = null;
            return;
        }
        if (userRole.userDepartment.type != UserDepartmentResponse.DepartmentType.HR) {
            this.salary = null;
            this.freeDaysCount = null;
        }
        if (userRole.userDepartment.type != UserDepartmentResponse.DepartmentType.Other &&
                userRole.userDepartment.type != UserDepartmentResponse.DepartmentType.Software) {
            this.jiraUserId = null;
            this.githubUserId = null;
        }
        if (userRole.userDepartment.type == UserDepartmentResponse.DepartmentType.Bussiness) {
            this.createdDate = null;
            this.createdBy = null;
        }
    }
}
