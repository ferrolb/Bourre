package com.lagniappeworkshop.bourre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lagniappeworkshop.bourre.gamemech.Game

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val game = Game()
        game.playGame()
    }
}
