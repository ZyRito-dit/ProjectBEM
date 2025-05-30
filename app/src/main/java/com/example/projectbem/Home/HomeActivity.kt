package com.example.projectbem.Home

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.projectbem.Drawer.Profile.ProfileActivity
import com.example.projectbem.Drawer.SettingMenu.SettingActivity
import com.example.projectbem.R
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

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
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
        navView.setupWithNavController(navController)

        val navigationView: NavigationView = findViewById(R.id.drawer_nav_view)
        val headerView = navigationView.getHeaderView(0)
        val imageProfile = headerView.findViewById<CircleImageView>(R.id.imageViewProfile)
        val tvName = headerView.findViewById<TextView>(R.id.textViewName)

        tvName.text = "Kirigaya Zyrito"

        Glide.with(this)
            .load("https://i.pinimg.com/736x/7c/65/0a/7c650a069bffad9c44fa6d2893132e59.jpg")
            .into(imageProfile)

        navigationView.setNavigationItemSelectedListener{ menuItem ->
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

    }
}
