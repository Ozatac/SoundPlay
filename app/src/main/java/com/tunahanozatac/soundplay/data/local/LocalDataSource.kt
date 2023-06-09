package com.tunahanozatac.soundplay.data.local

import com.tunahanozatac.soundplay.data.entity.DataList
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val sharedPrefManager: SharedPrefManager,
    private val itemDao: ItemDao
) {

    fun getFavoriteItems(): List<DataList> {
        return itemDao.getFavoriteItems()
    }

    fun addFavoriteItem(item: DataList) {
        itemDao.addFavoriteItem(item)
    }

    fun deleteFavoriteItem(item: DataList) {
        itemDao.deleteFavoriteItem(item)
    }
}