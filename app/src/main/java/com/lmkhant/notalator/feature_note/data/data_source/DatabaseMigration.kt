package com.lmkhant.notalator.feature_note.data.data_source

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_TO_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Perform the necessary database schema changes here
        database.execSQL("ALTER TABLE page ADD COLUMN favourite INTEGER NOT NULL DEFAULT 0")
    }
}
val MIGRATION_2_TO_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Perform the necessary database schema changes here
        database.execSQL("ALTER TABLE page ADD COLUMN collectionId INTEGER NOT NULL DEFAULT 0")
    }
}