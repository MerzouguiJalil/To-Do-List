package com.example.todolist

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutput
import java.io.ObjectOutputStream

class fileHelper {

    val FILNAME = "listInfo.dat"

    fun write(array : ArrayList<String> , context: Context){
        var fos : FileOutputStream = context.openFileOutput(FILNAME,Context.MODE_PRIVATE)
        var oas = ObjectOutputStream(fos)
        oas.writeObject(array)
        oas.close()
    }

    fun read(context: Context) : ArrayList<String> {

        var array : ArrayList<String>
        try {
            var fis : FileInputStream = context.openFileInput(FILNAME)
            var ois = ObjectInputStream(fis)
            array = ois.readObject() as ArrayList<String>

        }catch (e : FileNotFoundException) {
            array = ArrayList<String>()
        }
        return  array
    }
}