package com.example.projectbem.Home.dashboard_item.Gallery

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.projectbem.Adapter.FullScreenImageAdapter
import com.example.projectbem.R
import com.example.projectbem.databinding.ActivityFullScreenImageBinding


class FullScreenImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullScreenImageBinding
    private lateinit var adapter: FullScreenImageAdapter
    private val viewModel: FullScreenImageViewModel by viewModels()

    private var isUiVisible = true
    private val handler = Handler(Looper.getMainLooper())
    private var hideRunnable: Runnable? = null
    private var lastInteractionTime = 0L
    private var isInMenuInteraction = false



    private fun toggleUI() {
        if (isUiVisible) {
            binding.allbackTime.animate().translationY(-200f).setDuration(300).start()
            binding.menuBar.animate().translationY(200f).setDuration(300).start()
        } else {
            binding.allbackTime.animate().translationY(0f).setDuration(300).start()
            binding.menuBar.animate().translationY(0f).setDuration(300).start()
        }
        isUiVisible = !isUiVisible
        if (isUiVisible) startAutoHideTimer()
    }

    private fun startAutoHideTimer() {
        hideRunnable?.let { handler.removeCallbacks(it) }
        hideRunnable = Runnable {
            if (System.currentTimeMillis() - lastInteractionTime >= 10_000 && isUiVisible && !isInMenuInteraction) {
                toggleUI()
            }
        }
        lastInteractionTime = System.currentTimeMillis()
        handler.postDelayed(hideRunnable!!, 10_000)
    }

    private fun enableImmersiveMode() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("FullScreenImage", "Activity dibuka")
        enableImmersiveMode()


        val imageList = intent.getStringArrayListExtra("image_list") ?: arrayListOf()
        val imageIdList = intent.getStringArrayListExtra("image_id_list") ?: arrayListOf()
        val selectedPosition = intent.getIntExtra("position", 0)
        val imageDate = intent.getStringExtra("image_date") ?: "Unknown Date"
        val imageTime = intent.getStringExtra("image_time") ?: "Unknown Time"


        viewModel.setImageIdList(imageIdList)
        viewModel.setData(imageList, selectedPosition, imageDate, imageTime)


        binding.imageDate.text = imageDate
        binding.imageTime.text = imageTime


        adapter = FullScreenImageAdapter(imageList)
        binding.viewPager.adapter = adapter
        binding.viewPager.setCurrentItem(selectedPosition, false)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.setSelectedPosition(position)
            }
        })


        adapter.onImageClick = {
            toggleUI()
        }

        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }


        binding.MenuShare.setOnClickListener {
            isInMenuInteraction = true
            // TODO: share logic
            resetMenuInteraction()
        }

        binding.MenuFavorite.setOnClickListener {
            isInMenuInteraction = true
            // TODO: favorite logic
            resetMenuInteraction()
        }

        val role = getSharedPreferences("APP_PREF", MODE_PRIVATE).getString("ROLE", "user")


        if (role != "superadmin") {
            binding.MenuDelete.visibility = View.GONE
        }


        binding.MenuDelete.setOnClickListener {
            isInMenuInteraction = true
            showDeleteConfirmationDialog()
            resetMenuInteraction()
        }

        binding.MenuMore.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.popup_image_menu, null)
            val popup = PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
            popup.elevation = 10f
            popup.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            popup.isOutsideTouchable = true
            popup.showAsDropDown(it, -100, -220)

            val rincian = view.findViewById<TextView>(R.id.menuDetail)
            rincian.setOnClickListener {
                popup.dismiss()
                showRincianBottomSheet()
            }
        }

        startAutoHideTimer()
    }

    private fun showDeleteConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Hapus Gambar")
        builder.setMessage("Yakin ingin menghapus gambar ini?")
        builder.setPositiveButton("Hapus") { _, _ ->
            val position = viewModel.selectedPosition.value ?: 0
            val imageId = viewModel.getImageIdAt(position)
            viewModel.deleteGallery(this, imageId) { success ->
                if (success) {
                    Toast.makeText(this, "Berhasil menghapus gambar", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Gagal menghapus gambar", Toast.LENGTH_SHORT).show()
                }
                Log.d("DeleteImage", "Image ID yang akan dihapus: $imageId")
            }
        }
        builder.setNegativeButton("Batal") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun showRincianBottomSheet() {
        val bottomSheet = ImageDetailBottomSheet.newInstance(
            nama = "IMG-20250420-WA0000",
            waktu = "20/04/25 08.06.52",
            dimensi = "900 x 1600 Piksel",
            ukuran = "157 KB",
            lokasi = "Ponsel/WhatsApp/Media/WhatsApp Images/..."
        )
        bottomSheet.show(supportFragmentManager, "BottomSheetDialog")
    }

    private fun resetMenuInteraction() {
        handler.postDelayed({
            isInMenuInteraction = false
            startAutoHideTimer()
        }, 1500)
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        if (isUiVisible) {
            startAutoHideTimer()
        }
    }
}
