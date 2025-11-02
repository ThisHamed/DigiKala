package com.example.digikala.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.digikala.ui.screen.basket.BasketScreen
import com.example.digikala.ui.screen.category.CategoryScreen
import com.example.digikala.ui.screen.checkout.CheckoutScreen
import com.example.digikala.ui.screen.checkout.ConfirmPurchaseScreen
import com.example.digikala.ui.screen.home.HomeScreen
import com.example.digikala.ui.screen.home.WebPageScreen
import com.example.digikala.ui.screen.product_details.AllProductCommentScreen
import com.example.digikala.ui.screen.product_details.NewCommentScreen
import com.example.digikala.ui.screen.product_details.ProductDescriptionScreen
import com.example.digikala.ui.screen.product_details.ProductDetailScreen
import com.example.digikala.ui.screen.product_details.ProductPriceChartScreen
import com.example.digikala.ui.screen.product_details.ProductTechnicalFeatures
import com.example.digikala.ui.screen.profile.ProfileScreen
import com.example.digikala.ui.screen.profile.SettingScreen
import com.example.digikala.ui.screen.profile.UserAccountScreen
import com.example.digikala.ui.screen.splash.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.Category.route) {
            CategoryScreen(navController = navController)
        }

        composable(route = Screen.Basket.route) {
            BasketScreen(navController = navController)
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(route = Screen.Checkout.route) {
            CheckoutScreen(navController = navController)
        }

        composable(
            route = Screen.ConfirmPurchase.route + "/{orderId}/{orderPrice}",
            arguments = listOf(
                navArgument("orderId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("orderPrice") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
            )
        ) {

            it.arguments!!.getString("orderId")?.let { orderId ->
                it.arguments!!.getString("orderPrice")?.let { orderPrice ->
                    ConfirmPurchaseScreen(
                        navController = navController,
                        orderId = orderId,
                        orderPrice = orderPrice
                    )
                }
            }

        }

        composable(
            route = Screen.ProductDetail.route + "/{productId}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
            )
        ) {

            it.arguments!!.getString("productId")?.let { productId ->
                ProductDetailScreen(
                    navController = navController,
                    id = productId,
                )
            }

        }

        composable(
            route = Screen.AllComment.route + "/{productId}/{commentsCount}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("commentsCount") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {

            it.arguments!!.getString("productId")?.let { productId ->
                it.arguments!!.getString("commentsCount")?.let { commentsCount ->
                    AllProductCommentScreen(
                        navController = navController,
                        productId = productId,
                        commentsCount = commentsCount
                    )
                }
            }

        }

        composable(
            route = Screen.ProductDescription.route + "/{description}",
            arguments = listOf(
                navArgument("description") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
            )
        ) {

            it.arguments!!.getString("description")?.let { description ->
                ProductDescriptionScreen(
                    navController = navController,
                    description = description,
                )
            }

        }

        composable(
            route = Screen.ProductTechnicalFeatures.route + "/{jsonString}",
            arguments = listOf(
                navArgument("jsonString") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
            )
        ) {
            it.arguments!!.getString("jsonString")?.let { jsonString ->
                ProductTechnicalFeatures(
                    navController = navController,
                    jsonString = jsonString,
                )
            }
        }

        composable(
            route = Screen.ProductPriceChart.route + "?jsonString={jsonString}",
            arguments = listOf(
                navArgument("jsonString") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
            )
        ) {
            it.arguments!!.getString("jsonString")?.let { jsonString ->
                ProductPriceChartScreen(
                    navController = navController,
                    jsonString = jsonString,
                )
            }
        }

        composable(
            route = Screen.WebView.route + "?url={url}",
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })

        ) {
            val url = it.arguments?.getString("url")
            url?.let {
                WebPageScreen(navController = navController, url = url)
            }

        }

        composable(
            route = Screen.NewCommentScreen.route + "?productId={productId}?productName={productName}?imageUrl={imageUrl}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("productName") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("imageUrl") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
            )
        ) {

            it.arguments!!.getString("productId")?.let { productId ->
                it.arguments!!.getString("productName")?.let { productName ->
                    it.arguments!!.getString("imageUrl")?.let { imageUrl ->
                        NewCommentScreen(
                            navController = navController,
                            productId = productId,
                            productName = productName,
                            imageUrl = imageUrl
                        )
                    }
                }
            }
        }

        composable(Screen.Setting.route) {
            SettingScreen(navController = navController)
        }

        composable(Screen.UserAccount.route) {
            UserAccountScreen(navController = navController)
        }

    }
}