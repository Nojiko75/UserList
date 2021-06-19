package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.model.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM Users")
    fun findAllUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUsers(users: List<UserEntity>)
}