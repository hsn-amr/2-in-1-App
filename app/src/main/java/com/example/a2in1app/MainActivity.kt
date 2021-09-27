package com.example.a2in1app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var btnNumberGame: Button
    lateinit var btnGuessThePhrase: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNumberGame = findViewById(R.id.btnNumberGame)
        btnGuessThePhrase = findViewById(R.id.btnGuessThePhrase)

        btnNumberGame.setOnClickListener {
            val numberGameIntent = Intent(this, NumbersGame::class.java)
            startActivity(numberGameIntent)
        }

        btnGuessThePhrase.setOnClickListener {
            val GuessThePhraseIntent = Intent(this, GuessThePhrase::class.java)
            startActivity(GuessThePhraseIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.number_game -> {
                val numberGameIntent = Intent(this, NumbersGame::class.java)
                startActivity(numberGameIntent)
            }
            R.id.guess_the_phrase -> {
                val GuessThePhraseIntent = Intent(this, GuessThePhrase::class.java)
                startActivity(GuessThePhraseIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}