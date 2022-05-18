package com.example.projectmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectmvvm.model.ProviderQuot
import com.example.projectmvvm.model.Quote

class QuotViewModel: ViewModel() {
    private val quotMutableLiveData: MutableLiveData<Quote> = MutableLiveData()
    fun quotLiveData(): LiveData<Quote> = quotMutableLiveData

    fun randomQuot(){
        //quotMutableLiveData.postValue(ProviderQuot.getRandomQout())
        ProviderQuot.getRandomQout {
            quotMutableLiveData.postValue(it)
        }
    }
}