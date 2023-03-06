package com.todokanai.filemanager.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor(): ViewModel(){

    val bottomMenu = MutableLiveData<Boolean>(false)

    fun leftBtn(){
        bottomMenu.value = false
    }

    fun middleBtn(){

    }

    fun rightBtn(){
        bottomMenu.value = true

    }
}