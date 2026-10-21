package org.train.projecto.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.train.projecto.data.local.createStorage
import org.train.projecto.data.local.getDatabaseBuilder

actual val platformModule = module {
    single { getDatabaseBuilder(androidContext()) }
    single{ createStorage(androidContext()) }
}