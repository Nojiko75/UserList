package com.example.data.repository

import com.example.data.api.UserApi
import com.example.data.database.dao.UserDao
import com.example.domain.repository.UserRepository
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mockito

class UserRepositoryImplTest : KoinTest {

    @Before
    fun setup() {
        val mockedApi = Mockito.mock(UserApi::class.java)
        val mockedDao = Mockito.mock(UserDao::class.java)

        val mockedModule = module {
            single { UserRepositoryImpl(get(), get(), androidContext()) }
            single { mockedApi }
            single { mockedDao }
        }

        loadKoinModules(mockedModule)
    }

    @Test
    fun get_user_list_should_work() {

    }

    @Test
    fun get_user_list_should_fail() {

    }
}