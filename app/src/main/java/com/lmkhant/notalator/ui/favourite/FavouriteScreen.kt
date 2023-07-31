package com.lmkhant.notalator.ui.favourite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.lmkhant.notalator.R
import com.lmkhant.notalator.feature_note.viewmodel.NoteViewModel
import com.lmkhant.notalator.ui.library.note.NoteSection

@Composable
fun FavouriteScreen(viewModel: NoteViewModel = hiltViewModel()) {
    val emptyListAnimation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.empty_favourite))
    FavouriteAppBar()
    Box(modifier = Modifier.fillMaxSize()) {
        viewModel.getFavouriteNotes()
        if (viewModel.favouriteNotes.value.isEmpty())
            LottieAnimation(
                modifier = Modifier.align(Alignment.Center),
                composition = emptyListAnimation,
                iterations = LottieConstants.IterateForever,
            )
        else {
            NoteSection(notes = viewModel.favouriteNotes.value)
        }
    }
}

@Composable
fun FavouriteAppBar() {
    Row(
        modifier = Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Favourite", fontSize = 22.sp,
            modifier = Modifier.weight(1f)
        )
    }
}