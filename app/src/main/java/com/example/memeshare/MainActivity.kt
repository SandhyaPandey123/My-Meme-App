package com.example.memeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class MainActivity : AppCompatActivity() {
    var currentImageUrl:String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()
    }
    private fun loadMeme(){
        // Instantiate the RequestQueue.

        //ProgressBar.setvisibility=View.VISIBLE


        //ProgressBar.setvisibility=View.VISIBLE

        val url = "https://meme-api.herokuapp.com/gimme"
        // Request a string response from the provided URL.
        val JsonObjectRequest =JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener { response ->
                currentImageUrl = response.getString("url")


                Glide.with(this).load(currentImageUrl).into(findViewById(R.id.memeImage))//listener(object : RequestListener<drawable>{
                    /*override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<drawable>?, isFirstResource: Boolean): Boolean {
                        //ProgressBar.visibility=View.GONE
                        return false
                        TODO("Not yet implemented")
                    }

                    override fun onResourceReady(resource: drawable?, model: Any?, target: Target<drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        ProgressBar.visibility=View.GONE
                        return false
                        TODO("Not yet implemented")
                    }
                }).into(findViewById(R.id.memeImage))*/


            },
            Response.ErrorListener {
                Toast.makeText(this,"Something went worng",Toast.LENGTH_LONG).show()


            })


        MySingleton.getInstance(this).addToRequestQueue(JsonObjectRequest)
    }
    fun nextMeme(view: View) {
        loadMeme()

    }
    fun shareMeme(view: View) {
        val intent= Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"hey! $currentImageUrl")
        val chooser =Intent.createChooser(intent,"share this")
        startActivity(chooser)

    }
}