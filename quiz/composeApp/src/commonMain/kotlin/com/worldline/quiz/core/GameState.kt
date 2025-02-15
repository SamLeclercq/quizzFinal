package com.worldline.quiz.core

import com.worldline.quiz.data.JokerType
import com.worldline.quiz.data.Question
import com.worldline.quiz.data.DataRepository

object GameState {
    var nodeStates = List(15) { if (it == 0) NodeState.AVAILABLE else NodeState.LOCKED }

    var nodeDifficulties: List<String> = generateNodeDifficulties()

    var score = 0
    var collectedJokers = mutableListOf<JokerType>()
    var streakCount = 0
    var lastUnlockedJoker: JokerType? = null
    var allowDoubleTap = false 
    var currentNodeId = 0 

    private var currentQuestions = mutableMapOf<Int, Question>()
    private var bossQuestionList = mutableListOf<Question>() 
    private var bossQuestionIndex = 0 

    var selectedTheme: String = "Général"  

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

  
    fun getQuestionForNode(nodeId: Int): Question {
        currentNodeId = nodeId  // 🔥 Mettre à jour le nœud actuel
        val difficulty = nodeDifficulties[nodeId]
        return currentQuestions[nodeId] ?: DataRepository.getRandomQuestion(selectedTheme, difficulty).also {
            currentQuestions[nodeId] = it
        }
    }

    fun getBossQuestions(): List<Question> {
        return bossQuestionList.shuffled().take(3)
    }


   
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
        allowDoubleTap = false 
        streakCount = 0
        generateQuestions()
    }

    fun resetForNewRun() {
        nodeStates = List(15) { if (it == 0) NodeState.AVAILABLE else NodeState.LOCKED }
        nodeDifficulties = generateNodeDifficulties()
        score = 0
        allowDoubleTap = false  
        generateQuestions()
    }

    fun setTheme(theme: String) {
        selectedTheme = theme
        generateQuestions()
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
                val newQuestion = getRandomQuestion() 
                bossQuestionList[bossQuestionIndex] = newQuestion
            }
            else -> {}
        }
    }
}
