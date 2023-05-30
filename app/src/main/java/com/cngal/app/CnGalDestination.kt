package com.cngal.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

interface BaseDestination
{
    val route: String
    val text: String
}

interface OverviewItemDestination : BaseDestination
{
    val icon: ImageVector
}

object HomeDestination : OverviewItemDestination
{
    override val icon = Icons.Filled.Home
    override val route = "home"
    override val text = "主页"
}

object ExploreDestination : OverviewItemDestination
{
    override val icon = Icons.Filled.Explore
    override val route = "explore"
    override val text = "探索"
}

object SquareDestination : OverviewItemDestination
{
    override val icon = Icons.Filled.Category
    override val route = "square"
    override val text = "广场"
}

//概览页
object OverviewDestination : BaseDestination
{
    override val route = "overview"
    override val text = "概览"
}

//单个词条详情页
object SingleEntryDestination : BaseDestination
{
    override val route = "entries/index"
    override val text = "概览"
    const val idArg = "entry_id"
    val routeWithArgs = "${route}/{${idArg}}"
    val arguments = listOf(
        navArgument(idArg) { type = NavType.IntType }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "cngal://$route/{$idArg}" }
    )
}

//关联词条卡片列表
object EntryCardGroupDestination : BaseDestination
{
    override val route = "entries/detail/entry_card/"
    override val text = "概览"
    const val titleArg = "detail_title"
    const val dataArg = "detail_data"
    val routeWithArgs = "${route}/{${titleArg}}/{${dataArg}}"
    val arguments = listOf(
        navArgument(titleArg) { type = NavType.StringType },
        navArgument(titleArg) { type = NavType.StringType }
    )
    val deepLinks = listOf(
        navDeepLink {
            uriPattern = "cngal://$route/{$titleArg}/{${dataArg}}"
        }
    )
}


//关联词条角色卡片列表
object RoleCardGroupDestination : BaseDestination
{
    override val route = "entries/detail/role_card/"
    override val text = "概览"
    const val titleArg = "detail_title"
    const val dataArg = "detail_data"
    val routeWithArgs = "${route}/{${titleArg}}/{${dataArg}}"
    val arguments = listOf(
        navArgument(titleArg) { type = NavType.StringType },
        navArgument(titleArg) { type = NavType.StringType }
    )
    val deepLinks = listOf(
        navDeepLink {
            uriPattern = "cngal://$route/{$titleArg}/{${dataArg}}"
        }
    )
}


//单个文章详情页
object SingleArticleDestination : BaseDestination
{
    override val route = "articles/index"
    override val text = "概览"
    const val idArg = "article_id"
    val routeWithArgs = "${route}/{${idArg}}"
    val arguments = listOf(
        navArgument(idArg) { type = NavType.LongType }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "cngal://$route/{$idArg}" }
    )
}


//关联词条卡片列表
object ArticleCardGroupDestination : BaseDestination
{
    override val route = "articles/detail/article_card/"
    override val text = "概览"
    const val titleArg = "detail_title"
    const val dataArg = "detail_data"
    val routeWithArgs = "${route}/{${titleArg}}/{${dataArg}}"
    val arguments = listOf(
        navArgument(titleArg) { type = NavType.StringType },
        navArgument(titleArg) { type = NavType.StringType }
    )
    val deepLinks = listOf(
        navDeepLink {
            uriPattern = "cngal://$route/{$titleArg}/{${dataArg}}"
        }
    )
}

val CnGalOverviewScreens = listOf(HomeDestination, ExploreDestination, SquareDestination)