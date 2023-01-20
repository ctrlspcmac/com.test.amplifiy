package com.ihh.sub_app_1.library_interface

import com.ihh.sub_app_1.RBaseActivity
import com.ihh.sub_app_1.injection.DaggerRLibraryComponent

class RInjector {

    companion object {

        fun inject(activity: RBaseActivity, deps: RDependency) {

            return DaggerRLibraryComponent.builder()
                .rDependency(deps)
                .build()
                .inject(activity)
        }

//        fun inject(fragment: GmrBaseFragment, deps: RDependency) {
//            return DaggerGmrLibraryComponent.builder()
//                .gmrDependency(deps)
//                .build()
//                .inject(fragment)
//        }

    }

}