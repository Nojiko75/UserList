package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.api.model.Location
import com.example.data.database.converter.LocationConverter
import com.example.data.database.dao.UserDao
import com.example.data.database.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1, exportSchema = false
)

@TypeConverters(LocationConverter::class)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}