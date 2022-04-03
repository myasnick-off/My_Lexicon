package com.example.mylexicon.ui.dialog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.example.mylexicon.databinding.FragmentSearchDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSearchDialogBinding? = null
    private val binding get() = _binding!!
    private var onSearchClickListener: OnSearchClickListener? = null

    private val onSearchButtonClickListener = View.OnClickListener {
        onSearchClickListener?.onClick(binding.searchEditText.text.toString())
        dismiss()
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
            with(binding) {
                if (searchEditText.text != null && searchEditText.text.toString().isNotEmpty()) {
                    searchButtonTextview.isEnabled = true
                    clearTextImageview.visibility = VISIBLE
                } else {
                    searchButtonTextview.isEnabled = false
                    clearTextImageview.visibility = GONE
                }
            }
        override fun afterTextChanged(s: Editable?) {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchDialogBinding.inflate(inflater, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroyView() {
        _binding = null
        onSearchClickListener = null
        super.onDestroyView()
    }

    private fun initView() = with(binding) {
        searchEditText.addTextChangedListener(textWatcher)
        searchButtonTextview.setOnClickListener(onSearchButtonClickListener)
        clearTextImageview.setOnClickListener { clearSearch() }
    }

    fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    private fun clearSearch() {
        binding.searchEditText.setText("")
        binding.searchButtonTextview.isEnabled = false
    }

    interface OnSearchClickListener {
        fun onClick(searchWord: String)
    }

    companion object {
        fun newInstance() = SearchDialogFragment()
    }
}