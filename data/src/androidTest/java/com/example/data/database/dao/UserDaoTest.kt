package com.example.data.database.dao

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.database.UserDatabase
import com.example.data.database.databaseTestModule
import com.example.data.database.model.UserEntity
import com.example.domain.model.Location
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
        val fiedler = UserEntity("0F8JIqi4zwvb77FGz6Wt", "mr", "Heinz-Georg", "Fiedler", null, "heinz-georg.fiedler@example.com", null,null,null,"https://randomuser.me/api/portraits/men/81.jpg", null, null)
        val hughes = UserEntity("0P6E1d4nr0L1ntW8cjGU", "miss", "Katie", "Hughes", null, "katie.hughes@example.com",null, null, null,"https://randomuser.me/api/portraits/women/74.jpg", null, null)
        val userList = listOf(fiedler, hughes)

        userDao.addUsers(userList)

        val userFromDb = userDao.findAllUsers()

        Assert.assertEquals(userList, userFromDb)
    }

    @Test
    fun add_user_full_profile_should_work() {
        val location = Location("9614, SÃ¸ndermarksvej", "Kongsvinger", "Nordjylland", "Denmark", "-9:00")
        val andersen = UserEntity("60d0fe4f5311236168a109ca", "mrs", "Sarah", "Andersen", "female", "sarah.andersen@example.com", "1996-04-30T19:26:49.610Z", "2021-06-21T21:02:07.374Z", "92694011", "https://randomuser.me/api/portraits/women/58.jpg", location, "2021-06-21T21:02:07.374Z")

        userDao.addUserFullProfile(andersen)

        val userFromDb = userDao.getUserById("60d0fe4f5311236168a109ca")

        Assert.assertEquals(andersen, userFromDb)
    }
}