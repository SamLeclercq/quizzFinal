package com.worldline.quiz.data

data class Question(
    val id: Int,
    val text: String,
    val correctAnswerId: Int,
    val answers: List<Answer>
)

data class Answer(
    val id: Int,
    val text: String,
    val hidden: Boolean = false  // ✅ Ajout d'un champ pour cacher les mauvaises réponses (50/50)
)
