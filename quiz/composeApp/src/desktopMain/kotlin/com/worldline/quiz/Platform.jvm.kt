package com.worldline.quiz.core  // ✅ Assure-toi que c'est bien `core`

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()
