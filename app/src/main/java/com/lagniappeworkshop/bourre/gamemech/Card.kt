package com.lagniappeworkshop.bourre.gamemech

data class Card(val suit:Suit, val rank:Int, var faceUp:Boolean=false) {

}
enum class Suit {
    HEARTS,
    CLUBS,
    SPADES,
    DIAMONDS
}