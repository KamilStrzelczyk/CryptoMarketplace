package com.cryptomarketplace.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptomarketplace.domain.model.CoinType
import com.cryptomarketplace.domain.entity.TickerData
import com.cryptomarketplace.domain.usecase.GetDataUseCase
import com.cryptomarketplace.presentation.entity.EMPTY_STRING
import com.cryptomarketplace.presentation.entity.TokenUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoMarketPlaceViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ViewModelState())
    val state: StateFlow<ViewModelState> = _state

    private var coinList: List<TickerData> = emptyList()

    init {
        viewModelScope.launch {
            updateState(
                _state.value.copy(
                    isLoading = true,
                )
            )
            val coinTypeRequest = CoinType.entries.joinToString(separator = ",") { it.usdToCoinShortcut }
            getDataUseCase(coinTypeRequest).collect { result ->
                coinList = result
                updateState(
                    _state.value.copy(
                        dataFromTickers = coinList.mapToUiModel(),
                        isLoading = false,
                    )
                )
            }
        }
    }

    fun onCloseClicked() {
        if (_state.value.searchText.isNotBlank()) {
            onTextSearchChange(EMPTY_STRING)
        } else {
            updateState(
                _state.value.copy(
                    showSearchAppBar = false
                )
            )
        }
    }

    fun onSearchClicked() {
        updateState(
            _state.value.copy(
                showSearchAppBar = true
            )
        )
    }

    fun onTextSearchChange(searchText: String) {
        val searchedResult = coinList.filter { it.coinType.coinNameShortcut.contains(searchText) }
        updateState(
            _state.value.copy(
                searchText = searchText,
                dataFromTickers = searchedResult.mapToUiModel()
            )
        )
    }

    private fun updateState(state: ViewModelState) {
        _state.value = state
    }

    private fun List<TickerData>.mapToUiModel() = map {
        TokenUiModel(
            symbol = it.coinType.coinNameShortcut,
            lastPrice = it.lastPrice.toString(),
            dailyChangeRelative = String.format("%.2f", (it.dailyChangeRelative * 10)),
            logoIcon = it.coinType.iconRes,
            isUp = it.dailyChangeRelative >= 0.0
        )
    }

    data class ViewModelState(
        val dataFromTickers: List<TokenUiModel> = emptyList(),
        val isLoading: Boolean = false,
        val searchText: String = EMPTY_STRING,
        val showSearchAppBar: Boolean = false,
    )
}

