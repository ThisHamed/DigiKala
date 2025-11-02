package com.example.digikala.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun Loading(
    height: Dp, isDark: Boolean
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding()
            .height(height),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Loading3Dots(isDark = isDark)

    }

}