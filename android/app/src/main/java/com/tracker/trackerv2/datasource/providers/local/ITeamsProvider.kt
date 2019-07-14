package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.TeamEntity

interface ITeamsProvider {
    suspend fun getAll(): List<TeamEntity>
    suspend fun getById(teamId: String): TeamEntity?
    suspend fun create(newTeam: TeamEntity): TeamEntity?
    suspend fun update(team: TeamEntity)
    suspend fun delete(team: TeamEntity)
}