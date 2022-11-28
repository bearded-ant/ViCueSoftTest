package com.tests.vicuesofttest.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tests.vicuesofttest.R

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewHolder>() {

    private val colors = intArrayOf(
        Color.BLUE,
        Color.CYAN,
        Color.GRAY,
        Color.DKGRAY,
        Color.LTGRAY,
        Color.GREEN,
        Color.MAGENTA,
        Color.RED,
        Color.YELLOW
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.image.setBackgroundColor(colors[position])
    }

    override fun getItemCount(): Int = colors.size
}