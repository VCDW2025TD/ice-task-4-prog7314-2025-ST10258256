package com.example.memestreamapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.memestreamapp.data.Meme

class FeedAdapter(private val memes: List<Meme>) :
    RecyclerView.Adapter<FeedAdapter.MemeViewHolder>() {

    class MemeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.meme_image)
        val caption: TextView = itemView.findViewById(R.id.meme_caption)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meme, parent, false)
        return MemeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        val meme = memes[position]
        holder.caption.text = meme.caption
        Glide.with(holder.itemView.context).load(meme.imageUrl).into(holder.image)
    }

    override fun getItemCount() = memes.size
}
