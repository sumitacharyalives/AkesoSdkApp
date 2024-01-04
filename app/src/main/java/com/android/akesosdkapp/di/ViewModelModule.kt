package com.android.akesosdkapp.di

import com.android.akesosdkapp.model.UserRepository
import com.android.akesosdkapp.viewmodel.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // Dependency: ListViewModel
    viewModel {
        ListViewModel(get())
    }

    // Dependency: UserRepository
    single {
        UserRepository(get(), get())
    }
}