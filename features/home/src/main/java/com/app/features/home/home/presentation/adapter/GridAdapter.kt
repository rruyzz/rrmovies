package com.app.features.home.home.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.commons.models.Movie
import com.app.features.home.databinding.ItemPosterBinding
import com.app.features.home.home.domain.models.PopularMovies
import com.bumptech.glide.Glide

class GridAdapter(
    private val popularMovies: PopularMovies,
    private val context: Context,
    var onClick: (Movie) -> Unit,
) : RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

    lateinit var binding: ItemPosterBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        binding = ItemPosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridViewHolder(binding.root)
    }

    override fun getItemCount() = popularMovies.movies.size

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(popularMovies.movies[position])
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) = with(binding) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500${movie.poster}")
                .into(imagePoster)
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (RecyclerView.NO_POSITION != position)
                    onClick(popularMovies.movies[position])
            }
        }
    }
}