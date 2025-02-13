package com.worldline.quiz.core

interface Platform {
    val name: String
}

// Déclaration `expect` pour Kotlin Multiplatform
expect fun getPlatform(): Platform
