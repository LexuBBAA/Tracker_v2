package com.tracker.users.ws.datasource.responses.roles;

import java.io.Serializable;

public class UserAccessProfileResponse implements Serializable {
    public Integer profileId;
    public AccessType usersAccess;
    public AccessType rolesAccess;
    public AccessType profilesAccess;
    public AccessType departmentsAccess;
    public AccessType teamsAccess;
    public AccessType projectsAccess;
    public AccessType sprintsAccess;
    public AccessType workflowsAccess;
    public AccessType commentsAccess;
    public AccessType tasksAccess;
    public AccessType timeEntriesAccess;

    public enum AccessType {
        VIEW, EDIT, CREATE_AND_EDIT, DELETE, ALL, NONE
    }
}
