package com.cryptomarketplace.presentation


import androidx.compose.foundation.layout.*
import com.example.cryptomarketplace.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptomarketplace.infrastructure.entity.TickerDto

@Composable

fun CryptoMarketPlaceScreen(
    viewModel: CryptoMarketPlaceViewModel,
) {
    val state = viewModel.state.value
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(
                        text = "CryptoApp"
                    )
                },
                actions = {
                    IconButton(
                        onClick = { viewModel.onSearchClicked() }
                    ) {
                        Icon(Icons.Default.Search, contentDescription = null)
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
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(state.dataFromTickers) {
                    CoinDetailBox(
                        nameCoin = it.symbol,
                        lastPrice = "$ ${it.lastPrice}",
                        dailyChangeRelative = it.dailyChangeRelative.toString(),
                        logoIcon = it.logoIcon,
                        isUp = it.isUp,
                    )
                }
            }
            if (viewModel.state.value.isLoading) {

                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
    SearchTopAppBar(viewModel = viewModel)
}

@Composable

fun CoinDetailBox(
    nameCoin: String,
    lastPrice: String,
    dailyChangeRelative: String,
    logoIcon: Int,
    isUp: Boolean,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),

        ) {

        Card(elevation = 10.dp) {

            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .height(56.dp)
                    .fillMaxWidth()
                    ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                Icon(modifier = Modifier
                    .size(40.dp),
                    painter = painterResource(id = logoIcon),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = nameCoin,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(

                ) {
                    Text(
                        text = lastPrice,
                        fontWeight = FontWeight.ExtraBold)
                    Text(
                        text = "$dailyChangeRelative %",
                        fontWeight = FontWeight.Bold,
                        color = if (isUp) {
                            Color.Green
                        } else {
                            Color.Red
                        })
                }
            }
        }
    }
}

@Composable
fun SearchTopAppBar(
    viewModel: CryptoMarketPlaceViewModel,
) {
    if (viewModel.state.value.showSearchAppBar) {
        val state = viewModel.state.value
        Box(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth())
        {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null
                    )
                },
                actions = {
                    Surface(
                        modifier = Modifier,
                        color = Color.Transparent,
                        elevation = 0.dp
                    ) {
                        TextField(
                            singleLine = true,
                            maxLines = 1,
                            value = state.searchText,
                            onValueChange = {
                                viewModel.onTextSearchChange(it)
                            },
                            placeholder = {
                                Text(
                                    modifier = Modifier
                                        .alpha(ContentAlpha.medium),
                                    text = "Search",
                                    color = Color.White
                                )
                            },
                            textStyle = TextStyle(
                                color = Color.White,
                                fontSize = 16.sp
                            ),
                            trailingIcon = {
                                IconButton(onClick = {
                                    viewModel.onCloseClicked()
                                }) {
                                    Icon(imageVector = Icons.Filled.Close,
                                        contentDescription = null,
                                        tint = Color.White)
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Search
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                backgroundColor = Color.Transparent
                            )
                        )
                    }
                }
            )
        }
    }
}
