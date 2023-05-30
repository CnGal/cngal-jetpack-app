package com.cngal.app.compose.entry.single

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cngal.app.ArticleCardGroupDestination
import com.cngal.app.EntryCardGroupDestination
import com.cngal.app.RoleCardGroupDestination
import com.cngal.app.compose.article.shared.ArticleCard
import com.cngal.app.compose.entry.shared.EntryCard
import com.cngal.app.compose.entry.shared.RoleCard
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.helper.JsonHelper
import com.cngal.app.model.article.ArticleCardModel
import com.cngal.app.model.entry.EntryCardModel
import com.cngal.app.model.entry.RoleCardModel
import java.net.URLEncoder

@Composable
fun RelevanceGroupCard(
    roles: List<RoleCardModel>,
    castWorks: List<EntryCardModel>,
    productionGroupWorks: List<EntryCardModel>,
    publisherWorks: List<EntryCardModel>,
    participationWorks: List<EntryCardModel>,
    appreciatedParticWorks: List<EntryCardModel>,
    games: List<EntryCardModel>,
    groups: List<EntryCardModel>,
    staffs: List<EntryCardModel>,
    relevanceRoles: List<EntryCardModel>,
    articles: List<ArticleCardModel>,
    name: String,
    onNav: (String) -> Unit
)
{
    if (roles.isNotEmpty())
    {

        TitleCard(
            title = "角色",
            onClickLink = {
                val title = URLEncoder.encode("${name}的角色", "utf-8")
                val json = JsonHelper.toJson(roles)
                onNav("${RoleCardGroupDestination.route}/${title}/${json}")
            },
            content = {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp),
                ) {
                    items(items = roles) { item ->
                        RoleCard(item, false, onNav)
                    }
                }
            }
        )
    }

    SingleRelevanceGroupCard(castWorks, name, "配音作品", onNav)
    SingleRelevanceGroupCard(productionGroupWorks, name, "制作作品", onNav)
    SingleRelevanceGroupCard(publisherWorks, name, "发行作品", onNav)
    SingleRelevanceGroupCard(participationWorks, name, "参与作品", onNav)

    SingleRelevanceGroupCard(appreciatedParticWorks, name, "特别感谢", onNav)
    SingleRelevanceGroupCard(games, name, "相关游戏", onNav)
    SingleRelevanceGroupCard(groups, name, "相关组织", onNav)
    SingleRelevanceGroupCard(staffs, name, "相关STAFF", onNav)
    SingleRelevanceGroupCard(relevanceRoles, name, "相关角色", onNav)

    if (articles.isNotEmpty())
    {

        TitleCard(
            title = "文章",
            onClickLink = {
                val title = URLEncoder.encode("${name}的文章", "utf-8")
                val json = JsonHelper.toJson(articles)
                onNav("${ArticleCardGroupDestination.route}/${title}/${json}")
            },
            content = {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp),
                ) {
                    items(items = articles) { item ->
                        ArticleCard(item, false, onNav)
                    }
                }
            }
        )
    }

}

@Composable
fun SingleRelevanceGroupCard(
    list: List<EntryCardModel>,
    name: String,
    groupName: String,
    onNav: (String) -> Unit
)
{
    if (list.isNotEmpty())
    {

        TitleCard(title = groupName, onClickLink = {
            val title = URLEncoder.encode("${name}的${groupName}", "utf-8")
            val json = JsonHelper.toJson(list)
            onNav("${EntryCardGroupDestination.route}/${title}/${json}")
        }, content = {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(items = list) { item ->
                    EntryCard(Modifier,item, false, onNav)
                }
            }
        })
    }
}


