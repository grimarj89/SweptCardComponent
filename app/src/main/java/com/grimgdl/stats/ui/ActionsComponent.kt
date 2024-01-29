package com.grimgdl.stats.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.grimgdl.stats.ui.theme.AddColor
import com.grimgdl.stats.ui.theme.GreenLike
import com.grimgdl.stats.ui.theme.RedDislike


@Composable
fun ActionsButtons(cardViewModel: CardViewModel) {

    val stateFlowModel = cardViewModel.stateFlowCard.collectAsState()
    val stateAngle by cardViewModel.angle.collectAsState()

    //Log.i("Offset ViewModel", "X: ${stateFlowModel.value.x} Y: ${stateFlowModel.value.y}  Angle: $stateAngle")

    Row(
        modifier = Modifier.padding(bottom = 40.dp) ,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom
    ) {

        DisLike(cardViewModel)
        AddItems(cardViewModel = cardViewModel)
        Like()
    }

}


@Composable
fun AddItems(cardViewModel: CardViewModel) {
    OutlinedButton(
        onClick = cardViewModel::addRandomPerson,
        modifier = Modifier.size(50.dp),
        shape = CircleShape,
        border = BorderStroke(2.dp, AddColor),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = AddColor )
    )
    {
        Icon(Icons.Default.Add, contentDescription = null )
    }
}


@Composable
fun DisLike(cardViewModel: CardViewModel) {

    OutlinedButton(
        onClick = {
                  cardViewModel.removePerson(Person(id = 234, name = "gus", description = "flaksdjk"))
        },
        modifier = Modifier.size(50.dp),
        shape = CircleShape,
        border = BorderStroke(2.dp, RedDislike),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = RedDislike )
    )
    {
        Icon(Icons.Default.Clear, contentDescription = null )
    }


}


@Composable
fun Like() {

    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier.size(50.dp),
        shape = CircleShape,
        border = BorderStroke(2.dp, GreenLike),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = GreenLike )
    )
    {
        Icon(Icons.Default.FavoriteBorder, contentDescription = null )
    }


}