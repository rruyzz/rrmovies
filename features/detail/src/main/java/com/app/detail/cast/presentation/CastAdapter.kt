package com.app.detail.cast.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.commons.models.Movie
import com.app.detail.cast.domain.model.Actor
import com.app.detail.databinding.ItemCastBinding
import com.bumptech.glide.Glide

class CastAdapter(
    private val castList: List<Actor>,
    private val context: Context,
) : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    lateinit var binding: ItemCastBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding.root)
    }

    override fun getItemCount() = castList.size

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(castList[position])
    }

    inner class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(actor: Actor) = with(binding) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500${actor.image}")
                .into(imagePoster)
        }

    }
}