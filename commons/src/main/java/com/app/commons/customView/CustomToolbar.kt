package com.app.commons.customView

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.app.commons.R
import com.app.commons.databinding.ItemCustomToolbarBinding

class CustomToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var titleToolbar = ""
    private var iconToolbar : Drawable? = null
    private var binding: ItemCustomToolbarBinding
    private var hasMovieSave: Boolean = false

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

    fun hasSaved(_hasSaved: Boolean, isInit: Boolean = false) {
        hasMovieSave = if(isInit) _hasSaved else _hasSaved.not()
        val iconResource = if(_hasSaved) R.drawable.ic_saved else R.drawable.ic_not_saved
        val icon = ResourcesCompat.getDrawable(resources, iconResource, null);
        setIcon(icon)
    }
    private fun setIcon(icon: Drawable?) = with(binding) {
        this.icEnd.setImageDrawable(icon)
    }

    fun setOnClickListener(onClickListener: () -> Unit) {
        binding.icBack.setOnClickListener {
            onClickListener.invoke()
        }
    }

    fun setOnIconListener(onIconListener: (Boolean) -> Unit) {
        binding.icEnd.setOnClickListener {
            hasSaved(hasMovieSave)
            onIconListener.invoke(hasMovieSave)
        }
    }
}