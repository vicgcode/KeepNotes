package com.rudimentum.notetaking.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rudimentum.notetaking.models.AppNote

@Dao
interface AppRoomDao {
    @Query("SELECT *from note_tables")
    fun getAllNotes(): LiveData<List<AppNote>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: AppNote)

    @Delete
    suspend fun delete(note: AppNote)

    @Query("UPDATE note_tables SET name=:name, text=:text WHERE id LIKE :id")
    suspend fun update(name: String, text: String, id: Int)
}