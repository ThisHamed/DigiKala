package com.example.digikala.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.settingArrow
import com.example.digikala.ui.theme.spacing

@Composable
fun MenuRowItem(
    icon: @Composable () -> Unit,
    text: String,
    isHaveDivider: Boolean,
    color: Color = MaterialTheme.colors.darkText,
    addCompose: @Composable (() -> Unit)? = null,
    action: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { action() }
            .padding(horizontal = MaterialTheme.spacing.medium)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            icon()

        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.9f)
                .padding(horizontal = MaterialTheme.spacing.small),
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    text = text,
                    color = color
                )

                if (addCompose == null) {
                    Icon(
                        Icons.Outlined.KeyboardArrowLeft,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colors.settingArrow
                    )
                } else {
                    addCompose()
                }

            }

            if (isHaveDivider) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .alpha(0.4f),
                    color = Color.LightGray
                )
            }

        }

    }
}