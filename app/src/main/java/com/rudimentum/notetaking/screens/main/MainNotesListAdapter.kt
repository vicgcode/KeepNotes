package com.rudimentum.notetaking.screens.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rudimentum.notetaking.R
import com.rudimentum.notetaking.models.AppNote

class MainNotesListAdapter: RecyclerView.Adapter<MainNotesListAdapter.MainHolder>() {

    private var mNotesList = emptyList<AppNote>()

    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameNote: TextView = view.findViewById(R.id.itemNoteName)
        val textNote: TextView = view.findViewById(R.id.itemNoteText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)

        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.nameNote.text = mNotesList[position].name
        holder.textNote.text = mNotesList[position].text
    }

    override fun getItemCount(): Int = mNotesList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<AppNote>) {
        mNotesList = list
        notifyDataSetChanged()
    }

}