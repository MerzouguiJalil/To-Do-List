package com.example.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecyleAdapter( var context: Context, var tasks: ArrayList<String> , var dones : ArrayList<Boolean>) :
    RecyclerView.Adapter<RecyleAdapter.holder>() {

    class holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var task : TextView = itemView.findViewById(R.id.task)
        var checked : CheckBox = itemView.findViewById(R.id.done)
        var card : CardView = itemView.findViewById(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.resc,parent , false)
        return holder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        var helper1 = fileHelper()
        var helper = filehelper2()
        holder.task.text = tasks[position]
        holder.checked.isChecked = dones[position]
        holder.checked.setOnCheckedChangeListener { buttonView, isChecked ->
            dones[position] = isChecked
            helper.write(dones , context)
        }
        holder.card.setOnClickListener {
            var alert = android.app.AlertDialog.Builder(context)
            alert.setTitle("Delete")
            alert.setMessage("Do you want to delete this item")
            alert.setCancelable(false)
            alert.setNegativeButton("No") { dialog, which ->
                dialog.cancel()
    }
            alert.setPositiveButton("Yes") { dialog, which ->
                tasks.removeAt(position)
                dones.removeAt(position)
                notifyDataSetChanged()
                helper.write(dones , context)
                helper1.write(tasks , context)

            }
            alert.create().show()
        }

}}