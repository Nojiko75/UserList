package com.example.userlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.domain.model.UserFullProfile
import com.example.userlist.databinding.FragmentUserFullProfileBinding

class UserFullProfileFragment  : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(user: UserFullProfile) = UserFullProfileFragment().apply {
            arguments = Bundle().apply {
                putParcelable("user", user)
            }
        }
    }

    private var user: UserFullProfile? = null
    private lateinit var mViewDataBinding: FragmentUserFullProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.user = user
        mViewDataBinding.id.text = user?.id
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        user = arguments?.getParcelable("user")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewDataBinding  = DataBindingUtil.inflate(inflater,
            R.layout.fragment_user_full_profile, container, false)
        mViewDataBinding.lifecycleOwner = this
        return mViewDataBinding.root
    }
}