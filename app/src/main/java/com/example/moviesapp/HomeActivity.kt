package com.example.moviesapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.adapters.ImageAdapter
import com.example.moviesapp.databinding.ActivityHomeBinding
import com.example.moviesapp.models.ImageItem
import java.util.UUID

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageCarrousel = findViewById<RecyclerView>(R.id.imageCarousel)

        val imageList = arrayListOf(
            ImageItem(
                UUID.randomUUID().toString(),
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQiPkGXMO-C4oQkP3xxd5_e8kIwrT5cbPQ7TA&s"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://m.media-amazon.com/images/I/914T1aqfVtL._AC_UF894,1000_QL80_.jpg"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://maadshirts.com/wp-content/uploads/2024/02/The-Lobster.jpeg"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://image.tmdb.org/t/p/original/VHSzNBTwxV8vh7wylo7O9CLdac.jpg"
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                "https://i.etsystatic.com/18242346/r/il/7ad080/4580913585/il_570xN.4580913585_3yhu.jpg"
            )

        )

        val imageAdapter = ImageAdapter()
        imageCarrousel.adapter = imageAdapter
        imageAdapter.submitList(imageList)

        binding.watchedCardView.setOnClickListener {
            val intent = Intent(this, WatchedActivity::class.java)
            startActivity(intent)
        }

        binding.watchlistCardView.setOnClickListener {
            val intent = Intent(this, WatchlistActivity::class.java)
            startActivity(intent)
        }
    }
}