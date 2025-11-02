package com.example.digikala.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash_screen")
    data object Home : Screen("home_screen")
    data object Category : Screen("category_screen")
    data object Basket : Screen("basket_screen")
    data object Profile : Screen("profile_screen")
    data object Checkout : Screen("checkout_screen")
    data object ConfirmPurchase : Screen("confirm_purchase_screen")
    data object ProductPriceChart : Screen("product_price_chart")
    data object ProductDetail : Screen("product_detail_screen")
    data object ProductDescription : Screen("product_description_screen")
    data object NewCommentScreen : Screen("new_comment_screen")
    data object AllComment : Screen("all_comment_screen")
    data object ProductTechnicalFeatures : Screen("product_technical_features")
    data object WebView : Screen("webView_screen")
    data object Setting : Screen("setting_screen")
    data object UserAccount : Screen("user_account")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}