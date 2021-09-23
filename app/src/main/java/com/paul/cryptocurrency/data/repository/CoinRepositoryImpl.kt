package com.paul.cryptocurrency.data.repository

import com.paul.cryptocurrency.data.remote.CoinPaprikaApi
import com.paul.cryptocurrency.data.remote.dto.CoinDetailDto
import com.paul.cryptocurrency.data.remote.dto.CoinDto
import com.paul.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
): CoinRepository{

    override suspend fun getCoins(): List<CoinDto> {
        return   api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}