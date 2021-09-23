package com.paul.cryptocurrency.domain.use_case.get_coin

import com.paul.cryptocurrency.common.Resource
import com.paul.cryptocurrency.data.remote.dto.toCoin
import com.paul.cryptocurrency.data.remote.dto.toCoinDetail
import com.paul.cryptocurrency.domain.model.Coin
import com.paul.cryptocurrency.domain.model.CoinDetail
import com.paul.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase  @Inject constructor(
    private val repository: CoinRepository
){

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin =  repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))

        } catch (e : HttpException)
        {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occurred") )
        } catch (e : IOException){
            emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection") )

        }
    }
}