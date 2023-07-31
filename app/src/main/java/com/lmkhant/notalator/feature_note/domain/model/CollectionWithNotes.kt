package com.lmkhant.notalator.feature_note.domain.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
class CollectionWithNotes(
    @Embedded
    val collection: Collection,
    @Relation(
        parentColumn = "collectionId",
        entityColumn = "noteId",
        associateBy = Junction(CollectionNotesCrossRef::class)
    )
    val notes: List<Note>
) : Parcelable