package com.ihh.sub_app_1.injection.module

import androidx.lifecycle.ViewModel
import com.ihh.sub_app_1.SampleUseCase1
import com.ihh.sub_app_1.SampleVM1
import com.ihh.sub_app_1.ViewModelFactory
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import kotlin.reflect.KClass

@Module
class ViewModelModule {

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Provides
    fun provideViewModelFactory(providerMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory {
        return ViewModelFactory(providerMap)
    }

    @Provides
    @IntoMap
    @ViewModelKey(SampleVM1::class)
    fun provideSampleVM1(
        sampleUseCase1: SampleUseCase1
    ): ViewModel {
        return SampleVM1(sampleUseCase1)
    }
}