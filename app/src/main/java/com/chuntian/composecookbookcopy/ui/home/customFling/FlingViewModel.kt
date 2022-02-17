package com.chuntian.composecookbookcopy.ui.home.customFling

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FlingViewModel: ViewModel() {
    val flingStateStore: MutableLiveData<FlingStateStore> = MutableLiveData()


    fun updateFlingState(stateStore: FlingStateStore){
        flingStateStore.value = stateStore
    }
}