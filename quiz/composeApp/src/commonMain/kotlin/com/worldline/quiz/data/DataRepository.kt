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
        "Général" to mapOf(
            "Facile" to listOf(
                Question(1, "Quel est le plus grand océan du monde ?", 1, listOf(
                    Answer(1, "L'océan Pacifique"), Answer(2, "L'océan Atlantique"), Answer(3, "L'océan Indien"), Answer(4, "L'océan Arctique")
                )),
                Question(2, "Combien de continents y a-t-il sur Terre ?", 2, listOf(
                    Answer(1, "5"), Answer(2, "7"), Answer(3, "6"), Answer(4, "8")
                )),
                Question(3, "Qui a peint la Joconde ?", 3, listOf(
                    Answer(1, "Vincent van Gogh"), Answer(2, "Pablo Picasso"), Answer(3, "Léonard de Vinci"), Answer(4, "Claude Monet")
                )),
                Question(4, "Quelle est la capitale de la France ?", 4, listOf(
                    Answer(1, "Lyon"), Answer(2, "Marseille"), Answer(3, "Nice"), Answer(4, "Paris")
                )),
                Question(5, "Combien de côtés a un triangle ?", 1, listOf(
                    Answer(1, "3"), Answer(2, "4"), Answer(3, "5"), Answer(4, "6")
                ))
            ),
            "Moyenne" to listOf(
                Question(6, "Quel est le symbole chimique de l'or ?", 2, listOf(
                    Answer(1, "Ag"), Answer(2, "Au"), Answer(3, "Fe"), Answer(4, "O")
                )),
                Question(7, "Qui a écrit 'Les Misérables' ?", 3, listOf(
                    Answer(1, "Émile Zola"), Answer(2, "Molière"), Answer(3, "Victor Hugo"), Answer(4, "Gustave Flaubert")
                )),
                Question(8, "Quel est le plus grand désert du monde ?", 4, listOf(
                    Answer(1, "Le désert du Sahara"), Answer(2, "Le désert de Gobi"), Answer(3, "Le désert de l'Atacama"), Answer(4, "L'Antarctique")
                )),
                Question(9, "Combien de cordes a une guitare classique ?", 1, listOf(
                    Answer(1, "6"), Answer(2, "4"), Answer(3, "5"), Answer(4, "7")
                )),
                Question(10, "Qui a fondé l'Empire mongol ?", 2, listOf(
                    Answer(1, "Attila"), Answer(2, "Gengis Khan"), Answer(3, "Tamerlan"), Answer(4, "Kublai Khan")
                ))
            ),
            "Difficile" to listOf(
                Question(11, "Quel est le nom du plus grand satellite naturel de Saturne ?", 3, listOf(
                    Answer(1, "Io"), Answer(2, "Ganymède"), Answer(3, "Titan"), Answer(4, "Europe")
                )),
                Question(12, "En quelle année a eu lieu la Révolution française ?", 4, listOf(
                    Answer(1, "1776"), Answer(2, "1815"), Answer(3, "1603"), Answer(4, "1789")
                )),
                Question(13, "Quel est le nom scientifique du chat domestique ?", 1, listOf(
                    Answer(1, "Felis catus"), Answer(2, "Canis lupus"), Answer(3, "Panthera leo"), Answer(4, "Equus caballus")
                )),
                Question(14, "Quel est l'élément chimique le plus léger du tableau périodique ?", 2, listOf(
                    Answer(1, "Hélium"), Answer(2, "Hydrogène"), Answer(3, "Lithium"), Answer(4, "Oxygène")
                )),
                Question(15, "Dans quelle ville se trouve le Parthénon ?", 3, listOf(
                    Answer(1, "Rome"), Answer(2, "Istanbul"), Answer(3, "Athènes"), Answer(4, "Carthage")
                ))
            )
        ),
        "Géographie" to mapOf(
            "Facile" to listOf(
                Question(1, "Quelle est la capitale de l'Espagne ?", 1, listOf(
                    Answer(1, "Madrid"), Answer(2, "Barcelone"), Answer(3, "Séville"), Answer(4, "Valence")
                )),
                Question(2, "Quel est le plus grand océan du monde ?", 2, listOf(
                    Answer(1, "L'océan Atlantique"), Answer(2, "L'océan Pacifique"), Answer(3, "L'océan Indien"), Answer(4, "L'océan Arctique")
                )),
                Question(3, "Quel est le plus grand pays du monde en superficie ?", 3, listOf(
                    Answer(1, "Canada"), Answer(2, "Chine"), Answer(3, "Russie"), Answer(4, "États-Unis")
                )),
                Question(4, "Quel fleuve traverse l'Égypte ?", 4, listOf(
                    Answer(1, "Le Congo"), Answer(2, "L'Amazone"), Answer(3, "Le Gange"), Answer(4, "Le Nil")
                )),
                Question(5, "Combien y a-t-il de continents sur Terre ?", 1, listOf(
                    Answer(1, "7"), Answer(2, "5"), Answer(3, "6"), Answer(4, "8")
                ))
            ),
            "Moyenne" to listOf(
                Question(6, "Quel est le plus grand désert chaud du monde ?", 2, listOf(
                    Answer(1, "Le désert de Gobi"), Answer(2, "Le Sahara"), Answer(3, "Le désert de l'Atacama"), Answer(4, "Le désert du Kalahari")
                )),
                Question(7, "Quelle est la plus haute montagne du monde ?", 3, listOf(
                    Answer(1, "Mont McKinley"), Answer(2, "Mont Kilimandjaro"), Answer(3, "Mont Everest"), Answer(4, "Mont Elbrouz")
                )),
                Question(8, "Quelle est la capitale du Canada ?", 4, listOf(
                    Answer(1, "Toronto"), Answer(2, "Vancouver"), Answer(3, "Montréal"), Answer(4, "Ottawa")
                )),
                Question(9, "Quel pays a la plus grande population au monde en 2024 ?", 1, listOf(
                    Answer(1, "Inde"), Answer(2, "Chine"), Answer(3, "États-Unis"), Answer(4, "Indonésie")
                )),
                Question(10, "Quel pays est surnommé 'le pays du Soleil-Levant' ?", 2, listOf(
                    Answer(1, "Corée du Sud"), Answer(2, "Japon"), Answer(3, "Thaïlande"), Answer(4, "Vietnam")
                ))
            ),
            "Difficile" to listOf(
                Question(11, "Quel est le lac le plus profond du monde ?", 3, listOf(
                    Answer(1, "Lac Supérieur"), Answer(2, "Lac Victoria"), Answer(3, "Lac Baïkal"), Answer(4, "Lac Tanganyika")
                )),
                Question(12, "Quel pays d'Amérique du Sud n'a pas d'accès à la mer ?", 4, listOf(
                    Answer(1, "Argentine"), Answer(2, "Pérou"), Answer(3, "Équateur"), Answer(4, "Bolivie")
                )),
                Question(13, "Quelle rivière est la plus longue du monde ?", 1, listOf(
                    Answer(1, "Le Nil"), Answer(2, "L'Amazone"), Answer(3, "Le Mississippi"), Answer(4, "Le Yangtsé")
                )),
                Question(14, "Quelle est la capitale de la Mongolie ?", 2, listOf(
                    Answer(1, "Astana"), Answer(2, "Oulan-Bator"), Answer(3, "Douchanbé"), Answer(4, "Bichkek")
                )),
                Question(15, "Quelle mer sépare l'Europe de l'Afrique ?", 3, listOf(
                    Answer(1, "Mer Noire"), Answer(2, "Mer Rouge"), Answer(3, "Mer Méditerranée"), Answer(4, "Mer Caspienne")
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
