package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.TokenEntity

interface ITokensProvider {
    suspend fun getUserToken(userId: String): TokenEntity?
    suspend fun create(tokenEntity: TokenEntity): TokenEntity?
    suspend fun update(tokenEntity: TokenEntity)
    suspend fun delete(tokenEntity: TokenEntity)
}