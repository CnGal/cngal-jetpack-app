package com.cngal.app.compose.overview

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cngal.app.OverviewDestination
import com.cngal.app.SingleEntryDestination
import com.cngal.app.compose.entry.single.SingleEntryScreen
import com.cngal.app.extension.navigateSingleTopTo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen()
{
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        MainNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
)
{
    NavHost(
        navController = navController,
        startDestination = OverviewDestination.route,
        modifier = modifier
    ) {
        composable(route = OverviewDestination.route) {
            OverviewScreen(onNav = {
                navController.navigateSingleTopTo(it)
            })
        }
        composable(
            route = SingleEntryDestination.routeWithArgs,
            arguments = SingleEntryDestination.arguments,
            deepLinks = SingleEntryDestination.deepLinks
        ) { navBackStackEntry ->
            val entryId =
                navBackStackEntry.arguments?.getInt(SingleEntryDestination.singleEntryIdArg)
            SingleEntryScreen(entryId)

        }
    }
}
