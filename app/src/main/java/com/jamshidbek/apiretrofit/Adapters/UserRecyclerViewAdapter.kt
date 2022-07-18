package com.jamshidbek.apiretrofit.Adapters

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.RecyclerView
import com.jamshidbek.apiretrofit.API.User.User
import com.jamshidbek.apiretrofit.PostsActivity
import com.jamshidbek.apiretrofit.databinding.AddressDialogBinding
import com.jamshidbek.apiretrofit.databinding.CompanyDialogBinding
import com.jamshidbek.apiretrofit.databinding.UserItemBinding

class UserRecyclerViewAdapter(
    private val userList: User,
    public val context: Context
) : RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.txtName.text = userList[position].name
        holder.binding.txtUsername.text = userList[position].username
        holder.binding.txtEmail.text = userList[position].email
        holder.binding.txtPhone.text = userList[position].phone
        holder.binding.txtWebsite.text = userList[position].website

        holder.binding.btnAddress.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val dialog = p0?.context?.let { Dialog(it) }
                dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
                val inflater = p0?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val dialogBinding : AddressDialogBinding = AddressDialogBinding.inflate(inflater)
                dialog?.setContentView(dialogBinding.root)
                dialogBinding.txtStreet.text = userList[position].address.street
                dialogBinding.txtSuite.text = userList[position].address.suite
                dialogBinding.txtCity.text = userList[position].address.city
                dialogBinding.txtZipcode.text = userList[position].address.zipcode
                dialog?.show()
            }
        })

        holder.binding.btnCompany.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val dialog = p0?.context?.let { Dialog(it) }
                dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
                val inflater = p0?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val dialogBinding : CompanyDialogBinding = CompanyDialogBinding.inflate(inflater)
                dialog?.setContentView(dialogBinding.root)
                dialogBinding.txtName.text = userList[position].company.name
                dialogBinding.txtCatchPhrase.text = userList[position].company.catchPhrase
                dialogBinding.txtBs.text = userList[position].company.bs
                dialog?.show()
            }
        })

        holder.itemView.setOnClickListener {
            val intent = Intent(context, PostsActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("userId", userList[position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = userList.size

    inner class UserViewHolder(val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}