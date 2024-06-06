package com.cryptomarketplace.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun CoinDetailBox(
    nameCoin: String,
    lastPrice: String,
    dailyChangeRelative: String,
    logoIcon: Int,
    isUp: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Card(elevation = 10.dp) {
            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .height(56.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = logoIcon),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
                Text(
                    text = nameCoin,
                    fontWeight = FontWeight.Bold,
                )
                Column(horizontalAlignment = Alignment.End) {
                    val dailyChangeRelativeColor = if (isUp) Color.Green else Color.Red
                    Text(
                        text = lastPrice,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Text(
                        text = "$dailyChangeRelative %",
                        fontWeight = FontWeight.Bold,
                        color = dailyChangeRelativeColor,
                    )
                }
            }
        }
    }
}
