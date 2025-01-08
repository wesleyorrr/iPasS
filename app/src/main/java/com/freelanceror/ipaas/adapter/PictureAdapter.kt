package com.freelanceror.ipaas.adapter

import android.content.Context
import android.view.LayoutInflater

import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.freelanceror.ipaas.model.NasaPicture

import com.freelanceror.ipaas.databinding.ItemPictureBinding

class PictureAdapter(
    private val context: Context,
    private var pictures: List<NasaPicture>,
    private val onClick: (NasaPicture) -> Unit
) : RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        // Inflate o layout usando View Binding
        val binding = ItemPictureBinding.inflate(LayoutInflater.from(context), parent, false)
        return PictureViewHolder(binding)
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

    inner class PictureViewHolder(private val binding: ItemPictureBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(picture: NasaPicture) {
            // Use o binding para acessar os elementos da view
            Glide.with(context).load(picture.url).into(binding.imageView)
            binding.titleView.text = picture.title
        }
    }
}
