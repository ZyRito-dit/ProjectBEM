package com.example.projectbem.Home

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.projectbem.Data.repository.ProfileRepository
import com.example.projectbem.Data.retrofit.ApiConfig
import com.example.projectbem.Drawer.Profile.ProfileActivity
import com.example.projectbem.Drawer.Profile.ProfileViewModel
import com.example.projectbem.Drawer.SettingMenu.SettingActivity
import com.example.projectbem.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView

class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        drawerLayout = findViewById(R.id.drawer_layout)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val bottomNavView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment_home)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_jadwal,
                R.id.navigation_votes,
                R.id.navigation_notifications
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)


        val drawerNavView: NavigationView = findViewById(R.id.drawer_nav_view)

        drawerNavView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_settings -> {
                    startActivity(Intent(this, SettingActivity::class.java))
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }

        val headerView = drawerNavView.getHeaderView(0)
        val imageViewProfile = headerView.findViewById<CircleImageView>(R.id.imageViewProfile)
        val textViewName = headerView.findViewById<TextView>(R.id.textViewName)

        val profileViewModel = ProfileViewModel(ProfileRepository(ApiConfig.getApiService(this)))

        profileViewModel.profileLiveData.observe(this) { profile ->
            textViewName.text = profile.username

            val imageUrl = profile.image?.toString()
            if (!imageUrl.isNullOrBlank()) {
                Glide.with(this)
                    .load(imageUrl)
                    .into(imageViewProfile)
            } else {
                imageViewProfile.setImageResource(R.drawable.ic_profile)
            }
        }
        profileViewModel.loadProfile()
    }
}
