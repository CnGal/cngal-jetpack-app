package com.cngal.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Web
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
    override val text = "单个词条详情页"
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
    override val text = "关联词条卡片列表"
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
    override val text = "关联词条角色卡片列表"
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


//关联动态卡片列表
object NewsCardGroupDestination : BaseDestination
{
    override val route = "entries/detail/news_card/"
    override val text = "关联动态卡片列表"
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

//关联外部链接卡片列表
object OutlinkCardGroupDestination : BaseDestination
{
    override val route = "entries/detail/outlink_card/"
    override val text = "关联外部链接卡片列表"
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
    override val text = "单个文章详情页"
    const val idArg = "article_id"
    val routeWithArgs = "${route}/{${idArg}}"
    val arguments = listOf(
        navArgument(idArg) { type = NavType.LongType }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "cngal://$route/{$idArg}" }
    )
}


//关联文章卡片列表
object ArticleCardGroupDestination : BaseDestination
{
    override val route = "articles/detail/article_card/"
    override val text = "关联文章卡片列表"
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

//单个周边详情页
object SinglePeripheryDestination : BaseDestination
{
    override val route = "peripheries/index"
    override val text = "单个周边详情页"
    const val idArg = "periphery_id"
    val routeWithArgs = "${route}/{${idArg}}"
    val arguments = listOf(
        navArgument(idArg) { type = NavType.LongType }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "cngal://$route/{$idArg}" }
    )
}


//单个标签详情页
object SingleTagDestination : BaseDestination
{
    override val route = "tags/index"
    override val text = "单个标签详情页"
    const val idArg = "tag_id"
    val routeWithArgs = "${route}/{${idArg}}"
    val arguments = listOf(
        navArgument(idArg) { type = NavType.IntType }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "cngal://$route/{$idArg}" }
    )
}


//单个视频详情页
object SingleVideoDestination : BaseDestination
{
    override val route = "videos/index"
    override val text = "单个视频详情页"
    const val idArg = "video_id"
    val routeWithArgs = "${route}/{${idArg}}"
    val arguments = listOf(
        navArgument(idArg) { type = NavType.LongType }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "cngal://$route/{$idArg}" }
    )
}


//关联视频卡片列表
object VideoCardGroupDestination : BaseDestination
{
    override val route = "videos/detail/video_card/"
    override val text = "关联视频卡片列表"
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

//搜索
object SearchDestination : OverviewItemDestination
{
    override val icon = Icons.Filled.Search
    override val route = "search"
    override val text = "搜索"
}

//设置
object SettingDestination : OverviewItemDestination
{
    override val icon = Icons.Filled.Settings
    override val route = "setting"
    override val text = "设置"
}

//免费游戏
object FreeDestination : OverviewItemDestination
{
    override val icon = Icons.Filled.SportsEsports
    override val route = "tags/index/92"
    override val text = "免费游戏"
}


//打折游戏
object DiscountDestination : OverviewItemDestination
{
    override val icon = Icons.Filled.LocalOffer
    override val route = "https://www.cngal.org/discount"
    override val text = "折扣中"
}


//打折游戏
object WeeklyNewsDestination : OverviewItemDestination
{
    override val icon = Icons.Filled.Newspaper
    override val route = "https://www.cngal.org/weeklynews"
    override val text = "每周速报"
}

//时间线
object GameTimelineDestination : OverviewItemDestination
{
    override val icon = Icons.Filled.CalendarMonth
    override val route = "https://www.cngal.org/time"
    override val text = "时间线"
}


//CV专题页
object CVThematicDestination : OverviewItemDestination
{
    override val icon = Icons.Filled.Mic
    override val route = "https://www.cngal.org/time"
    override val text = "专题页"
}


//数据汇总
object DataDestination : OverviewItemDestination
{
    override val icon = Icons.Filled.Cloud
    override val route = "https://app.cngal.org/data"
    override val text = "数据汇总"
}

//关于我们
object AboutDestination : OverviewItemDestination
{
    override val icon = Icons.Filled.Info
    override val route = "https://www.cngal.org/about"
    override val text = "关于"
}


//关于我们
object WebsiteDestination : OverviewItemDestination
{
    override val icon = Icons.Filled.Web
    override val route = "https://www.cngal.org"
    override val text = "网站"
}




//打折游戏
object BirthdayDestination : OverviewItemDestination
{
    override val icon = Icons.Filled.Cake
    override val route = "https://www.cngal.org/birthday"
    override val text = "生日日历"
}


val CnGalOverviewScreens = listOf(HomeDestination, ExploreDestination, SquareDestination)

val CnGalLinkGroup = listOf(
    SearchDestination,
    FreeDestination,
    DiscountDestination,
    WeeklyNewsDestination,
    BirthdayDestination,
    GameTimelineDestination,
    CVThematicDestination,
    DataDestination,
    WebsiteDestination,
    SettingDestination
).chunked(5)