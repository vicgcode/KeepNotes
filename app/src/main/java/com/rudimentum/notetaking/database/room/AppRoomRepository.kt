package com.rudimentum.notetaking.database.room

import androidx.lifecycle.LiveData
import com.rudimentum.notetaking.database.DatabaseRepository
import com.rudimentum.notetaking.models.AppNote

class AppRoomRepository(private val appRoomDao: AppRoomDao) : DatabaseRepository {
    override val allNotes: LiveData<List<AppNote>>
        get() = appRoomDao.getAllNotes()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.insert(note)
        onSuccess()
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.delete(note)
        onSuccess()
    }

}