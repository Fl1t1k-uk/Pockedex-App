package org.train.projecto

import androidx.compose.ui.window.ComposeUIViewController
import org.train.projecto.data.local.AppDatabase
import org.train.projecto.data.local.createDatabase
import org.train.projecto.data.local.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController { App()
    // iosMain
    val database: AppDatabase by lazy { createDatabase(getDatabaseBuilder()) }

}