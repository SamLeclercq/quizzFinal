package com.worldline.quiz

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.worldline.quiz.core.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "quiz",
    ) {
        App()
    }
}