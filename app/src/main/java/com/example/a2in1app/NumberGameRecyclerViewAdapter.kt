package com.example.a2in1app


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.number_game_item_row.view.*

class NumberGameRecyclerViewAdapter(private val guesses: ArrayList<String>): RecyclerView.Adapter<NumberGameRecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.number_game_item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val guess = guesses[position]
        holder.itemView.apply {
            tvGuessNumber.text = "You guessed $guess"
            tvLeft.text = "You have ${2-position} guesses left"
        }
    }

    override fun getItemCount() = guesses.size


}