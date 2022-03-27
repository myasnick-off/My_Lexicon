package com.example.mylexicon.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.example.mylexicon.R
import com.example.mylexicon.databinding.FragmentMainBinding
import com.example.mylexicon.model.AppState
import com.example.mylexicon.model.Word
import com.example.mylexicon.presenter.IPresenter
import com.example.mylexicon.presenter.MainPresenter
import com.example.mylexicon.ui.base.BaseFragment
import com.example.mylexicon.ui.base.View
import com.example.mylexicon.ui.main.adapter.ItemClickListener
import com.example.mylexicon.ui.main.adapter.MainAdapter

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

    override fun createPresenter(): IPresenter<AppState, View> {
        return MainPresenter()
    }

    override fun renderData(state: AppState) = with(binding) {
        when (state) {
            is AppState.Loading -> showLoading()
            is AppState.Success -> showResult(state.data)
            is AppState.Error -> showErrorScreen(state.error.message)
        }
    }

    private fun initView() = with(binding) {
        mainRecyclerview.adapter = adapter
        searchFab.setOnClickListener {
//            TODO("Not yet implemented")
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

    private fun showErrorScreen(error: String?) = with(binding) {
        showError()
        errorTextview.text = error ?: getString(R.string.loading_error)
        reloadButton.setOnClickListener {
            presenter.getData("hi", true)
        }
    }

    private fun showSuccess() = with(binding) {
        successBlock.visibility = VISIBLE
        loadingBlock.visibility = GONE
        errorBlock.visibility = GONE
    }

    private fun showLoading() = with(binding) {
        successBlock.visibility = GONE
        loadingBlock.visibility = VISIBLE
        errorBlock.visibility = GONE
    }

    private fun showError() = with(binding) {
        successBlock.visibility = GONE
        loadingBlock.visibility = GONE
        errorBlock.visibility = VISIBLE
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}