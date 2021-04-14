package no.kristiania.eksamen_androidprogrammering

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
        holder?.view?.findViewById<TextView>(R.id.textView_crypto_priceUsd).text = "$" +
            data?.priceUsd?.toDouble()?.round(2)?.toBigDecimal().toString()
        if (data?.changePercent24Hr!! >= "0.00"){
            holder?.view?.findViewById<TextView>(R.id.textView_crypto_changePercent24Hr).setTextColor(
                Color.GREEN)
        }else{
            holder?.view?.findViewById<TextView>(R.id.textView_crypto_changePercent24Hr).setTextColor(
                Color.RED)
        }
        holder?.view?.findViewById<TextView>(R.id.textView_crypto_changePercent24Hr).text =
            data?.changePercent24Hr?.toDouble()?.round(2)?.toBigDecimal().toString() + "%"
    }
}

private fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) {multiplier *= 10}
    return kotlin.math.round(this * multiplier) /multiplier
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}