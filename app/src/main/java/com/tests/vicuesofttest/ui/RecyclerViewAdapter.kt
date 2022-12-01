package com.tests.vicuesofttest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tests.vicuesofttest.R
import com.tests.vicuesofttest.domain.DataResponse

class RecyclerViewAdapter(
    private val data: List<DataResponse>,
    private val posterClickListener: OnPosterClickListener
) :
    RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        Picasso.get().load(data[position].smallPosterUrl).into(holder.image)
        holder.card.setOnClickListener {
            posterClickListener.onPosterClick(data[position].fileUrl)
        }
    }

    override fun getItemCount(): Int = data.size
}