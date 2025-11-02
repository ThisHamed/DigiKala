package com.example.digikala.ui.screen.checkout

import android.provider.MediaStore.Audio.Radio
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.Oranges
import com.example.digikala.ui.theme.spacing

@Composable
fun DeliveryTimeBottomSheet() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(selected = true, onClick = { /*TODO*/ })
                Text(
                    text = stringResource(id = R.string.pishtaz_post),
                    style = MaterialTheme.typography.h6
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.digi_plus_icon),
                    contentDescription = "",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = stringResource(id = R.string.delivery_delay),
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.Oranges,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                )

            }
        }

    }
}