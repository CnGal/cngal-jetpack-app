package com.cngal.app.helper

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Build
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService

class ClipboardHelper
{
    companion object
    {

        fun textCopy(label: String,text: String)
        {
            val clipboardManager =
                getSystemService(appContext, ClipboardManager::class.java) as ClipboardManager
            // When setting the clip board text.
            val clip: ClipData = ClipData.newPlainText(label, text)
            clipboardManager.setPrimaryClip(clip)

            Toast.makeText(appContext, "已复制", Toast.LENGTH_LONG).show()
        }
    }
}