package com.grimgdl.stats.ui

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class CardViewModel: ViewModel(){

    private val offsetCard = MutableStateFlow(Offset(0f,0f))
    val stateFlowCard: StateFlow<Offset> = offsetCard

    fun setOffset(offset: Offset){
        offsetCard.value = offset
    }

}