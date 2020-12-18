package com.example.mulpiplayertest

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class GameActivity : AppCompatActivity() {

    var user = FirebaseAuth.getInstance().currentUser
    var playerName = user?.displayName
    var roomName = ""
    var role = ""
    var second_role =""
    var message = ""
    var game = false
    var ships = ""
    var ships2 = ""
    lateinit var set1 : Button
    lateinit var set2 : Button
    lateinit var set3 : Button
    lateinit var set4 : Button
    lateinit var set5 : Button
    lateinit var set6 : Button
    lateinit var set7 : Button
    lateinit var set8 : Button
    lateinit var set9 : Button
    lateinit var database : FirebaseDatabase
    lateinit var messageRef : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        database = FirebaseDatabase.getInstance()

        playerName = user?.displayName
        var extras = intent.extras
        if(extras!= null){
            roomName = extras.getString("roomName").toString()
            if(roomName.equals(playerName)){
                role = "host"
                second_role = "guest"
            }
            else{
                role="guest"
                second_role = "host"
            }
        }
        setUI()


        //incoming message
        messageRef = database.getReference("rooms/"+roomName+"/message")
        message = role+":Poked!"
        messageRef.setValue(message)
        addShipEventListener()
    }

    fun setUI(){
        set1 = findViewById(R.id.set1)
        set2 = findViewById(R.id.set2)
        set3 = findViewById(R.id.set3)
        set4 = findViewById(R.id.set4)
        set5 = findViewById(R.id.set5)
        set6 = findViewById(R.id.set6)
        set7 = findViewById(R.id.set7)
        set8 = findViewById(R.id.set8)
        set9 = findViewById(R.id.set9)
        set1.setOnClickListener {
            shot("1")
        }
        set2.setOnClickListener {
            shot("2")
        }
        set3.setOnClickListener {
            shot("3")
        }
        set4.setOnClickListener {
            shot("4")
        }
        set5.setOnClickListener {
            shot("5")
        }
        set6.setOnClickListener {
            shot("6")
        }
        set7.setOnClickListener {
            shot("7")
        }
        set8.setOnClickListener {
            shot("8")
        }
        set9.setOnClickListener {
            shot("9")
        }
    }

    fun setup_ships(ships:String, role : String){
        messageRef = database.getReference("rooms/"+roomName+"/ships/"+role)
        messageRef.setValue(ships)
        Toast.makeText(applicationContext,ships,Toast.LENGTH_SHORT).show()
        disableButtons()
        messageRef = database.getReference("rooms/" + roomName + "/ready/" + role)
        messageRef.setValue("ready")
        addReadyEventListener()
    }

    fun shot(target : String){
        if(game) {
            messageRef = database.getReference("rooms/" + roomName + "/shot/" + role)
            messageRef.setValue(target)
        }
        else{
            setship(target)
        }
    }

    fun setship(target: String){
        if(ships.length<4){
            ships +=target
        }
        else if(ships.equals("null")){
            ships = target
        }
        else{
            setup_ships(ships, role)
        }
    }

    fun disableButtons(){
        set1.isEnabled = false
        set2.isEnabled = false
        set3.isEnabled = false
        set4.isEnabled = false
        set5.isEnabled = false
        set6.isEnabled = false
        set7.isEnabled = false
        set8.isEnabled = false
        set9.isEnabled = false
    }

    fun enableButtons(){
        set1.isEnabled = true
        set2.isEnabled = true
        set3.isEnabled = true
        set4.isEnabled = true
        set5.isEnabled = true
        set6.isEnabled = true
        set7.isEnabled = true
        set8.isEnabled = true
        set9.isEnabled = true

    }


    fun addShotEventListener(){
        var messageRefe = database.getReference("rooms/" + roomName + "/shot/" + role)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                //val post = dataSnapshot.getValue<Post>()
                // ...
                if (role.equals("host")) {
                    Toast.makeText(applicationContext,""+dataSnapshot.value.toString(),Toast.LENGTH_SHORT ).show()
                    check_shot(dataSnapshot.value.toString(), second_role)

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
            }
        }
        messageRefe.addValueEventListener(postListener)

        var messageRefe2 = database.getReference("rooms/" + roomName + "/shot/" + second_role)
        val postListener2 = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                //val post = dataSnapshot.getValue<Post>()
                // ...
                if (role.equals("host")) {
                    Toast.makeText(applicationContext,""+dataSnapshot.value.toString(),Toast.LENGTH_SHORT ).show()
                    check_shot(dataSnapshot.value.toString(), second_role)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
            }
        }
        messageRefe2.addValueEventListener(postListener2)
    }


    fun addReadyEventListener(){
        var messageReadyRef = database.getReference("rooms/"+roomName+"/ready/"+second_role)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if(dataSnapshot.value.toString().contains("ready")){
                        game = true
                        addWinEventListener()
                        addShotEventListener()
                        enableButtons()
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Toast.makeText( applicationContext,"Fail in event listener", Toast.LENGTH_SHORT).show()
                // ...
            }
        }
        messageReadyRef.addValueEventListener(postListener)
    }

    fun addShipEventListener(){
        var messageShipRef1 = database.getReference("rooms/"+roomName+"/ships/"+role)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                    ships = dataSnapshot.value.toString()
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText( applicationContext,"Fail in event listener", Toast.LENGTH_SHORT).show()
            }
        }

        var messageShipRef2 = database.getReference("rooms/"+roomName+"/ships/"+second_role)
        val post2Listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                ships2 = dataSnapshot.value.toString()
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText( applicationContext,"Fail in event listener", Toast.LENGTH_SHORT).show()
            }
        }
        messageShipRef1.addValueEventListener(postListener)
        messageShipRef2.addValueEventListener(post2Listener)
    }


    fun addWinEventListener(){
        var messageWinRef1 = database.getReference("rooms/"+roomName+"/ships/"+role)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.value.toString().isEmpty()){
                    Toast.makeText(applicationContext,"The lose is yours",Toast.LENGTH_LONG).show()
                    var messageR = database.getReference("players/"+playerName+"/games/game")
                    messageR.setValue("lose")
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText( applicationContext,"Fail in event listener", Toast.LENGTH_SHORT).show()
            }
        }

        var messageWinRef2 = database.getReference("rooms/"+roomName+"/ships/"+second_role)
        val post2Listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.value.toString().isEmpty()){
                    Toast.makeText(applicationContext,"The win is yours",Toast.LENGTH_LONG).show()
                    var messageR = database.getReference("players/"+playerName+"/games/game")
                    messageR.setValue("winn")
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText( applicationContext,"Fail in event listener", Toast.LENGTH_SHORT).show()
            }
        }
        messageWinRef1.addValueEventListener(postListener)
        messageWinRef2.addValueEventListener(post2Listener)
    }

    fun check_shot(target : String, role : String){
        if(role=="host"){
            if(ships.contains(target)){
                ships = ships.replace(target,"")
                setup_ships(ships,"host")
            }
        }
        if(role=="guest"){
            if(ships2.contains(target)){
                ships2 = ships2.replace(target,"")
                setup_ships(ships2,"guest")
            }
        }
    }

    override fun onStop () {
        var mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("rooms").child(roomName);
        mPostReference.removeValue();
        super.onStop()
    }
}