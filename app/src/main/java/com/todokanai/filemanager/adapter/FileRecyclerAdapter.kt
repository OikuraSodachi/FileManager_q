package com.todokanai.filemanager.adapter

import android.icu.text.DateFormat.getDateTimeInstance
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todokanai.filemanager.R
import com.todokanai.filemanager.application.MyApplication
import com.todokanai.filemanager.tools.Tools
import java.io.File

class FileRecyclerAdapter(private val onItemClick:(file:File)->Unit,private val onItemLongClick:(file:File)->Unit ) : RecyclerView.Adapter<FileRecyclerViewHolder>(){

    private val myContext = MyApplication.appContext
    var filesAndFolders = emptyArray<File>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.file_list_recycler,parent,false)
        return FileRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filesAndFolders.size
    }

    override fun onBindViewHolder(holder: FileRecyclerViewHolder, position: Int) {
        val selectedFile = filesAndFolders[position]
        holder.fileName.text = selectedFile.name
        holder.fileDate.text = getDateTimeInstance().format(selectedFile.lastModified())
        if (selectedFile.isDirectory) {
            val size = selectedFile.listFiles()?.size
            holder.typeImage.setImageResource(R.drawable.ic_baseline_folder_24)
            holder.fileSize.text = "$size 개"
        } else {
            val size = selectedFile.length()
            holder.typeImage.setImageResource(R.drawable.ic_baseline_insert_drive_file_24)
            holder.fileSize.text = Tools().readableFileSize(size)
        }
        holder.itemView.setOnClickListener {
            onItemClick(selectedFile)
        }
        holder.itemView.setOnLongClickListener {

            onItemLongClick(selectedFile)
            true
        }

    }
}