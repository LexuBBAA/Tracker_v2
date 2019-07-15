package com.tracker.trackerv2.datasource.paths

enum class DatasourcePath(val path: String) {

    //region Auth
    LOGIN("/auth/login"),
    REGISTER("/auth/register"),
    VALIDATE_TOKEN("/auth/validate"),
    RESET_PASS("/auth/reset"),
    //endregion

    //region Users
    GET_USERS("/users"),
    GET_USERS_CREATED_BY("/users/createdby"),
    GET_USER_DETAILS("/users/details"),
    CREATE_USER("/users/new"),
    UPDATE_USER("/users/update"),
    DELETE_USER("/users/delete"),
    //endregion

    //region Teams
    GET_TEAMS("/teams"),
    GET_TEAMS_CREATED_BY("/teams/createdby"),
    GET_TEAM_DETAILS("/teams/details"),
    CREATE_TEAM("/teams/new"),
    UPDATE_TEAM("/teams/update"),
    DELETE_TEAM("/teams/delete"),
    //endregion

    //region Projects
    GET_PROJECTS("/projects"),
    CREATE_PROJECT("/projects/new"),
    UPDATE_PROJECT("/projects/update"),
    DELETE_PROJECT("/projects/delete"),
    //endregion

    //region Sprints
    GET_SPRINTS_FOR_PROJECT("/sprints"),
    GET_SPRINTS_DETAILS("/sprints/details"),
    CREATE_SPRINT("/sprints/new"),
    UPDATE_SPRINT("/sprints/update"),
    DELETE_SPRINT("/sprints/delete"),
    //endregion

    //region Tasks
    GET_TASKS_FOR_PROJECT("/tasks"),
    GET_TASKS_FOR_EPIC("/tasks"),
    GET_TASKS_FOR_SPRINT("/tasks"),
    GET_TASKS_DETAILS("/tasks/details"),
    CREATE_TASKS("/tasks/new"),
    UPDATE_TASK("/tasks/update"),
    DELETE_TASK("/tasks/delete"),
    //endregion

    //region Worklogs
    GET_WORKLOGS_FOR_TASK("/worklogs"),
    CREATE_WORKLOG("/worklogs/new"),
    UPDATE_WORKLOG("/worklogs/update"),
    DELETE_WORKLOG("/worklogs/delete"),
    DELETE_WORKLOGS_RELATED_TO("/worklogs/deletefor"),
    //endregion

    //region Firebase
    SEND_PUSH_NOTIFICATION("/firebase/send")
    //endregion


}