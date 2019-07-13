package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.dao.UserTeamDao
import com.tracker.trackerv2.datasource.providers.local.room.entity.utils.UserTeamEntity

class UserTeamProvider(private val dao: UserTeamDao):
    IUserTeamProvider {
    override fun getForTeam(teamId: String): List<UserTeamEntity> = dao.getForTeam(teamId)

    override fun getForUser(userId: String): UserTeamEntity? = dao.getForUser(userId)

    override fun create(newUserTeam: UserTeamEntity): UserTeamEntity? = dao.getById(dao.insert(newUserTeam))

    override fun delete(userTeam: UserTeamEntity) = dao.delete(userTeam)
}