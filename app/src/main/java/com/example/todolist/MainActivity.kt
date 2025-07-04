package com.example.todolist

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextClock
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var adapter: RecyleAdapter
    lateinit var task : EditText
    lateinit var add : Button
    lateinit var list : RecyclerView
    lateinit var refrech : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var arr = ArrayList<String>()
        var arrb = ArrayList<Boolean>()
        task = findViewById(R.id.editTextText)
        add = findViewById(R.id.button)
        list = findViewById(R.id.res)
        refrech = findViewById(R.id.button2)





        list.layoutManager = LinearLayoutManager(this)

        var helper = fileHelper()
        var helper2 = filehelper2()
        arr = helper.read(this)
        arrb = helper2.read(this)

        adapter = RecyleAdapter(this@MainActivity ,arr , arrb)
        list.adapter = adapter



        add.setOnClickListener {
            if(task.text.toString().isNotEmpty()) { // Changed to isNotEmpty() for better practice
                val newTaskText = task.text.toString()
                arr.add(newTaskText)
                arrb.add(false) // Add a corresponding boolean value
                helper.write(arr , this) // Consider if helper needs to handle arrb too
                helper2.write(arrb, this)
                task.setText("")
                adapter.notifyDataSetChanged()
            }
        }
        refrech.setOnClickListener {
            for(i in arr.size-1 downTo 0){
                if(arrb[i] == true){
                    arr.removeAt(i)
                    arrb.removeAt(i)
                }
            }
            helper.write(arr , this)
            helper2.write(arrb , this)
            adapter.notifyDataSetChanged()
        }
    }


}