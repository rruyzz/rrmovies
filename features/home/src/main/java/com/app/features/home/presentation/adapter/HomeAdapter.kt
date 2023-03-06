package com.app.features.home.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.features.home.databinding.ItemMovieBinding
import com.app.features.home.domain.models.Movie
import com.app.features.home.domain.models.PopularMovies
import com.bumptech.glide.Glide

class HomeAdapter(
    var onClick: (Movie) -> Unit,
    private val popularMovies: PopularMovies,
    private val context: Context
) : RecyclerView.Adapter<HomeAdapter.ListViewHolder>() {
    lateinit var binding: ItemMovieBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding.root)
    }

    override fun getItemCount() = popularMovies.movies.size

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(popularMovies.movies[position], popularMovies.iconList[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie, icon: Int) = with(binding) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500${movie.poster}").into(imagePoster)
            position.setImageResource(icon)
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