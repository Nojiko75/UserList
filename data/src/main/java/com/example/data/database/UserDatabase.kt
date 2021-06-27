package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.database.converter.LocationConverter
import com.example.data.database.dao.UserDao
import com.example.data.database.model.UserEntity
import com.example.domain.model.Location

@Database(
    entities = [UserEntity::class],
    version = 4, exportSchema = false
)

@TypeConverters(LocationConverter::class)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}