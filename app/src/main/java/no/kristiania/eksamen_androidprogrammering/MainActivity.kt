package no.kristiania.eksamen_androidprogrammering

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Eksamen_Androidprogrammering)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recyclerView_main).layoutManager = LinearLayoutManager(this)

        val pointsHeader = findViewById<TextView>(R.id.textView)
        pointsHeader.setOnClickListener {
            val intent = Intent(this, PortfolioActivity::class.java)
            startActivity(intent)
        }

        fetchJson()
    }


    fun fetchJson() {
        val url = "https://api.coincap.io/v2/assets"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                //println(body)

                val gson = GsonBuilder().create()

                val crypto = gson.fromJson(body, Crypto::class.java)

                runOnUiThread{
                    findViewById<RecyclerView>(R.id.recyclerView_main).adapter = MainAdapter(crypto)
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }
}