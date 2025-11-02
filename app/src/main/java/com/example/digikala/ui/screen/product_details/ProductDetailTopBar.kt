package com.example.digikala.ui.screen.product_details

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.digikala.R
import com.example.digikala.data.model.product_detail.ProductDetail
import com.example.digikala.data.model.profile.FavoriteItem
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.digitByLocateAndSeparator
import com.google.gson.Gson

@Composable
fun ProductDetailTopBar(
    navController: NavController,
    priceList: ProductDetail
) {

    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(horizontal = MaterialTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(0.6f),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.exit),
                    contentDescription = null,
                    modifier = Modifier.size(17.dp),
                    tint = MaterialTheme.colors.darkText
                )
            }
        }

        Row(
            modifier = Modifier.weight(0.4f),
        ) {
            IconButton(onClick = { navController.navigate(Screen.Basket.route) }) {
                Icon(
                    painter = painterResource(id = R.drawable.basket),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp),
                    tint = MaterialTheme.colors.darkText
                )
            }


            FavoriteToggleButton(
                FavoriteItem(
                    id = priceList._id ?: "",
                    discountPercent = priceList.discountPercent ?: 0,
                    image = priceList.imageSlider?.get(0)?.image ?: "",
                    name = priceList.name ?: "",
                    price = priceList.price ?: 0,
                    seller = priceList.seller ?: "",
                    star = priceList.star ?: 0.0
                )
            )

            IconButton(onClick = { expanded = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_dots),
                    contentDescription = "",
                    modifier = Modifier.size(27.dp),
                    tint = MaterialTheme.colors.darkText
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(MaterialTheme.colors.surface)
            ) {
                DropdownMenuItem(
                    onClick = {
                        val priceListString = Gson().toJson(priceList.priceList)
                        expanded = false
                        navController.navigate(
                            Screen.ProductPriceChart.route + "?jsonString=${priceListString}"
                        )
                    }
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = MaterialTheme.spacing.extraSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.chart),
                            contentDescription = "",
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colors.darkText
                        )
                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                        Text(
                            text = stringResource(id = R.string.price_chart),
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.darkText
                        )
                    }
                }
                val context = LocalContext.current
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        shareToSocialMedia(
                            context,
                            priceList.name!!,
                            digitByLocateAndSeparator(priceList.price!!.toString()),
                            url = "https://truelearn.ir/"
                        )
                    }
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = MaterialTheme.spacing.extraSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = "",
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colors.darkText
                        )
                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                        Text(
                            text = stringResource(id = R.string.share_product),
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.darkText
                        )
                    }
                }
            }
        }
    }
}

private fun shareToSocialMedia(
    context: Context,
    productName: String,
    productPrice: String,
    url: String
) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"

    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        "$productName با قیمت باورنکردنی $productPrice تومان فقط در فروشگاه زیر \n $url"
    )

    context.startActivity(Intent.createChooser(shareIntent, "share to..."))


}