package com.jamshidbek.apiretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jamshidbek.apiretrofit.API.APIRequests
import com.jamshidbek.apiretrofit.API.Comments.Comments
import com.jamshidbek.apiretrofit.API.Posts.Post
import com.jamshidbek.apiretrofit.Adapters.CommentRecyclerViewAdapter
import com.jamshidbek.apiretrofit.Adapters.PostRecyclerViewAdapter
import com.jamshidbek.apiretrofit.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCommentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postId = intent.getIntExtra("postId", -1).toString()

        APIRequests.GetResponse().getComments(postId).enqueue(object : Callback<Comments> {
            override fun onResponse(call: Call<Comments>, response: Response<Comments>) {
                if (response.isSuccessful){
                    val comments = response.body()
                    val adapter = CommentRecyclerViewAdapter(comments!!, this@CommentsActivity)
                    binding.commentsRecycler.adapter = adapter
                }
            }

            override fun onFailure(call: Call<Comments>, t: Throwable) {
                Toast.makeText(this@CommentsActivity, ""+t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}