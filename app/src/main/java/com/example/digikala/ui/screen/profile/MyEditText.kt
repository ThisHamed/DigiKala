package com.example.digikala.ui.screen.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.theme.CursorColor
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.spacing

@Composable
fun MyEditText(
    value: String,
    placeHolder: String,
    onValueChanged: (item: String) -> Unit
) {

    TextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .padding(
                top = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.semiLarge,
                start = MaterialTheme.spacing.semiLarge,
                end = MaterialTheme.spacing.semiLarge
            ),
        shape = MaterialTheme.roundedShape.small,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.searchBarBg,
            focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
            unfocusedIndicatorColor = MaterialTheme.colors.searchBarBg,
            cursorColor = MaterialTheme.colors.CursorColor
        ),
        placeholder = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = placeHolder,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
        }
    )

}