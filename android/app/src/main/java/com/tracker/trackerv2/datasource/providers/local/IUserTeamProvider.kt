package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.utils.UserTeamEntity

interface IUserTeamProvider {
    fun getForUser(userId: String): UserTeamEntity?
    fun getForTeam(teamId: String): List<UserTeamEntity>
    fun create(newUserTeam: UserTeamEntity): UserTeamEntity?
    fun delete(userTeam: UserTeamEntity)
}