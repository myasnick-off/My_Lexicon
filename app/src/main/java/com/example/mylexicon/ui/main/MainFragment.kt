package com.example.mylexicon.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.mylexicon.R
import com.example.mylexicon.databinding.FragmentMainBinding
import com.example.mylexicon.model.AppState
import com.example.mylexicon.model.Word
import com.example.mylexicon.ui.base.BaseFragment
import com.example.mylexicon.ui.base.IPresenter
import com.example.mylexicon.ui.base.BaseView
import com.example.mylexicon.ui.dialog.SearchDialogFragment
import com.example.mylexicon.ui.main.adapter.ItemClickListener
import com.example.mylexicon.ui.main.adapter.MainAdapter
import com.example.mylexicon.utils.hide
import com.example.mylexicon.utils.show

class MainFragment : BaseFragment<AppState>() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    private val adapter = MainAdapter(object : ItemClickListener {
        override fun onItemClick(item: Word) {
            Toast.makeText(requireContext(), item.text, Toast.LENGTH_SHORT).show()
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
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

    override fun createPresenter(): IPresenter<AppState, BaseView> {
        return MainPresenter()
    }

    override fun renderData(state: AppState) {
        when (state) {
            AppState.Loading -> showLoading()
            is AppState.Success -> showResult(state.data)
            is AppState.Error -> showErrorScreen(state.error.message)
        }
    }

    private fun initView() = with(binding) {
        mainRecyclerview.adapter = adapter
        searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) { presenter.getData(searchWord, true) }
            })
            searchDialogFragment.show(parentFragmentManager, "")
        }
    }

    private fun showResult(words: List<Word>?) {
        if (words == null || words.isEmpty()) {
            showErrorScreen(getString(R.string.loading_error))
        } else {
            showSuccess()
            adapter.submitList(words)
        }
    }

    private fun showErrorScreen(error: String?) {
        showError()
        binding.errorTextview.text = error ?: getString(R.string.loading_error)
        binding.reloadButton.setOnClickListener {
            presenter.getData("hi", true)
        }
    }

    private fun showSuccess() {
        binding.successBlock.show()
        binding.loadingBlock.hide()
        binding.errorBlock.hide()
    }

    private fun showLoading() {
        binding.successBlock.hide()
        binding.loadingBlock.show()
        binding.errorBlock.hide()
    }

    private fun showError() {
        binding.successBlock.hide()
        binding.loadingBlock.hide()
        binding.errorBlock.show()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}