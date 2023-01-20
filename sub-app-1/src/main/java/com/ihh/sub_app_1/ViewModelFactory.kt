package com.ihh.sub_app_1

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    private val mProviderMap: Map<Class<out ViewModel>, Provider<ViewModel>>

    @NonNull
    override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
        return mProviderMap.get(modelClass)!!.get() as T
    }

    init {
        mProviderMap = providerMap
    }
}