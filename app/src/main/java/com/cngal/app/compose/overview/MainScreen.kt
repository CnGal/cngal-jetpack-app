package com.cngal.app.compose.overview

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cngal.app.ArticleCardGroupDestination
import com.cngal.app.EntryCardGroupDestination
import com.cngal.app.NewsCardGroupDestination
import com.cngal.app.OutlinkCardGroupDestination
import com.cngal.app.OverviewDestination
import com.cngal.app.RoleCardGroupDestination
import com.cngal.app.SingleArticleDestination
import com.cngal.app.SingleEntryDestination
import com.cngal.app.SingleTagDestination
import com.cngal.app.compose.article.detail.ArticleCardGroupScreen
import com.cngal.app.compose.article.single.SingleArticleScreen
import com.cngal.app.compose.entry.detail.EntryCardGroupScreen
import com.cngal.app.compose.entry.detail.NewsCardGroupScreen
import com.cngal.app.compose.entry.detail.OutlinkCardGroupScreen
import com.cngal.app.compose.entry.detail.RoleCardGroupScreen
import com.cngal.app.compose.entry.single.SingleEntryScreen
import com.cngal.app.compose.tag.single.SingleTagScreen
import com.cngal.app.helper.JsonHelper
import com.cngal.app.helper.appContext
import com.cngal.app.helper.openNewTabWindow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen()
{
    val navController = rememberNavController()
    MainNavHost(
        navController = navController,
        onNav = {
            if (it.contains("://"))
            {
                openNewTabWindow(it, appContext)
            }
            else
            {
                navController.navigateTo(it)
            }
        }
    )
}

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onNav: (String) -> Unit
)
{
    NavHost(
        navController = navController,
        startDestination = OverviewDestination.route,
        modifier = modifier
    ) {
        composable(route = OverviewDestination.route) {
            OverviewScreen(onNav = onNav)
        }
        composable(
            route = SingleEntryDestination.routeWithArgs,
            arguments = SingleEntryDestination.arguments,
            deepLinks = SingleEntryDestination.deepLinks
        ) { navBackStackEntry ->
            val id =
                navBackStackEntry.arguments?.getInt(SingleEntryDestination.idArg)
            SingleEntryScreen(id = id, onNav = onNav)
        }
        composable(
            route = EntryCardGroupDestination.routeWithArgs,
            arguments = EntryCardGroupDestination.arguments,
            deepLinks = EntryCardGroupDestination.deepLinks
        ) { navBackStackEntry ->
            val title =
                navBackStackEntry.arguments?.getString(EntryCardGroupDestination.titleArg)
            val data =
                navBackStackEntry.arguments?.getString(EntryCardGroupDestination.dataArg)
            EntryCardGroupScreen(title ?: "", JsonHelper.fromJson(data!!), onNav)
        }
        composable(
            route = RoleCardGroupDestination.routeWithArgs,
            arguments = RoleCardGroupDestination.arguments,
            deepLinks = RoleCardGroupDestination.deepLinks
        ) { navBackStackEntry ->
            val title =
                navBackStackEntry.arguments?.getString(RoleCardGroupDestination.titleArg)
            val data =
                navBackStackEntry.arguments?.getString(RoleCardGroupDestination.dataArg)
            RoleCardGroupScreen(title ?: "", JsonHelper.fromJson(data!!), onNav)
        }
        composable(
            route = NewsCardGroupDestination.routeWithArgs,
            arguments = NewsCardGroupDestination.arguments,
            deepLinks = NewsCardGroupDestination.deepLinks
        ) { navBackStackEntry ->
            val title =
                navBackStackEntry.arguments?.getString(NewsCardGroupDestination.titleArg)
            val data =
                navBackStackEntry.arguments?.getString(NewsCardGroupDestination.dataArg)
            NewsCardGroupScreen(title ?: "", JsonHelper.fromJson(data!!), onNav)
        }
        composable(
            route = OutlinkCardGroupDestination.routeWithArgs,
            arguments = OutlinkCardGroupDestination.arguments,
            deepLinks = OutlinkCardGroupDestination.deepLinks
        ) { navBackStackEntry ->
            val title =
                navBackStackEntry.arguments?.getString(OutlinkCardGroupDestination.titleArg)
            val data =
                navBackStackEntry.arguments?.getString(OutlinkCardGroupDestination.dataArg)
            OutlinkCardGroupScreen(title ?: "", JsonHelper.fromJson(data!!), onNav)
        }
        composable(
            route = SingleArticleDestination.routeWithArgs,
            arguments = SingleArticleDestination.arguments,
            deepLinks = SingleArticleDestination.deepLinks
        ) { navBackStackEntry ->
            val id =
                navBackStackEntry.arguments?.getLong(SingleArticleDestination.idArg)
            SingleArticleScreen(id = id, onNav = onNav)

        }
        composable(
            route = ArticleCardGroupDestination.routeWithArgs,
            arguments = ArticleCardGroupDestination.arguments,
            deepLinks = ArticleCardGroupDestination.deepLinks
        ) { navBackStackEntry ->
            val title =
                navBackStackEntry.arguments?.getString(ArticleCardGroupDestination.titleArg)
            val data =
                navBackStackEntry.arguments?.getString(ArticleCardGroupDestination.dataArg)
            ArticleCardGroupScreen(title ?: "", JsonHelper.fromJson(data!!), onNav)
        }
        composable(
            route = SingleTagDestination.routeWithArgs,
            arguments = SingleTagDestination.arguments,
            deepLinks = SingleTagDestination.deepLinks
        ) { navBackStackEntry ->
            val id =
                navBackStackEntry.arguments?.getInt(SingleTagDestination.idArg)
            SingleTagScreen(id = id, onNav = onNav)

        }
    }
}

fun NavHostController.navigateTo(route: String) = this.navigate(route) {
    popUpTo(
        route
    ) {
        saveState = true
    }
    launchSingleTop = false
    restoreState = true
}
