package com.example.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.core.R
import com.example.core.databinding.CustomToolbarBinding

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyle: Int = 0
) : Toolbar(
    context,
    attrs,
    defStyle
) {

    private val binding: CustomToolbarBinding =
        CustomToolbarBinding.inflate(LayoutInflater.from(context), this)

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar, defStyle, 0)
        val title = typedArray.getString(R.styleable.CustomToolbar_ctbTitle)
        val changeSettings =
            typedArray.getBoolean(R.styleable.CustomToolbar_ctbIsBtnSettings, false)
        val changeSort =
            typedArray.getBoolean(R.styleable.CustomToolbar_ctbIsBtnSort, false)

        val backBtn = typedArray.getBoolean(R.styleable.CustomToolbar_ctbIsBtnBack, false)
        setTitle(title)
        setButtonSettingsEnabled(changeSettings)
        setButtonSortEnabled(changeSort)
        setButtonBackEnabled(backBtn)
    }

    private fun setTitle(title: String?) {
        binding.headingMainScreen.text = title
    }

    private fun setButtonSettingsEnabled(isEnabled: Boolean) {
        binding.settings.visibility = if (isEnabled) View.VISIBLE else View.GONE
    }

    private fun setButtonSortEnabled(isEnabled: Boolean) {
        binding.sortCars.visibility = if (isEnabled) View.VISIBLE else View.GONE
    }

    private fun setButtonBackEnabled (isEnabled: Boolean){
        binding.backBtn.visibility = if (isEnabled) View.VISIBLE else View.GONE
    }


    fun setSettingsClickListener(action: () -> Unit) {
        binding.backBtn.setOnClickListener {
            action.invoke()
        }
    }

}