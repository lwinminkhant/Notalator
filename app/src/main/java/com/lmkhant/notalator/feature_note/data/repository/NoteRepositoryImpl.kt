package com.lmkhant.notalator.feature_note.data.repository

import com.lmkhant.notalator.feature_note.data.data_source.dao.NoteDao
import com.lmkhant.notalator.feature_note.domain.model.Note
import com.lmkhant.notalator.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository{
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getAll()
    }

    override fun getNotesBy(collectionId: Int): Flow<List<Note>> {
        return noteDao.getPagesBy(collectionId)
    }

    override fun getFavouriteNotes(): Flow<List<Note>> {
        return noteDao.getFavourite()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getById(id)
    }

    override suspend fun insertNote(note: Note) {
        return noteDao.insert(note)
    }

    override suspend fun deleteNote(note: Note) {
        return noteDao.delete(note)
    }

    override suspend fun updateNote(note: Note) {
        return noteDao.update(note)
    }
}