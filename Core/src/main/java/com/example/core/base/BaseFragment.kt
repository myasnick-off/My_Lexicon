package com.example.core.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.core.R
import com.example.core.model.AppState
import com.example.core.utils.OnlineLiveData

abstract class BaseFragment<T: AppState>: Fragment() {

    abstract val viewModel: BaseViewModel<T>

    protected var isOnline: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNetworkState()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    abstract fun renderData(appState: T)

    private fun observeNetworkState() {
        OnlineLiveData(requireContext()).observe(viewLifecycleOwner, ::renderState)
    }

    private fun renderState(isOnline: Boolean) {
        this.isOnline = isOnline
        if (!isOnline) {
            Toast
                .makeText(requireContext(), R.string.message_device_is_offline, Toast.LENGTH_LONG)
                .show()
        }
    }
}