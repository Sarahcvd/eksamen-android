package no.kristiania.eksamen_androidprogrammering

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BuySellActivity: AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recyclerView_main).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.recyclerView_main).adapter = BuySellAdapter()
    }

    private class BuySellAdapter : RecyclerView.Adapter<BuySellViewHolder>(){
        override fun getItemCount(): Int {
            return 2
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuySellViewHolder {

            val layoutInflater = LayoutInflater.from(parent.context)
            val customView = layoutInflater.inflate(R.layout.buy_sell_row, parent, false)
            val intent = null
            return BuySellViewHolder(customView)
        }

        override fun onBindViewHolder(holder: BuySellViewHolder, position: Int) {

        }
    }

    private class BuySellViewHolder(val customView: View): RecyclerView.ViewHolder(customView){

    }
}