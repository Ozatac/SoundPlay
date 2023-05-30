package com.tunahanozatac.soundplay.ui.searchscreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tunahanozatac.soundplay.base.BaseFragment
import com.tunahanozatac.soundplay.data.entity.DataList
import com.tunahanozatac.soundplay.databinding.FragmentSearchBinding
import com.tunahanozatac.soundplay.utils.Resource
import com.tunahanozatac.soundplay.utils.gone
import com.tunahanozatac.soundplay.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    private val dataListAdapter by lazy { DataListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchSearchData("jack johnson", viewModel.limit)
        initAdapters()
        addListeners()
    }

    private fun initAdapters() {
        binding.dataRecyclerView.adapter = dataListAdapter
    }

    private fun addListeners() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (binding.searchView.query.length <= 2) {
                    viewModel.query = newText!!
                    viewModel.limit = 20
                    viewModel.dataList?.clear()
                    resetRecyclerView()
                    fetchSearchData("jack johnson", viewModel.limit)
                } else {
                    viewModel.query = newText!!
                    viewModel.limit = 20
                    viewModel.dataList?.clear()
                    resetRecyclerView()
                    fetchSearchData(viewModel.query, viewModel.limit)
                }
                return true
            }
        })

        dataListAdapter.addListener(object : IDataOnClickListener {
            override fun onClick(dataList: DataList) {
                val action =
                    SearchFragmentDirections.actionSearchFragmentToDetailFragment(dataList.trackId)
                findNavController().navigate(action)
                dataListAdapter.removeListener()
            }
        })

        binding.dataRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.dataRecyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (viewModel.limit == viewModel.dataList?.size) {
                        viewModel.limit += 20
                        fetchSearchData(viewModel.query, viewModel.limit)
                    }
                }
            }
        })
    }

    private fun fetchSearchData(query: String, limit: Int) {
        if (query.length > 2) {
            viewModel.getDataByQuery(query, limit).observe(viewLifecycleOwner) { response ->
                when (response.status) {
                    Resource.Status.LOADING -> {
                        binding.loadingAnimation.show()
                        binding.dataRecyclerView.gone()
                        binding.notFoundTextView.gone()
                    }

                    Resource.Status.SUCCESS -> {
                        binding.loadingAnimation.gone()
                        binding.dataRecyclerView.show()
                        binding.notFoundTextView.gone()
                        viewModel.dataList = response.data?.results
                        setData(viewModel.dataList)

                    }

                    Resource.Status.ERROR -> {
                        binding.loadingAnimation.gone()
                        binding.dataRecyclerView.gone()
                        Toast.makeText(requireContext(), "Error! Try Again", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

    private fun setData(dataList: ArrayList<DataList>?) {
        dataListAdapter.setDataList(dataList)
    }

    private fun resetRecyclerView() {
        binding.dataRecyclerView.scrollToPosition(0)
    }
}