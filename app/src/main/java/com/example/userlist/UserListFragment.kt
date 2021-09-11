package com.example.userlist

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.UserListItem
import com.example.userlist.databinding.FragmentUserListBinding
import com.example.userlist.ui.UserClickListener
import com.example.userlist.ui.UserRecyclerAdapter
import com.example.userlist.ui.base.BaseFragment
import com.example.userlist.util.subscribe
import com.example.userlist.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : BaseFragment<FragmentUserListBinding?, UserViewModel>(), UserClickListener {
    private lateinit var userAdapter: UserRecyclerAdapter
    override val viewModel by viewModel<UserViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewReady()
    }

    override fun onItemClick(user: UserListItem) {
        val action = UserListFragmentDirections.actionUserListFragmentToUserFullProfileFragment(user.id)
        findNavController().navigate(action)
    }

    override fun getFragmentView() = R.layout.fragment_user_list

    override fun getViewModel() = UserViewModel::class.java

    override fun viewReady() {
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        userAdapter = UserRecyclerAdapter(context, this)
        recyclerView.adapter = userAdapter
        recyclerView.isNestedScrollingEnabled = false

        binding?.viewModel = viewModel
        viewModel.getUserList()
        viewModel.viewState.subscribe(this, ::handleViewState)
    }

    override fun showData(data: Any) {
        hideLoading(progressBar)
        userAdapter.setUsers(data as List<UserListItem>)
    }
}