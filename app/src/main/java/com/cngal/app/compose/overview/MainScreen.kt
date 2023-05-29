package com.cngal.app.compose.overview

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cngal.app.OverviewDestination
import com.cngal.app.SingleEntryDestination
import com.cngal.app.compose.entry.single.SingleEntryScreen
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
                navBackStackEntry.arguments?.getInt(SingleEntryDestination.singleEntryIdArg)
            SingleEntryScreen(id = id, onNav = onNav)

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
