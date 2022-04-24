package com.example.core.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.core.model.Word

object WordItemCallback : DiffUtil.ItemCallback<Word>() {

    override fun areItemsTheSame(oldItem: Word, newItem: Word) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Word, newItem: Word) =
        oldItem == newItem
}