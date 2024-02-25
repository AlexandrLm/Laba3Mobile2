package com.example.laba3mobile2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegestrationActivity : AppCompatActivity() {
    lateinit var playersName : Array<String>

    var nomer : Int = 0
    lateinit var regText : TextView
    lateinit var name : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regestration)

        regText= findViewById(R.id.regText)
        name= findViewById(R.id.forNameText)

        updateRegText(nomer + 1)
        val arguments = intent.extras
        if (arguments != null) {
            playersName = Array(arguments.getInt("countOfPlayers")) { String() }
        }
        println("sizew ${playersName.size}")
    }

    @SuppressLint("SetTextI18n")
    private fun updateRegText(n : Int){
        regText.text = "Введите имя для игрока № $n"
    }

    fun nextButtonPress(v : View){
        if (name.text.toString() == ""){
            Toast.makeText(this, "Введите имя", Toast.LENGTH_LONG).show()
        }
        else if (nomer < playersName.size){
            for (n in 0..nomer){ //проверка на одинаковые имена
                 if (playersName[n] == name.text.toString()){
                     Toast.makeText(this, "Имена не должны совпадать", Toast.LENGTH_LONG).show()
                     return
                 }
            }

            playersName[nomer] = name.text.toString()
            nomer++
            if (nomer == playersName.size)
            {
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("players", playersName)
                startActivity(intent)
                return
            }
            name.text = null
            updateRegText(nomer + 1)
            println(nomer)
        }
        else{
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("players", playersName)
            startActivity(intent)
        }

    }
}