package com.example.data.di

import androidx.room.Room
import com.example.data.database.UserDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    factory { get<UserDatabase>().userDao }

    single {
        Room.databaseBuilder(androidContext(), UserDatabase::class.java, "user_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}
