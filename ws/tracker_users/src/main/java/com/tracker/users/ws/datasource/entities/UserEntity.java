package com.tracker.users.ws.datasource.entities;

import com.tracker.users.ws.datasource.dto.UserDto;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "Users")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "reporting_to")
    private Integer reportingTo;
    @Column(name = "efficiency")
    private Double efficiency;
    @Column(name = "projects_count")
    private Integer projectsCount;
    @Column(name = "projects_completed")
    private Integer projectsCompleted;
    @Column(name = "birthdate")
    private Date birthdate;
    @Column(name = "salary")
    private Double salary;
    @Column(name = "phone")
    private String phone;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "free_days_count")
    private Integer freeDaysCount;
    @Column(name = "jira_user_id")
    private String jiraUserId;
    @Column(name = "github_user_id")
    private String githubUserId;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "team_id")
    private Integer teamId;
    @Column(name = "role")
    private Integer roleId;
    @Column(name = "last_activity")
    private Date lastActivityTime;
    @Column(name = "password")
    private String password;

    public UserEntity() {
    }

    public UserEntity(UserDto userDto) {
        this.userId = userDto.userId;
        this.firstname = userDto.firstname;
        this.lastname = userDto.lastname;
        this.username = userDto.username;
        this.email = userDto.email;
        this.createdBy = userDto.createdBy;
        this.reportingTo = userDto.reportingTo;
        this.efficiency = userDto.efficiency;
        this.projectsCount = userDto.projectsCount;
        this.projectsCompleted = userDto.projectsCompleted;
        this.birthdate = userDto.birthdate;
        this.salary = userDto.salary;
        this.phone = userDto.phone;
        this.createdDate = userDto.createdDate;
        this.freeDaysCount = userDto.freeDaysCount;
        this.jiraUserId = userDto.jiraUserId;
        this.githubUserId = userDto.githubUserId;
        this.avatar = userDto.avatar;
        this.teamId = userDto.teamId;
        if(userDto.role != null && userDto.role.id != null) {
            this.roleId = userDto.role.id;
        } else {
            this.roleId = -1;
        }
        this.lastActivityTime = userDto.lastActivityTime;
        this.password = userDto.password;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public Integer getReportingTo() {
        return reportingTo;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public Integer getProjectsCount() {
        return projectsCount;
    }

    public Integer getProjectsCompleted() {
        return projectsCompleted;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public Double getSalary() {
        return salary;
    }

    public String getPhone() {
        return phone;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Integer getFreeDaysCount() {
        return freeDaysCount;
    }

    public String getJiraUserId() {
        return jiraUserId;
    }

    public String getGithubUserId() {
        return githubUserId;
    }

    public String getAvatar() {
        return avatar;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setReportingTo(Integer reportingTo) {
        this.reportingTo = reportingTo;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public void setProjectsCount(Integer projectsCount) {
        this.projectsCount = projectsCount;
    }

    public void setProjectsCompleted(Integer projectsCompleted) {
        this.projectsCompleted = projectsCompleted;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFreeDaysCount(Integer freeDaysCount) {
        this.freeDaysCount = freeDaysCount;
    }

    public void setJiraUserId(String jiraUserId) {
        this.jiraUserId = jiraUserId;
    }

    public void setGithubUserId(String githubUserId) {
        this.githubUserId = githubUserId;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Date getLastActivityTime() {
        return lastActivityTime;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastActivityTime(Date lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
    }
}
