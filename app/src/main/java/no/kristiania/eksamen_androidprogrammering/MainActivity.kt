package no.kristiania.eksamen_androidprogrammering

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Eksamen_Androidprogrammering)
        setContentView(R.layout.activity_main)
    }
}