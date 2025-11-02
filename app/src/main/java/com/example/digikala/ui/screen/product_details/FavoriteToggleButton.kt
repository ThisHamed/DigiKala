package com.example.digikala.ui.screen.product_details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.model.profile.FavoriteItem
import com.example.digikala.ui.theme.darkText
import com.example.digikala.viewModel.FavoriteListViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FavoriteToggleButton(
    item: FavoriteItem,
    viewModel: FavoriteListViewModel = hiltViewModel()
) {
    var checkState by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        viewModel.isItemExist(item.id).collectLatest {
            checkState = it
        }
    }

    IconToggleButton(
        checked = checkState,
        onCheckedChange = {
            checkState = !checkState
            if (checkState) {
                viewModel.addFavoriteItem(item)
            } else {
                viewModel.deleteFavoriteItem(item)
            }
        }
    ) {
        val transition = updateTransition(checkState, label = "icon transition")
        val tint by transition.animateColor(label = "iconColor") { isCheck ->
            if (isCheck) Color.Red else MaterialTheme.colors.darkText
        }
        Icon(
            imageVector = if (checkState) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .size(27.dp)
        )
    }
}