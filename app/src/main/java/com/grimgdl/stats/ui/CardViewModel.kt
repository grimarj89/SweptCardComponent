package com.grimgdl.stats.ui


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class CardViewModel: ViewModel(){

    private val offsetCard = MutableStateFlow(Offset(0f,0f))
    val stateFlowCard: StateFlow<Offset> = offsetCard
    private val _angle = MutableStateFlow(0f)
    val angle: StateFlow<Float> = _angle

    var cards by mutableStateOf<List<Person>>(emptyList())

    fun setOffset(offset: Offset){
        offsetCard.value = offset
    }

    fun setAngle(angle: Float){
        _angle.value = angle
    }

    fun addToListPearson(){
        cards = getList()

        Log.i("List Add", "$cards")
    }

    fun removePerson(pearson: Person){
        cards = cards.filter {
            it.name != pearson.name
        }

        Log.i("List Remove", "$cards")

    }


}

fun getList(): List<Person>{

    return listOf(
        Person(
            name = "Lola",
            id = 234,
            description = "fladsjfklasdjklfjklsdjklfs",
            img = "https://img.freepik.com/free-photo/beautiful-girl-stands-near-walll-with-leaves_8353-5377.jpg?w=740&t=st=1696021390~exp=1696021990~hmac=63c4b3713bd110e941417b6b0c7d0b694a72c4ef71630e77732260e7893c167f"
        ),
        Person(
            name = "Maria",
            id = 232,
            description = "lorem itsum kd lkfjd iekde498d lkds dkd",
            img = "https://img.freepik.com/free-photo/beautiful-girl-stands-park_8353-5084.jpg?w=740&t=st=1696021460~exp=1696022060~hmac=71d31bd330bccbebeb00db4f4e5700849c17a0bc8253d48e55bf070af12046a1"
        ),
    )

}
