package com.example.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.userlist.databinding.FragmentUserFullProfileBinding
import com.example.userlist.util.parseDate
import com.example.userlist.viewmodel.UserViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFullProfileFragment  : Fragment() {

    private lateinit var userID: String
    private var mViewDataBinding: FragmentUserFullProfileBinding? = null
    private val binding get() = mViewDataBinding!!
    private val userViewModel by viewModel<UserViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = userViewModel
        userID = UserFullProfileFragmentArgs.fromBundle(requireArguments()).userArgs
        userViewModel.getUserFullProfile(userID)
        userViewModel.userFullProfile.observe(viewLifecycleOwner, { userFullProfile ->
            if (userFullProfile !=  null) {
                binding.user = userFullProfile
                Picasso.get()
                    .load(binding.user?.picture)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.picture)

                val dateOfBirth = parseDate(binding.user?.dateOfBirth)
                binding.dateOfBirth.text = dateOfBirth

                val registerDate = parseDate(binding.user?.registerDate)
                binding.registerDate.text = registerDate
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewDataBinding  = DataBindingUtil.inflate(inflater,
            R.layout.fragment_user_full_profile, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewDataBinding = null
    }
}