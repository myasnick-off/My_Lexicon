package com.example.mylexicon.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import android.widget.SearchView
import com.example.mylexicon.R
import com.example.mylexicon.databinding.FragmentHistoryBinding
import com.example.mylexicon.model.AppState
import com.example.mylexicon.model.Word
import com.example.mylexicon.ui.base.BaseFragment
import com.example.mylexicon.ui.details.DetailsFragment
import com.example.mylexicon.ui.base.adapter.ItemClickListener
import com.example.mylexicon.ui.base.adapter.MainAdapter
import com.example.mylexicon.utils.hide
import com.example.mylexicon.utils.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment<AppState>() {

    override val viewModel: HistoryViewModel by viewModel()

    private var _binding: FragmentHistoryBinding? = null
    private val binding: FragmentHistoryBinding
        get() = _binding!!

    private val adapter = MainAdapter(object : ItemClickListener {
        override fun onItemClick(item: Word) {
            parentFragmentManager.beginTransaction()
                .add(R.id.main_container, DetailsFragment.newInstance(word = item))
                .addToBackStack("")
                .commit()
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initData() {
        viewModel.liveData.observe( viewLifecycleOwner, ::renderData)
    }

    private fun initView() = with(binding) {
        historyRecyclerview.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_history, menu)
        initSearchMenu(menu)
    }

    private fun initSearchMenu(menu: Menu) {
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Search film..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(phrase: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(phrase: String?): Boolean {
                phrase?.let {
                    viewModel.getData(phrase, false)
                }
                return true
            }
        })
        searchView.setOnCloseListener {
            viewModel.loadData()
            false
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            AppState.Loading -> showLoading()
            is AppState.Success -> showResult(appState.data)
            is AppState.Error -> showError(appState.error.message)
        }
    }

    private fun showResult(words: List<Word>?) {
        if (words == null || words.isEmpty()) {
            showError(null)
        } else {
            showSuccess()
            adapter.submitList(words)
            binding.historyRecyclerview.smoothScrollToPosition(0)
        }
    }

    private fun showSuccess() {
        binding.historyRecyclerview.show()
        binding.historyProgressBar.hide()
        binding.historyErrorTextview.hide()
    }

    private fun showLoading() {
        binding.historyRecyclerview.hide()
        binding.historyProgressBar.show()
        binding.historyErrorTextview.hide()
    }

    private fun showError(error: String?) {
        binding.historyRecyclerview.hide()
        binding.historyProgressBar.hide()
        binding.historyErrorTextview.apply {
            this.show()
            this.text = error ?: getString(R.string.empty_list)
        }
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}