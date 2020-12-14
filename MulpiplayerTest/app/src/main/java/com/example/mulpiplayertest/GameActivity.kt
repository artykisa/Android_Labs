package com.example.mulpiplayertest

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*

class GameActivity : AppCompatActivity() {

    lateinit var button : Button
    var playerName =""
    var roomName = ""
    var role = ""
    var message = ""

    lateinit var database : FirebaseDatabase
    lateinit var messageRef : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        button = findViewById(R.id.button3)
        button.isEnabled = false

        database = FirebaseDatabase.getInstance()

        var preferences = getSharedPreferences("PREFS", 0)
        playerName = preferences.getString("playerName","").toString()
        var extras = intent.extras
        if(extras!= null){
            roomName = extras.getString("roomName").toString()
            if(roomName.equals(playerName)){
                role = "host"
            }
            else{
                role="guest"
            }
        }
        button.setOnClickListener {
            button.isEnabled = false
            message = role + ":Poked!"
            messageRef.setValue(message)

        }

        //incoming message
        messageRef = database.getReference("rooms/"+roomName+"/message")
        message = role+":Poked!"
        messageRef.setValue(message)
        addRoomEventListener()
    }
    fun addRoomEventListener(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                //val post = dataSnapshot.getValue<Post>()
                // ...
                if (role.equals("host")) {
                    if(dataSnapshot.value.toString().contains("guest:")){
                        button.isEnabled = true
                        Toast.makeText(applicationContext,""+dataSnapshot.value.toString().replace("guest:",""),Toast.LENGTH_SHORT )

                    }
                }
                else{
                    if(dataSnapshot.value.toString().contains("host:")){
                        button.isEnabled = true
                        Toast.makeText(applicationContext,""+dataSnapshot.value.toString().replace("host:",""),Toast.LENGTH_SHORT )

                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                messageRef.setValue(message)
                // ...
            }
        }
        messageRef.addValueEventListener(postListener)
    }
}