package com.freelanceror.ipaas.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import com.freelanceror.ipaas.viewmodel.MainViewModel
import com.freelanceror.ipaas.adapter.PictureAdapter
import com.freelanceror.ipaas.data.PreferencesManager
import com.freelanceror.ipaas.databinding.ActivityMainBinding
import com.freelanceror.ipaas.utils.Constants
import com.freelanceror.ipaas.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(PreferencesManager(this))
    }
    private lateinit var adapter: PictureAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
        viewModel.fetchPictures(Constants.NASA_API_KEY)
    }

    private fun setupRecyclerView() {
        adapter = PictureAdapter(this, listOf()) { picture ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("PICTURE", picture)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.pictures.observe(this) { pictures ->
            adapter.updateList(pictures)
        }

        viewModel.error.observe(this) { error ->
            if (!error.isNullOrEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}