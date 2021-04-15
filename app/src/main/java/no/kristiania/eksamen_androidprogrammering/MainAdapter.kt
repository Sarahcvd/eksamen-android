package no.kristiania.eksamen_androidprogrammering

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

class MainAdapter(private val crypto: Crypto): RecyclerView.Adapter<CustomViewHolder>() {

    // numberOfItems
    override fun getItemCount(): Int {
        return crypto.data?.count()!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.crypto_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    fun formatDollar(dollars: String?): String {
        val toBeformattedPriceUsd: Double? = dollars?.toDouble()
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        return format.format(toBeformattedPriceUsd)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val data = crypto.data?.get(position)
        val symbol: String? = crypto.data?.get(position)?.symbol
        holder.view.findViewById<TextView>(R.id.textView_crypto_name).text = data?.name
        holder.view.findViewById<TextView>(R.id.textView_crypto_symbol).text = data?.symbol
        holder.view.findViewById<TextView>(R.id.textView_crypto_priceUsd).text = "$" +
            data?.priceUsd?.toDouble()?.round(2)?.toBigDecimal().toString()
        if (data?.changePercent24Hr!! >= "0.00"){
            holder.view.findViewById<TextView>(R.id.textView_crypto_changePercent24Hr).setTextColor(
                Color.GREEN)
        }else{
            holder.view.findViewById<TextView>(R.id.textView_crypto_changePercent24Hr).setTextColor(
                Color.RED)
        }
        holder.view.findViewById<TextView>(R.id.textView_crypto_changePercent24Hr).text =
            data.changePercent24Hr.toDouble().round(2).toBigDecimal().toString() + "%"

        val imageViewCryptoSymbol = holder.view.findViewById<ImageView>(R.id.imageView_crypto)
        val cryptoImageUrl = "https://static.coincap.io/assets/icons/${symbol?.toLowerCase(Locale.ROOT).toString()}@2x.png"
        Picasso.get().load(cryptoImageUrl).into(imageViewCryptoSymbol)

        holder.crypto = data
    }

}

// Function to shorten decimals
private fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) {multiplier *= 10}
    return kotlin.math.round(this * multiplier) /multiplier
}

class CustomViewHolder(val view: View, var crypto: Data? = null): RecyclerView.ViewHolder(view){

    companion object {
        const val CRYPTO_NAME_KEY = "CRYPTO_NAME"
        const val CRYPTO_ID_KEY = "CRYPTO_ID"
        const val CRYPTO_SYMBOL_KEY = "CRYPTO_SYMBOL"
        const val CRYPTO_PRICE_KEY = "CRYPTO_PRICE"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, BuySellActivity::class.java)

            intent.putExtra(CRYPTO_NAME_KEY, crypto?.name)
            intent.putExtra(CRYPTO_ID_KEY, crypto?.id)
            intent.putExtra(CRYPTO_SYMBOL_KEY, crypto?.symbol)
            intent.putExtra(CRYPTO_PRICE_KEY, crypto?.priceUsd)

            view.context.startActivity(intent)
        }
    }
}





