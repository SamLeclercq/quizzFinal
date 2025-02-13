package com.worldline.quiz.core

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Bienvenue sur Quiz Rogue, ${platform.name}!"
    }
}
