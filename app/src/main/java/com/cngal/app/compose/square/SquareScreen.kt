package com.cngal.app.compose.square


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cngal.app.viewmodel.home.SquareViewModel

@Composable
fun SquareScreen(viewModel: SquareViewModel = viewModel())
{
    val list by viewModel.list.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items = list) { item ->
            Text(text = item.toString())
        }
        item {
            Text(text = "正在刷新")
            LaunchedEffect(Unit) {
                viewModel.add()
            }
        }
    }
}
