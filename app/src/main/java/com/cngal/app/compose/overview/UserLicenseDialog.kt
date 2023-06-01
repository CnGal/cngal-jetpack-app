package com.cngal.app.compose.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.cngal.app.MainActivity
import com.cngal.app.helper.appContext
import com.cngal.app.helper.openNewTabWindow
import kotlin.system.exitProcess

@Composable
fun UserLicenseDialog(onConfirm:()->Unit,onDismiss:()->Unit,)
{
    AlertDialog(
        onDismissRequest = { },
        icon = { Icon(Icons.Filled.Gavel, contentDescription = null) },
        title = {
            Text(text = "CnGal资料站隐私协议")
        },
        text = {
            Column {
                Text(
                    "请你务必审慎阅读、充分理解下述条款，包括但不限于：" +
                            "为了能给你提供更好更个性化的服务，我们会收集你的设备信息、操作日志等基础信息。"
                )
                val annotatedString = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        append("请你阅读")
                    }

                    pushStringAnnotation(
                        tag = "link",
                        annotation = "https://www.cngal.org/privacy"
                    )
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            textDecoration = TextDecoration.Underline,
                            fontSize = 14.sp,
                        )
                    ) {
                        append("《CnGal资料站隐私协议》")
                    }
                    pop()
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        append("了解更详细的信息，如你同意，请点击“同意”开始接受我们的服务。")
                    }
                }


                ClickableText(
                    text = annotatedString,
                    onClick = { offset ->
                        annotatedString.getStringAnnotations(start = offset, end = offset)
                            .firstOrNull()?.let {
                                openNewTabWindow(it.item, appContext)
                            }
                    })
            }

        },
        confirmButton = {
            TextButton(
                onClick = {onConfirm()}
            ) {
                Text("同意")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("拒绝")
            }
        }
    )
}