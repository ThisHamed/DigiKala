package com.example.digikala.ui.screen.product_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.data.model.product_detail.Comment
import com.example.digikala.ui.theme.Green
import com.example.digikala.ui.theme.Oranges
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.grayAlpha
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.DigitHelper.digitByLocate
import com.example.digikala.utils.DigitHelper.gregorianToJalali

@Composable
fun TextCommentCard(comment: Comment) {

    val dateParts = comment.updatedAt.substringBefore("T").split("-")
    val year = dateParts[0].toInt()
    val month = dateParts[1].toInt()
    val day = dateParts[2].toInt()

    val context = LocalContext.current

    var iconRotate = 0f

    var iconSuggestion = R.drawable.like
    var colorSuggestion = MaterialTheme.colors.Green
    var textSuggestion = context.getString(R.string.good_comment)

    when (comment.star) {
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

    Card(
        modifier = Modifier
            .padding(
                horizontal = MaterialTheme.spacing.small,
                vertical = MaterialTheme.spacing.medium,
            )
            .width(260.dp)
            .height(210.dp),
        shape = MaterialTheme.roundedShape.small,
        elevation = 2.dp
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
        ) {
            Text(
                text = comment.title,
                color = MaterialTheme.colors.darkText,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                Modifier.padding(vertical = MaterialTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
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
                text = comment.description,
                modifier = Modifier.weight(1f),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6
            )
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = digitByLocate(gregorianToJalali(year, month, day)),
                    color = MaterialTheme.colors.semiDarkText,
                    style = MaterialTheme.typography.h6
                )
                Icon(
                    painter = painterResource(id = R.drawable.dot),
                    contentDescription = "",
                    tint = MaterialTheme.colors.grayAlpha,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(horizontal = MaterialTheme.spacing.small)
                )
                Text(
                    text = comment.userName,
                    color = MaterialTheme.colors.grayAlpha,
                    style = MaterialTheme.typography.h6
                )
            }

        }

    }
}