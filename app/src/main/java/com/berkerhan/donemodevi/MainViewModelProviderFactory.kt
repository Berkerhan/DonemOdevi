package com.berkerhan.donemodevi

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.berkerhan.donemodevi.data.repository.MainRepository
import com.berkerhan.donemodevi.MainViewModel

class MainViewModelProviderFactory(val app: Application, private val mainRepository: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(app, mainRepository) as T
    }
}