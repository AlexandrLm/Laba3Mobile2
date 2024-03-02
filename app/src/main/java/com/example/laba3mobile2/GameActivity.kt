package com.example.laba3mobile2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.EditText

class GameActivity : AppCompatActivity() {
    private var nomer = 0
    private var players = mutableListOf<Player>()

    private var words = mutableListOf<String>()

    private lateinit var letterText : TextView
    private lateinit var whoType : TextView
    private lateinit var mainWord : EditText
    private lateinit var countOfPlayers: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        letterText = findViewById(R.id.wordText)
        whoType = findViewById(R.id.whoType)
        mainWord = findViewById(R.id.mainWordText)
        countOfPlayers =  findViewById(R.id.lastText)

        val chars = ('a'..'z')
        letterText.text = chars.random().toString()

        val arguments = intent.extras
        if (arguments != null) {
            val playersName: Array<String> = arguments.getStringArray("playerNames")!!
            for (n in playersName){
                players.add(Player(n))
            }
            whoTypeChange()
        }
        else{
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
        }
        counter()
    }

    fun enterButtonPress(v : View){
        if (mainWord.text != null){
            //проверка на то, что первая буква введенного слова подходит
            if (mainWord.text.toString().startsWith(letterText.text.toString(), true)
                && mainWord.text.toString().length > 2)
            {
                if (words.contains(mainWord.text.toString())){
                    Toast.makeText(this, "Такое слово уже было", Toast.LENGTH_SHORT).show()
                    return
                }
                letterText.text = mainWord.text.toString().takeLast(1)
                words.add(mainWord.text.toString())
                mainWord.text = null
                players[nomer].howMuchWordsType += 1

                nomer = (nomer + 1) % players.size

                while (players[nomer].lose) {
                    nomer = (nomer + 1) % players.size
                }
                whoTypeChange()
            }
            else{
                Toast.makeText(this, "Не правильно введено слово", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun giveupButtonPress(v : View){
        players[nomer].lose = true
        while (players[nomer].lose) {
            nomer = (nomer + 1) % players.size
        }
        counter()
        for(n in players){
            if (n.win){
                val intent = Intent(this, WinActivity::class.java)
                intent.putExtra("whoWin", "Выиграл ${n.name}")
                intent.putExtra("winData", n)
                startActivity(intent)
                return
            }
        }
        whoTypeChange()
    }
    @SuppressLint("SetTextI18n")
    private fun whoTypeChange(){
        whoType.text = "Сейчас вводит слово - ${players[nomer].name}"
    }
    @SuppressLint("SetTextI18n")
    private fun counter() : Int{
        var count = 0
        for (n in players){
            if (!n.lose)
                count++
        }
        if(count == 1) {
            for (n in players) {
                if (!n.lose)
                    n.win = true
            }
        }
        countOfPlayers.text = "Игроков осталось $count"
        return count
    }

}