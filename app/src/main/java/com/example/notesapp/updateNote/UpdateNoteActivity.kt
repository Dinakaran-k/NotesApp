package com.example.notesapp.updateNote

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.data.Note
import com.example.notesapp.data.NotesDatabaseHelper
import com.example.notesapp.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var databaseHelper: NotesDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = NotesDatabaseHelper(this)
        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1) {
            finish()
            return
        }

        val note = databaseHelper.getNoteById(noteId)
        binding.titleEditText.setText(note.title)
        binding.contentEditText.setText(note.content)

        binding.saveBtn.setOnClickListener {
            val newTitle = binding.titleEditText.text.toString()
            val newContent = binding.contentEditText.text.toString()
            val updateNote = Note(noteId, newTitle, newContent)
            databaseHelper.updateNote(updateNote)
            finish()
            Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show()
        }

    }
}