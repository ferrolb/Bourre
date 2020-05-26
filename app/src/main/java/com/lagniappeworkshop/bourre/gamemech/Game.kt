package com.lagniappeworkshop.bourre.gamemech

import android.util.Log

class Game() {
    val players : ArrayList<Player> = arrayListOf(
        Player("Player 1"),
        Player("Player 2"),
        Player("Player 3"))

    var deck: ArrayList<Card> = ArrayList()
    var discardDeck: ArrayList<Card> = ArrayList()
    var dealerIndex: Int

    init {
        createCards()
        dealerIndex = 0
    }

    private fun createCards() {
        deck.clear()
        enumValues<Suit>().forEach {
            for (rank in 2 until 14) {
                deck.add(Card(it, rank, false))
            }
        }
    }

    private fun shuffle () {
        deck.shuffle()
        deck.shuffle()
        deck.shuffle()
    }

    fun deal(player:Player) {
        player.currentHand.cards.add(deck.removeAt(0))
    }

    fun playGame() {
        shuffle()
        var nextPlayer = dealerIndex+1
        do  {
            var nextPlayer =  (nextPlayer++) % players.size
            Log.i("########", "nextPlayer: $nextPlayer")
            deal(players[nextPlayer])
            Log.i("########", "Cards: ${players[nextPlayer].currentHand.cards}")
        } while(players[nextPlayer] != players[dealerIndex])
    }
}