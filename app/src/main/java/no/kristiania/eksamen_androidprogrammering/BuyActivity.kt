package no.kristiania.eksamen_androidprogrammering

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BuyActivity  : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_buy)

        val buySellName = intent.getStringExtra(CustomViewHolder.CRYPTO_NAME_KEY)
        val buySellSymbol = intent.getStringExtra(CustomViewHolder.CRYPTO_SYMBOL_KEY)
        val buySellPrice = intent.getStringExtra(CustomViewHolder.CRYPTO_PRICE_KEY)?.toDouble()
            ?.round(2)
            ?.toBigDecimal().toString()

        findViewById<TextView>(R.id.textView_buy_name).text = "$buySellName ($buySellSymbol)"
        findViewById<TextView>(R.id.textView_buy_priceUsd).text = "$$buySellPrice"
        findViewById<TextView>(R.id.textView_buysell_current_holdings).text =
            "You have 0.00 $buySellSymbol"
    }

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) {multiplier *= 10}
        return kotlin.math.round(this * multiplier) /multiplier
    }
}