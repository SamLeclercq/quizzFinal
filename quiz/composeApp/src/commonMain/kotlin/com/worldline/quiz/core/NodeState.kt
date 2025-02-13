package com.worldline.quiz.core

enum class NodeState {
    LOCKED,      // Non accessible
    AVAILABLE,   // Cliquable (cercles verts)
    SELECTED,    // Choisi (remplissage vert)
    SKIPPED      // Ignoré (remplissage gris)
}
