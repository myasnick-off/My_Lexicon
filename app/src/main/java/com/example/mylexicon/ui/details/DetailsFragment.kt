package com.example.mylexicon.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.mylexicon.databinding.FragmentDetailsBinding
import com.example.mylexicon.model.Word

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding!!

    private val word: Word? by lazy {
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
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() = with(binding) {
        word?.let {
            headerTextview.text = it.text
            transcriptionTextview.text = "[${it.meanings?.first()?.transcription.orEmpty()}]"
            descriptionTextview.text = it.meanings?.first()?.translation?.text.orEmpty()
            noteTextview.text = it.meanings?.first()?.translation?.note.orEmpty()
        }
    }

    companion object {
        private const val ARG_WORD = "arg_word"

        fun newInstance(word: Word) = DetailsFragment().apply {
            arguments = bundleOf(ARG_WORD to word)
        }
    }
}