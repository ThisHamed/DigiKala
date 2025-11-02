package com.example.digikala.ui.screen.profile

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.HelpCenter
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.OtherHouses
import androidx.compose.material.icons.outlined.PestControl
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digikala.MainActivity
import com.example.digikala.R
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.digikalaRed
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.DIGI_BUG
import com.example.digikala.utils.Constants.DIGI_FAQ
import com.example.digikala.utils.Constants.DIGI_PRIVACY
import com.example.digikala.utils.Constants.DIGI_SCORE
import com.example.digikala.utils.Constants.DIGI_TERMS
import com.example.digikala.utils.Constants.DIGI_TURLEARN
import com.example.digikala.viewModel.BasketViewModel
import com.example.digikala.viewModel.DatastoreViewModel
import com.example.digikala.viewModel.ProfileViewModel

@Composable
fun SettingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        SettingHeader(navController = navController)
        SettingMenuSection(navController = navController)
    }
}

@Composable
fun SettingHeader(
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = MaterialTheme.spacing.large, end = MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.setting),
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold
        )

        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "",
                modifier = Modifier.padding(
                    vertical = MaterialTheme.spacing.small,
                    horizontal = MaterialTheme.spacing.small
                ),
                tint = MaterialTheme.colors.selectedBottomBar
            )
        }

    }
}

@Composable
fun SettingMenuSection(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStore: DatastoreViewModel = hiltViewModel(),
    basketViewModel: BasketViewModel = hiltViewModel()
) {
    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.HelpCenter,
                contentDescription = "",
            )
        },
        text = stringResource(id = R.string.repetitive_questions),
        isHaveDivider = true
    ) {
        navController.navigate(Screen.WebView.route + "?url=${DIGI_FAQ}")
    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.PrivacyTip,
                contentDescription = "",
            )
        },
        text = stringResource(id = R.string.privacy),
        isHaveDivider = true
    ) {
        navController.navigate(Screen.WebView.route + "?url=${DIGI_PRIVACY}")
    }
    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.OtherHouses,
                contentDescription = "",
            )
        },
        text = stringResource(id = R.string.terms_of_use),
        isHaveDivider = true
    ) {
        navController.navigate(Screen.WebView.route + "?url=${DIGI_TERMS}")
    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.Call,
                contentDescription = "",
            )
        },
        text = stringResource(id = R.string.contact_us),
        isHaveDivider = true
    ) {
        navController.navigate(Screen.WebView.route + "?url=${DIGI_TURLEARN}")
    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.PestControl,
                contentDescription = "",
            )
        },
        text = stringResource(id = R.string.error_report),
        isHaveDivider = true
    ) {
        navController.navigate(Screen.WebView.route + "?url=${DIGI_BUG}")
    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.StarRate,
                contentDescription = "",
            )
        },
        text = stringResource(id = R.string.rating_to_digikal),
        isHaveDivider = true
    ) {
        navController.navigate(Screen.WebView.route + "?url=${DIGI_SCORE}")
    }

    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.Language,
                contentDescription = "",
            )
        },
        text = stringResource(id = R.string.changing_lang),
        addCompose = { ChangeLanguage() },
        isHaveDivider = true
    )
    var isShow by remember { mutableStateOf(false) }
    MenuRowItem(
        icon = {
            Icon(
                Icons.Outlined.Logout,
                contentDescription = "",
                tint = MaterialTheme.colors.digikalaRed
            )
        },
        text = stringResource(id = R.string.sign_out),
        color = MaterialTheme.colors.digikalaRed,
        addCompose = { },
        isHaveDivider = false
    ) {
        isShow = true
    }

    if (isShow) {
        CustomDialog(
            onDismiss = {
                isShow = false
            },
            onExit = {
                logOut(
                    dataStore,
                    navController,
                    profileViewModel,
                    basketViewModel
                )
                isShow = false

            })
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDialog(onDismiss: () -> Unit, onExit: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss() }, properties = DialogProperties(
            dismissOnBackPress = true, dismissOnClickOutside = true
        )
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .height(IntrinsicSize.Min),
            elevation = 0.dp
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(
                    text = stringResource(id = R.string.sign_out),
                    modifier = Modifier
                        .padding(8.dp, 16.dp, 8.dp, 2.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(), fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.darkText
                )
                Text(
                    text = stringResource(id = R.string.sign_out_sure),
                    modifier = Modifier
                        .padding(8.dp, 2.dp, 8.dp, 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.darkText
                )
                Divider(
                    color = Color.LightGray, modifier = Modifier
                        .fillMaxWidth()
                        .width(1.dp)
                )
                Row(Modifier.padding(top = 0.dp)) {
                    CompositionLocalProvider(
                        LocalMinimumInteractiveComponentEnforcement provides false,
                    ) {
                        TextButton(
                            onClick = { onDismiss() },
                            Modifier
                                .fillMaxWidth()
                                .padding(0.dp)
                                .weight(1F)
                                .border(0.dp, Color.Transparent)
                                .height(48.dp),
                            elevation = ButtonDefaults.elevation(0.dp, 0.dp),
                            shape = RoundedCornerShape(0.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                color = MaterialTheme.colors.darkText
                            )
                        }
                    }
                    Divider(
                        color = Color.LightGray, modifier =
                        Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )
                    CompositionLocalProvider(
                        LocalMinimumInteractiveComponentEnforcement provides false,
                    ) {
                        TextButton(
                            onClick = {
                                onExit.invoke()
                            },
                            Modifier
                                .fillMaxWidth()
                                .padding(0.dp)
                                .weight(1F)
                                .border(0.dp, color = Color.Transparent)
                                .height(48.dp),
                            elevation = ButtonDefaults.elevation(0.dp, 0.dp),
                            shape = RoundedCornerShape(0.dp),
                            contentPadding = PaddingValues()
                        ) {
                            Text(
                                text = stringResource(id = R.string.logout),
                                color = MaterialTheme.colors.digikalaRed
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChangeLanguage(
    viewModel: DatastoreViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity
    val lang = viewModel.getUserLanguage()
    val checkState by remember { mutableStateOf(lang) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = stringResource(id = R.string.english))
        Switch(
            checked = (checkState == "fa"),
            onCheckedChange = {
                viewModel.saveUserLanguage(if (lang == "en") "fa" else "en")
                activity.apply {
                    finish()
                    startActivity(Intent(activity, MainActivity::class.java))
                }
            }
        )
        Text(text = stringResource(id = R.string.farsi))
    }

}


fun logOut(
    dataStore: DatastoreViewModel,
    navController: NavController,
    profileState: ProfileViewModel,
    basketViewModel: BasketViewModel
) {
    dataStore.apply {
        saveUserId("null")
        saveUserToken("null")
        saveUserPassword("null")
        saveUserPhone("null")
        saveUserName("null")
    }
    basketViewModel.deleteAllItems()
    profileState.screenState = ProfileScreenState.LOGIN_STATE
    navController.navigate(Screen.Profile.route)

}


