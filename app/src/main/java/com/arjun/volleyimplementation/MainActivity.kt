package com.arjun.volleyimplementation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.volleyimplementation.R
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val url:String = "https://meme-api.com/gimme"

    private lateinit var btn:Button
    private lateinit var title:TextView
    private lateinit var img:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = findViewById(R.id.txt_title)
        img = findViewById(R.id.meme_img)

        btn = findViewById(R.id.btn)
        btn.setOnClickListener {
            getMemeData()
        }

    }
    fun getMemeData(){

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                var responseObject = JSONObject(response)
                title.text = responseObject.getString("title")
                Glide.with(this@MainActivity).load(responseObject.get("url")).into(img)
//                Log.e("Responce","getMemeData: " + response.toString())
            },
            { error ->
                Toast.makeText(this@MainActivity," ${ error.localizedMessage}",Toast.LENGTH_LONG)
                    .show()
            })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}