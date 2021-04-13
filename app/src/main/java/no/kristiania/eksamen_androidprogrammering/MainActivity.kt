package no.kristiania.eksamen_androidprogrammering

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val exampleList = generateDummyList(500)

        findViewById<RecyclerView>(R.id.recycler_view).adapter = ExampleAdapter(exampleList)
        findViewById<RecyclerView>(R.id.recycler_view).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.recycler_view).setHasFixedSize(true)

        //fetchJson()
    }

    private fun generateDummyList(size: Int): List<ExampleItem> {
        val list = ArrayList<ExampleItem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_cash
                1 -> R.drawable.ic_100
                else -> R.drawable.ic_money_off
            }
            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }
    fun fetchJson() {
        println("Attempting to fetch JSON")

        val url = "https://api.coincap.io/v2/assets"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()

                val crypto = gson.fromJson(body, Crypto::class.java)
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }
}