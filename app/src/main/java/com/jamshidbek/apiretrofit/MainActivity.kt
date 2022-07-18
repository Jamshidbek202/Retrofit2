package com.jamshidbek.apiretrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jamshidbek.apiretrofit.API.APIRequests
import com.jamshidbek.apiretrofit.API.User.User
import com.jamshidbek.apiretrofit.Adapters.UserRecyclerViewAdapter
import com.jamshidbek.apiretrofit.databinding.ActivityMainBinding
import retrofit2.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        APIRequests.GetResponse().getUsers().enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful){
                    val users = response.body()
                    val adapter = UserRecyclerViewAdapter(users!!, applicationContext)
                    binding.recyclerUser.adapter = adapter
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(applicationContext, ""+t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}