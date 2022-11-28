package com.tests.vicuesofttest.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import com.tests.vicuesofttest.R

class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val card: MaterialCardView = itemView.findViewById(R.id.itemPosterCard)
    val image: ShapeableImageView = itemView.findViewById(R.id.itemPosterImage)
}