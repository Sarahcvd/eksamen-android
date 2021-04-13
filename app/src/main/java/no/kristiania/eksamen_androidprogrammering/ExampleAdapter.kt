package no.kristiania.eksamen_androidprogrammering

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExampleAdapter(private val exampleList: List<ExampleItem>) : RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

    //For å generere disse: ctrl + I
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_item,
        parent, false)

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2

        // Eksempel så man ser at kortene blir brukt på nytt
        if(position == 0){
            holder.textView1.setBackgroundColor(Color.YELLOW)
        }
    }

    override fun getItemCount() = exampleList.size

    class ExampleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.image_cash)
        val textView1: TextView = itemView.findViewById(R.id.textView_1)
        val textView2: TextView = itemView.findViewById(R.id.textView_2)
    }
}