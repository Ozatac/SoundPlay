package com.tunahanozatac.soundplay.ui.detailscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tunahanozatac.soundplay.data.ApiRepository
import com.tunahanozatac.soundplay.data.entity.BaseResponse
import com.tunahanozatac.soundplay.data.entity.DataList
import com.tunahanozatac.soundplay.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getDetailById(id: String?): LiveData<Resource<BaseResponse>> {
        return apiRepository.getDetailById(id)
    }

    fun getFavoriteItems(): List<DataList> {
        return apiRepository.getFavoriteItems()
    }

    fun addFavoriteItem(item: DataList) {
        apiRepository.addFavoriteItem(item)
    }

    fun deleteFavoriteItem(item: DataList) {
        apiRepository.deleteFavoriteItem(item)
    }
}