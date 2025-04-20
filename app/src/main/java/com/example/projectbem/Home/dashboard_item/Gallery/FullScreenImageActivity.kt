package com.example.projectbem.Home.dashboard_item.Gallery



import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.projectbem.Adapter.FullScreenImageAdapter
import com.example.projectbem.databinding.ActivityFullScreenImageBinding

class FullScreenImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullScreenImageBinding
    private lateinit var adapter: FullScreenImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageList = intent.getIntegerArrayListExtra("image_list") ?: arrayListOf()
        val selectedPosition = intent.getIntExtra("position", 0)
        val imageDate = intent.getStringExtra("image_date") ?: "Unknown Date"
        val imageTime = intent.getStringExtra("image_time") ?: "Unknown Time"

        adapter = FullScreenImageAdapter(imageList)
        binding.viewPager.adapter = adapter
        binding.viewPager.setCurrentItem(selectedPosition, false)


        binding.imageDate.text = imageDate
        binding.imageTime.text = imageTime

        binding.backButton.setOnClickListener {
            onBackPressed()
            finish()
        }

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                )




        binding.MenuShare.setOnClickListener {
            // TODO: share logic
        }
        binding.MenuFavorite.setOnClickListener {
            // TODO: favorite logic
        }
        binding.MenuDelete.setOnClickListener {
            // TODO: delete logic
        }
        binding.MenuMore.setOnClickListener {
            // TODO: more options logic
        }
    }
}
