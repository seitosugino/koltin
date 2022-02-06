package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiKey = "365640f0c8e89280079422e19cf14089"
        val mainUrl = "https://api.openweathermap.org/data/2.5/weather?lang=ja"

        val btnTokyo: Button = findViewById(R.id.btnTokyo)
        val btnOsaka:Button = findViewById(R.id.btnOsaka)
        val tvCityName:TextView = findViewById(R.id.tvCityName)
        val tvCityWeather:TextView = findViewById(R.id.tvCityWeather)
        val tvMax:TextView = findViewById(R.id.tvMax)
        val tvMin:TextView = findViewById(R.id.tvMin)
        val btnClear:Button = findViewById(R.id.btnClear)

        btnTokyo.setOnClickListener {
            val weatherUrl = "$mainUrl&q=tokyo&appid=$apiKey"
        }
    }
}