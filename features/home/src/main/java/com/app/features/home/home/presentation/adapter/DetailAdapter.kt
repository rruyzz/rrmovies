package com.app.features.home.home.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.commons.models.Movie
import com.app.features.home.databinding.ItemDetailAdapterBinding
import com.app.features.home.home.domain.models.PopularMovies
import com.bumptech.glide.Glide

class DetailAdapter(
    var onClick: (Movie) -> Unit,
    private val searchMovies: PopularMovies,
    private val context: Context
) : RecyclerView.Adapter<DetailAdapter.ListViewHolder>() {

    lateinit var binding: ItemDetailAdapterBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding = ItemDetailAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding.root)
    }

    override fun getItemCount() = searchMovies.movies.size

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(searchMovies.movies[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) = with(binding) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500${movie.poster}")
                .into(imagePoster)
            binding.textName.text = movie.title
            binding.textGrade.text = movie.grade
            binding.textGender.text = movie.gender
            binding.textYear.text = movie.year
        }


        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (RecyclerView.NO_POSITION != position)
                    onClick(searchMovies.movies[position])
            }
        }
    }
}