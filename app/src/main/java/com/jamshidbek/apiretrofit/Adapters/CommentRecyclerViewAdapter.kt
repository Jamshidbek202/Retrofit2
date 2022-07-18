package com.jamshidbek.apiretrofit.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jamshidbek.apiretrofit.API.Comments.Comments
import com.jamshidbek.apiretrofit.databinding.CommentItemBinding

class CommentRecyclerViewAdapter(
    private val commentsList: Comments,
    public val context: Context
) : RecyclerView.Adapter<CommentRecyclerViewAdapter.CommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder =
        CommentsViewHolder(CommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.binding.txtPostid.text = commentsList[position].postId.toString()
        holder.binding.txtPostName.text = commentsList[position].name
        holder.binding.txtPosterEmail.text = commentsList[position].email
        holder.binding.txtBody.text = commentsList[position].body
    }

    override fun getItemCount() = commentsList.size

    inner class CommentsViewHolder(val binding: CommentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}