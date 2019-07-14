package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.dao.UserTeamDao
import com.tracker.trackerv2.datasource.providers.local.room.entity.utils.UserTeamEntity

class UserTeamProvider(private val dao: UserTeamDao):
    IUserTeamProvider {
    override suspend fun getForTeam(teamId: String): List<UserTeamEntity> = dao.getForTeam(teamId)

    override suspend fun getForUser(userId: String): UserTeamEntity? = dao.getForUser(userId)

    override suspend fun create(newUserTeam: UserTeamEntity): UserTeamEntity? = dao.getById(dao.insert(newUserTeam))

    override suspend fun delete(userTeam: UserTeamEntity) = dao.delete(userTeam)
}