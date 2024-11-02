package mk.project.musicappui.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun subscriptionScreen(){
    Column(
        modifier= Modifier.height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Manage Subscription")
        Card(
            modifier= Modifier.padding(8.dp), elevation = 4.dp)
        {
            Column(modifier= Modifier.padding(8.dp)) {
                Column {
                    Text(text = "Musical")
                    Row (
                        modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(text = "Free Tier")
                        TextButton(onClick = { /*TODO*/ }) {
                            Text(text = "See all Plans", color = Color.Blue)
                            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "See all plan Button")
                        }
                    }
                    Divider()
                    Row {
                        Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
                        Text(text = "Get a Plan", color=Color.Magenta)
                    }

                }
            }
        }
    }

}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun subsPreview(){
    subscriptionScreen()
}