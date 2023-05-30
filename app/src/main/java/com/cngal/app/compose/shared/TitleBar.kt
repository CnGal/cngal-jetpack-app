package com.cngal.app.compose.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.OpenInBrowser
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.zIndex


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleBar(
    title: String?,
    onBack: () -> Unit,
    onClickOpenInBrowser: () -> Unit,
    onClickLink: () -> Unit,
    onClickShare: () -> Unit,
    showActions: Boolean = true
)
{
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            if (!title.isNullOrBlank())
            {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "返回"
                )
            }
        },
        actions = {
            if (showActions)
            {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "更多"
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("浏览器打开") },
                        onClick = {
                            onClickOpenInBrowser()
                            expanded = false
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.OpenInBrowser,
                                contentDescription = null
                            )
                        })
                    DropdownMenuItem(
                        text = { Text("复制链接") },
                        onClick = {
                            onClickLink()
                            expanded = false
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Link,
                                contentDescription = null
                            )
                        })

                    DropdownMenuItem(
                        text = { Text("复制分享文案") },
                        onClick = {
                            onClickShare()
                            expanded = false
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Share,
                                contentDescription = null
                            )
                        })
                }
            }

        },
        modifier = Modifier.zIndex(1f)
    )
}