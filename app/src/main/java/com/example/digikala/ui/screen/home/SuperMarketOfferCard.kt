package com.example.digikala.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.component.IconWithRotate
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants
import java.nio.file.WatchEvent.Modifier

@Composable
fun SuperMarketOfferCard(
    topImageResId: Int,
    bottomImageResId: Int
) {
    Column(
        modifier = androidx.compose.ui.Modifier
            .width(160.dp)
            .height(380.dp)
            .padding(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.extraSmall
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = androidx.compose.ui.Modifier.height(60.dp))

        Image(
            painter = amazingLogoChangeByLanguage(),
            contentDescription = "",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .height(95.dp)
        )


        Spacer(modifier = androidx.compose.ui.Modifier.height(15.dp))

        Image(
            painter = painterResource(id = bottomImageResId),
            contentDescription = "",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(40.dp))

        Row(
            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {

            Text(
                text = stringResource(id = com.example.digikala.R.string.see_all),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )

            IconWithRotate(imageVector = Icons.Filled.KeyboardArrowLeft, tint = Color.White)
        }

    }
}

@Composable
private fun amazingLogoChangeByLanguage(): Painter {
    return if (Constants.USER_LANGUAGE == Constants.ENGLISH_LANGUAGE) {
        painterResource(id = com.example.digikala.R.drawable.amazing_en)
    } else {
        painterResource(id = com.example.digikala.R.drawable.amazings)
    }
}