package com.cngal.app.compose.overview

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cngal.app.CnGalOverviewScreens
import com.cngal.app.ExploreDestination
import com.cngal.app.HomeDestination
import com.cngal.app.SquareDestination
import com.cngal.app.compose.explore.ExploreScreen
import com.cngal.app.compose.home.HomeScreen
import com.cngal.app.compose.square.SquareScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen()
{
    val navController = rememberNavController()

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination

    // Change the variable to this and use Overview as a backup screen if this returns null
    val selectedItem = CnGalOverviewScreens.indexOfFirst { it.route == currentDestination?.route }

    Scaffold(
        bottomBar = {
            NavigationBar {
                CnGalOverviewScreens.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = item.text
                            )
                        },
                        label = { Text(item.text) },
                        selected = selectedItem == index,
                        onClick = { navController.navigateSingleTopTo(CnGalOverviewScreens[index].route) }
                    )
                }
            }
        }
    ) { innerPadding ->
        CnGalNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun CnGalNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
)
{
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen()
        }
        composable(route = ExploreDestination.route) {
            ExploreScreen()
        }
        composable(route = SquareDestination.route) {
            SquareScreen()
        }
    }
}


fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(
        this@navigateSingleTopTo.graph.findStartDestination().id
    ) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}
