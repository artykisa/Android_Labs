package com.example.timer


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    var MAIN_FRAGMENT_TAG="MAIN_ACTIVITY_LIST"
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this,mainFactory(application)).get(MainViewModel::class.java)

    }
    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.xml.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.settings) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivityForResult(intent, 1)
        }
        return super.onOptionsItemSelected(item)

    }
    fun Start(v:View){
        val intent=Intent(this,TimerActivity::class.java)
        intent.putExtra("exeId",mainViewModel.exersice!!.id)
        startActivity(intent)
    }
    fun AddExe(v:View){
        val intent=Intent(this,AddExeActivity::class.java)
        startActivity(intent)
    }
    fun AllExes(v:View){
        val intent=Intent(this,ExeActivity::class.java)
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data==null){return}
        val currActivity=data.getIntExtra("exeId",1)
        Toast.makeText(this,currActivity.toString(), Toast.LENGTH_SHORT).show()
        mainViewModel.renewExercise(currActivity)
        val ft=supportFragmentManager.beginTransaction()
        var fragment=supportFragmentManager.findFragmentByTag(MAIN_FRAGMENT_TAG)
        if(fragment!=null)
            ft.remove(fragment)
        fragment=MainFragment.newInstance()
        ft.add(R.id.main_activities,fragment,MAIN_FRAGMENT_TAG)
        ft.commit()
        mainViewModel.subscibe(fragment)
        fragment.setCRUDListner(mainViewModel)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.checkactivity()
        val ft=supportFragmentManager.beginTransaction()
        var fragment=supportFragmentManager.findFragmentByTag( MAIN_FRAGMENT_TAG)
        if(fragment!=null)
            ft.remove(fragment)
        fragment=MainFragment.newInstance()
        ft.add(R.id.main_activities,fragment,MAIN_FRAGMENT_TAG)
        ft.commit()
        //showSplashScreen()
        mainViewModel.subscibe(fragment)
        fragment.setCRUDListner(mainViewModel)
        findViewById<Button>(R.id.btn2).invalidate()

    }
}