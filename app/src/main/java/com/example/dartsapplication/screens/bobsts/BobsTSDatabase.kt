package com.example.dartsapplication.screens.bobsts

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dartsapplication.entities.BobsEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(
    entities = [BobsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BobsTSDatabase : RoomDatabase(){
    abstract val bobsTSDao: BobsTSDao
}

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun provideMyDataBase(app: Application): BobsTSDatabase {
        return Room.databaseBuilder(
            app,
            BobsTSDatabase::class.java,
            "BobsTSDataBase"
        )
//            .addMigrations() later add migrations if u change the table
            .build()
    }



    @Provides
    @Singleton
    fun provideMyRepository(mydb:BobsTSDatabase) : BobsTSRepository {
        return RepositoryImpl(mydb.bobsTSDao)
    }
}