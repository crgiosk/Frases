package com.example.projectmvvm.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectmvvm.core.Mappers.toQuot
import com.example.projectmvvm.core.UIState
import com.example.projectmvvm.domain.QuotBind
import com.example.projectmvvm.domain.GetQuotUseCase
import com.example.projectmvvm.domain.GetRandomQuotUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotViewModel @Inject constructor(
    private val getQuotUseCase: GetQuotUseCase,
    private val getRandomQuotUseCase: GetRandomQuotUseCase
) : ViewModel() {
    private val getAllQuotsMutableLiveData: MutableLiveData<UIState<QuotBind>> = MutableLiveData()
    private val getQuotMutableLiveData: MutableLiveData<UIState<QuotBind>> = MutableLiveData()

    fun getAllQuotsLiveData(): LiveData<UIState<QuotBind>> = getAllQuotsMutableLiveData
    fun getQuotLiveData(): LiveData<UIState<QuotBind>> = getQuotMutableLiveData

    fun randomQuot() {
        getQuotMutableLiveData.postValue(UIState.OnLoading)
        viewModelScope.launch {
            val result = getRandomQuotUseCase()
            if (result != null) {
                getQuotMutableLiveData.postValue(UIState.OnSuccess(result.toQuot()))
            } else {
                getQuotMutableLiveData.postValue(UIState.OnError("Error al hacer la peticion al api."))
            }
        }

    }

    fun onCreate() {
        getAllQuotsMutableLiveData.postValue(UIState.OnLoading)
        viewModelScope.launch {
            val result = getQuotUseCase()
            if (result.isNotEmpty()) {
                getAllQuotsMutableLiveData.postValue(UIState.OnSuccess(result[0]))
            } else {
                getAllQuotsMutableLiveData.postValue(UIState.OnError("Error al hacer la peticion al api."))
            }
        }
    }
}