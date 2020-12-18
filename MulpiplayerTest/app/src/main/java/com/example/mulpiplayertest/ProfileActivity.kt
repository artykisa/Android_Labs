package com.example.mulpiplayertest

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import com.squareup.picasso.Picasso


class ProfileActivity : AppCompatActivity() {

    private val TAG = "StorageActivity"
    //track Choosing Image Intent
    private val CHOOSING_IMAGE_REQUEST = 1234
    lateinit var btn_choosefile:Button
    lateinit var btn_uploadfile:Button
    lateinit var btn_save:Button
    private lateinit var btn_gravatar: Button
    lateinit var tvFileName:TextView
    private var fileUri: Uri? = null
    private var bitmap: Bitmap? = null
    lateinit  var editFileName:EditText
    lateinit  var editName:EditText
    var user = FirebaseAuth.getInstance().currentUser
    lateinit var imgFile:ImageView
    private var imageReference: StorageReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        imageReference = FirebaseStorage.getInstance().reference.child("images")
        setupUI()
    }
    fun setupUI(){
        tvFileName =findViewById<TextView>(R.id.tvFileName)
        imgFile = findViewById(R.id.imgFile)
        btn_gravatar= findViewById(R.id.gravatarButton)
        editName = findViewById(R.id.editName)
        editName.setText(user?.displayName)
        editFileName = findViewById(R.id.edtFileName)
        btn_choosefile = findViewById(R.id.btn_choose_file)
        btn_uploadfile = findViewById(R.id.btn_upload_file)
        btn_save = findViewById(R.id.setup_profile)
        btn_choosefile.setOnClickListener {
            showChoosingFile()
        }

        btn_gravatar.setOnClickListener {
            val hash = utility.algo_MD5(Firebase.auth.currentUser?.email!!)
            val gravatarUrl = "https://s.gravatar.com/avatar/$hash?s=80"
            Picasso.with(applicationContext)
                .load(gravatarUrl)
                .into(imgFile)
        }
        btn_save.setOnClickListener {
            var nick = editName.text.toString()
            if(nick.equals("")){
                Toast.makeText(this, "Write some name!", Toast.LENGTH_SHORT).show()
            }
            else{
                val user = FirebaseAuth.getInstance().currentUser

                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(nick).build()

                user!!.updateProfile(profileUpdates)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "User profile updated.")
                            Toast.makeText(this,"Profile updated!",Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(this,"Error in profile update!",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        btn_uploadfile.setOnClickListener {
            uploadFile()
        }
    }

    private fun uploadFile() {
        if (fileUri != null) {
            val fileName = editFileName.text.toString()

            if (!validateInputFileName(fileName)) {
                return
            }

            val fileRef = imageReference!!.child(fileName + "." + getFileExtension(fileUri!!))
            fileRef.putFile(fileUri!!)
                .addOnSuccessListener { taskSnapshot ->
                    Log.e(TAG, "Name: " + taskSnapshot.metadata!!.name)
                    tvFileName.text = taskSnapshot.metadata!!.path + " - " + taskSnapshot.metadata!!.sizeBytes / 1024 + " KBs"
                    Toast.makeText(this, "File Uploaded ", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, exception.message, Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener { taskSnapshot ->
                    // progress percentage
                    val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount

                    // percentage in progress dialog
                    val intProgress = progress.toInt()
                    tvFileName.text = "Uploaded " + intProgress + "%..."
                }
                .addOnPausedListener { System.out.println("Upload is paused!") }

        } else {
            Toast.makeText(this, "No File!", Toast.LENGTH_LONG).show()
        }
    }

    private fun showChoosingFile() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Image"), CHOOSING_IMAGE_REQUEST)
    }

    private fun validateInputFileName(fileName: String): Boolean {
        if (TextUtils.isEmpty(fileName)) {
            Toast.makeText(this, "Enter file name!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
    private fun getFileExtension(uri: Uri): String? {
        val contentResolver = contentResolver
        val mime = MimeTypeMap.getSingleton()

        return mime.getExtensionFromMimeType(contentResolver.getType(uri))
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (bitmap != null) {
            bitmap!!.recycle()
        }

        if (requestCode == CHOOSING_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            fileUri = data.data
            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
                imgFile.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}