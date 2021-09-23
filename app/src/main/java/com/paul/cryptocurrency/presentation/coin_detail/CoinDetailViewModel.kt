package com.paul.cryptocurrency.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paul.cryptocurrency.common.Constants
import com.paul.cryptocurrency.common.Resource
import com.paul.cryptocurrency.domain.use_case.get_coins.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<CoinDetailState>(CoinDetailState())
    val state: State<CoinDetailState> = _state

   init {
       savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId -> {
           getCoin(coinId)
       } }
   }
    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId = coinId).onEach { result ->

            when (result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(
                        coin = result.data
                    )
                }
                is Resource.Error -> {

                    _state.value = CoinDetailState(
                            error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(
                        isLoading = true
                    )
                }
            }

        }.launchIn(viewModelScope)
    }


}