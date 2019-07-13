package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.TeamEntity

interface ITeamsProvider {
    fun getAll(): List<TeamEntity>
    fun getById(teamId: String): TeamEntity?
    fun create(newTeam: TeamEntity): TeamEntity?
    fun update(team: TeamEntity)
    fun delete(team: TeamEntity)
}