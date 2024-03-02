package com.example.laba3mobile2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class WinActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)

        val whoWinT : TextView = findViewById(R.id.whoWinText)
        val arguments = intent.extras
        if (arguments != null) {
            val player = arguments.getParcelable<Player>("winData")!!
            whoWinT.text = "Выиграл ${player.name}\n\nНаписал слов ${player.howMuchWordsType}"
        }

    }

    fun againButtonPress(v : View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}