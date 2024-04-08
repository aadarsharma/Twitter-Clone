package com.example.twitterclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TweetActivity : AppCompatActivity() {
    private lateinit var edtEnterTweet: EditText
    private lateinit var btnUploadTweet: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)

        init()

        btnUploadTweet.setOnClickListener{
            val tweet = edtEnterTweet.text.toString()
            addTweet(tweet)
        }
    }

    private fun addTweet(tweet: String) {
        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().uid.toString())
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val listOfTweets = snapshot.child("listOfTweets").value as MutableList<String>
                    listOfTweets.add(tweet)
                    uploadTweetList(listOfTweets)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }
    private fun uploadTweetList(listOfTweet: List<String>) {
        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().uid.toString())
            .child("listOfTweets").setValue(listOfTweet)
        Toast.makeText(this, "Tweet uploaded successfully", Toast.LENGTH_SHORT).show()
    }

    private fun init() {
        edtEnterTweet = findViewById(R.id.edt_enter_tweet)
        btnUploadTweet = findViewById(R.id.btn_upload_tweet)
    }
}