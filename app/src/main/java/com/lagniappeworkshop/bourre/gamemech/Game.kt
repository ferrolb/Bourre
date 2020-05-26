package com.lagniappeworkshop.bourre.gamemech

class Game() {
    val players : ArrayList<Player> = arrayListOf(
        Player("Player 1"),
        Player("Player 2"),
        Player("Player 3"))

    lateinit var deck: ArrayList<Card>
    var discardDeck: ArrayList<Card> = ArrayList()
    var dealer: Player

    init {
        createCards()
        dealer = players[0]
    }

    private fun createCards() {
        enumValues<Suit>().forEach {
            for (rank in 2 until 14) {
                deck.add(Card(it, rank, false))
            }
        }
    }

    fun shuffle () {
        deck.shuffle()
    }
}