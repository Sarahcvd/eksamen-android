package no.kristiania.eksamen_androidprogrammering

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class BuySellActivity: AppCompatActivity() {
    @SuppressLint("CutPasteId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_buysell)

        val navBarTitle = intent.getStringExtra(CustomViewHolder.CRYPTO_NAME_KEY)
        val buySellName = intent.getStringExtra(CustomViewHolder.CRYPTO_NAME_KEY)
        val buySellSymbol = intent.getStringExtra(CustomViewHolder.CRYPTO_SYMBOL_KEY)
        val buySellPrice = intent.getStringExtra(CustomViewHolder.CRYPTO_PRICE_KEY)?.toDouble()
            ?.round(2)
            ?.toBigDecimal().toString()

        supportActionBar?.title = navBarTitle
        findViewById<TextView>(R.id.textView_buy_name).text = "$buySellName ($buySellSymbol)"
        findViewById<TextView>(R.id.textView_buy_priceUsd).text = "$$buySellPrice"
        findViewById<TextView>(R.id.textView_buysell_current_holdings).text =
            "You have 0.00 $buySellSymbol"


        //FIX
        val buyButton = findViewById<TextView>(R.id.button_buysell_buy)
        buyButton.setOnClickListener {
            val intent = Intent(this, BuyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) {multiplier *= 10}
        return kotlin.math.round(this * multiplier) /multiplier
    }
}
