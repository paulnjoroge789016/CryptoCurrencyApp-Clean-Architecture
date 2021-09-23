package com.paul.cryptocurrency.domain.repository

import com.paul.cryptocurrency.data.remote.dto.CoinDetailDto
import com.paul.cryptocurrency.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}