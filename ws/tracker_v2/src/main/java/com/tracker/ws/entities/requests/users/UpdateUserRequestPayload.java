package com.tracker.ws.entities.requests.users;

import java.io.Serializable;
import java.sql.Date;

public class UpdateUserRequestPayload implements Serializable {
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
    public Integer role;
    public Date lastActivityTime;
    public String password;
}
