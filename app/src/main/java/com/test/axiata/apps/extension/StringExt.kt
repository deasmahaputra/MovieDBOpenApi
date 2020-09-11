package com.test.axiata.apps.extension

fun String.takeIfNotEmpty(defValue: String): String {
    return if (this.isNotEmpty()) {
        this
    } else {
        defValue
    }
}