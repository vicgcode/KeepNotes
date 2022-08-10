package com.rudimentum.notetaking.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "note_tables")
data class AppNote (
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo val name: String = "",
    @ColumnInfo val text: String = ""
)
