package com.example.domain.di

import com.example.domain.usecase.GetUserFullProfile
import com.example.domain.usecase.GetUserFullProfileImpl
import com.example.domain.usecase.GetUserList
import com.example.domain.usecase.GetUserListImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetUserList> { GetUserListImpl(get()) }
    factory<GetUserFullProfile> { GetUserFullProfileImpl(get()) }
}