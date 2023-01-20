package com.ihh.sub_app_1.injection.module

import com.ihh.sub_app_1.RBaseActivity
import com.ihh.sub_app_1.RMainActivity
import com.ihh.sub_app_1.injection.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ApplicationModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun rBaseActivity(): RBaseActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): RMainActivity
}