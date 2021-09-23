package com.paul.cryptocurrency.presentation.coin_detail

import com.paul.cryptocurrency.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""

)