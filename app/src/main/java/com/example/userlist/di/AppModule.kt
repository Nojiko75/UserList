package com.example.userlist.di

import com.example.userlist.viewmodel.UserViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    viewModel { UserViewModel(get()) }
}