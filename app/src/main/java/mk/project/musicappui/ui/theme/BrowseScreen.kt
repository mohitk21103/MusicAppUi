package mk.project.musicappui.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import mk.project.musicappui.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Browse(){
    val categories = listOf("Hits", "Happy", "Workout", "Running", "TGIF", "Yoga")

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(categories){
                item->
                BrowserItem(cat = item, drawable = R.drawable.baseline_music_video_24)
            }
        })
}


@Preview(showBackground = true)
@Composable
fun browsePre(){
    Browse()
}