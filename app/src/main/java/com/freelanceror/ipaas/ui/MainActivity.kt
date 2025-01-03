package com.freelanceror.ipaas.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import com.freelanceror.ipaas.viewmodel.MainViewModel
import com.freelanceror.ipaas.adapter.PictureAdapter
import com.freelanceror.ipaas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.fetchPictures("YOUR_API_KEY_HERE")
        viewModel.pictures.observe(this) { pictures ->
            binding.recyclerView.adapter = PictureAdapter(this, pictures)
            { picture ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("PICTURE", picture)
                startActivity(intent)
            }
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }
}
