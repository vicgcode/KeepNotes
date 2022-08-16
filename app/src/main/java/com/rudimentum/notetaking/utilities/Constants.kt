package com.rudimentum.notetaking.utilities

import com.rudimentum.notetaking.MainActivity
import com.rudimentum.notetaking.database.DatabaseRepository

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: DatabaseRepository
const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"