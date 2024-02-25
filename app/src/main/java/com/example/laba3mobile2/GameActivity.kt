package com.example.laba3mobile2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.EditText

class GameActivity : AppCompatActivity() {
    private var nomer = 0
    private lateinit var playersName: Array<String>
    private lateinit var players : Array<Player>

    private lateinit var letterText : TextView
    private lateinit var whoType : TextView
    private lateinit var mainWord : EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        letterText =  findViewById(R.id.wordText)
        whoType  =findViewById(R.id.whoType)
        mainWord = findViewById(R.id.mainWordText)

        val firstWord = "первое"
        letterText.text = firstWord.takeLast(1)

        val arguments = intent.extras
        if (arguments != null) {
            playersName = arguments.getStringArray("players")!!
            players = Array(playersName.size) { Player("none") }
            for (n in players.indices){
                players[n].name = playersName[n]
            }
            whoTypeChange()
        }
        else{
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
        }

    }

    fun enterButtonPress(v : View){
        if (mainWord.text != null){
            if (mainWord.text.toString().take(1) == letterText.text.toString()){
                letterText.text = mainWord.text.toString().takeLast(1)
                mainWord.text = null
                var count = 0
                while (players[nomer].lose) {
                    count++
                    nomer++
                    if (nomer == players.size - 1){
                        nomer = 0
                    }
                }
                if (count == 1){
                    players[nomer].win = true
                    //тут надо дописать выигрыш
                }
                else{
                    whoTypeChange()
                }
            }
            else{
                Toast.makeText(this, "Не правильно введено слово", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun giveupButtonPress(v : View){
        players[nomer].lose = true
        while (players[nomer].lose) {
            nomer++
            if (nomer == players.size - 1){
                nomer = 0
            }
        }
        whoTypeChange()
    }

    @SuppressLint("SetTextI18n")
    private fun whoTypeChange(){
        whoType.text = "Сейчас вводит слово - ${players[nomer].name}"
    }

}