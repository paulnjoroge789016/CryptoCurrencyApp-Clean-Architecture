package com.paul.cryptocurrency.domain.use_case.get_coins

import com.paul.cryptocurrency.common.Resource
import com.paul.cryptocurrency.data.remote.dto.toCoin
import com.paul.cryptocurrency.domain.model.Coin
import com.paul.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinsUseCase  @Inject constructor(
    private val repository: CoinRepository
    ){

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins =  repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))

        } catch (e : HttpException)
        {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occurred") )
        } catch (e : IOException){
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection") )

        }
    }
}