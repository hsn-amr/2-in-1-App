package com.example.a2in1app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.guess_phrase_item_row.view.*

class GuessPhraseRecyclerViewAdapter(private val guesses: ArrayList<String>): RecyclerView.Adapter<GuessPhraseRecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.guess_phrase_item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val guess = guesses[position]
        holder.itemView.apply {
            tvGuessPhrase.text = guess
        }
    }

    override fun getItemCount() = guesses.size


}