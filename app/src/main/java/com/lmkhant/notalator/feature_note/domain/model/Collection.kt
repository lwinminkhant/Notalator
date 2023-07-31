package com.lmkhant.notalator.feature_note.domain.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lmkhant.notalator.util.Converters
import kotlinx.parcelize.Parcelize
import java.util.Date

@Entity(tableName = "collection")
@Parcelize
@TypeConverters(Converters::class)
data class Collection (
    @PrimaryKey(autoGenerate = true)
    var collectionId: Int?,
    val collectionUpdatedOn: Date,
    val collectionTitle: String,
    val collectionImageUrl: String,
) : Parcelable

class InvalidDocumentException(message: String) : Exception(message)