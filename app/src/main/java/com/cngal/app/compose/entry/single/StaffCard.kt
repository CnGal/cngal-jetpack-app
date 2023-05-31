package com.cngal.app.compose.entry.single

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.cngal.app.SingleEntryDestination
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.model.entry.StaffGroupModel
import com.cngal.app.model.entry.StaffModel

@Composable
fun StaffCard(staffs: List<StaffGroupModel>, onNav: (String) -> Unit)
{
    if (staffs.isEmpty())
    {
        return
    }
    TitleCard(title = "STAFF", expandable = true, linkText = null, onClickLink = {},content = {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(horizontal = 12.dp)
        ) {
            staffs.forEach { item ->
                StaffGroupCard(item, onNav)
            }
        }
    })
}

@Composable
fun StaffGroupCard(staff: StaffGroupModel, onNav: (String) -> Unit)
{
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        if (!staff.modifier.isNullOrBlank())
        {
            Text(
                text = staff.modifier,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
        }

        staff.content.forEach { item ->
            StaffPositionGroupCard(item.modifier, item.content, onNav)
        }
    }
}


@Composable
fun StaffPositionGroupCard(
    position: String?,
    staffs: List<StaffModel>,
    onNav: (String) -> Unit,
    fontSize: TextUnit = TextUnit.Unspecified
)
{
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontSize,
                color=MaterialTheme.colorScheme.onBackground
            )
        ) {
            append("${position}：")
        }
        staffs.forEach {
            if (staffs.indexOf(it) != 0)
            {
                withStyle(
                    style = SpanStyle(
                        fontSize = fontSize,
                        color=MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    append("、")
                }
            }
            if (it.id > 0)
            {
                pushStringAnnotation(
                    tag = it.id.toString(),
                    annotation = "${SingleEntryDestination.route}/${it.id}"
                )
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline,
                        fontSize = fontSize
                    )
                ) {
                    append(it.displayName)
                }
                pop()
            }
            else
            {
                withStyle(
                    style = SpanStyle(
                        fontSize = fontSize,
                        color=MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    append(it.displayName)
                }
            }
        }
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
