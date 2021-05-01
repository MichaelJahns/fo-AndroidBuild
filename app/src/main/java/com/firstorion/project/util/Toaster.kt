package com.firstorion.project.util

import android.content.Context
import android.widget.Toast

object Toaster {

    fun makeToast(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    fun futureToast(context: Context){
        Toast.makeText(context, "Future Feature", Toast.LENGTH_SHORT).show()
    }
}