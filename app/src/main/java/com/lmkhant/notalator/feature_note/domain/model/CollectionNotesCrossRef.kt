package com.lmkhant.notalator.feature_note.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["collectionId", "noteId"])
data class CollectionNotesCrossRef(
    @ColumnInfo(name = "collectionId", index = true) var collectionId: Int,
    @ColumnInfo(name = "noteId", index = true) var noteId: Int
)