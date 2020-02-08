package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.utils.UserTeamEntity

interface IUserTeamProvider {
    suspend fun getForUser(userId: String): UserTeamEntity?
    suspend fun getForTeam(teamId: String): List<UserTeamEntity>
    suspend fun create(newUserTeam: UserTeamEntity): UserTeamEntity?
    suspend fun delete(userTeam: UserTeamEntity)
}