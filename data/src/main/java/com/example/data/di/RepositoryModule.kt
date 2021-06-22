package com.example.data.di

import com.example.data.repository.UserRepositoryImpl
import com.example.data.util.NetworkStateManager
import com.example.data.util.NetworkStateManagerImpl
import com.example.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
    factory<NetworkStateManager> { NetworkStateManagerImpl(androidContext()) }
}
