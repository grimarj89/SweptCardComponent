package com.grimgdl.stats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grimgdl.stats.ui.ActionsButtons
import com.grimgdl.stats.ui.CardViewModel
import com.grimgdl.stats.ui.DraggableCard
import com.grimgdl.stats.ui.Person
import com.grimgdl.stats.ui.PersonCard



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainComponent()
        }
    }

}


@Composable
fun MainComponent() {

    MaterialTheme {

        val cardViewModel = remember { CardViewModel() }
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ){


            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                cardViewModel.cards.forEachIndexed { index, person ->

                    DraggableCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0x00505050))
                            .padding(16.dp)
                        ,
                        cardViewModel = cardViewModel
                    ) {
                        PersonCard(
                            name = person.name,
                            description = person.description,
                            img = person.img
                        )
                    }

                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = "Dislike")

                    Text(text = "Like")
                }



                ActionsButtons(
                    cardViewModel = cardViewModel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.BottomCenter)
                        .padding(bottom = 16.dp)

                )

            }






        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMain() {
    MainComponent()
}


