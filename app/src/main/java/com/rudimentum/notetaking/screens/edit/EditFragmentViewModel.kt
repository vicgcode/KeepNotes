package com.rudimentum.notetaking.screens.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rudimentum.notetaking.models.AppNote
import com.rudimentum.notetaking.utilities.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditFragmentViewModel(application: Application): AndroidViewModel(application) {

    fun update(note: AppNote, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.update(note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }
}