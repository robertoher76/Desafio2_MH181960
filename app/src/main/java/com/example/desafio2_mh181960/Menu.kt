package com.example.desafio2_mh181960

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Menu : AppCompatActivity() {
    private lateinit var btnfar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(500)
        setTheme(R.style.Theme_Desafio2_MH181960)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        btnfar = findViewById(R.id.btnopcion3)
        btnfar.setOnClickListener{
            this.goToMedic()
        }
        }
        private fun goToMedic(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
