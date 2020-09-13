package com.test.axiata.apps.extension

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

inline fun tryCatch(throwableFunction: () -> Unit) {
    try {
        throwableFunction()
    } catch (t: Throwable) {
        Log.d("error", "error navigate")
    }
}

fun NavController.navigateSafe(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navExtras: Navigator.Extras? = null
) {
    val action = currentDestination?.getAction(resId) ?: graph.getAction(resId)
    if (action != null && currentDestination?.id != action.destinationId) {
        tryCatch {
            navigate(resId, args, navOptions, navExtras)
        }
    }
}