package com.cngal.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Square
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.cngal.app.compose.explore.ExploreScreen
import com.cngal.app.compose.home.HomeScreen
import com.cngal.app.compose.square.SquareScreen

interface CnGalDestination {
    val icon: ImageVector
    val route: String
    val text: String
    val screen: @Composable (modifier: Modifier) -> Unit
}

object HomeDestination : CnGalDestination {
    override val icon = Icons.Filled.Home
    override val route = "home"
    override val text = "主页"
    override val screen: @Composable (modifier: Modifier) -> Unit = { HomeScreen() }
}

object ExploreDestination : CnGalDestination {
    override val icon = Icons.Filled.Explore
    override val route = "explore"
    override val text = "探索"
    override val screen: @Composable (modifier: Modifier) -> Unit = { ExploreScreen() }
}
object SquareDestination : CnGalDestination {
    override val icon = Icons.Filled.Category
    override val route = "square"
    override val text = "广场"
    override val screen: @Composable (modifier: Modifier) -> Unit = { SquareScreen() }
}

val CnGalOverviewScreens = listOf(HomeDestination,ExploreDestination,SquareDestination)