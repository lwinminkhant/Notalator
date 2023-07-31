package com.lmkhant.notalator.feature_note.domain.use_case

import com.lmkhant.notalator.feature_note.domain.use_case.collection.AddCollection
import com.lmkhant.notalator.feature_note.domain.use_case.collection.DeleteCollection
import com.lmkhant.notalator.feature_note.domain.use_case.collection.GetAllCollectionWithNotes
import com.lmkhant.notalator.feature_note.domain.use_case.collection.GetCollection
import com.lmkhant.notalator.feature_note.domain.use_case.collection.GetCollections
import com.lmkhant.notalator.feature_note.domain.use_case.collection.UpdateCollection

data class CollectionUseCases(
    val getCollections: GetCollections,
    val getAllCollectionWithNotes: GetAllCollectionWithNotes,
    val getCollection: GetCollection,
    val deleteCollection: DeleteCollection,
    val updateCollection: UpdateCollection,
    val addCollection: AddCollection,
)
