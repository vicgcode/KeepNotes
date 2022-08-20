package com.rudimentum.notetaking.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rudimentum.notetaking.utilities.REPOSITORY

class MainFragmentViewModel(application: Application): AndroidViewModel(application) {
    // get LiveData from REPOSITORY
    val allNotes = REPOSITORY.allNotes
}