package com.example.notesapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.Note
import com.example.notesapp.data.NotesDatabaseHelper
import com.example.notesapp.databinding.NoteItemBinding
import com.example.notesapp.updateNote.UpdateNoteActivity

class NotesAdapter(private var notes : List<Note>, private val context: Context) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private  val databaseHelper = NotesDatabaseHelper(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteItemBinding.inflate(inflater, parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.titleTv.text = note.title
        holder.binding.contentTv.text = note.content
        holder.binding.updateBtn.setOnClickListener {
            val intent = Intent(context, UpdateNoteActivity::class.java).apply {
                putExtra("note_id", note.id)
            }
            context.startActivity(intent)
        }
        holder.binding.deleteBtn.setOnClickListener {
            databaseHelper.deleteNote(note.id)
            refreshDate(databaseHelper.getAllNotes())
            Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun refreshDate(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}