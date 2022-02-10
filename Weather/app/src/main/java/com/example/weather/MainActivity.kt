package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

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
            weatherTask(weatherUrl)
        }
    }

    private fun weatherTask(weatherUrl:String){
        lifecycleScope.launch{
            val result = weatherBackgroundTask(weatherUrl)

            weatherJsonTask(result)
        }
    }

    private suspend fun weatherBackgroundTask(weatherUrl: String) :String {
        val response = withContext(Dispatchers.IO){
            var httpResult = ""

            try {
                val urlObj = URL(weatherUrl)
                val br = BufferedReader(InputStreamReader(urlObj.openStream()))
                httpResult = br.readText()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return@withContext httpResult
        }
        return response
    }

    private fun weatherJsonTask(result: String) {
        val tvCityName:TextView = findViewById(R.id.tvCityName)
        val tvCityWeather:TextView = findViewById(R.id.tvCityWeather)
        val tvMax:TextView = findViewById(R.id.tvMax)
        val tvMin:TextView = findViewById(R.id.tvMin)

        val jsonObj = JSONObject(result)

        val cityName = jsonObj.getString("name")
        tvCityName.text = cityName
    }
}