package com.example.todolist

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class filehelper2 {
    val FILNAME = "listInfo2.dat"

    fun write(array : ArrayList<Boolean> , context: Context){
        var fos : FileOutputStream = context.openFileOutput(FILNAME, Context.MODE_PRIVATE)
        var oas = ObjectOutputStream(fos)
        oas.writeObject(array)
        oas.close()
    }

    fun read(context: Context) : ArrayList<Boolean> {

        var array : ArrayList<Boolean>
        try {
            var fis : FileInputStream = context.openFileInput(FILNAME)
            var ois = ObjectInputStream(fis)
            array = ois.readObject() as ArrayList<Boolean>

        }catch (e : FileNotFoundException) {
            array = ArrayList<Boolean>()
        }
        return  array
    }
}