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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.cngal.app.compose.explore.ExploreScreen
import com.cngal.app.compose.home.HomeScreen
import com.cngal.app.compose.overview.OverviewScreen
import com.cngal.app.compose.square.SquareScreen

interface BaseDestination {
    val route: String
    val text: String
}

interface OverviewItemDestination:BaseDestination{
    val icon: ImageVector
}

object HomeDestination : OverviewItemDestination {
    override val icon = Icons.Filled.Home
    override val route = "home"
    override val text = "主页"
}

object ExploreDestination : OverviewItemDestination {
    override val icon = Icons.Filled.Explore
    override val route = "explore"
    override val text = "探索"
}
object SquareDestination : OverviewItemDestination {
    override val icon = Icons.Filled.Category
    override val route = "square"
    override val text = "广场"
}

//概览页
object OverviewDestination : BaseDestination {
    override val route = "overview"
    override val text = "概览"
}

//单个词条详情页
object SingleEntryDestination : BaseDestination {
    override val route = "entries/index"
    override val text = "概览"
    const val singleEntryIdArg = "entry_id"
    val routeWithArgs = "${route}/{${singleEntryIdArg}}"
    val arguments = listOf(
        navArgument(singleEntryIdArg) { type = NavType.IntType }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "rally://$route/{$singleEntryIdArg}"}
    )
}

val CnGalOverviewScreens = listOf(HomeDestination,ExploreDestination,SquareDestination)