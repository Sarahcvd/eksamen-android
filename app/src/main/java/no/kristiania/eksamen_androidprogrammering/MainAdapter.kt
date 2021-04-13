package no.kristiania.eksamen_androidprogrammering

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MainAdapter(val crypto: Crypto): RecyclerView.Adapter<CustomViewHolder>() {

    // numberOfItems
    override fun getItemCount(): Int {
        return crypto.data?.count()!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.crypto_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val data = crypto.data?.get(position)
        holder?.view?.findViewById<TextView>(R.id.textView_crypto_name).text = data?.name
        holder?.view?.findViewById<TextView>(R.id.textView_crypto_symbol).text = data?.symbol
        holder?.view?.findViewById<TextView>(R.id.textView_crypto_priceUsd).text =
            data?.priceUsd?.toDouble()?.round(2)?.toBigDecimal().toString()
        holder?.view?.findViewById<TextView>(R.id.textView_crypto_changePercent24Hr).text =
            data?.changePercent24Hr?.toDouble()?.round(2)?.toBigDecimal().toString()
        /* Trying to access crypto symbol imageView and fill it with url-symbols */
        val imageViewCryptoSymbol = holder?.view?.findViewById<ImageView>(R.id.imageView_crypto_symbol)
        val cryptoImageUrl = "https://static.coincap.io/assets/icons/btc@2x.png";
        Picasso.get().load(cryptoImageUrl).into(imageViewCryptoSymbol)
    }
}

private fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) {multiplier *= 10}
    return kotlin.math.round(this * multiplier) /multiplier
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}