package com.tracker.users.ws.datasource.responses.roles;

import java.io.Serializable;

public class UserRoleResponse implements Serializable {
    public Integer id;
    public String title;
    public String description;
    public UserDepartmentResponse userDepartment;
    public RoleVeterancy veterancy;
    public UserAccessProfileResponse userAccessRights;

    public enum RoleVeterancy {
        Junior, Senior
    }
}
