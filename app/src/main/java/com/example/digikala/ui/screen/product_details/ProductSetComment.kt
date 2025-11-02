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
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.settingArrow
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.USER_TOKEN


@Composable
fun ProductSetComment(navController: NavHostController, item: ProductDetail) {
    Column(
        modifier = Modifier
            .padding(
                horizontal = MaterialTheme.spacing.semiLarge,
                vertical = MaterialTheme.spacing.medium
            )
            .clickable {
                if (USER_TOKEN == "null") {
                    navController.navigate(Screen.Profile.route)
                } else {
                    navController.navigate(Screen.NewCommentScreen.route + "?productId=${item._id}?productName=${item.name}?imageUrl=${item.imageSlider!![0].image}")
                }

            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.comment),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = stringResource(id = R.string.write_your_comment),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp),
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h5
            )
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colors.settingArrow
            )

        }

        Text(
            text = stringResource(id = R.string.comment_desc),
            modifier = Modifier.padding(start = MaterialTheme.spacing.large + MaterialTheme.spacing.small),
            color = MaterialTheme.colors.semiDarkText,
            style = MaterialTheme.typography.h6
        )

        Spacer(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large,
                    top = MaterialTheme.spacing.medium
                )
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)
        )


    }
}