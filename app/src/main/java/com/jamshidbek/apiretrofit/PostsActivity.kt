package com.jamshidbek.apiretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jamshidbek.apiretrofit.API.APIRequests
import com.jamshidbek.apiretrofit.API.Posts.Post
import com.jamshidbek.apiretrofit.Adapters.PostRecyclerViewAdapter
import com.jamshidbek.apiretrofit.databinding.ActivityPostsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPostsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getIntExtra("userId", -1).toString()

        APIRequests.GetResponse().getPosts(userId).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful){
                    val posts = response.body()
                    val adapter = PostRecyclerViewAdapter(posts!!, this@PostsActivity)
                    binding.recyclerPost.adapter = adapter
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(this@PostsActivity, ""+t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}