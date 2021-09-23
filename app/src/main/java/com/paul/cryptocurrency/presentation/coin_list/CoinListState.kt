package com.paul.cryptocurrency.presentation.coin_list

import com.paul.cryptocurrency.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: Coin? = null,
    val error: String = ""

)