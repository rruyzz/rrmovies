package com.app.commons.presentation.customView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.commons.databinding.LayoutListItemViewBinding

class PosterListCustomViewAdapter(
    private var list: List<String> = listOf()
) : RecyclerView.Adapter<PosterListCustomViewAdapter.PosterListViewHolder>() {


    lateinit var binding: LayoutListItemViewBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PosterListCustomViewAdapter.PosterListViewHolder {
        binding = LayoutListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PosterListViewHolder(binding.root)
    }

    fun setData(_list: List<String>){
        list = _list
        notifyDataSetChanged()
    }
    override fun getItemCount() = list.size

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: PosterListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class PosterListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(movie: String) = with(binding) {
            textTitle.text = movie
        }
    }
}