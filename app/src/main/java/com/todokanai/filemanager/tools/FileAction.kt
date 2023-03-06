package com.todokanai.filemanager.tools

import android.content.Context
import java.io.File

class FileAction {
    private val model = FileActionModel()

    fun openAction(context:Context,selectedFile: File){
        if (selectedFile.isDirectory) {
            model.openFolder(selectedFile)
        } else {
            model.openFile(context,selectedFile)
        }
    }

    fun deleteAction(selectedFiles: Set<File>){
        for(element in selectedFiles){
            model.deleteFile(element)
        }
    }

    fun deleteSingle(selectedFile: File){
        model.deleteFile(selectedFile)
    }

    fun renameAction(selectedFile:File){

    }





}