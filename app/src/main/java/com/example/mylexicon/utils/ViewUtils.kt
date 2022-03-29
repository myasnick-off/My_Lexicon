package com.example.mylexicon.utils

import android.view.View

// метод отображения View-компонента
fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

// метод скрытия View-компонента
fun View.hide(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}
