package mk.project.musicappui.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mk.project.musicappui.R

@Composable
fun AccountScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row (
            modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row{
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Account")
                Column {
                    Text(text = "MohitTutorials")
                    Text(text="@mk121Tut")
                }
            }
            IconButton(onClick = {}){
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription =null)
                
            }
        }

        Row (
            modifier= Modifier.padding(16.dp)
        ){
            Icon(painter = painterResource(id = R.drawable.baseline_music_video_24),
                contentDescription = "music Icon",
                modifier= Modifier.padding(8.dp)
                )

            Text(text = "My Music")
        }
        Divider()
    }
}