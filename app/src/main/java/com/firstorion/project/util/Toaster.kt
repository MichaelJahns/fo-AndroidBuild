package com.firstorion.project.util

import android.content.Context
import android.widget.Toast

object Toaster {
    fun futureToast(context: Context){
        Toast.makeText(context, "Future Feature", Toast.LENGTH_SHORT)
    }
}