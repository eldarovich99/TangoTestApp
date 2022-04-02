package com.eldarovich99.tangotestapp.ui

import androidx.lifecycle.ViewModel
import com.eldarovich99.tangotestapp.data.ProductRepository
import com.eldarovich99.tangotestapp.data.model.ProductNetworkModelItem
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ProductViewModel: ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val repository = ProductRepository()
    private val productsSubject = BehaviorRelay.create<List<ProductNetworkModelItem>>()
    private val searchSubject = BehaviorRelay.create<String>()
    private val loadingSubject = BehaviorRelay.create<Boolean>()
    private val errorSubject = BehaviorRelay.create<String>()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        repository.retreiveProducts().subscribeOn(
            Schedulers.io()
        ).subscribeBy(
            onSuccess = {

            },
            onError = {

            }
        ).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}