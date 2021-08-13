package com.example.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.UserListItem
import com.example.userlist.databinding.FragmentUserListBinding
import com.example.userlist.ui.UserClickListener
import com.example.userlist.ui.UserRecyclerAdapter
import com.example.userlist.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment(), UserClickListener {
    private val userViewModel by viewModel<UserViewModel>()
    private lateinit var userAdapter: UserRecyclerAdapter
    private var viewDataBinding: FragmentUserListBinding? = null
    private val binding get() = viewDataBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        userAdapter = UserRecyclerAdapter(context, this)
        recyclerView.adapter = userAdapter
        recyclerView.isNestedScrollingEnabled = false

        binding.viewModel = userViewModel

        userViewModel.getUserList()
        userViewModel.userList.observe(viewLifecycleOwner, { userList ->
            if (userList.isNotEmpty() && userList != null) {
                userAdapter.setUsers(userList)
            }
        })
    }

    override fun onItemClick(user: UserListItem) {
        val action = UserListFragmentDirections.actionUserListFragmentToUserFullProfileFragment(user.id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDataBinding = null
    }
}