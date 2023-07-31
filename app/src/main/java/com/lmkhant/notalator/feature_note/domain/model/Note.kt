package com.lmkhant.notalator.feature_note.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId: Int?,
    val noteNumber: Int,
    val noteContent: String = "",
    val favourite: Boolean,
    @ColumnInfo(name = "collectionId") val collectionId: Int

) : Parcelable

class InvalidPageException(message: String) : Exception(message)