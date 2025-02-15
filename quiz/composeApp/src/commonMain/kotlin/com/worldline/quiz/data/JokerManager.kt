package com.worldline.quiz.data

import com.worldline.quiz.core.GameState

object JokerManager {

   
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
                GameState.allowDoubleTap = true
            }
        }
        GameState.useJoker(joker) 
    }

 
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
                val newBossQuestion = GameState.getBossQuestion() 
                updateQuestion(newBossQuestion)
            }
            JokerType.DOUBLE_TAP -> {
                GameState.allowDoubleTap = true
            }
        }
        GameState.useJoker(joker)
    }

    fun getJokerEffect(joker: JokerType): String {
        return when (joker) {
            JokerType.FIFTY_FIFTY -> "Supprime deux mauvaises réponses"
            JokerType.SKIP -> "Passe la question sans pénalité"
            JokerType.DOUBLE_TAP -> "Permet une deuxième tentative en cas d'erreur"
        }
    }

    fun getJokerEmoji(joker: JokerType): String {
        return when (joker) {
            JokerType.FIFTY_FIFTY -> "❓"
            JokerType.SKIP -> "⏩"
            JokerType.DOUBLE_TAP -> "🔄"
        }
    }

  
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
