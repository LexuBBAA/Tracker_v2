package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.dao.TeamsDao
import com.tracker.trackerv2.datasource.providers.local.room.entity.TeamEntity

class TeamsProvider(private val dao: TeamsDao): ITeamsProvider {
    override suspend fun getAll(): List<TeamEntity> = dao.getAll()

    override suspend fun getById(teamId: String): TeamEntity? = dao.getById(teamId)

    override suspend fun create(newTeam: TeamEntity): TeamEntity? = dao.getById(dao.insert(newTeam))

    override suspend fun update(team: TeamEntity) = dao.update(team)

    override suspend fun delete(team: TeamEntity) = dao.delete(team)
}