package com.example.proyectog2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window

class DefinirHabito : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()?.hide();
        setContentView(R.layout.activity_definir_habito)
    }
}