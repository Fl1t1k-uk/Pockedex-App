package org.train.projecto.data.local

import android.app.Application



// androidApp (або shared/androidMain, залежно від структури)
class App : Application() {
    lateinit var database: AppDatabase //Для створення database нам потрібен builder, а йому потрібен context. Context ми ж отримуємо лише на етапі onCreate а не зразу, тому тут і lateinit.
        private set // Читати database можна звідусіль. Змінювати може лише App.

    override fun onCreate() {
        super.onCreate()
        database = createDatabase(getDatabaseBuilder(this))
    }
}