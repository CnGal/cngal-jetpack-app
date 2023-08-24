package com.cngal.app.compose.entry.single

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
import androidx.compose.ui.unit.sp
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.model.entry.InformationModel
import com.cngal.app.model.entry.StaffModel

@Composable
fun InformationCard(
    information: List<InformationModel>,
    productionGroups: List<StaffModel>,
    publishers: List<StaffModel>,
    onNav: (String) -> Unit
)
{
    if (information.isEmpty() && productionGroups.isEmpty() && publishers.isEmpty())
    {
        return
    }

    TitleCard(title = "基本信息",linkText = null, onClickLink = {}, content = {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .padding(horizontal = 12.dp)
        ) {
            if (productionGroups.isNotEmpty())
            {
                StaffPositionGroupCard("制作组", productionGroups, onNav, 16.sp)
            }
            if (publishers.isNotEmpty())
            {
                StaffPositionGroupCard("发行商", publishers, onNav, 16.sp)
            }
            information.forEach { item ->
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,color=MaterialTheme.colorScheme.onBackground)) {
                            append(item.name)
                            append("：")
                        }
                        append(item.value)
                    }
                )
            }
        }
    })
}
