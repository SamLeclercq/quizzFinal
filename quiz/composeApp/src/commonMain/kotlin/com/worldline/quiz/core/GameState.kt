package com.worldline.quiz.core

import com.worldline.quiz.data.JokerType
import com.worldline.quiz.data.Question
import com.worldline.quiz.data.DataRepository

object GameState {
    var nodeStates = List(15) { if (it == 0) NodeState.AVAILABLE else NodeState.LOCKED }

    // La difficulté de chaque nœud est générée aléatoirement, sauf pour le premier et le boss.
    var nodeDifficulties: List<String> = generateNodeDifficulties()

    var score = 0
    var collectedJokers = mutableListOf<JokerType>()
    var streakCount = 0
    var lastUnlockedJoker: JokerType? = null
    var allowDoubleTap = false  // Pour gérer le Joker Double Tap
    var currentNodeId = 0  // 🔥 Suivi du nœud actuel

    private var currentQuestions = mutableMapOf<Int, Question>()
    private var bossQuestionList = mutableListOf<Question>()  // Questions pour le boss
    private var bossQuestionIndex = 0 // Suivi de la progression dans le combat du boss

    // 🎯 Thème sélectionné par le joueur
    var selectedTheme: String = "Général"  // Définir un thème par défaut

    val diamondLayers = listOf(
        listOf(0),          // Layer 0
        listOf(1, 2),       // Layer 1
        listOf(3, 4, 5),    // Layer 2
        listOf(6, 7, 8),    // Layer 3
        listOf(9, 10, 11),  // Layer 4
        listOf(12, 13),     // Layer 5
        listOf(14)          // Layer 6 (boss)
    )

    init {
        generateQuestions()
    }

    /**
     * Génère aléatoirement la difficulté pour chaque nœud.
     * Pour les nœuds 1 à 13, la difficulté est tirée au hasard parmi "Facile", "Moyenne" et "Difficile".
     * Le premier nœud (0) sera toujours "Facile" et le boss (14) sera toujours "Difficile".
     */
    private fun generateNodeDifficulties(): List<String> {
        val difficulties = listOf("Facile", "Moyenne", "Difficile")
        return List(15) { index ->
            when (index) {
                0 -> "Facile"
                14 -> "Difficile"
                else -> difficulties.random()
            }
        }
    }

    /**
     * Retourne la question pour le nœud donné en se basant sur la difficulté assignée à ce nœud et le thème sélectionné.
     */
    fun getQuestionForNode(nodeId: Int): Question {
        currentNodeId = nodeId  // 🔥 Mettre à jour le nœud actuel
        val difficulty = nodeDifficulties[nodeId]
        return currentQuestions[nodeId] ?: DataRepository.getRandomQuestion(selectedTheme, difficulty).also {
            currentQuestions[nodeId] = it
        }
    }

    fun getBossQuestions(): List<Question> {
        return List(3) { DataRepository.getRandomQuestion(selectedTheme, "Difficile") }
    }

    /**
     * Génère les questions pour chaque nœud en fonction de la difficulté assignée et du thème sélectionné.
     */
    fun generateQuestions() {
        currentQuestions.clear()
        for (nodeId in nodeStates.indices) {
            currentQuestions[nodeId] = DataRepository.getRandomQuestion(selectedTheme, nodeDifficulties[nodeId])
        }
        generateBossQuestions()
    }

    fun generateBossQuestions() {
        bossQuestionList.clear()
        repeat(3) {
            bossQuestionList.add(DataRepository.getRandomQuestion(selectedTheme, "Difficile"))
        }
        bossQuestionIndex = 0
    }

    fun getBossQuestion(): Question {
        return if (bossQuestionIndex < bossQuestionList.size) {
            bossQuestionList[bossQuestionIndex]
        } else {
            bossQuestionList.last()
        }
    }

    fun advanceBossQuestion(): Boolean {
        return if (bossQuestionIndex < bossQuestionList.size - 1) {
            bossQuestionIndex++
            false
        } else {
            true
        }
    }

    fun getRandomQuestion(): Question {
        return DataRepository.getRandomQuestion(selectedTheme, "Difficile")
    }

    fun reset() {
        nodeStates = List(15) { if (it == 0) NodeState.AVAILABLE else NodeState.LOCKED }
        nodeDifficulties = generateNodeDifficulties()
        score = 0
        collectedJokers.clear()
        lastUnlockedJoker = null
        allowDoubleTap = false  // Réinitialisation du Double Tap
        streakCount = 0
        generateQuestions()
    }

    fun resetForNewRun() {
        nodeStates = List(15) { if (it == 0) NodeState.AVAILABLE else NodeState.LOCKED }
        nodeDifficulties = generateNodeDifficulties()
        score = 0
        allowDoubleTap = false  // Réinitialisation du Double Tap
        generateQuestions()
    }

    fun setTheme(theme: String) {
        selectedTheme = theme
        generateQuestions() // 🔥 Regénérer les questions avec le nouveau thème
    }

    fun unlockRandomJoker() {
        val availableJokers = JokerType.values().filter { it !in collectedJokers }
        if (availableJokers.isNotEmpty()) {
            val newJoker = availableJokers.random()
            collectedJokers.add(newJoker)
            lastUnlockedJoker = newJoker
        }
    }

    fun unlockJoker(joker: JokerType) {
        if (joker !in collectedJokers) {
            collectedJokers.add(joker)
            lastUnlockedJoker = joker
        }
    }

    fun useJoker(joker: JokerType) {
        collectedJokers.remove(joker)
        lastUnlockedJoker = null
        when (joker) {
            JokerType.DOUBLE_TAP -> allowDoubleTap = true
            JokerType.SKIP -> {
                val newQuestion = getRandomQuestion()  // ✅ Correction ici
                bossQuestionList[bossQuestionIndex] = newQuestion
            }
            else -> {}
        }
    }
}
