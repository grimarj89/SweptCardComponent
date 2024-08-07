package com.grimgdl.stats.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import kotlin.math.abs

@OptIn(ExperimentalTextApi::class)
@Composable
fun PersonCard(
    modifier: Modifier = Modifier,
    img: String = "https://images.unsplash.com/photo-1609132718484-cc90df3417f8?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80",
    name: String,
    description: String,
    offsetX: Float = 0f
) {

    val photoList = remember { mutableStateOf(mutableListOf(randomImage(), randomImage(), randomImage(), randomImage())) }


    Card {

        Box(
            modifier = modifier
        ) {


            SubcomposeAsyncImage(
                model = img,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                loading = {
                    Box(
                        modifier = Modifier.size(32.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        CircularProgressIndicator()
                    }

                }
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {


                photoList.value.forEachIndexed { index, _ ->

                    Box(
                        modifier = Modifier
                            .height(5.dp)
                            .weight(1f)
                            .background(color = Color.White)

                    )

                    if (index < photoList.value.size - 1) {
                        Spacer(modifier = Modifier.width(5.dp))
                    }

                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {


                Text(
                    text = "Like",
                    color = Color.Green,
                    fontSize = 40.sp,
                    modifier = Modifier.rotate(-45f)
                        .alpha( if(isAbsolute(offsetX)) 0f else abs(alphaText(offsetX)) ),
                    style = LocalTextStyle.current.merge(
                        TextStyle(
                            color = Color(0xFF3FDD6C),
                            fontSize = 80.sp,
                            drawStyle = Stroke(width = 8f, join = StrokeJoin.Round),
                            fontWeight = FontWeight.Bold,
                            shadow = Shadow()
                        )
                    )

                )

                Text(
                    text = "Dislike",
                    color = Color.Red,
                    fontSize = 35.sp,
                    modifier = Modifier.rotate(45f)
                        .alpha( if(isAbsolute(offsetX)) abs(alphaText(offsetX)) else 0f)
                    ,
                    style = LocalTextStyle.current.merge(
                        TextStyle(
                            color = Color(0xFFDD3F44),
                            fontSize = 80.sp,
                            drawStyle = Stroke(width = 6f, join = StrokeJoin.Round),
                            fontWeight = FontWeight.Bold,
                            shadow = Shadow()
                        )
                    )
                )






            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomStart)
                    .padding(bottom = 80.dp)
            ) {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF1F1F1),
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2.0f, 2.0f),
                            blurRadius = 2f
                        )

                    )
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color(0xFFFAFAFA),
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2.0f, 2.0f),
                            blurRadius = 2f
                        )

                    )
                )

            }
            
            



        }

    }

}


fun randomImage(): String {

    val girls = arrayOf(
        "https://images.unsplash.com/photo-1609132718484-cc90df3417f8?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80",
        "https://img.freepik.com/free-photo/beautiful-girl-stands-near-walll-with-leaves_8353-5377.jpg?w=740&t=st=1696021390~exp=1696021990~hmac=63c4b3713bd110e941417b6b0c7d0b694a72c4ef71630e77732260e7893c167f",
        "https://images.unsplash.com/photo-1491349174775-aaafddd81942?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80",
        "https://images.unsplash.com/photo-1544005313-94ddf0286df2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1976&q=80",
        "https://images.unsplash.com/photo-1593104547489-5cfb3839a3b5?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2053&q=80",
        "https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80",
        "https://images.unsplash.com/photo-1489424731084-a5d8b219a5bb?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80",
        "https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1976&q=80",
        "https://images.unsplash.com/photo-1532074205216-d0e1f4b87368?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1941&q=80",
        "https://images.unsplash.com/photo-1525134479668-1bee5c7c6845?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80",
        "https://images.unsplash.com/photo-1539698103494-a76dd0436fbc?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80"

    )

    val offset = girls.size - 1

    val random = (0..offset).random()

    return girls[random]
}

data class Person(
    val id: Int,
    val name: String,
    val img: String = randomImage(),
    val description: String
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CardPreview() {
    PersonCard(name = "gus", description = "lfsadkj")
}

fun alphaText(number: Float): Float = (number / 60f)

fun isAbsolute(number: Float): Boolean = (number >= 0f)
