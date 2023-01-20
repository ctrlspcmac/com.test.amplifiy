package com.ihh.sub_app_1.injection

import com.ihh.sub_app_1.RBaseActivity
import com.ihh.sub_app_1.injection.module.ApplicationModule
import com.ihh.sub_app_1.injection.module.UseCaseModule
import com.ihh.sub_app_1.injection.module.ViewModelModule
import com.ihh.sub_app_1.library_interface.RDependency
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    ApplicationModule::class,
    ViewModelModule::class,
    UseCaseModule::class,
    AndroidSupportInjectionModule::class],
    dependencies = [RDependency::class]
)

@Singleton
interface RLibraryComponent {

    fun inject(activity: RBaseActivity)

}