package com.example.digikala.ui.screen.product_details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.product_detail.Comment
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.LightCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.digitByLocate

@Composable
fun ProductComment(
    comments: List<Comment>,
    commentCount: Int,
    productId: String,
    navController: NavHostController
) {

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.semiLarge),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.user_comments),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText
            )
            Text(
                text = "${digitByLocate(commentCount.toString())} " + stringResource(id = R.string.comment),
                color = MaterialTheme.colors.LightCyan,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.clickable {
                    navController.navigate(
                        Screen.AllComment.withArgs(
                            productId, commentCount
                        )
                    )
                }
            )
        }
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.medium)
    ) {
        items(items = comments) { comment ->
            TextCommentCard(comment)
        }
        item {
            CommentShowMoreItem(
                productId = productId,
                navController = navController,
                commentCount = commentCount
            )
        }

    }

}

