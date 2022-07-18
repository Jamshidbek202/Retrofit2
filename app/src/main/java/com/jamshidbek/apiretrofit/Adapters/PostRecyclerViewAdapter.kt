package com.jamshidbek.apiretrofit.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jamshidbek.apiretrofit.API.Posts.Post
import com.jamshidbek.apiretrofit.CommentsActivity
import com.jamshidbek.apiretrofit.PostsActivity
import com.jamshidbek.apiretrofit.databinding.PostItemBinding

class PostRecyclerViewAdapter(
    private val postList: Post,
    public val context: Context
) : RecyclerView.Adapter<PostRecyclerViewAdapter.PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder =
        PostsViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.binding.txtId.text = postList[position].userId.toString()
        holder.binding.txtTitle.text = postList[position].title
        holder.binding.txtBody.text = postList[position].body

        holder.itemView.setOnClickListener {
            val intent = Intent(context, CommentsActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("postId", postList[position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = postList.size

    inner class PostsViewHolder(val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}