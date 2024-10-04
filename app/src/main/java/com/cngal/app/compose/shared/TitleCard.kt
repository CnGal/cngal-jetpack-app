package com.cngal.app.compose.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TitleCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    title: String,
    expandable: Boolean = false,
    linkText: String? = "更多",
    onClickLink: (() -> Unit)?=null
)
{

    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .heightIn(
                    max = if (expandable && !expanded)
                    {
                        200.dp
                    }
                    else
                    {
                        Dp.Unspecified
                    }
                )

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if (!linkText.isNullOrBlank() && onClickLink != null)
                {
                    TextButton(onClick = { onClickLink() }) {
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text(
                            text = linkText,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )

                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = linkText)
                    }
                }

            }
            content()


        }
        if (expandable && !expanded)
        {
            TextButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded)
                    {
                        "展开"
                    }
                    else
                    {
                        "收缩"
                    }
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = if (expanded)
                    {
                        "收缩"
                    }
                    else
                    {
                        "展开"

                    },
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            }
        }
    }
}
