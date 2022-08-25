package com.rudimentum.notetaking.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rudimentum.notetaking.models.AppNote
import com.rudimentum.notetaking.utilities.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragmentViewModel(application: Application): AndroidViewModel(application) {

    fun delete(note: AppNote, onSuccess: () -> Unit) {
        viewModelScope.launch (Dispatchers.IO) {
            REPOSITORY.delete(note) {
                viewModelScope.launch (Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }
}