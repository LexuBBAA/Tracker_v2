package com.tracker.users.ws.datasource.responses.roles;

import java.io.Serializable;

public class UserRoleResponse implements Serializable {
    public Integer id;
    public String title;
    public String description;
    public RoleVeterancy veterancy;

    public enum RoleVeterancy {
        Junior, Senior
    }
}
