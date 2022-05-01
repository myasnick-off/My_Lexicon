package com.example.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.databinding.FragmentMainItemBinding
import com.example.core.ui.model.UiWord

class MainAdapter(
    private val listener: ItemClickListener
    ): ListAdapter<UiWord, MainAdapter.WordViewHolder>(WordItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WordViewHolder(
            FragmentMainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class WordViewHolder(
        private val binding: FragmentMainItemBinding
        ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UiWord) = with(binding) {
            headerTextview.text = item.word
            descriptionTextview.text = item.translation
            itemView.setOnClickListener { listener.onItemClick(item) }
        }
    }
}