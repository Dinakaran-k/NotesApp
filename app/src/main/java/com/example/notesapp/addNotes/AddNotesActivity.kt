package com.example.notesapp.addNotes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.data.Note
import com.example.notesapp.data.NotesDatabaseHelper
import com.example.notesapp.databinding.ActivityAddNotesBinding

class AddNotesActivity : AppCompatActivity() {

    var db : NotesDatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        binding.saveBtn.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val note = Note(0,title, content)
            db?.insertNote(note)
            finish()

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
        }

    }
}