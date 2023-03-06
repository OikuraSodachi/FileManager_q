package com.todokanai.filemanager.tools

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.todokanai.filemanager.myobjects.MyObjects
import okio.Path.Companion.toPath
import java.io.File
import java.nio.file.Files
import java.nio.file.Files.delete
import java.nio.file.Paths

class FileActionModel {

    private val currentPath = MyObjects.currentPath

    fun openFile(context: Context, selectedFile: File){
        try {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            val type = "*/*"
            intent.setDataAndType(Uri.parse(selectedFile.absolutePath), type)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(
                context.applicationContext,
                "Cannot open the file",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun openFolder(selectedFile: File) = currentPath.postValue(selectedFile.absolutePath)

    fun deleteFile(selectedFile: File) {
        println("exist: ${selectedFile.exists()}")
        delete(selectedFile.toPath())
    }

    fun deleteFolder(selectedFolder:File) = selectedFolder.deleteRecursively()
}