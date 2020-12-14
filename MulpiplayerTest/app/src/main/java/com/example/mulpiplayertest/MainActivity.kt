package com.example.mulpiplayertest

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var button : Button
    lateinit var editText : EditText
    lateinit var playerName : String
    private lateinit var database: FirebaseDatabase
    private lateinit var playerRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editTextTextPersonName)
        button = findViewById(R.id.button)
        database = FirebaseDatabase.getInstance()


        var preferences = getSharedPreferences("PREFS", 0)
        playerName = preferences.getString("playerName", "").toString()
        if (playerName.equals("")) {
            playerRef = database.getReference("players/" + playerName)
            addEventListener()
            playerRef.setValue("")
        }
        button.setOnClickListener {
            playerName = editText.text.toString()
            editText.setText("")
            if (!playerName.equals("")) {
                button.setText("Logging in")
                button.isEnabled = false
                playerRef = database.getReference("players/" + playerName)
                addEventListener()
                playerRef.setValue("")
            }
        }
    }
    //read from db
fun addEventListener(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                //val post = dataSnapshot.getValue<Post>()
                // ...
                if (!playerName.equals("")) {
                    var preferences = getSharedPreferences("PREFS", 0)
                    var editor: SharedPreferences.Editor
                    editor = preferences.edit()
                    editor.putString("playerName", playerName)
                    editor.apply()
                    var intent = Intent(applicationContext, RoomActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                button.setText("LOG IN")
                button.isEnabled = true
                Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show()
                // ...
            }
        }
        playerRef.addValueEventListener(postListener)
    }

}