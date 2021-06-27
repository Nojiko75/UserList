package com.example.userlist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.domain.model.UserFullProfile
import com.example.userlist.databinding.FragmentUserFullProfileBinding
import com.example.userlist.util.parseDate
import com.squareup.picasso.Picasso

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
        Picasso.get()
            .load(mViewDataBinding.user?.picture)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(mViewDataBinding.picture)

        val dateOfBirth = parseDate(mViewDataBinding.user?.dateOfBirth)
        mViewDataBinding.dateOfBirth.text = dateOfBirth

        val registerDate = parseDate(mViewDataBinding.user?.registerDate)
        mViewDataBinding.registerDate.text = registerDate
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        user = arguments?.getParcelable("user")
        Log.d("APP", user.toString())
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