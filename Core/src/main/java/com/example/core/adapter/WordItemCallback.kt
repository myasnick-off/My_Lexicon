package com.example.core.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.core.ui.model.UiWord

object WordItemCallback : DiffUtil.ItemCallback<UiWord>() {

    override fun areItemsTheSame(oldItem: UiWord, newItem: UiWord) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UiWord, newItem: UiWord) =
        oldItem == newItem
}