package com.freelanceror.ipaas.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.freelanceror.ipaas.model.NasaPicture
import com.freelanceror.ipaas.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val picture = intent.getSerializableExtra("PICTURE") as? NasaPicture ?: return

        binding.titleView.text = picture.title
        binding.explanationView.text = picture.explanation
        Glide.with(this).load(picture.url).into(binding.imageView)

        binding.moreButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(picture.hdurl))
            startActivity(intent)
        }

        binding.shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "${picture.title}\n${picture.hdurl}")
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }
}
