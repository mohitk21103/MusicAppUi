package mk.project.musicappui.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mk.project.musicappui.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(){
    val grouped = listOf<String>("New Release", "Top Rated", "Favorites").groupBy{it[0]}
    val categories = listOf("Hits", "Happy", "Workout", "Running", "TGIF", "Yoga")

    LazyColumn{
        grouped.forEach{
            stickyHeader {
                Text(text = it.value[0], modifier= Modifier.padding(16.dp))
                LazyRow{
                    items(categories){
                        cat->
                        BrowserItem(cat, R.drawable.baseline_music_video_24)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowserItem(cat :String, drawable: Int) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(200.dp),
        border = BorderStroke(3.dp, color = Color.DarkGray)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(text = cat)
            Image(painter = painterResource(id = drawable), contentDescription = cat)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun homePre(){
    Home()
}