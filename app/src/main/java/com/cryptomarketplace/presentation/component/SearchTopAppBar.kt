package com.cryptomarketplace.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun SearchTopAppBar(
    showSearchAppBar: Boolean,
    searchText: String,
    onTextSearchChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
) {
    if (showSearchAppBar) {
        Box(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
        )
        {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                    )
                },
                actions = {
                    Surface(
                        modifier = Modifier,
                        color = Color.Transparent,
                        elevation = 0.dp,
                    ) {
                        TextField(
                            singleLine = true,
                            maxLines = 1,
                            value = searchText,
                            onValueChange = { onTextSearchChange(it) },
                            placeholder = {
                                Text(
                                    modifier = Modifier.alpha(ContentAlpha.medium),
                                    text = "Search",
                                    color = Color.White,
                                )
                            },
                            textStyle = TextStyle(
                                color = Color.White,
                                fontSize = 16.sp,
                            ),
                            trailingIcon = {
                                IconButton(onClick = onCloseClicked) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = null,
                                        tint = Color.White,
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                backgroundColor = Color.Transparent,
                            )
                        )
                    }
                }
            )
        }
    }
}
