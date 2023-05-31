package com.cngal.app.compose.video.single

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.model.entry.InformationContentModel


@Composable
fun InformationCard(
    information: List<InformationContentModel>
)
{
    if (information.isEmpty())
    {
        return
    }

    TitleCard(title = "基本信息", linkText = null, onClickLink = {}, content = {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .padding(horizontal = 12.dp)
        ) {
            information.forEach { item ->
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        ) {
                            append(item.displayName)
                            append("：")
                        }
                        append(item.displayValue)
                    }
                )
            }
        }
    })
}
