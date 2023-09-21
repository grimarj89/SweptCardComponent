package com.grimgdl.stats

import android.app.Person
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.grimgdl.stats.ui.DraggableCard
import com.grimgdl.stats.ui.PersonCard
import com.grimgdl.stats.ui.SweptCardDemo
import com.grimgdl.stats.ui.SwipeableCard


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


data class User(var id:Int,var name: String, var age: Int, var finish: Boolean = false)



@Composable
fun MyRecycler(userList: MutableList<User>) {

    fun onRemove(item: User) {
        userList.removeIf { i-> i.name == item.name && i.age == item.age  }
    }

    LazyColumn {
        items(userList, key = {item -> item.id }) { item ->

            Card (modifier = Modifier.fillMaxSize() ){

                Row(modifier = Modifier.padding(16.dp)){
                    Text(text = item.name )

                    Button(onClick = { onRemove(item) }) {
                        Icon(painter = painterResource(id = R.drawable.ic_trash), contentDescription = "icon")
                    }

                    val finishKey = remember { mutableStateOf(item.finish) }

                    Button(onClick = { finishKey.value = true }, enabled = !finishKey.value ) {
                        Icon(painter = painterResource(id = R.drawable.ic_done), contentDescription = "icon")
                    }

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


