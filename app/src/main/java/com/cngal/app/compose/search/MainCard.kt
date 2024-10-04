package com.cngal.app.compose.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Segment
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Segment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainCard(
    text: String,
    isSegment: Boolean,
    onTextChanged: (String) -> Unit,
    OnIsSegmentChanged: (Boolean) -> Unit,
    onKeyboardDone: () -> Unit
) {
    val kc = LocalSoftwareKeyboardController.current
    Column(
        Modifier.padding(12.dp, 0.dp, 12.dp, 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { onTextChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onKeyboardDone()
                    kc?.hide()
                }
            ),
            placeholder = {
                Text(text = "可以搜索哦~")
            },
            maxLines = 1,
            prefix = {
                Row {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "搜索"
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                }

            }
        )
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                if (isSegment) {
                    Button(onClick = { OnIsSegmentChanged(false) }, Modifier.height(30.dp)) {
                        Icon(
                            Icons.AutoMirrored.Filled.Segment,
                            contentDescription = null,
                            Modifier.size(16.dp)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text(
                            text = "段落", fontSize = 10.sp
                        )
                    }
                } else {
                    Button(onClick = { OnIsSegmentChanged(true) }, Modifier.height(30.dp)) {
                        Icon(Icons.Filled.Image, contentDescription = null, Modifier.size(16.dp))
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text(
                            text = "图片", fontSize = 10.sp
                        )

                    }
                }


                /*  Button(onClick = { }, Modifier.height(30.dp)) {
                      Icon(Icons.Filled.FilterAlt, contentDescription = null, Modifier.size(16.dp))
                      Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                      Text(
                          text = "过滤", fontSize = 10.sp
                      )
                  }*/
            }
        }
    }
}