package com.example.userlist.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.fragment_user_list.*

abstract class BaseFragment<T: ViewDataBinding?, V: ViewModel> : Fragment() {

    protected var binding: T? = null
    protected abstract val viewModel: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            getFragmentView(),
            container,
            false
        )
        binding?.lifecycleOwner = this
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewReady()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    abstract fun getFragmentView(): Int

    abstract fun getViewModel(): Class<V>

    abstract fun viewReady()

    open fun showLoading(progressBar: ProgressBar) {
        (activity as BaseActivity).showLoading(progressBar)
    }

    open fun hideLoading(progressBar: ProgressBar) {
        (activity as BaseActivity).hideLoading(progressBar)
    }

    open fun handleViewState(viewState: ViewState<Any>) {
        when (viewState) {
            is Loading -> showLoading(progressBar)
            is Success -> showData(viewState.data)
            is Error -> handleError(viewState.error.localizedMessage)
            is NoInternetState -> showNoInternetError()
        }
    }

    private fun handleError(error: String?) {
        hideLoading(progressBar)
    }

    private fun showNoInternetError() {
        hideLoading(progressBar)
    }

    abstract fun showData(data: Any)
}