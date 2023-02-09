package com.app.commons.presentation.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.commons.R
import com.app.commons.databinding.LayoutListCustomViewBinding
import com.app.commons.domain.MovieList

class PosterListCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle)  {

    private lateinit var binding: LayoutListCustomViewBinding
    private val adapter = PosterListCustomViewAdapter(listOf())
    var title = ""
    init {
        binding = LayoutListCustomViewBinding.inflate(LayoutInflater.from(context), this, true)

        attrs?.let {

            val typedArray = context.obtainStyledAttributes(it, R.styleable.PosterListCustomView)
            title = typedArray.getString(R.styleable.PosterListCustomView_custom_component_titles).orEmpty()

            typedArray.recycle()
        }


        binding.recycler.layoutManager =  LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recycler.adapter =  adapter
    }

    fun setState(state: MovieList) {
        when(state) {
            is MovieList.Success -> renderSuccess(state.list)
            is MovieList.Error -> renderError()
            is MovieList.Loading -> renderLoading(state.isLoading)
        }
    }

    private fun renderSuccess(list: List<String>) {
        adapter.setData(list)
    }

    private fun renderError() {
        binding.recycler.setBackgroundColor(resources.getColor(R.color.black))
    }

    private fun renderLoading(isLoading: Boolean) {
        binding.progress.isVisible = isLoading
    }

}