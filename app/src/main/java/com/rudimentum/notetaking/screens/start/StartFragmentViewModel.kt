package com.rudimentum.notetaking.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rudimentum.notetaking.database.room.AppRoomDatabase
import com.rudimentum.notetaking.database.room.AppRoomRepository
import com.rudimentum.notetaking.utilities.REPOSITORY
import com.rudimentum.notetaking.utilities.TYPE_ROOM

class StartFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when(type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                // callback about completed
                onSuccess()
            }
        }
    }
}