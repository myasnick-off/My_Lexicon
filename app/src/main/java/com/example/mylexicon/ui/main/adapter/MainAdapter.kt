package com.example.mylexicon.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mylexicon.databinding.FragmentMainItemBinding
import com.example.mylexicon.model.Word

class MainAdapter(
    private val listener: ItemClickListener
    ): ListAdapter<Word, MainAdapter.WordViewHolder>(WordItemCallback) {

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
        fun bind(item: Word) = with(binding) {
            headerTextview.text = item.text
            descriptionTextview.text = item.meanings?.first()?.translation?.text.orEmpty()
            itemView.setOnClickListener { listener.onItemClick(item) }
        }
    }
}