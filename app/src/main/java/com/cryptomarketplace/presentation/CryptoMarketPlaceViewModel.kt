package com.cryptomarketplace.presentation

import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptomarketplace.domain.coin.CoinType
import com.cryptomarketplace.domain.entity.TickerData
import com.cryptomarketplace.domain.usecase.GetDataUseCase
import com.cryptomarketplace.infrastructure.entity.TickerDto
import com.example.cryptomarketplace2.presentation.entity.TokenUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoMarketPlaceViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
) : ViewModel() {
    val state = mutableStateOf(ViewModelState())
    var coinList: List<TickerData> = emptyList()

    init {
        viewModelScope.launch {
            updateState(state.value.copy(
                isLoading = true,
            ))
            val coinTypeRequest = CoinType.values().joinToString(separator = ",") { it.usdToCoinShortcut }
            getDataUseCase(coinTypeRequest).collect { result ->
                coinList = result
                updateState(state.value.copy(
                    dataFromTickers = coinList.mapToUiModel(),
                    isLoading = false,
                ))
            }
        }
    }

    fun onCloseClicked() {
        if (state.value.searchText.isNotBlank()) {
            onTextSearchChange("")
        } else {
            updateState(state.value.copy(
                showSearchAppBar = false
            ))
        }
    }

    fun onSearchClicked() {
        updateState(state.value.copy(
            showSearchAppBar = true
        ))
    }

    fun onTextSearchChange(searchText: String) {
        val searchedResult = coinList.filter { it.coinType.coinNameShortcut.contains(searchText) }
        updateState(state.value.copy(
            searchText = searchText,
            dataFromTickers = searchedResult.mapToUiModel()
        ))
    }

    private fun updateState(state: ViewModelState) {
        this.state.value = state
    }

    private fun List<TickerData>.mapToUiModel() = map {
        TokenUiModel(
            symbol = it.coinType.coinNameShortcut,
            lastPrice = it.lastPrice,
            dailyChangeRelative = it.dailyChangeRelative,
            logoIcon = it.coinType.iconRes,
            isUp = it.dailyChangeRelative >= 0.0)
    }

    data class ViewModelState(
        val dataFromTickers: List<TokenUiModel> = emptyList(),
        val isLoading: Boolean = false,
        val searchText: String = "",
        val showSearchAppBar: Boolean = false,
    )
}

