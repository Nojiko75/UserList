package com.example.data.database

import androidx.room.Room
import org.koin.dsl.module

val databaseTestModule = module(override = true) {
    factory { get<UserDatabase>().userDao }

    single {
        Room.inMemoryDatabaseBuilder(get(), UserDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
}