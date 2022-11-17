package com.example.cryptomarketplace2.presentation

import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptomarketplace.presentation.Utils
import com.example.cryptomarketplace2.domain.usecase.GetDataUseCase
import com.example.cryptomarketplace2.infrastructure.entity.TickerDto
import com.example.cryptomarketplace2.presentation.entity.TokenUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoMarketPlaceViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
) : ViewModel() {
    val state = mutableStateOf(ViewModelState())
    var coinList: List<TickerDto> = emptyList()

    init {
        viewModelScope.launch {
            updateState(state.value.copy(
                isLoading = true,
            ))
            getDataUseCase(Utils.REQUEST).collect { result ->
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
        val searchedResult = coinList.filter { it.symbol.contains(searchText) }
        updateState(state.value.copy(
            searchText = searchText,
            dataFromTickers = searchedResult.mapToUiModel()
        ))
    }

    private fun updateState(state: ViewModelState) {
        this.state.value = state
    }

    private fun List<TickerDto>.mapToUiModel() = map {
        TokenUiModel(
            symbol = it.symbol,
            lastPrice = it.lastPrice,
            dailyChangeRelative = it.dailyChangeRelative,
            isUp = it.dailyChangeRelative >= 0.0)
    }

    data class ViewModelState(
        val dataFromTickers: List<TokenUiModel> = emptyList(),
        val isLoading: Boolean = false,
        val searchText: String = "",
        val showSearchAppBar: Boolean = false,
    )
}

