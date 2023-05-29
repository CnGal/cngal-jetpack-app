package com.cngal.app.compose.entry.single

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.SingleEntryDestination
import com.cngal.app.compose.entry.shared.EntryCard
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.model.entry.EntryCardModel
import com.cngal.app.model.entry.RoleCardModel

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
    onNav: (String) -> Unit
)
{
    if (roles.isNotEmpty())
    {
        TitleCard(title = "角色", link = "entries/roles/", content = {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(items = roles) { item ->
                    RoleCard(item, onNav)
                }
            }
        })
    }

    if (castWorks.isNotEmpty())
    {
        TitleCard(title = "配音作品", link = "entries/roles/", content = {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(items = castWorks) { item ->
                    EntryCard(item, onNav)
                }
            }
        })
    }

    if (productionGroupWorks.isNotEmpty())
    {
        TitleCard(title = "制作作品", link = "entries/roles/", content = {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(items = productionGroupWorks) { item ->
                    EntryCard(item, onNav)
                }
            }
        })
    }

    if (publisherWorks.isNotEmpty())
    {
        TitleCard(title = "发行作品", link = "entries/roles/", content = {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(items = publisherWorks) { item ->
                    EntryCard(item, onNav)
                }
            }
        })
    }

    if (participationWorks.isNotEmpty())
    {
        TitleCard(title = "参与作品", link = "entries/roles/", content = {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(items = participationWorks) { item ->
                    EntryCard(item, onNav)
                }
            }
        })
    }

    if (appreciatedParticWorks.isNotEmpty())
    {
        TitleCard(title = "特别感谢", link = "entries/roles/", content = {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(items = appreciatedParticWorks) { item ->
                    EntryCard(item, onNav)
                }
            }
        })
    }

    if (games.isNotEmpty())
    {
        TitleCard(title = "相关游戏", link = "entries/roles/", content = {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(items = games) { item ->
                    EntryCard(item, onNav)
                }
            }
        })
    }

    if (groups.isNotEmpty())
    {
        TitleCard(title = "相关组织", link = "entries/roles/", content = {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(items = groups) { item ->
                    EntryCard(item, onNav)
                }
            }
        })
    }

    if (staffs.isNotEmpty())
    {
        TitleCard(title = "相关STAFF", link = "entries/roles/", content = {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(items = staffs) { item ->
                    EntryCard(item, onNav)
                }
            }
        })
    }

    if (relevanceRoles.isNotEmpty())
    {
        TitleCard(title = "相关角色", link = "entries/roles/", content = {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(items = relevanceRoles) { item ->
                    EntryCard(item, onNav)
                }
            }
        })
    }

}

@Composable
fun RoleCard(model: RoleCardModel, onNav: (String) -> Unit)
{
    Card(modifier = Modifier
        .width(80.dp)
        .fillMaxHeight()
        .clickable {
            onNav("${SingleEntryDestination.route}/${model.id}")
        }
    ) {
        Column {
            AsyncImage(
                model = model.mainImage,
                contentDescription = model.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f / 1f)
                    .clip(RoundedCornerShape(12.dp))
            )
            Text(
                text = model.name,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
            )


        }
    }
}