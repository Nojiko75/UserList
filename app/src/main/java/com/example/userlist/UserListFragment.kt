package com.example.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.UserListItem
import com.example.userlist.databinding.FragmentUserListBinding
import com.example.userlist.ui.UserClickListener
import com.example.userlist.ui.UserRecyclerAdapter
import com.example.userlist.util.replaceFragment
import com.example.userlist.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment(), UserClickListener {
    private val userViewModel by viewModel<UserViewModel>()
    private lateinit var userAdapter: UserRecyclerAdapter
    private lateinit var viewDataBinding: FragmentUserListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false)
        viewDataBinding.lifecycleOwner = this
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        userAdapter = UserRecyclerAdapter(context, this)
        recyclerView.adapter = userAdapter
        recyclerView.isNestedScrollingEnabled = false

        viewDataBinding.viewModel = userViewModel

        userViewModel.getUserList()
        userViewModel.userList.observe(viewLifecycleOwner, { userList ->
            if (userList.isNotEmpty() && userList != null) {
                userAdapter.setUsers(userList)
            }
        })
    }

    override fun onItemClick(user: UserListItem) {
        userViewModel.getUserFullProfile(user.id)
        userViewModel.userFullProfile.observe(viewLifecycleOwner, { userFullProfile ->
            if (userFullProfile !=  null) {
                (activity as MainActivity).replaceFragment(UserFullProfileFragment.newInstance(userFullProfile),
                    R.id.fragment_layout, "userFullProfile")
            }
        })
    }
}