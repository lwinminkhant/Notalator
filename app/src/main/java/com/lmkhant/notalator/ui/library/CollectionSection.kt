package com.lmkhant.notalator.ui.library

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore.Images.Media.getBitmap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.lmkhant.notalator.R
import com.lmkhant.notalator.ui.activity.EditPageActivity
import com.lmkhant.notalator.ui.activity.PageDetailActivity
import com.lmkhant.notalator.feature_note.domain.model.Collection
import com.lmkhant.notalator.feature_note.viewmodel.CollectionViewModel
import com.lmkhant.notalator.ui.components.ModelTextFieldSheet
import com.lmkhant.notalator.ui.components.ModelTextFieldSheetDelete
import com.lmkhant.notalator.util.saveBitmap
import java.util.Date

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CollectionSection(viewModel: CollectionViewModel = hiltViewModel()) {
    var openNewCollectionSheet by rememberSaveable { mutableStateOf(false) }

    val openCollectionTitleEditor = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Collection",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f)
                .clickable {

                }
        )

        IconButton(onClick = {
            openNewCollectionSheet = !openNewCollectionSheet
        }) {
            Icon(
                imageVector = Icons.Default.AddBox,
                tint = MaterialTheme.colorScheme.surfaceTint,
                contentDescription = "new doc"
            )
        }

        if (openNewCollectionSheet) {
            ModelTextFieldSheet(
                onDismiss = { openNewCollectionSheet = false },
                text = "",
                onSubmit = {
                    viewModel.insert(Collection(null, Date(), it, ""))
                })

        }
    }

    BooksGrid(viewModel, openCollectionTitleEditor)

}

@Composable
fun BooksGrid(viewModel: CollectionViewModel, openBooksTitle: MutableState<Boolean>) {

    val docs = viewModel.state

    val context = LocalContext.current
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    val selectedCollection = remember {
        mutableStateOf<Collection?>(null)
    }
    val emptyListAnimation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.empty_list))

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        if (selectedImageUri != null) {
            selectedCollection.value?.let {

                selectedImageUri?.let { image ->
                    if (Build.VERSION.SDK_INT < 28) {
                        bitmap.value = getBitmap(context.contentResolver, image)

                    } else {
                        val source = ImageDecoder
                            .createSource(context.contentResolver, image)
                        bitmap.value = ImageDecoder.decodeBitmap(source)
                    }

                    if (bitmap.value != null) {
                        val imageUrl = saveBitmap(
                            context,
                            bitmap.value!!,
                            Bitmap.CompressFormat.PNG,
                            "image/png",
                            "image"
                        )
                        val updatedDocument =
                            it.copy(collectionImageUrl = imageUrl.toString())
                        updatedDocument.collectionId = it.collectionId
                        viewModel.update(updatedDocument)
                        Toast.makeText(context, "Update document cover", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        if (viewModel.state.value.collections.isEmpty()) {
            LottieAnimation(
                modifier = Modifier.align(Alignment.Center),
                composition = emptyListAnimation,
                iterations = LottieConstants.IterateForever,
            )
        } else {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxHeight(),
                columns = GridCells.Adaptive(150.dp),
                contentPadding = PaddingValues(
                    start = 12.dp, top = 16.dp, end = 12.dp, bottom = 12.dp
                ),

                ) {

                items(docs.value.collections.size) { index ->
                    val doc = docs.value.collections[index]

                    Card(
                        modifier = Modifier
                            .width(150.dp)
                            .height(200.dp)
                            .padding(6.dp)
                            .clickable {
                                val intent = Intent(context, PageDetailActivity::class.java)
                                intent.putExtra("collectionId", doc.collectionId)
                                context.startActivity(intent)
                            }, elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {

                            AsyncImage(
                                model = doc.collectionImageUrl,
                                contentDescription = null,
                                modifier = Modifier.fillMaxWidth(),
                                contentScale = ContentScale.Crop
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .clickable {
                                        selectedCollection.value = doc
                                        openBooksTitle.value = !openBooksTitle.value
                                    }
                                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)),
                                contentAlignment = Alignment.Center

                            ) {
                                Text(
                                    text = doc.collectionTitle,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onSecondary,
                                )
                            }

                            IconButton(
                                onClick = {
                                    context.startActivity(
                                        Intent(
                                            context,
                                            EditPageActivity::class.java
                                        ).putExtra("collectionId", doc.collectionId)
                                    )
                                },
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(3.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.BorderColor,
                                    tint = MaterialTheme.colorScheme.surfaceTint,
                                    contentDescription = "edit"
                                )
                            }
                            IconButton(
                                onClick = {
                                    launcher.launch("image/*")
                                    selectedCollection.value = doc
                                },
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(3.dp)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .padding(5.dp),
                                    imageVector = Icons.Default.Image,
                                    tint = MaterialTheme.colorScheme.surfaceTint,
                                    contentDescription = "image selector"
                                )
                            }

                            if (openBooksTitle.value) {
                                selectedCollection.value?.let { doc ->
                                    ModelTextFieldSheetDelete(
                                        "Collection title",
                                        text = doc.collectionTitle,
                                        onDismiss = { openBooksTitle.value = false },
                                        onDelete = { viewModel.delete(doc) },
                                        onSubmit = {
                                            viewModel.update(doc.copy(collectionTitle = it))
                                        })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



