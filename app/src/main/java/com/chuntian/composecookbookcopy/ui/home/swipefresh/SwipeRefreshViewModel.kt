package com.chuntian.composecookbookcopy.ui.home.swipefresh

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chuntian.composecookbookcopy.utils.IOScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SwipeRefreshViewModel: ViewModel() {
    val isRefreshing = MutableLiveData<Boolean>(false)

    fun fresh(){
        isRefreshing.value = true
        IOScope().launch {
            delay(3000)
            withContext(Dispatchers.Main){
                isRefreshing.value = false
            }
        }
    }
}