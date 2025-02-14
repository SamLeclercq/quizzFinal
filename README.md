# 🎮 Quiz Rogue-like

Un jeu de quiz rogue-like où chaque partie est différente, avec une progression dynamique et des jokers pour aider les joueurs à avancer.

## 📜 Description

Ce projet est un rogue-like de quiz développé en **Kotlin** avec **Jetpack Compose**.  
Le joueur progresse sur une **carte dynamique** en répondant correctement aux questions.

- 🧠 **Réponds aux questions** pour avancer sur la carte.
- 🗺️ **Explore un parcours généré dynamiquement**, différent à chaque partie.
- 🎭 **Utilise des jokers** pour t’aider (**50/50**, **Skip**, **Double Tap**).
- 🔥 **Affronte le boss final** en répondant à une série de questions.
- 🏆 **Accumule des streaks** pour débloquer des bonus et aller toujours plus loin !

---

## 🚀 Fonctionnalités principales

✔️ **Cartes générées dynamiquement** à chaque partie.  
✔️ **Système de streaks** : enchaîne les runs sans perdre pour obtenir des récompenses.  
✔️ **Plusieurs niveaux de difficulté**, les questions deviennent plus dures au fur et à mesure.  
✔️ **Système de Jokers** pour aider le joueur :  
   - ❓ *50/50* : Supprime deux mauvaises réponses.  
   - ⏩ *Skip* : Change la question actuelle.  
   - 🔄 *Double Tap* : Permet une deuxième tentative en cas d’erreur.  
✔️ **Affrontement contre un Boss final**, avec plusieurs questions à réussir.  

---

## 🛠️ Technologies utilisées

- **Kotlin** (Jetpack Compose)
- **Jetpack Navigation** pour gérer les écrans.
- **Jetpack Compose Canvas** pour afficher la carte et les connexions.
- **Gestion d’état avec `GameState`** pour suivre la progression du joueur.
- **Architecture basée sur un modèle `GameState`** pour gérer les données et la logique du jeu.

---
