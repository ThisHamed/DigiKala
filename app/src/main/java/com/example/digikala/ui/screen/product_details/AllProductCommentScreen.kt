package com.example.digikala.ui.screen.product_details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.digikala.R
import com.example.digikala.data.model.product_detail.Comment
import com.example.digikala.ui.component.Loading
import com.example.digikala.ui.component.Loading3Dots
import com.example.digikala.ui.theme.Green
import com.example.digikala.ui.theme.Oranges
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.grayAlpha
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper
import com.example.digikala.viewModel.ProductDetailViewModel

@Composable
fun AllProductCommentScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    navController: NavHostController,
    productId: String,
    commentsCount: String
) {

    LaunchedEffect(true) {
        viewModel.getCommentList(productId)
    }

    val commentsList = viewModel.commentList.collectAsLazyPagingItems()

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
                    .clickable { navController.popBackStack() }
                    .size(24.dp),
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "${DigitHelper.digitByLocate(commentsCount)}${stringResource(id = R.string.comment)}",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText
            )

        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(MaterialTheme.colors.searchBarBg)
        )

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(
                    count = commentsList.itemCount,
                    key = commentsList.itemKey { comment -> comment._id },
                    contentType = commentsList.itemContentType { "Comments" }
                ) { index ->
                    CommentItem(commentsList[index]!!)
                }


                commentsList.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item {
                                val config = LocalConfiguration.current
                                Loading(config.screenHeightDp.dp, isDark = true)
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(20.dp),
                                    horizontalAlignment = CenterHorizontally,
                                    verticalArrangement = Center
                                ) {
                                    Loading3Dots(isDark = true)
                                }
                            }
                        }
                    }
                }

            }
        }

    }

}

@Composable
private fun CommentItem(item: Comment) {
    val dateParts = item.updatedAt.substringBefore("T").split("-")
    val year = dateParts[0].toInt()
    val month = dateParts[1].toInt()
    val day = dateParts[2].toInt()

    val context = LocalContext.current

    var iconRotate = 0f

    var iconSuggestion = R.drawable.like
    var colorSuggestion = MaterialTheme.colors.Green
    var textSuggestion = context.getString(R.string.good_comment)

    when (item.star) {
        in Int.MIN_VALUE..2 -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colors.Oranges
            textSuggestion = context.getString(R.string.bad_comment)
            iconRotate = 180f
        }

        in 2..3 -> {
            iconSuggestion = R.drawable.info
            colorSuggestion = MaterialTheme.colors.semiDarkText
            textSuggestion = context.getString(R.string.so_so_comment)
            iconRotate = 0f
        }

        in 3..Int.MAX_VALUE -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colors.Green
            textSuggestion = context.getString(R.string.good_comment)
            iconRotate = 0f
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .width(MaterialTheme.spacing.large)
                    .background(colorSuggestion),
                text = DigitHelper.digitByLocate(item.star.toString() + ".0"),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.medium),
                text = DigitHelper.gregorianToJalali(year, month, day),
                color = MaterialTheme.colors.semiDarkText,
                style = MaterialTheme.typography.h6
            )
            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .padding(horizontal = MaterialTheme.spacing.small),
                tint = MaterialTheme.colors.grayAlpha
            )
            Text(
                text = item.userName,
                color = MaterialTheme.colors.grayAlpha,
                style = MaterialTheme.typography.h6
            )

        }

        Divider(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.large)
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.4f)
                .shadow(2.dp),
            color = Color.LightGray
        )

        Row(
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.large,
                    vertical = MaterialTheme.spacing.medium
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(iconSuggestion),
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp)
                    .rotate(iconRotate),
                tint = colorSuggestion
            )
            Text(
                text = textSuggestion,
                modifier = Modifier.padding(start = MaterialTheme.spacing.small),
                maxLines = 1,
                style = MaterialTheme.typography.h6,
                color = colorSuggestion
            )

        }

        Text(
            modifier = Modifier.padding(start = MaterialTheme.spacing.large),
            text = item.title,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h5
        )

        Text(
            modifier = Modifier.padding(
                start = MaterialTheme.spacing.large,
                top = MaterialTheme.spacing.small,
                bottom = MaterialTheme.spacing.large
            ),
            text = item.description,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h5
        )

    }


}

