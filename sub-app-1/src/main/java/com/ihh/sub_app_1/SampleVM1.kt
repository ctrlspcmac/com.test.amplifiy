package com.ihh.sub_app_1

import androidx.lifecycle.ViewModel

class SampleVM1(private val sampleUsecase1: SampleUseCase1) : ViewModel() {

    fun getText(): String {
        return sampleUsecase1.invoke()
    }
}