package com.grimgdl.stats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){

            cardViewModel.cards.forEachIndexed { index, person ->

                DraggableCard(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x00505050)),
                    cardViewModel = cardViewModel
                ) {
                    PersonCard(
                        name = person.name,
                        description = person.description,
                        img = person.img
                    )
                }

            }

            ActionsButtons(cardViewModel = cardViewModel)

        }

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    MainComponent()
}


