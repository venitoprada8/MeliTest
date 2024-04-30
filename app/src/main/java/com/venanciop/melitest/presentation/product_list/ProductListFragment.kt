package com.venanciop.melitest.presentation.product_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.venanciop.melitest.R
import com.venanciop.melitest.data.api.service.RetrofitClient
import com.venanciop.melitest.data.repository.SearchRepositoryImpl
import com.venanciop.melitest.databinding.FragmentProductlistBinding
import com.venanciop.melitest.domain.model.ProductDomainModel
import com.venanciop.melitest.domain.GetProducts
import com.venanciop.melitest.presentation.product_list.adapter.ProductListAdapter


class ProductListFragment : Fragment(R.layout.fragment_productlist) {

    private lateinit var binding: FragmentProductlistBinding
    private lateinit var adapter: ProductListAdapter


    private val viewModel by viewModels<ProductListViewModel> {
        ProductListViewModelFactory(GetProducts(SearchRepositoryImpl(RetrofitClient.retrofit)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductlistBinding.bind(view)
        setupViewModel()
        setupUI()
        initSearchView()
    }

    //view model
    private fun setupViewModel() {
        viewModel.products.observe(viewLifecycleOwner, fetchProducts)
        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.isEmptyList.observe(viewLifecycleOwner, emptyListObserver)

    }

    //ui
    private fun setupUI() {
        adapter = ProductListAdapter()
        binding.recyclerView.adapter = adapter
        adapter.onItemClickListener = { product ->
            val action =
                ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
                    product.price,
                    product.productImage,
                    product.title,
                    product.availableQuantity,
                )
            findNavController().navigate(action)
        }
    }

    private val fetchProducts = Observer<List<ProductDomainModel>> { result ->

        adapter.submitList(result)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        val visibility = if (it) View.VISIBLE else View.GONE
        binding.progressBar.visibility = visibility
    }


    private val emptyListObserver = Observer<Boolean> {
        Glide.with(requireContext()).load(R.drawable.bandeja_vacia).centerCrop().into(binding.ivEmptyView)
        val visibility = if (it) View.VISIBLE else View.GONE
        binding.ivEmptyView.visibility = visibility
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getProducts(query)
                return false
            }

        })
    }

}