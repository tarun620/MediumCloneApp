package com.example.conduit_2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.example.api.models.entities.User
import com.example.conduit_2.databinding.ActivityMainBinding

class   MainActivity : AppCompatActivity() {
    companion object{
        const val PREFS_FILE_AUTH="prefs_auth"
        const val PREFS_KEY_TOKEN="token"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var sharedPrefrences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefrences=getSharedPreferences(PREFS_FILE_AUTH, Context.MODE_PRIVATE)
        binding = ActivityMainBinding.inflate(layoutInflater)
        authViewModel=ViewModelProvider(this).get(AuthViewModel::class.java)

//        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_feed,
            R.id.nav_my_feed,
            R.id.nav_auth), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//shared prefrences handling
        sharedPrefrences.getString(PREFS_KEY_TOKEN,null)?.let{
            authViewModel.getCurrentUser(it)
        }
//navigation handling
        authViewModel.user?.observe({lifecycle}){
                updateMenu(it)
            Toast.makeText(this,"logged in as ${it?.username}", Toast.LENGTH_LONG).show()
            it?.token?.let{
                sharedPrefrences.edit{
                    putString(PREFS_KEY_TOKEN,it)
                }
            }?:run{                       //     ?: IS CALLED ELVIS OPERATOR
                sharedPrefrences.edit{
                    remove(PREFS_KEY_TOKEN)
                }
            }
            navController.navigateUp()
        }
    }

    private fun updateMenu(user: User?){
        when(user){
            is User->{
                binding.navView.menu.clear()
                binding.navView.inflateMenu(R.menu.menu_main_user)
            }
            else->{
                binding.navView.menu.clear()
                binding.navView.inflateMenu(R.menu.menu_main_guest)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_logout ->{
                authViewModel.logout()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}