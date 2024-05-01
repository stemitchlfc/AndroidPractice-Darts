package com.example.dartsapplication.screens.practice

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dartsapplication.entities.PracticeEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(
    entities = [PracticeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PracticeDatabase : RoomDatabase(){
    abstract val practiceDao: PracticeDao
}

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun provideMyDataBase(app: Application): PracticeDatabase {
        return Room.databaseBuilder(
            app,
            PracticeDatabase::class.java,
            "PracticeDatabase"
        )
//            .addMigrations() later add migrations if u change the table
            .build()
    }



    @Provides
    @Singleton
    fun provideMyRepository(mydb:PracticeDatabase) :PracticeRepository {
        return RepositoryImpl(mydb.practiceDao)
    }
}