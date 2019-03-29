package com.tracker.users.ws.datasource.responses.roles;

import java.io.Serializable;

public class UserDepartmentResponse implements Serializable {
    public Integer id;
    public String title;
    public String description;
    public DepartmentType type;

    public enum DepartmentType {
        Software, HR, Bussiness, Other
    }
}
