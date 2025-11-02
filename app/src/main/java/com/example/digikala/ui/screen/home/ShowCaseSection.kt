package com.example.digikala.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.component.RoundedIconBox
import com.example.digikala.ui.theme.LocalSpacing
import com.example.digikala.ui.theme.amber
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.utils.Constants.AUCTION_URL
import com.example.digikala.utils.Constants.DIGIJET_URL
import com.example.digikala.utils.Constants.DIGIPAY_URL
import com.example.digikala.utils.Constants.DIGIPLUS_URL
import com.example.digikala.utils.Constants.GIFT_CARD_URL
import com.example.digikala.utils.Constants.MORE_URL
import com.example.digikala.utils.Constants.PINDO_URL
import com.example.digikala.utils.Constants.SHOPPING_URL
import java.net.URL

@Composable
fun ShowCaseSection(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = LocalSpacing.current.semiMedium,
                vertical = LocalSpacing.current.biggerSmall
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = LocalSpacing.current.semiSmall
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RoundedIconBox(
                title = stringResource(id = R.string.digikala_jet),
                image = painterResource(id = R.drawable.digijet),
                onClick = onBoxClick(
                    navController = navController,
                    url = DIGIJET_URL
                ),
            )
            RoundedIconBox(
                title = stringResource(id = R.string.digi_style),
                image = painterResource(id = R.drawable.auction),
                onClick = onBoxClick(
                    navController = navController,
                    url = AUCTION_URL
                ),
            )
            RoundedIconBox(
                title = stringResource(id = R.string.digi_pay),
                image = painterResource(id = R.drawable.digipay),
                onClick = onBoxClick(
                    navController,
                    url = DIGIPAY_URL
                ),
            )
            RoundedIconBox(
                title = stringResource(id = R.string.pindo),
                image = painterResource(id = R.drawable.pindo),
                bgColor = MaterialTheme.colors.amber,
                onClick = onBoxClick(
                    navController,
                    url = PINDO_URL
                ),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = LocalSpacing.current.semiSmall
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RoundedIconBox(
                title = stringResource(id = R.string.digi_shopping),
                image = painterResource(id = R.drawable.shopping),
                onClick = onBoxClick(
                    navController,
                    url = SHOPPING_URL
                ),
            )
            RoundedIconBox(
                title = stringResource(id = R.string.gift_card),
                image = painterResource(id = R.drawable.giftcard),
                onClick = onBoxClick(
                    navController,
                    url = GIFT_CARD_URL
                ),
            )
            RoundedIconBox(
                title = stringResource(id = R.string.digi_plus),
                image = painterResource(id = R.drawable.digiplus),
                onClick = onBoxClick(
                    navController,
                    url = DIGIPLUS_URL
                ),
            )
            RoundedIconBox(
                title = stringResource(id = R.string.more),
                image = painterResource(id = R.drawable.more),
                bgColor = MaterialTheme.colors.grayCategory,
                onClick = onBoxClick(
                    navController,
                    url = MORE_URL
                ),
            )
        }

    }

}


@Composable
fun onBoxClick(navController: NavHostController, url: String): () -> Unit =
    { navController.navigate(route = Screen.WebView.route + "?url=${url}") }