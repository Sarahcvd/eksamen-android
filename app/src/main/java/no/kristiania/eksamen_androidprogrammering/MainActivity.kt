package no.kristiania.eksamen_androidprogrammering

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.gson.GsonBuilder
import no.kristiania.eksamen_androidprogrammering.database.DATABASE_NAME
import no.kristiania.eksamen_androidprogrammering.database.DataBase
import no.kristiania.eksamen_androidprogrammering.entities.Wallet
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Eksamen_Androidprogrammering)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recyclerView_main).layoutManager = LinearLayoutManager(this)

        fetchJson()

        insertToDatabaseAndPrint()
    }

    suspend fun insertToDatabaseAndPrint() {
        val db = Room.databaseBuilder(
                applicationContext,
                DataBase::class.java, "wallet_database"
        ).build()

        val walletDao = db.getWalletDAO()
        val wallet: Wallet = Wallet(0, "bitcoin", 10.00);
        val users: List<Wallet> = walletDao.fetchAll() // denne får appen til å kræsje
        println(users);
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