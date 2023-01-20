package com.ihh.sub_app_1.injection.module

import com.ihh.sub_app_1.SampleUseCase1
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSampleUseCase1(): SampleUseCase1 {
        return SampleUseCase1()
    }
}