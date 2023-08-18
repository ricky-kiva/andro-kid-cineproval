package com.rickyslash.kidcineproval

import android.app.Application
import com.rickyslash.kidcineproval.core.di.databaseModule
import com.rickyslash.kidcineproval.core.di.networkModule
import com.rickyslash.kidcineproval.core.di.repositoryModule
import com.rickyslash.kidcineproval.di.useCaseModule
import com.rickyslash.kidcineproval.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}