package no.kristiania.eksamen_androidprogrammering

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(val crypto: Crypto): RecyclerView.Adapter<CustomViewHolder>() {

    val cryptoTitles = listOf("First title", "Second", "3rd", "MOOOOORE", "First title", "Second", "3rd", "MOOOOORE", "First title", "Second", "3rd", "MOOOOORE", "First title", "Second", "3rd", "MOOOOORE")

    // numberOfItems
    override fun getItemCount(): Int {
        return crypto.data?.count()!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.crypto_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //val cryptoTitle = cryptoTitles.get(position)
        val data = crypto.data?.get(position)
        holder?.view?.findViewById<TextView>(R.id.textView_crypto_name).text = data?.name
        holder?.view?.findViewById<TextView>(R.id.textView_crypto_symbol).text = data?.symbol
        holder?.view?.findViewById<TextView>(R.id.textView_crypto_priceUsd).text = data?.priceUsd
        holder?.view?.findViewById<TextView>(R.id.textView_crypto_changePercent24Hr).text = data?.changePercent24Hr
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}