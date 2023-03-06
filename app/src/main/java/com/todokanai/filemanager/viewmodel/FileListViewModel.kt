package com.todokanai.filemanager.viewmodel

import android.app.Activity
import android.content.Context
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.todokanai.filemanager.myobjects.MyObjects
import com.todokanai.filemanager.tools.FileAction
import dagger.hilt.android.lifecycle.HiltViewModel
import okio.Path.Companion.toPath
import java.io.File
import javax.inject.Inject
import kotlin.system.exitProcess

@HiltViewModel
class FileListViewModel @Inject constructor(): ViewModel(){

    private val fAction = FileAction()
    val selectedFiles = mutableSetOf<File>()    // 선택된 파일(들)

    lateinit var selc : File

    val currentPath = MyObjects.currentPath
    val fileList = MutableLiveData<Array<File>>(emptyArray())

    fun prepareContents(path:String) {
        fileList.value = File(path).listFiles()
    }

    fun onBackPressed(){
        currentPath.value = currentPath.value!!.toPath().parent.toString()
    }

    fun exit(activity: Activity){
        ActivityCompat.finishAffinity(activity)
        System.runFinalization()
        exitProcess(0)
    }

    fun onItemClick(context: Context,selectedFile: File){
        fAction.openAction(context, selectedFile)
    }

    fun onItemLongClick(selected: File){
        selectedFiles.add(selected)
        selc = selected
        println("selc: $selc")
    }

    fun moveBtn(){

    }

    fun copyBtn(){

    }

    fun renameBtn(){

    }

    fun deleteBtn(){
        fAction.deleteSingle(selc)
    }

    fun moreBtn(){

    }

    //--------------------- private fun

}