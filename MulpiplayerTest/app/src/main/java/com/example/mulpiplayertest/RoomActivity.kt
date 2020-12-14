package com.example.mulpiplayertest

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*

class RoomActivity : AppCompatActivity() {
    lateinit var listView : ListView
    lateinit var button : Button
    var roomList = mutableListOf<String>()
    lateinit var playerName:String
    lateinit var roomName:String

    lateinit var database : FirebaseDatabase
    lateinit var roomRef : DatabaseReference
    lateinit var roomsRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        database = FirebaseDatabase.getInstance()

        var preferences = getSharedPreferences("PREF", 0)

        playerName = preferences.getString("playerName","").toString()
        Toast.makeText(applicationContext,playerName,Toast.LENGTH_SHORT).show()
        roomName = playerName
        listView = findViewById(R.id.listView)
        button = findViewById(R.id.button2)

        roomList = mutableListOf<String>()

        button.setOnClickListener {
            button.setText("CREATING ROOM")
            button.isEnabled = false
            roomName = playerName
            roomRef = database.getReference("rooms/" + roomName + "/player1")
            addRoomEventListener()
            roomRef.setValue(playerName)
        }
        listView.setOnItemClickListener { parent, view, position, id ->
            roomName = roomList.get(position)
            roomRef = database.getReference("room/"+roomName+"/player2")
            addRoomEventListener()
            roomRef.setValue(playerName)
        }
        addRoomsEventListener()
    }
    fun addRoomEventListener(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                //val post = dataSnapshot.getValue<Post>()
                // ...
                button.setText("CREATE ROOM")
                button.isEnabled = true
                var intent = Intent(applicationContext,GameActivity::class.java)
                intent.putExtra("roomName", roomName)
                startActivity(intent)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                button.setText("CREATE ROOM")
                button.isEnabled = true
                Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show()
                // ...
            }
        }
        roomRef.addValueEventListener(postListener)
    }

    fun addRoomsEventListener(){
        roomsRef = database.getReference("rooms")
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                //val post = dataSnapshot.getValue<Post>()
                // ...
                roomList.clear()
               // Toast.makeText(applicationContext, "Error in ondata!", Toast.LENGTH_SHORT).show()
                var rooms = dataSnapshot.children//tut
                for(snapshot in rooms){
                    roomList.add(snapshot.key.toString())
                    var adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_list_item_1, roomList)//tut
                    listView.adapter = adapter
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
               // Toast.makeText(applicationContext, "Error rooms!", Toast.LENGTH_SHORT).show()
                // Getting Post failed, log a message
                // ...
            }
        }
        roomsRef.addValueEventListener(postListener)
    }
}