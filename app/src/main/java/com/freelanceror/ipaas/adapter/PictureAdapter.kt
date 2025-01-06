package com.freelanceror.ipaas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.freelanceror.ipaas.model.NasaPicture
import com.freelanceror.ipaas.R

class PictureAdapter(
    private val context: Context,
    private var pictures: List<NasaPicture>,
    private val onClick: (NasaPicture) -> Unit
) : RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_picture, parent, false)
        return PictureViewHolder(view)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        val picture = pictures[position]
        holder.bind(picture)
        holder.itemView.setOnClickListener { onClick(picture) }
    }

    override fun getItemCount() = pictures.size

    fun updateList(newList: List<NasaPicture>) {
        pictures = newList
        notifyDataSetChanged()
    }

    inner class PictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val titleView: TextView = itemView.findViewById(R.id.titleView)

        fun bind(picture: NasaPicture) {
            Glide.with(context).load(picture.url).into(imageView)
            titleView.text = picture.title
        }
    }
}
