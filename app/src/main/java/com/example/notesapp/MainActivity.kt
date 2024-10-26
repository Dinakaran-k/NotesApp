package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.adapter.NotesAdapter
import com.example.notesapp.addNotes.AddNotesActivity
import com.example.notesapp.data.NotesDatabaseHelper
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseHelper: NotesDatabaseHelper
    private var notesAdapter: NotesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = NotesDatabaseHelper(this)
        notesAdapter = NotesAdapter(databaseHelper.getAllNotes(), this)

        binding.notesRv.layoutManager =LinearLayoutManager(this)
        binding.notesRv.adapter = notesAdapter


        binding.addBtn.setOnClickListener {
            val intent = Intent(this, AddNotesActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        notesAdapter?.refreshDate(databaseHelper.getAllNotes())
    }

}