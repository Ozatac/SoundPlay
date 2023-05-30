package com.tunahanozatac.soundplay.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tunahanozatac.soundplay.data.entity.DataList
import com.tunahanozatac.soundplay.data.local.ItemDao

@Database(entities = [DataList::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}