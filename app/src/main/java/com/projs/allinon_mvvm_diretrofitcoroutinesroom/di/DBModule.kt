package com.projs.allinon_mvvm_diretrofitcoroutinesroom.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.local.UserDB
import com.projs.allinon_mvvm_diretrofitcoroutinesroom.model.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    fun providesuserDB(@ApplicationContext context: Context): UserDB{
        return Room.databaseBuilder(
            context,
            UserDB::class.java,
            "user_db"
        ).build()
    }

    @Provides
    fun providesUserDao(db: UserDB): UserDao{
        return db.userDao()
    }
}