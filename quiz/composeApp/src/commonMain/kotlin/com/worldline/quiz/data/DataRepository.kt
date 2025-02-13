package com.worldline.quiz.data

object DataRepository {
    private val questionPool = mapOf(

        "Star Wars" to mapOf(
            "Facile" to listOf(
                Question(1, "Quel est le nom du vaisseau de Han Solo ?", 1, listOf(
                    Answer(1, "Millennium Falcon"), Answer(2, "X-Wing"), Answer(3, "Slave I"), Answer(4, "Star Destroyer")
                )),
                Question(2, "Qui est le père de Luke Skywalker ?", 2, listOf(
                    Answer(1, "Obi-Wan Kenobi"), Answer(2, "Dark Vador"), Answer(3, "Yoda"), Answer(4, "Mace Windu")
                )),
                Question(3, "De quelle couleur est le sabre laser de Luke dans Le Retour du Jedi ?", 3, listOf(
                    Answer(1, "Rouge"), Answer(2, "Bleu"), Answer(3, "Vert"), Answer(4, "Violet")
                )),
                Question(4, "Comment s'appelle le droïde astromécano bleu et blanc ?", 4, listOf(
                    Answer(1, "C-3PO"), Answer(2, "BB-8"), Answer(3, "K-2SO"), Answer(4, "R2-D2")
                )),
                Question(5, "Qui est l'empereur dans la trilogie originale ?", 1, listOf(
                    Answer(1, "Palpatine"), Answer(2, "Dark Vador"), Answer(3, "Grand Moff Tarkin"), Answer(4, "Yoda")
                )),
                Question(6, "Quel est le vrai nom de Dark Vador ?", 2, listOf(
                    Answer(1, "Luke Skywalker"), Answer(2, "Anakin Skywalker"), Answer(3, "Obi-Wan Kenobi"), Answer(4, "Dooku")
                )),
                Question(7, "Quel est le premier film sorti en salle ?", 3, listOf(
                    Answer(1, "L'Empire contre-attaque"), Answer(2, "Le Retour du Jedi"), Answer(3, "Un nouvel espoir"), Answer(4, "La Menace fantôme")
                )),
                Question(8, "Qui forme Luke Skywalker aux arts Jedi après Obi-Wan Kenobi ?", 4, listOf(
                    Answer(1, "Mace Windu"), Answer(2, "Qui-Gon Jinn"), Answer(3, "Anakin Skywalker"), Answer(4, "Yoda")
                )),
                Question(9, "Quel est le surnom de l'Alliance Rebelle ?", 1, listOf(
                    Answer(1, "Les Rebelles"), Answer(2, "L'Empire Galactique"), Answer(3, "Les Mandaloriens"), Answer(4, "Le Premier Ordre")
                )),
                Question(10, "Qui est capturé par Jabba le Hutt dans Le Retour du Jedi ?", 2, listOf(
                    Answer(1, "Luke Skywalker"), Answer(2, "Han Solo"), Answer(3, "Obi-Wan Kenobi"), Answer(4, "Lando Calrissian")
                ))
            ),
            "Moyenne" to listOf(
                Question(11, "Qui a construit C-3PO ?", 1, listOf(
                    Answer(1, "Anakin Skywalker"), Answer(2, "Obi-Wan Kenobi"), Answer(3, "Yoda"), Answer(4, "Leia Organa")
                )),
                Question(12, "Quel est le nom du chasseur de primes qui capture Han Solo ?", 2, listOf(
                    Answer(1, "Cad Bane"), Answer(2, "Boba Fett"), Answer(3, "Greedo"), Answer(4, "Bossk")
                )),
                Question(13, "Sur quelle planète Obi-Wan affronte-t-il Anakin ?", 3, listOf(
                    Answer(1, "Endor"), Answer(2, "Naboo"), Answer(3, "Mustafar"), Answer(4, "Tatooine")
                )),
                Question(14, "Quel est le titre de Dark Sidious ?", 4, listOf(
                    Answer(1, "Grand Moff"), Answer(2, "Chancelier"), Answer(3, "Commandant"), Answer(4, "Empereur")
                )),
                Question(15, "Quel Jedi découvre Kamino dans L'Attaque des Clones ?", 1, listOf(
                    Answer(1, "Obi-Wan Kenobi"), Answer(2, "Qui-Gon Jinn"), Answer(3, "Anakin Skywalker"), Answer(4, "Mace Windu")
                ))
            ),
            "Difficile" to listOf(
                Question(16, "Quelle est la durée exacte d'une année sur Tatooine ?", 3, listOf(
                    Answer(1, "365 jours"), Answer(2, "500 jours"), Answer(3, "304 jours"), Answer(4, "421 jours")
                )),
                Question(17, "Quel est le nom complet du sabre laser de Dark Maul ?", 2, listOf(
                    Answer(1, "Sabre double standard"), Answer(2, "Sabre laser à double lame"), Answer(3, "Épée Sith"), Answer(4, "Bâton de force")
                )),
                Question(18, "Combien de Jedi survivent à l'Ordre 66 d'après les films ?", 1, listOf(
                    Answer(1, "2"), Answer(2, "5"), Answer(3, "10"), Answer(4, "7")
                )),
                Question(19, "Quelle substance est utilisée pour congeler Han Solo ?", 4, listOf(
                    Answer(1, "Plastacier"), Answer(2, "Glace Noire"), Answer(3, "Beskar"), Answer(4, "Carbonite")
                )),
                Question(20, "Quel est le numéro de cellule de la princesse Leia sur l'Étoile de la Mort ?", 3, listOf(
                    Answer(1, "1138"), Answer(2, "2187"), Answer(3, "327"), Answer(4, "4201")
                ))
            )
        ),
        "Lost Ark" to mapOf(
            "Facile" to listOf(
                Question(1, "Quel est le principal objectif du jeu Lost Ark ?", 1, listOf(
                    Answer(1, "Trouver l’Arche perdue"),
                    Answer(2, "Battre tous les boss sans mourir"),
                    Answer(3, "Construire une maison et adopter un chat"),
                    Answer(4, "Devenir le plus riche du jeu")
                )),
                Question(2, "Quelle est la classe de départ qui se divise en Berserker, Paladin et Pistolancier ?", 3, listOf(
                    Answer(1, "Assassin"),
                    Answer(2, "Mage"),
                    Answer(3, "Guerrier"),
                    Answer(4, "Artiste")
                )),
                Question(3, "Dans quelle région commence l’histoire principale de Lost Ark ?", 4, listOf(
                    Answer(1, "Rohendel"),
                    Answer(2, "Feiton"),
                    Answer(3, "Arthetine"),
                    Answer(4, "Rethramis")
                )),
                Question(4, "Comment se nomme le grand méchant qui veut récupérer l’Arche ?", 2, listOf(
                    Answer(1, "Thanos"),
                    Answer(2, "Kazeros"),
                    Answer(3, "Sauron"),
                    Answer(4, "Rakanoth")
                )),
                Question(5, "Quel est le nom de la monnaie premium utilisée dans Lost Ark ?", 2, listOf(
                    Answer(1, "Gold"),
                    Answer(2, "Cristaux royaux"),
                    Answer(3, "Pièces d’or maudites"),
                    Answer(4, "Shillings")
                )),
                Question(6, "Quel type de monture reçoit-on très tôt dans l’aventure ?", 1, listOf(
                    Answer(1, "Un cheval"),
                    Answer(2, "Un dragon"),
                    Answer(3, "Un tank à vapeur"),
                    Answer(4, "Un griffon volant")
                )),
                Question(7, "Que se passe-t-il lorsqu’un joueur atteint le niveau 50 ?", 2, listOf(
                    Answer(1, "Il a terminé le jeu"),
                    Answer(2, "Il commence le contenu endgame"),
                    Answer(3, "Il doit recommencer son personnage à zéro"),
                    Answer(4, "Il reçoit une quête pour trouver la Pierre Philosophale")
                )),
                Question(8, "Quel est le mode de jeu JcJ le plus classique dans Lost Ark ?", 2, listOf(
                    Answer(1, "Course de bateaux"),
                    Answer(2, "Arène 3v3"),
                    Answer(3, "Combat à mains nues sur une île secrète"),
                    Answer(4, "Duel de danses")
                )),
                Question(9, "Quel événement saisonnier transforme Lost Ark en un jeu de fête avec des mini-jeux ?", 2, listOf(
                    Answer(1, "Fête des Lanternes"),
                    Answer(2, "Festival Maharaka"),
                    Answer(3, "La Guerre des Cookies"),
                    Answer(4, "Noël Arctique")
                ))
            ),
            "Moyenne" to listOf(
                Question(10, "Quel est le nom du raid de légion dirigé par Valtan ?", 1, listOf(
                    Answer(1, "Le Cauchemar de la Légion Démoniaque"),
                    Answer(2, "La Conquête du Chaos"),
                    Answer(3, "La Malédiction de Kazeros"),
                    Answer(4, "L’Invasion des Douze Rois")
                )),
                Question(11, "Comment s’appelle la ville technologique où les robots et les machines dominent ?", 3, listOf(
                    Answer(1, "Yorn"),
                    Answer(2, "Rohendel"),
                    Answer(3, "Arthetine"),
                    Answer(4, "Punika")
                )),
                Question(12, "Quelle classe utilise un fauchon et invoque des clones pour attaquer ?", 1, listOf(
                    Answer(1, "Lame fatale"),
                    Answer(2, "Arcaniste"),
                    Answer(3, "Barde"),
                    Answer(4, "Pistolancier")
                )),
                Question(13, "Quel élément de gameplay est inspiré de Diablo ?", 2, listOf(
                    Answer(1, "Les courses de chevaux"),
                    Answer(2, "Les donjons instanciés et le loot explosif"),
                    Answer(3, "Les combats de cuisine entre PNJ"),
                    Answer(4, "Les puzzles en temps réel")
                )),
                Question(14, "Quel est le meilleur navire en fin de jeu ?", 3, listOf(
                    Answer(1, "Le Poisson Volant"),
                    Answer(2, "Le Croiseur Fantôme"),
                    Answer(3, "L’Eibern’s Wound"),
                    Answer(4, "Le Titanic (trop tard)")
                ))
            ),
            "Difficile" to listOf(
                Question(15, "Qui est le chef des Gardiens Démoniaques de Kazeros ?", 1, listOf(
                    Answer(1, "Akkan"),
                    Answer(2, "Thaemine"),
                    Answer(3, "Brelshaza"),
                    Answer(4, "Vykas")
                )),
                Question(16, "Quel raid de Légion est connu pour être l’un des plus difficiles du jeu ?", 4, listOf(
                    Answer(1, "Argos"),
                    Answer(2, "Valtan"),
                    Answer(3, "Kakul-Saydon"),
                    Answer(4, "Brelshaza")
                )),
                Question(17, "Quelle mécanique est essentielle dans le raid d’Argos ?", 1, listOf(
                    Answer(1, "Les positions Soleil et Lune"),
                    Answer(2, "La danse des Gardiens"),
                    Answer(3, "L’offrande aux anciens"),
                    Answer(4, "Le combat dans l’eau")
                )),
                Question(18, "Quel est le niveau d’objet requis pour entrer dans le raid de Thaemine ?", 2, listOf(
                    Answer(1, "1340"),
                    Answer(2, "1580"),
                    Answer(3, "1500"),
                    Answer(4, "1610")
                )),
                Question(19, "Quel est le nom de l’entité divine qui guide les joueurs dans Lost Ark ?", 2, listOf(
                    Answer(1, "Arkesia"),
                    Answer(2, "Beatrice"),
                    Answer(3, "Nia"),
                    Answer(4, "Morpheus")
                ))
            )
        )

    )

    fun getRandomQuestion(theme: String, difficulty: String): Question {
        return questionPool[theme]?.get(difficulty)?.randomOrNull()
            ?: questionPool["Star Wars"]?.get("Facile")!!.random()
    }

    fun getBossQuestions(theme: String): List<Question> {
        return questionPool[theme]?.get("Difficile")?.shuffled()?.take(3)
            ?: questionPool["Star Wars"]?.get("Difficile")!!.shuffled().take(3)
    }
}
