package com.app.commons.customView

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.app.commons.R
import com.app.commons.databinding.ItemCustomToolbarBinding

class CustomToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var titleToolbar = ""
    private var iconToolbar : Drawable? = null
    private var binding: ItemCustomToolbarBinding

    init {
        binding = ItemCustomToolbarBinding.inflate(LayoutInflater.from(context), this, true)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomToolbar, 0, 0)
            titleToolbar = typedArray.getString(R.styleable.CustomToolbar_toolbar_title).orEmpty()
            iconToolbar = typedArray.getDrawable(R.styleable.CustomToolbar_toolbar_icon)
            typedArray.recycle()
        }
        setTitle(titleToolbar)
        setIcon(iconToolbar)
    }

    private fun setTitle(title: String) = with(binding) {
        this.title.text = title
    }

    private fun setIcon(icon: Drawable?) = with(binding) {
        this.icEnd.setImageDrawable(icon)
    }

//
//
//    init {
//        val binding = ItemCustomToolbarBinding.inflate(LayoutInflater.from(context), this, true)
//
//        inflate(context, R.layout.item_custom_toolbar, binding.root)
//        attrs?.let {
//            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomToolbar, 0, 0)
//            titleToolbar = typedArray.getString(R.styleable.CustomToolbar_toolbar_title).orEmpty()
//            typedArray.recycle()
//        }
//        binding.title.text = titleToolbar
//    }
//


}

//
//class PosterListCustomView @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyle: Int = 0,
//    defStyleRes: Int = 0
//) : ConstraintLayout(context, attrs, defStyle)  {
//
//    private lateinit var binding: LayoutListCustomViewBinding
//    private val adapter = PosterListCustomViewAdapter(listOf())
//    var title = ""
//    init {
//        binding = LayoutListCustomViewBinding.inflate(LayoutInflater.from(context), this, true)
//
//        attrs?.let {
//
//            val typedArray = context.obtainStyledAttributes(it, R.styleable.PosterListCustomView)
//            title = typedArray.getString(R.styleable.PosterListCustomView_custom_component_titles).orEmpty()
//
//            typedArray.recycle()
//        }
//
//
//        binding.recycler.layoutManager =  LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        binding.recycler.adapter =  adapter
//    }
//
//    fun setState(state: MovieList) {
//        when(state) {
//            is MovieList.Success -> renderSuccess(state.list)
//            is MovieList.Error -> renderError()
//            is MovieList.Loading -> renderLoading(state.isLoading)
//        }
//    }
//
//    private fun renderSuccess(list: List<String>) {
//        adapter.setData(list)
//    }
//
//    private fun renderError() {
//        binding.recycler.setBackgroundColor(resources.getColor(R.color.black))
//    }
//
//    private fun renderLoading(isLoading: Boolean) {
//        binding.progress.isVisible = isLoading
//    }
//
//}