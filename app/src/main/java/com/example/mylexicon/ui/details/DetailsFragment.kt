package com.example.mylexicon.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.core.ui.model.UiWord
import com.example.mylexicon.R
import com.example.mylexicon.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding!!

    private val word: UiWord? by lazy {
        requireArguments().getParcelable(ARG_WORD)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() = with(binding) {
        word?.let {
            headerTextview.text = it.word
            transcriptionTextview.text = "[${it.transcription}]"
            descriptionTextview.text = it.translation
            noteTextview.text = it.note
            loadImage(imageUrl = it.imageUrl)
        }
    }

    private fun loadImage(imageUrl: String?) {
        imageUrl?.let { url ->
            Glide.with(this)
                .load("https:$url")
                .placeholder(R.drawable.ic_baseline_wallpaper_96)
                .error(R.drawable.ic_baseline_broken_image_96)
                .transition(DrawableTransitionOptions.withCrossFade(CROSS_FADE_DURATION))
                .transform(MultiTransformation(CenterCrop(), RoundedCorners(CORNER_RADIUS)))
                .into(binding.descriptionImageview)
        }
    }

    companion object {
        private const val ARG_WORD = "arg_word"
        private const val CROSS_FADE_DURATION = 1000
        private const val CORNER_RADIUS = 20

        fun newInstance(word: UiWord) = DetailsFragment().apply {
            arguments = bundleOf(ARG_WORD to word)
        }
    }
}