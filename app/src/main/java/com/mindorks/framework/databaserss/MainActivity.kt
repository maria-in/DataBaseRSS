package com.mindorks.framework.databaserss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.mindorks.framework.databaserss.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_main) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menu?.findItem(R.id.change_sql_screen)?.isEnabled = false
        this.menu = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.change_room_screen){
            navController.navigate(R.id.action_roomFragment_to_sqlFragment)
            item.isEnabled = false
            menu?.findItem(R.id.change_sql_screen)?.isEnabled = true
        }
        if (item.itemId == R.id.change_sql_screen){
            navController.navigate(R.id.action_sqlFragment_to_roomFragment)
            item.isEnabled = false
            menu?.findItem(R.id.change_room_screen)?.isEnabled = true
        }
        return super.onOptionsItemSelected(item)
    }

}