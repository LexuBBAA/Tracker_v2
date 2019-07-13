package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.local.room.dao.TokensDao
import com.tracker.trackerv2.datasource.providers.local.room.entity.TokenEntity
import java.sql.Date

class TokensProvider(private val dao: TokensDao): ITokensProvider {
    //TODO redundant; will be implemented into UserTokens table
    override fun getUserToken(userId: String): TokenEntity =
        TokenEntity(-1, "token", Date(System.currentTimeMillis() + 10 * 60 * 1000), "newToken")

    override fun create(tokenEntity: TokenEntity): TokenEntity? = dao.getToken(dao.insert(tokenEntity))

    override fun update(tokenEntity: TokenEntity) = dao.update(tokenEntity)

    override fun delete(tokenEntity: TokenEntity) = dao.delete(tokenEntity)
}