package com.rudimentum.notetaking.screens.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rudimentum.notetaking.models.AppNote
import com.rudimentum.notetaking.utilities.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditFragmentViewModel(application: Application): AndroidViewModel(application) {

    fun update(name: String, text: String, id: Int, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.update(name, text, id) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }
}