package com.tunahanozatac.soundplay.ui.favoritescreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tunahanozatac.soundplay.ui.searchscreen.IDataOnClickListener
import com.tunahanozatac.soundplay.base.BaseFragment
import com.tunahanozatac.soundplay.data.entity.DataList
import com.tunahanozatac.soundplay.databinding.FragmentFavoriteListBinding
import com.tunahanozatac.soundplay.utils.hide
import com.tunahanozatac.soundplay.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteListFragment :
    BaseFragment<FragmentFavoriteListBinding>(FragmentFavoriteListBinding::inflate) {

    private val viewModel: FavoriteListViewModel by viewModels()
    private val favoriteListAdapter by lazy { FavoriteListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapters()
        getFavoriteItems()
        addListeners()
    }

    private fun initAdapters() {
        binding.favoriteRecyclerView.adapter = favoriteListAdapter
    }

    private fun getFavoriteItems() {
        viewModel.getFavoriteItems()
        if (viewModel.favoriteList.isNullOrEmpty()){
            binding.favoriteRecyclerView.hide()
            binding.messageTextView.show()

        }else{
            binding.favoriteRecyclerView.show()
            binding.messageTextView.hide()
            favoriteListAdapter.setFavoriteList(viewModel.favoriteList)
        }
    }

    private fun addListeners() {
        favoriteListAdapter.addListener(object : IDataOnClickListener {
            override fun onClick(dataList: DataList) {
                val action = FavoriteListFragmentDirections.actionFavoriteListFragmentToDetailFragment(dataList.trackId)
                findNavController().navigate(action)
                favoriteListAdapter.removeListener()
            }
        })
    }
}