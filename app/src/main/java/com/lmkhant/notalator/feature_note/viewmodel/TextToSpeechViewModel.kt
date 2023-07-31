package com.lmkhant.notalator.feature_note.viewmodel

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmkhant.notalator.feature_note.presentation.page.NoteState
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale

@ViewModelScoped
class TextToSpeechViewModel : ViewModel(), TextToSpeech.OnInitListener {

    private val _isSpeaking = MutableStateFlow(false)
    private val isSpeaking get() = _isSpeaking.asStateFlow()
    private lateinit var textToSpeech: TextToSpeech
    private var ps: NoteState? = null
    fun setup(context: Context) {
        textToSpeech = TextToSpeech(context, this)
    }

    fun speakText(noteState: NoteState) {
        viewModelScope.launch {
            ps?.let {
                it.isPlaying.value = false
            }
        }



        ps?.let {
            if(it == noteState){
                textToSpeech.stop()
                ps = null
                return
            }
        }
        this.ps = noteState

        textToSpeech.speak(
            noteState.note.noteContent,
            TextToSpeech.QUEUE_FLUSH,
            null,
            "${noteState.note.noteId}",
        )
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {
                Log.d("TextToSpeechViewModel", "Language not Found")
            }

            textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(p0: String?) {
                    viewModelScope.launch {

                        _isSpeaking.emit(true)
                        ps?.let {
                            it.isPlaying.value = true
                        }

                    }

                }

                override fun onDone(p0: String?) {
                    viewModelScope.launch {
                        _isSpeaking.emit(false)
                        isSpeaking.collect {

                            ps?.let {
                                it.isPlaying.value = false
                            }

                        }

                    }

                }

                @Deprecated("Deprecated in Java")
                override fun onError(p0: String?) {
                }
            })
        }

    }


}
