package org.train.projecto.data.local

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor
import org.train.projecto.data.local.entities.PokemonPreviewEntity

@Database(entities = [PokemonPreviewEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonPreviewDao
}


@Suppress("KotlinNoActualForExpect") //Глушить попередження про те, що в expect немає actual реалізації.
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>{
    override fun initialize(): AppDatabase

}
