package com.lmkhant.notalator.ui.library.pager

import com.lmkhant.notalator.feature_note.domain.model.Note

object pageMock {
    val listNote = List(10) {
        Note(
            1,
            3,
            "PAGE : $it requires all serialized properties to be declared in the primary constructor. The plugin issues a warning on each property with a backing field declared in the class body. Also, you can't apply @Parcelize if some of the primary constructor parameters are not properties.",
            false,
            0
        )
    }
}

