package com.cngal.app.compose.article.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cngal.app.compose.article.shared.ArticleCard
import com.cngal.app.compose.entry.shared.EntryCard
import com.cngal.app.compose.shared.TitleBar
import com.cngal.app.model.article.ArticleCardModel
import com.cngal.app.model.entry.EntryCardModel

@Composable
fun ArticleCardGroupScreen(title: String, model: List<ArticleCardModel>, onNav: (String) -> Unit)
{
    Scaffold(
        topBar = {
            TitleBar(title = title,  onBack = {})
        },
        content = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(0.dp,0.dp,0.dp,12.dp),
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 12.dp)
            ) {
                items(items = model)  { item ->
                    ArticleCard(item,true, onNav)
                }
            }
        }
    )
}