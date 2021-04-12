package no.kristiania.eksamen_androidprogrammering

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Eksamen_Androidprogrammering)
        setContentView(R.layout.activity_main)

        fetchJson()
    }

    fun fetchJson() {
        println("Attempting to fetch JSON")

        val url = "https://api.coincap.io/v2/assets/"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        // kan ikke bruke "execute" i main thread så blir "enqueue"
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                println(body)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute!")
            }
        })

    }
}