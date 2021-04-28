package com.example.task.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.example.task.base.BaseViewModel
import com.example.task.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val repo: Repository
) : BaseViewModel<HomeNavigator>() {
//    fun getAllNotes() = repo.allNotes.asLiveData()
//    fun deleteNote(id: Int) = viewModelScope.launch {
//        repo.deleteNote(id)
//    }
//fun insertNote(note: Note) = viewModelScope.launch {
//    repo.insertNote(note)
//}
}