package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.dao.TeamsDao
import com.tracker.trackerv2.datasource.providers.local.room.entity.TeamEntity

class TeamsProvider(private val dao: TeamsDao): ITeamsProvider {
    override fun getAll(): List<TeamEntity> = dao.getAll()

    override fun getById(teamId: String): TeamEntity? = dao.getById(teamId)

    override fun create(newTeam: TeamEntity): TeamEntity? = dao.getById(dao.insert(newTeam))

    override fun update(team: TeamEntity) = dao.update(team)

    override fun delete(team: TeamEntity) = dao.delete(team)
}