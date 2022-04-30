package com.example.mylexicon.ui.main

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.core.adapter.ItemClickListener
import com.example.core.adapter.MainAdapter
import com.example.core.base.BaseFragment
import com.example.core.model.AppState
import com.example.core.model.Word
import com.example.mylexicon.R
import com.example.mylexicon.databinding.FragmentMainBinding
import com.example.mylexicon.ui.details.DetailsFragment
import com.example.mylexicon.ui.dialog.SearchDialogFragment
import com.example.mylexicon.ui.history.HistoryFragment
import com.example.mylexicon.utils.hide
import com.example.mylexicon.utils.show
import com.example.mylexicon.utils.viewById
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.ext.android.inject
import org.koin.android.scope.getOrCreateScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.Scope

class MainFragment : BaseFragment<AppState>(), KoinScopeComponent {

    override val scope: Scope by getOrCreateScope()
    override val viewModel: MainViewModel by inject()

    private val mainRecyclerView by viewById<RecyclerView>(R.id.main_recyclerview)
    private val searchFab by viewById<FloatingActionButton>(R.id.search_fab)

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    private val adapter = MainAdapter(object : ItemClickListener {
        override fun onItemClick(item: Word) {
            parentFragmentManager.beginTransaction()
                .add(R.id.main_container, DetailsFragment.newInstance(word = item))
                .addToBackStack(null)
                .commit()
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_history -> runHistoryScreen()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initData() {
        viewModel.liveData.observe( viewLifecycleOwner, ::renderData)
    }

    private fun initView() {
        mainRecyclerView.adapter = adapter
        searchFab.setOnClickListener { showSearchDialog() }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            AppState.Loading -> showLoading()
            is AppState.Success -> showResult(appState.data)
            is AppState.Error -> showErrorScreen(appState.error.message)
        }
    }

    private fun runHistoryScreen() {
        parentFragmentManager.beginTransaction()
            .add(R.id.main_container, HistoryFragment.newInstance(), "")
            .addToBackStack("SearchFragment")
            .commit()
    }

    private fun showSearchDialog() {
        SearchDialogFragment.newInstance().apply {
            setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    viewModel.getData(searchWord, true)
                }
            })
        }.show(parentFragmentManager, "")
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
            viewModel.getData(getString(R.string.default_word), true)
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