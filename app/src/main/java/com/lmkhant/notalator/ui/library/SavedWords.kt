package com.lmkhant.notalator.ui.library

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SavedWordsSection() {
    Row(
        modifier = Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Saved Words",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        Card(shape = CircleShape) {
            Row(modifier = Modifier.padding(6.dp)) {
                Icon(imageVector = Icons.Default.Sort, contentDescription = "")
            }
        }


    }
    LazySavedWordsHorizontalGrid()
}
@Composable
fun LazySavedWordsHorizontalGrid(){
    val list = (1..5).map { it.toString() }

    LazyHorizontalGrid(
        rows = GridCells.Fixed(3),
        modifier = Modifier.height(240.dp),
        verticalArrangement = Arrangement.Center,
        // content padding
        content = {
            item {
                Card(
                    modifier = Modifier
                        .padding(6.dp)
                        .width(200.dp)
                        .height(200.dp)
                        .clickable { },
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize().width(200.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Default.AddBox,
                            contentDescription = null
                        )
                    }
                }
            }
            items(list.size) { index ->
                Card(
                    modifier = Modifier
                        .padding(6.dp)
                        .width(200.dp)
                        .height(200.dp)
                        .clickable { },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.Center,

                        ) {
                        Text(
                            text = "${
                                "asdfasfd".repeat(list[index].toInt())
                            } ",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFFFFFFFF),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun SavedWordsPreview() {
    SavedWordsSection()
}