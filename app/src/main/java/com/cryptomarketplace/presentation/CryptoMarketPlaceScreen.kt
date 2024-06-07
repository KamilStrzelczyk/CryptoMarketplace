package com.cryptomarketplace.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cryptomarketplace.presentation.component.CoinDetailBox
import com.cryptomarketplace.presentation.component.SearchTopAppBar

@Composable
fun CryptoMarketPlaceScreen(
    viewModel: CryptoMarketPlaceViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = { Text(text = "CryptoApp") },
                actions = {
                    IconButton(onClick = { viewModel.onSearchClicked() }
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.dataFromTickers) {
                    CoinDetailBox(
                        nameCoin = it.symbol,
                        lastPrice = "$ ${it.lastPrice}",
                        dailyChangeRelative = it.dailyChangeRelative,
                        logoIcon = it.logoIcon,
                        isUp = it.isUp,
                    )
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
    SearchTopAppBar(
        showSearchAppBar = state.showSearchAppBar,
        searchText = state.searchText,
        onCloseClicked = { viewModel.onCloseClicked() },
        onTextSearchChange = { viewModel.onTextSearchChange(it) }
    )
}
