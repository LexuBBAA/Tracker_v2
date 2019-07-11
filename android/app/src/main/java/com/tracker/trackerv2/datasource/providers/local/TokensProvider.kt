package com.tracker.trackerv2.datasource.providers.local

import com.tracker.trackerv2.datasource.providers.room.dao.TokensDao
import com.tracker.trackerv2.datasource.providers.room.entity.TokenEntity
import java.sql.Date

class TokensProvider(private val dao: TokensDao): ITokensProvider {
    override fun getUserToken(userId: String): TokenEntity =
        TokenEntity(-1, "tokeb", Date(System.currentTimeMillis() + 10 * 60 * 1000), "newToken")

    override fun create(tokenEntity: TokenEntity) = dao.insert(tokenEntity)

    override fun update(tokenEntity: TokenEntity) = dao.update(tokenEntity)

    override fun delete(tokenEntity: TokenEntity) = dao.delete(tokenEntity)
}