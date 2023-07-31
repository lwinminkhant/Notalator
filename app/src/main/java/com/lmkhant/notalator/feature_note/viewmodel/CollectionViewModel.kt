package com.lmkhant.notalator.feature_note.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmkhant.notalator.feature_note.domain.model.Collection
import com.lmkhant.notalator.feature_note.domain.use_case.CollectionUseCases
import com.lmkhant.notalator.feature_note.domain.util.CollectionOrder
import com.lmkhant.notalator.feature_note.domain.util.OrderType
import com.lmkhant.notalator.feature_note.presentation.collections.CollectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(private val collectionUseCases: CollectionUseCases) :
    ViewModel() {

    private val _state = mutableStateOf(CollectionState())
    val state: State<CollectionState> = _state

    private var getDocumentJob: Job? = null

    init {
        getDocuments(CollectionOrder.Date(OrderType.Descending))
    }

    fun insert(collection: Collection) = viewModelScope.launch(Dispatchers.IO) {
        collectionUseCases.addCollection(collection)
    }

    fun delete(collection: Collection) = viewModelScope.launch(Dispatchers.IO) {
        collectionUseCases.deleteCollection(collection)
    }

    fun update(collection: Collection) = viewModelScope.launch(Dispatchers.IO) {
        collectionUseCases.updateCollection(collection)
    }

    /*private fun getDocuments(noteOrder: DocumentOrder) {
        getDocumentJob?.cancel()
        getDocumentJob = documentUseCases.getDocuments(noteOrder)
            .onEach { documents ->
                _state.value = state.value.copy(
                    documents = documents,
                    documentOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }*/


    private fun getDocuments(noteOrder: CollectionOrder) {
        getDocumentJob?.cancel()
        getDocumentJob = collectionUseCases.getCollections(noteOrder)
            .onEach { documentWithPages ->
                _state.value = state.value.copy(
                    collections = documentWithPages,
                    collectionOrder = noteOrder
                )
            }.launchIn(viewModelScope)
    }

}