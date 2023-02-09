package com.app.commons.presentation.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
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
    val data = MutableLiveData<MovieList>()
    var title = ""
    init {
        binding = LayoutListCustomViewBinding.inflate(LayoutInflater.from(context), this, true)

        attrs?.let {

            val typedArray = context.obtainStyledAttributes(it, R.styleable.PosterListCustomView)
            title = typedArray.getString(R.styleable.PosterListCustomView_custom_component_titles).orEmpty()

            typedArray.recycle()
        }


        binding.recycler.layoutManager =  LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recycler.adapter =  PosterListCustomViewAdapter(listOf(title.toString(),"321","123","123","123","123"))
    }

}