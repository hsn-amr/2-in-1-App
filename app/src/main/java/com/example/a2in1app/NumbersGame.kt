package com.example.a2in1app

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class NumbersGame : AppCompatActivity() {

    private lateinit var mainLayout: ConstraintLayout
    private lateinit var rvMain: RecyclerView
    private lateinit var userInput: EditText
    private lateinit var guessButton: Button
    private lateinit var guesses: ArrayList<String>
    private val secretNumber = Random.nextInt(11)
    private var doesWin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers_game)
        // change title of activity
        this.title = "Numbers Game"

        guesses= ArrayList()
        mainLayout = findViewById(R.id.mainLayout)
        rvMain = findViewById(R.id.rvMain)
        userInput = findViewById(R.id.etUserInput)
        guessButton = findViewById(R.id.btnGuess)

        rvMain.adapter = NumberGameRecyclerViewAdapter(guesses)
        rvMain.layoutManager = LinearLayoutManager(this)

        guessButton.setOnClickListener{
            when{
                // first, check that input is not empty
                userInput.text.isNotEmpty() ->{
                    // second, check the number is in the range 0-10
                    // if yes, add the input to guesses array
                    // else, show Snackbar to inform user
                    checkRange()
                    when{
                        // third, is user number = secret number?
                        userInput.text.toString().toInt() == secretNumber ->{
                            // if true, then end the game
                            doesWin = true
                            gameOver(doesWin)
                        }
                        // forth, Is the number of times played complete? if yes then end game
                        guesses.size >= 3 -> gameOver(doesWin)
                        // if not,
                        else -> {
                            // update the recycler view
                            rvMain.adapter!!.notifyDataSetChanged()
                            // and go down to last item has added
                            rvMain.smoothScrollToPosition(guesses.size)
                        }
                    }
                }
                else -> {
                    // if input is empty, show this Snackbar
                    Snackbar.make(mainLayout, "Please, enter a number", Snackbar.LENGTH_SHORT).show()
                }
            }
            // after all, clear the edit text
            userInput.text.clear()
        }
    }

    // store data before we lost them because recreate activity
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("guesses", guesses)
        outState.putBoolean("doesWin", doesWin)
    }

    // reload data after recreate activity
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        guesses = savedInstanceState.getStringArrayList("guesses")!!
        doesWin = savedInstanceState.getBoolean("doesWin")
        rvMain.adapter = NumberGameRecyclerViewAdapter(guesses)
        rvMain.layoutManager = LinearLayoutManager(this)
    }

    // function to alert when game over
    private fun gameOver(doesWin: Boolean){
        var message = if(doesWin) "You Win, it is $secretNumber" else "You Lost, it is $secretNumber"
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage(message)
            .setPositiveButton("Restart Game", DialogInterface.OnClickListener{ _, _ -> recreate()})
            .setNegativeButton("Cancel", DialogInterface.OnClickListener{  dialog, _ ->  dialog.cancel()})

        val alart = dialogBuilder.create()
        alart.setTitle("Game Over")
        alart.show()

        disableButtons()
        // and reset some variables
        guesses.clear()
        this.doesWin = false
    }

    // function to check the number is in the range 0-10
    private fun checkRange(){
        when{
            userInput.text.toString().toInt() > 10 || userInput.text.toString().toInt() < 0 ->
                Snackbar.make(mainLayout, "Please, enter a number between 0 and 10", Snackbar.LENGTH_SHORT).show()
            else -> {
                guesses.add(userInput.text.toString())
            }
        }
    }

    // function to disable buttons
    private fun disableButtons(){
        guessButton.isEnabled = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.number_game_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.restart_game -> {
                recreate()
            }
            R.id.guess_the_phrase -> {
                val GuessThePhraseIntent = Intent(this, GuessThePhrase::class.java)
                startActivity(GuessThePhraseIntent)
            }
            R.id.main_activity -> {
                val mainActivityIntent = Intent(this, MainActivity::class.java)
                startActivity(mainActivityIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}