package com.eldarovich99.tangotestapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.eldarovich99.tangotestapp.R
import kotlin.properties.Delegates

class ProductFragment: Fragment(R.layout.product_fragment) {
    private var viewModel: ProductViewModel by Delegates.notNull()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
    }
}