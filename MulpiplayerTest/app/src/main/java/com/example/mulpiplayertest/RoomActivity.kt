package com.example.mulpiplayertest

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class RoomActivity : AppCompatActivity() {
    lateinit var listView : ListView
    lateinit var button : Button
    var roomList = mutableListOf<String>()

    lateinit var roomName:String
    var user = FirebaseAuth.getInstance().currentUser
    var playerName = user?.displayName
    lateinit var database : FirebaseDatabase
    lateinit var roomRef : DatabaseReference
    lateinit var roomsRef: DatabaseReference
    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, RoomActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        database = FirebaseDatabase.getInstance()

        var preferences = getSharedPreferences("PREFS", 0)

        Toast.makeText(applicationContext,playerName,Toast.LENGTH_SHORT).show()
        roomName = playerName.toString()
        listView = findViewById(R.id.listView)
        button = findViewById(R.id.button2)
        button.text = playerName
        roomList = mutableListOf<String>()

        button.setOnClickListener {
            button.setText("CREATING ROOM")
            button.isEnabled = false
            roomName = playerName.toString()
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

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.action_settings) {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivityForResult(intent, 1)
        }
        return super.onOptionsItemSelected(item)

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