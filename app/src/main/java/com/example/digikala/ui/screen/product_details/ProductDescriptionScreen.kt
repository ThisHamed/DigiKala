package com.example.digikala.ui.screen.product_details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.ui.component.Loading
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing

@Composable
fun ProductDescriptionScreen(
    navController: NavHostController,
    description: String
) {
    if (description.isBlank() || description.isEmpty()) {
        val config = LocalConfiguration.current
        Loading(height = config.screenHeightDp.dp, isDark = true)
    } else {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.medium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium)
                        .clickable {
                            navController.popBackStack()
                        }
                        .size(24.dp)

                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.review),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.darkText,
                )

            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .background(MaterialTheme.colors.searchBarBg)
            )


            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.semiMedium),
                    text = stringResource(id = R.string.product_introduce),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.darkText,
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.semiMedium),
                    text = description,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.semiDarkText,
                )
            }

        }
    }


}