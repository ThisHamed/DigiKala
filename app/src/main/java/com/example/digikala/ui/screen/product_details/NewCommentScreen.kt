package com.example.digikala.ui.screen.product_details

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.product_detail.NewComment
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.component.Loading
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.amber
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.grayAlpha
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.USER_NAME
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.viewModel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewCommentScreen(
    navController: NavController,
    productId: String,
    productName: String,
    imageUrl: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Header(navController, productName, imageUrl)
        CommentForm(navController, productId)
    }

}

@Composable
private fun Header(
    navController: NavController,
    productName: String,
    imageUrl: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.spacing.extraSmall,
                horizontal = MaterialTheme.spacing.small
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "")
        }
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small)
                .clip(MaterialTheme.roundedShape.small)
                .size(50.dp)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.your_comment),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.darkText
            )
            Text(
                text = productName,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkText
            )

        }

    }

    Divider(color = MaterialTheme.colors.grayCategory, thickness = 2.dp)

}


@Composable
fun CommentForm(
    navController: NavController,
    productId: String,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    var sliderValue by remember { mutableStateOf(0f) }

    val score = when (sliderValue.toInt()) {
        1 -> stringResource(id = R.string.very_bad)
        2 -> stringResource(id = R.string.bad)
        3 -> stringResource(id = R.string.normal)
        4 -> stringResource(id = R.string.good)
        5 -> stringResource(id = R.string.very_good)
        else -> ""
    }

    Text(
        text = score,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = MaterialTheme.spacing.medium),
        style = MaterialTheme.typography.h2,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.darkText,
        textAlign = TextAlign.Center
    )

    Slider(
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.large),
        value = sliderValue,
        onValueChange = { _sliderValue ->
            sliderValue = _sliderValue
        },
        onValueChangeFinished = {
            Log.e("1212", "sliderValue = $sliderValue")
        },
        valueRange = 1f..5f,
        steps = 4,
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colors.amber,
            activeTrackColor = MaterialTheme.colors.amber,
            inactiveTrackColor = MaterialTheme.colors.grayCategory,
            activeTickColor = MaterialTheme.colors.amber,
            inactiveTickColor = MaterialTheme.colors.grayAlpha
        ),
    )

    Divider(
        modifier = Modifier.padding(top = MaterialTheme.spacing.semiMedium),
        color = MaterialTheme.colors.grayCategory,
        thickness = 1.dp
    )

    ////////////////////////////////////////////////////////////////////////////////

    var commentTitle by remember { mutableStateOf("") }
    var commentBody by remember { mutableStateOf("") }

    val context = LocalContext.current


    var loading by remember {
        mutableStateOf(false)
    }


    LaunchedEffect(true) {

        viewModel.newCommentResult.collectLatest { newCommentResult ->
            when (newCommentResult) {
                is NetworkResult.Success -> {
                    if (newCommentResult.message.equals("کامنت با موفقیت ثبت شد")) { //todo edit backend
                        navController.popBackStack()
                    }
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("3636", "ProductDetailScreen error : ${newCommentResult.message}")
                }

                is NetworkResult.Loading -> {

                }

            }
        }

    }


    Column(Modifier.padding(horizontal = MaterialTheme.spacing.medium)) {
        Text(
            text = stringResource(id = R.string.say_your_comment),
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.darkText
        )
        Text(
            text = stringResource(id = R.string.comment_title),
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.darkText
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = commentTitle,
            onValueChange = { commentTitle = it },
            maxLines = 1,
            singleLine = true,
            shape = MaterialTheme.roundedShape.small,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.darkText,
                backgroundColor = MaterialTheme.colors.grayCategory,
                focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Text(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.biggerMedium,
                    start = MaterialTheme.spacing.extraSmall,
                    bottom = MaterialTheme.spacing.extraSmall,
                ),
            text = stringResource(id = R.string.comment_text),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.darkText,
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            value = commentBody,
            onValueChange = { commentBody = it },
            shape = MaterialTheme.roundedShape.small,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.darkText,
                backgroundColor = MaterialTheme.colors.grayCategory,
                focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            maxLines = 3,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.small,
                ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val checkedState = remember { mutableStateOf(false) }

            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                colors = CheckboxDefaults.colors(MaterialTheme.colors.DarkCyan)
            )
            Text(
                text = stringResource(id = R.string.comment_anonymously),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.darkText,
            )
        }

        Divider(color = MaterialTheme.colors.grayCategory, thickness = 2.dp)

        val name = if (USER_NAME == "null") {
            stringResource(id = R.string.user)
        } else {
            USER_NAME.replace("-","")
        }

        if (loading) {
            Loading(height = 60.dp, isDark = true)
        } else {
            OutlinedButton(
                onClick = {
                    loading = true
                    val newComment = NewComment(
                        token = USER_TOKEN,
                        productId = productId,
                        title = commentTitle,
                        star = (sliderValue - 1).toInt(),
                        description = commentBody,
                        userName = name
                    )
                    if (newComment.title.isBlank()) {
                        loading = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.comment_title_null),
                            Toast.LENGTH_LONG
                        ).show()
                    } else if (newComment.star == 0) {
                        loading = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.comment_star_null),
                            Toast.LENGTH_LONG
                        ).show()
                    } else if (newComment.description.isBlank()) {
                        loading = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.comment_body_null),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        viewModel.setNewComment(newComment)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.medium)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.extraSmall),
                    text = stringResource(id = R.string.set_new_comment),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.semiDarkText
                )

            }
        }


    }


}
