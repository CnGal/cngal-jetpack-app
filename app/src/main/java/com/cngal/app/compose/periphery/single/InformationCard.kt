package com.cngal.app.compose.periphery.single

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.model.periphery.InformationContentModel
import com.cngal.app.model.periphery.InformationModel


@Composable
fun InformationCard(
    information: List<InformationContentModel>,
    saleLink: String?,
    onNav: (String) -> Unit
)
{
    if (information.isEmpty()&&saleLink.isNullOrBlank())
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
            if (!saleLink.isNullOrBlank())
            {
                val annotatedString = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        append("贩售链接：")
                    }

                    pushStringAnnotation(
                        tag = "link",
                        annotation = saleLink
                    )
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            textDecoration = TextDecoration.Underline,
                            fontSize = 16.sp,
                        )
                    ) {
                        append(saleLink)
                    }
                    pop()

                }


                ClickableText(
                    text = annotatedString,
                    onClick = { offset ->
                        annotatedString.getStringAnnotations(start = offset, end = offset)
                            .firstOrNull()?.let {
                                onNav(it.item)
                            }
                    })
            }
        }
    })
}
