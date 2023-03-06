package com.todokanai.filemanager.myobjects

import android.os.Environment
import androidx.lifecycle.MutableLiveData

object MyObjects {
    val currentPath = MutableLiveData<String>(Environment.getExternalStorageDirectory().path)      // 현재 경로
}