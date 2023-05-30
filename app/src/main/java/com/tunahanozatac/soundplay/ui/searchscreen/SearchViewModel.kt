package com.tunahanozatac.soundplay.ui.searchscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tunahanozatac.soundplay.data.ApiRepository
import com.tunahanozatac.soundplay.data.entity.BaseResponse
import com.tunahanozatac.soundplay.data.entity.DataList
import com.tunahanozatac.soundplay.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private var apiRepository: ApiRepository
) : ViewModel() {
    var dataList: ArrayList<DataList>? = arrayListOf()
    var mediaType: String? = "music"

    var limit = 20
    var query = ""

    init {
        getDataByQuery("jack", limit)
    }

    fun getDataByQuery(query: String, limit: Int): LiveData<Resource<BaseResponse>> {
        return apiRepository.getDataByQuery(query, mediaType, limit)
    }
}
