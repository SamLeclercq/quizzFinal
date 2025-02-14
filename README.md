🎮 Quiz Rogue-like
Un jeu de quiz roguelike où chaque partie est différente, avec une progression dynamique et des jokers pour aider les joueurs à avancer.

📜 Description
Ce projet est un jeu rogue-like de quiz développé en Kotlin avec Jetpack Compose.
Les joueurs doivent répondre à une série de questions en progressant sur une carte dynamique. Chaque partie est différente grâce à une génération de chemins aléatoires.

🧠 Réponds aux questions pour avancer sur la carte.
🗺️ Explore une carte générée dynamiquement, avec des chemins différents à chaque run.
🎭 Utilise des jokers pour t’aider (50/50, Skip, Double Tap).
🔥 Bats le boss final en répondant correctement à une série de questions.
🏆 Accumule des streaks pour débloquer des bonus et voir jusqu’où tu peux aller !
🚀 Fonctionnalités principales
✔️ Cartes dynamiques : Chaque partie a un chemin différent.
✔️ Plusieurs niveaux de difficulté : Le jeu s’adapte en difficulté en fonction de la progression.
✔️ Système de Jokers :

🔥 50/50 : Supprime deux mauvaises réponses.
⏩ Skip : Change la question actuelle.
🔄 Double Tap : Permet une deuxième tentative en cas d’erreur.
✔️ Boss final : Un enchaînement de questions à répondre pour terminer un run.
✔️ Système de streaks : Gagne des runs d’affilée pour accumuler des récompenses.
🛠️ Technologies utilisées
Kotlin (Jetpack Compose)
Jetpack Navigation pour la gestion des écrans.
Jetpack Compose Canvas pour dessiner la carte et les connexions entre les nodes.
Gestion d’état avec GameState pour suivre la progression.
Architecture basée sur un modèle GameState pour gérer les données et la progression.
📂 Structure du projet
bash
Copier
Modifier
📦 quiz-roguelike
 ┣ 📂 src/main/kotlin/com/worldline/quiz
 ┃ ┣ 📂 core
 ┃ ┃ ┣ 📜 GameState.kt        # Gère les données globales du jeu
 ┃ ┃ ┣ 📜 NodeState.kt        # États des noeuds de la carte
 ┃ ┃ ┗ 📜 App.kt              # Gère la navigation et la structure de l'application
 ┃ ┣ 📂 data
 ┃ ┃ ┣ 📜 Question.kt         # Modèle des questions et réponses
 ┃ ┃ ┣ 📜 DataRepository.kt   # Stocke et génère les questions
 ┃ ┃ ┣ 📜 Joker.kt            # Définition des différents jokers
 ┃ ┃ ┗ 📜 JokerManager.kt     # Gère l'utilisation des jokers
 ┃ ┣ 📂 ui
 ┃ ┃ ┣ 📜 MapScreen.kt        # Affichage de la carte dynamique
 ┃ ┃ ┣ 📜 QuestionScreen.kt   # Écran de question avec validation
 ┃ ┃ ┣ 📜 BossScreen.kt       # Écran du boss final
 ┃ ┃ ┣ 📜 VictoryScreen.kt    # Écran de victoire après un run réussi
 ┃ ┃ ┣ 📜 GameOverScreen.kt   # Écran de game over en cas d’échec
 ┃ ┃ ┗ 📜 StartScreen.kt      # Écran de démarrage
🎮 Comment jouer ?
1️⃣ Lancer le projet
Ouvre Android Studio.
Clone le projet :
bash
Copier
Modifier
git clone https://github.com/ton-repo/quiz-roguelike.git
Lancer l’application sur un émulateur ou un appareil physique.
2️⃣ Règles du jeu
Commence avec un nœud disponible en bas de la carte.
Réponds aux questions pour débloquer les nœuds suivants.
Tu peux utiliser des jokers pour t’aider.
Une mauvaise réponse signifie Game Over !
Arrive au Boss final et réponds correctement aux questions pour gagner la partie.
🛠️ Améliorations futures
🚧 Fonctionnalités en cours de développement :

📊 Un tableau des scores pour voir les meilleures streaks.
🌍 Mode multijoueur pour jouer contre d’autres joueurs.
🔄 Plus de variabilité dans les cartes et les niveaux de difficulté.
👨‍💻 Contribuer
Envie d’améliorer le projet ? Voici comment contribuer :

Fork le projet 🍴
Crée une branche (feature/ma-feature)
Ajoute tes modifications (git commit -m "Ajout d'une nouvelle fonctionnalité")
Pousse la branche (git push origin feature/ma-feature)
Crée une Pull Request ✅
📜 Licence
Ce projet est sous licence MIT. Tu peux l’utiliser librement et proposer des améliorations.
