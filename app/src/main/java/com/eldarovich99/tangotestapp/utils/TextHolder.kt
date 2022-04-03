package com.eldarovich99.tangotestapp.utils

import android.content.Context
import androidx.annotation.StringRes

sealed class TextHolder {
    abstract fun toString(context: Context): String

    data class Resource(
        @StringRes val text: Int,
        val placeholders: List<Any> = emptyList()
    ) : TextHolder() {
        override fun toString(context: Context): String =
            context.resources.getString(text).format(*placeholders.toTypedArray())
    }

    data class Text(
        val text: String
    ) : TextHolder() {
        override fun toString(context: Context) = text
    }

    companion object {
        val EMPTY = Text("")
    }
}

fun String.toHolder() = TextHolder.Text(this)