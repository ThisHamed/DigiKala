package com.example.digikala.ui.screen.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.basket.CartStatus
import com.example.digikala.ui.component.IconWithRotate
import com.example.digikala.ui.component.currentLogoChangeByLanguage
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.DigikalaLightGreen
import com.example.digikala.ui.theme.DigikalaLightRed
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.ui.theme.veryExtraSmall
import com.example.digikala.utils.Constants
import com.example.digikala.utils.DigitHelper.digitByLocateAndSeparator
import com.example.digikala.viewModel.BasketViewModel

@Composable
fun CartItemCard(
    cart: CartItem,
    mode: CartStatus,
    viewModel: BasketViewModel = hiltViewModel()
) {

    val count = remember {
        mutableStateOf(cart.count)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = MaterialTheme.spacing.extraSmall)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = stringResource(id = R.string.your_shopping_list),
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.darkText
                    )
                    Text(
                        text = "${digitByLocateAndSeparator(count.value.toString())} ${
                            stringResource(
                                id = R.string.goods
                            )
                        }",
                        style = MaterialTheme.typography.h6,
                        color = Color.Gray
                    )
                }

                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "",
                    tint = MaterialTheme.colors.darkText
                )

            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = rememberAsyncImagePainter(cart.image),
                    contentDescription = "",
                    modifier = Modifier
                        .width(130.dp)
                        .height(90.dp)
                        .weight(0.3f)
                )

                Column(
                    modifier = Modifier
                        .weight(0.7f)
                ) {

                    Text(
                        text = cart.name,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.darkText,
                        maxLines = 2,
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.extraSmall)
                    )

                    DetailRow(
                        icon = painterResource(id = R.drawable.warranty),
                        text = stringResource(id = R.string.warranty),
                        color = MaterialTheme.colors.darkText,
                        fontStyle = MaterialTheme.typography.extraSmall
                    )
                    DetailRow(
                        icon = painterResource(id = R.drawable.store),
                        text = stringResource(id = R.string.digikala),
                        color = MaterialTheme.colors.darkText,
                        fontStyle = MaterialTheme.typography.extraSmall
                    )


                    Row {

                        Column(
                            modifier = Modifier
                                .width(16.dp)
                                .fillMaxHeight()
                                .padding(vertical = MaterialTheme.spacing.extraSmall),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.in_stock),
                                contentDescription = "",
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colors.DarkCyan
                            )

                            Divider(
                                Modifier
                                    .width(2.dp)
                                    .height(16.dp)
                                    .alpha(0.6f),
                                color = Color.LightGray
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(10.dp)
                                    .padding(1.dp),
                                tint = MaterialTheme.colors.DarkCyan
                            )
                            Divider(
                                Modifier
                                    .width(2.dp)
                                    .height(16.dp)
                                    .alpha(0.6f),
                                color = Color.LightGray
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(10.dp)
                                    .padding(1.dp),
                                tint = MaterialTheme.colors.DarkCyan
                            )

                        }

                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {

                            Text(
                                text = cart.seller,
                                style = MaterialTheme.typography.extraSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colors.semiDarkText,
                                modifier = Modifier
                                    .padding(vertical = MaterialTheme.spacing.extraSmall)
                            )


                            DetailRow(
                                icon = painterResource(id = R.drawable.k1),
                                text = stringResource(id = R.string.digikala_send),
                                color = MaterialTheme.colors.DigikalaLightRed,
                                fontStyle = MaterialTheme.typography.veryExtraSmall
                            )
                            DetailRow(
                                icon = painterResource(id = R.drawable.k2),
                                text = stringResource(id = R.string.fast_send),
                                color = MaterialTheme.colors.DigikalaLightGreen,
                                fontStyle = MaterialTheme.typography.veryExtraSmall
                            )

                        }

                    }

                }

            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

            Row(
                modifier = Modifier
                    .align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Surface(
                    modifier = Modifier
                        .clip(MaterialTheme.roundedShape.semiSmall)
                        .border(
                            1.dp,
                            Color.LightGray.copy(0.6f),
                            MaterialTheme.roundedShape.semiSmall
                        )
                ) {

                    if (mode == CartStatus.CURRENT_CART) {

                        Row(
                            modifier = Modifier.padding(
                                horizontal = MaterialTheme.spacing.small,
                                vertical = MaterialTheme.spacing.extraSmall
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.ic_increase_24),
                                contentDescription = "",
                                tint = MaterialTheme.colors.digikalaRed,
                                modifier = Modifier.clickable {
                                    count.value++
                                    viewModel.changeCartItemCount(cart.itemId, count.value)
                                }
                            )

                            Text(
                                text = digitByLocateAndSeparator(count.value.toString()),
                                style = MaterialTheme.typography.body2,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colors.digikalaRed,
                                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                            )

                            if (count.value == 1) {

                                Icon(
                                    painter = painterResource(id = R.drawable.digi_trash),
                                    contentDescription = "",
                                    tint = MaterialTheme.colors.digikalaRed,
                                    modifier = Modifier.clickable { viewModel.removeCartItem(cart) }
                                )

                            } else {

                                Icon(
                                    painter = painterResource(id = R.drawable.ic_decrease_24),
                                    contentDescription = "",
                                    tint = MaterialTheme.colors.digikalaRed,
                                    modifier = Modifier.clickable {
                                        count.value--
                                        viewModel.changeCartItemCount(cart.itemId, count.value)
                                    }
                                )

                            }
                        }

                    } else {

                        Row(
                            modifier = Modifier
                                .padding(
                                    horizontal = 48.dp,
                                    vertical = MaterialTheme.spacing.small
                                )
                                .clickable {
                                    viewModel.changeCartItemStatus(
                                        cart.itemId, CartStatus.CURRENT_CART
                                    )
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painterResource(id = R.drawable.ic_baseline_shopping_cart_checkout),
                                contentDescription = "increase icon",
                                tint = MaterialTheme.colors.digikalaRed,
                                modifier = Modifier
                                    .size(28.dp)
                            )
                        }


                    }

                }

                Spacer(modifier = Modifier.padding(MaterialTheme.spacing.semiMedium))

                Column() {

                    val discountAmount = (cart.price * cart.discountPercent) / 100

                    Text(
                        text = "${digitByLocateAndSeparator(discountAmount.toString())} ${
                            stringResource(
                                id = R.string.discount
                            )
                        }",
                        style = MaterialTheme.typography.extraSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.DigikalaLightRed
                    )
                    Row {

                        Text(
                            text = digitByLocateAndSeparator(cart.price.toString()),
                            style = MaterialTheme.typography.h3,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.darkText
                        )

                        Icon(
                            painter = currentLogoChangeByLanguage(),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(MaterialTheme.spacing.extraSmall)
                        )

                    }
                }

            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

            if (mode == CartStatus.CURRENT_CART) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.changeCartItemStatus(cart.itemId, CartStatus.NEXT_CART)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    Text(
                        text = stringResource(id = R.string.save_to_next_list),
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.DarkCyan
                    )

                    IconWithRotate(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        tint = MaterialTheme.colors.DarkCyan
                    )

                }

            } else {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.removeCartItem(cart)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    Text(
                        text = stringResource(id = R.string.delete_from_list),
                        fontWeight = FontWeight.Light,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.DigikalaLightRed
                    )

                    IconWithRotate(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        tint = MaterialTheme.colors.DigikalaLightRed
                    )

                }

            }

        }

    }
}


@Composable
fun DetailRow(
    icon: Painter,
    text: String,
    color: Color,
    fontStyle: androidx.compose.ui.text.TextStyle
) {

    Row(
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.extraSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(painter = icon, contentDescription = "", modifier = Modifier.size(16.dp), tint = color)

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

        Text(
            text = text,
            style = fontStyle,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.semiDarkText
        )

    }


}
