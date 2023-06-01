package com.cngal.app.compose.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cngal.app.CnGalLinkGroup
import com.cngal.app.OverviewItemDestination

@Composable
@Preview(showBackground = true)
fun LinkGroupCardPreview()
{
    LinkGroupCard(onNav = {})
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LinkGroupCard(onNav: (String) -> Unit)
{
    Column(Modifier.padding(horizontal = 12.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {
        CnGalLinkGroup.forEach{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                it.forEach {
                    LinkCard(it, onNav)
                }
            }
        }

    }

}

@Composable
fun LinkCard(model: OverviewItemDestination, onNav: (String) -> Unit)
{
    Column(
        modifier= Modifier.clickable{onNav(model.route)},
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier.clip(CircleShape)
        ) {
            Icon(model.icon, contentDescription = null, Modifier.padding(14.dp).size(18.dp))
        }
        Text(
            text = model.text,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
    }


}