
🎮 Quiz Rogue-like
Un jeu de quiz rogue-like où chaque partie est différente, avec une progression dynamique et des jokers pour aider les joueurs à avancer.

📜 Description
Ce projet est un rogue-like de quiz développé en Kotlin avec Jetpack Compose.
Le joueur progresse sur une carte dynamique en répondant correctement aux questions.

🧠 Réponds aux questions pour avancer sur la carte.
🗺️ Explore un parcours généré dynamiquement, différent à chaque partie.
🎭 Utilise des jokers pour t’aider (50/50, Skip, Double Tap).
🔥 Affronte le boss final en répondant à une série de questions.
🏆 Accumule des streaks pour débloquer des bonus et aller toujours plus loin !
🚀 Fonctionnalités principales
✔️ Cartes générées dynamiquement à chaque partie.
✔️ Système de streaks : enchaîne les runs sans perdre pour obtenir des récompenses.
✔️ Plusieurs niveaux de difficulté, les questions deviennent plus dures au fur et à mesure.
✔️ Système de Jokers pour aider le joueur :

❓ 50/50 : Supprime deux mauvaises réponses.
⏩ Skip : Change la question actuelle.
🔄 Double Tap : Permet une deuxième tentative en cas d’erreur. ✔️ Affrontement contre un Boss final, avec plusieurs questions à réussir.
🛠️ Technologies utilisées
Kotlin (Jetpack Compose)
Jetpack Navigation pour gérer les écrans.
Jetpack Compose Canvas pour afficher la carte et les connexions.
Gestion d’état avec GameState pour suivre la progression du joueur.
Architecture basée sur un modèle GameState pour gérer les données et la logique du jeu.
📂 Structure du projet
bash
Copier
Modifier
📦 quiz-roguelike
 ┣ 📂 src/main/kotlin/com/worldline/quiz
 ┃ ┣ 📂 core
 ┃ ┃ ┣ 📜 GameState.kt        # Gère les données globales du jeu
 ┃ ┃ ┣ 📜 NodeState.kt        # États des nœuds de la carte
 ┃ ┃ ┗ 📜 App.kt              # Gère la navigation et l'architecture de l'application
 ┃ ┣ 📂 data
 ┃ ┃ ┣ 📜 Question.kt         # Modèle des questions et réponses
 ┃ ┃ ┣ 📜 DataRepository.kt   # Génère et stocke les questions
 ┃ ┃ ┣ 📜 Joker.kt            # Définition des jokers
 ┃ ┃ ┗ 📜 JokerManager.kt     # Gestion des jokers et effets
 ┃ ┣ 📂 ui
 ┃ ┃ ┣ 📜 MapScreen.kt        # Affichage de la carte dynamique et gestion des connexions
 ┃ ┃ ┣ 📜 QuestionScreen.kt   # Écran de question avec interactions
 ┃ ┃ ┣ 📜 BossScreen.kt       # Combat contre le Boss final
 ┃ ┃ ┣ 📜 VictoryScreen.kt    # Écran de victoire après un run réussi
 ┃ ┃ ┣ 📜 GameOverScreen.kt   # Écran de game over
 ┃ ┃ ┗ 📜 StartScreen.kt      # Écran de démarrage du jeu
🎮 Comment jouer ?
1️⃣ Installation et exécution
Ouvre Android Studio.
Clone le projet :
bash
Copier
Modifier
git clone https://github.com/ton-repo/quiz-roguelike.git
Lance l’application sur un émulateur ou un appareil physique.
2️⃣ Règles du jeu
Commence en bas de la carte générée avec des chemins aléatoires.
Réponds correctement aux questions pour avancer.
Se tromper = Game Over, sauf si tu utilises un Joker.
Affronte le Boss final en répondant à plusieurs questions.
Continue ta streak en enchaînant les runs sans perdre pour gagner des bonus.
🛠️ Améliorations prévues
🚧 En développement :

📊 Un tableau des scores pour suivre les streaks des joueurs.
🌍 Mode multijoueur, affronte d’autres joueurs en temps réel.
🎲 Plus de variabilité dans les cartes et ajustement des niveaux de difficulté.
👨‍💻 Contribuer
Vous souhaitez améliorer le projet ? Voici comment faire :

Fork le repository 🍴
Crée une branche (feature/ma-feature)
Ajoute tes modifications (git commit -m "Ajout d'une nouvelle fonctionnalité")
Pousse la branche (git push origin feature/ma-feature)
Crée une Pull Request ✅
📜 Licence
Ce projet est sous licence MIT.
Tu peux l’utiliser librement et proposer des améliorations.
