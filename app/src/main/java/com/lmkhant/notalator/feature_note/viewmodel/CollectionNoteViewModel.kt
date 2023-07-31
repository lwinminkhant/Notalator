package com.lmkhant.notalator.feature_note.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmkhant.notalator.feature_note.domain.use_case.DocumentWithPagesUseCases
import com.lmkhant.notalator.feature_note.presentation.collections.CollectionNotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CollectionNoteViewModel @Inject constructor(private val documentWithPagesUseCases: DocumentWithPagesUseCases):ViewModel(){
    var _state = mutableStateOf(CollectionNotesState())
    val state = _state

    private var getDocumentJob: Job? = null

    fun loadPages(documentId: Int){
        getDocumentWithPages(documentId)
    }

    private fun getDocumentWithPages(documentId: Int){
        getDocumentJob?.cancel()
        getDocumentJob = documentWithPagesUseCases.documentWithPages(documentId)
            .onEach { documentWithPages ->
                _state.value = state.value.copy(
                    collection = documentWithPages.collection,
                    notes = documentWithPages.notes
                )
            }.launchIn(viewModelScope)
    }

}