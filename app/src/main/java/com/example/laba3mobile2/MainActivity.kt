package com.example.laba3mobile2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var countOfPlayers : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countOfPlayers = findViewById(R.id.countOfPlayersText)
    }

    fun startButtonPress(v : View){
        if(countOfPlayers.text.toString().toInt() < 2){
            Toast.makeText(this, "Введите количество игроков больше 1", Toast.LENGTH_LONG).show()
        }
        else{
            val intent = Intent(this, RegestrationActivity::class.java)
            intent.putExtra("countOfPlayers", countOfPlayers.text.toString().toInt())
            startActivity(intent)
        }

    }
}