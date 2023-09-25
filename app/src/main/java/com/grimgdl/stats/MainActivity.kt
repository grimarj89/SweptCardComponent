package com.grimgdl.stats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grimgdl.stats.ui.DraggableCard
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

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                DraggableCard(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xff505050))
                        .padding(16.dp)
                ) {
                    PersonCard(
                        modifier = Modifier.fillMaxSize()
                    )
                }

            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    MainComponent()
}


