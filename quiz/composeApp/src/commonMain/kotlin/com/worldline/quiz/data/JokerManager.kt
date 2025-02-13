package com.worldline.quiz.data

import com.worldline.quiz.core.GameState

object JokerManager {

    /**
     * Applique l'effet du Joker sélectionné sur une question normale.
     */
    fun useJoker(
        joker: JokerType,
        question: Question,
        updateQuestion: (Question) -> Unit,
        onAnswerCorrect: () -> Unit
    ) {
        when (joker) {
            JokerType.FIFTY_FIFTY -> {
                updateQuestion(removeTwoWrongAnswers(question))
            }
            JokerType.SKIP -> {
                val nodeId = GameState.currentNodeId
                if (nodeId in GameState.nodeDifficulties.indices) {
                    val newQuestion = GameState.getRandomQuestion()
                    updateQuestion(newQuestion)
                } else {
                    println("⚠ Erreur: ID du nœud invalide ($nodeId)")
                }
            }
            JokerType.DOUBLE_TAP -> {
                GameState.allowDoubleTap = true // ✅ Active le mode double tentative
            }
        }
        GameState.useJoker(joker)  // ✅ Supprime le Joker après utilisation
    }

    /**
     * Applique l'effet du Joker sélectionné sur une question de boss.
     */
    fun useJokerForBoss(
        joker: JokerType,
        question: Question,
        updateQuestion: (Question) -> Unit
    ) {
        when (joker) {
            JokerType.FIFTY_FIFTY -> {
                updateQuestion(removeTwoWrongAnswers(question))
            }
            JokerType.SKIP -> {
                val newBossQuestion = GameState.getBossQuestion() // ✅ Suppression du paramètre inutile
                updateQuestion(newBossQuestion)
            }
            JokerType.DOUBLE_TAP -> {
                GameState.allowDoubleTap = true
            }
        }
        GameState.useJoker(joker)  // ✅ Supprime le Joker après utilisation
    }

    /**
     * Retourne la description de l'effet du Joker.
     */
    fun getJokerEffect(joker: JokerType): String {
        return when (joker) {
            JokerType.FIFTY_FIFTY -> "Supprime deux mauvaises réponses"
            JokerType.SKIP -> "Passe la question sans pénalité"
            JokerType.DOUBLE_TAP -> "Permet une deuxième tentative en cas d'erreur"
        }
    }

    /**
     * Retourne l'emoji correspondant au Joker.
     */
    fun getJokerEmoji(joker: JokerType): String {
        return when (joker) {
            JokerType.FIFTY_FIFTY -> "❓"
            JokerType.SKIP -> "⏩"
            JokerType.DOUBLE_TAP -> "🔄"
        }
    }

    /**
     * Supprime deux mauvaises réponses aléatoires pour le Joker 50/50.
     */
    private fun removeTwoWrongAnswers(question: Question): Question {
        val incorrectAnswers = question.answers.filter { it.id != question.correctAnswerId }
            .shuffled()
            .take(2)

        return question.copy(
            answers = question.answers.map {
                if (it in incorrectAnswers) it.copy(hidden = true) else it
            }
        )
    }
}
