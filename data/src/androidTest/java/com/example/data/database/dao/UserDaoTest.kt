package com.example.data.database.dao

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.database.UserDatabase
import com.example.data.database.databaseTestModule
import com.example.data.database.model.UserEntity
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class UserDaoTest : KoinTest {

    private val userDatabase: UserDatabase by inject()
    private val userDao: UserDao by inject()

    @Before
    fun before() {
        startKoin { androidContext(ApplicationProvider.getApplicationContext()) }
        loadKoinModules(databaseTestModule)
    }

    @After
    fun after() {
        userDatabase.close()
        stopKoin()
    }

    @Test
    fun add_users_should_work() {
        val fiedler = UserEntity("0F8JIqi4zwvb77FGz6Wt", "Fiedler", "Heinz-Georg", "heinz-georg.fiedler@example.com", "mr", "https://randomuser.me/api/portraits/men/81.jpg")
        val hughes = UserEntity("0P6E1d4nr0L1ntW8cjGU", "Hughes", "Katie", "katie.hughes@example.com", "miss", "https://randomuser.me/api/portraits/women/74.jpg")
        val userList = listOf(fiedler, hughes)

        userDao.addUsers(userList)

        val userFromDb = userDao.findAllUsers()

        Assert.assertEquals(userList, userFromDb)
    }
}