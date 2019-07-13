package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.entity.TokenEntity

interface ITokensProvider {
    fun getUserToken(userId: String): TokenEntity?
    fun create(tokenEntity: TokenEntity): TokenEntity?
    fun update(tokenEntity: TokenEntity)
    fun delete(tokenEntity: TokenEntity)
}