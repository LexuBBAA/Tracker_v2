package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.room.entity.TokenEntity

interface ITokensProvider {
    fun getUserToken(userId: String): TokenEntity
    fun create(tokenEntity: TokenEntity)
    fun update(tokenEntity: TokenEntity)
    fun delete(tokenEntity: TokenEntity)
}