package com.tunahanozatac.soundplay.ui.favoritescreen

import androidx.lifecycle.ViewModel
import com.tunahanozatac.soundplay.data.ApiRepository
import com.tunahanozatac.soundplay.data.entity.DataList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private var apiRepository: ApiRepository
) : ViewModel() {

    var favoriteList: ArrayList<DataList>? = arrayListOf()

    fun getFavoriteItems() {
        favoriteList = apiRepository.getFavoriteItems() as ArrayList<DataList>
    }
}