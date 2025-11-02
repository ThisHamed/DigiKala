package com.example.digikala.ui.screen.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.ui.theme.spacing

@Composable
fun CartInfoSection() {

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.grayCategory)
            .padding(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.small,
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier
                .weight(0.1f)
                .padding(horizontal = MaterialTheme.spacing.small),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                painter = painterResource(id = R.drawable.info),
                contentDescription = "",
                modifier = Modifier.size(26.dp),
                tint = Color.DarkGray
            )
        }

        Column(
            modifier = Modifier
                .weight(0.9f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.factor_info),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6,
                color = Color.DarkGray
            )
        }

    }

}