package com.cngal.app.compose.entry.single

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SingleEntryScreen(id: Int?)
{
    Text(text = id.toString())
}