package com.venanciop.melitest.presentation.product_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.venanciop.melitest.R
import com.venanciop.melitest.databinding.FragmentProductDetailBinding


class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private lateinit var binding : FragmentProductDetailBinding
    private val args by navArgs<ProductDetailFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailBinding.bind(view)
        setupView()
        backButtonListener()
    }


    private fun setupView(){
        Glide.with(requireContext()).load(args.image).centerCrop().into(binding.ivProduct)
        binding.tvProductTitle.text = args.title
        binding.txtProductPrice.text = args.price
        binding.txtProductAvailableQuantity.text = args.availableQuantity.toString()

    }


    private fun backButtonListener() {
        binding.ibBackButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }





}