package com.berkerhan.donemodevi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.berkerhan.donemodevi.data.repository.MainRepository
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var mainViewModel: MainViewModel

    lateinit var aboutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        navController=Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this,navController)

        val mainRepository=MainRepository()
        val viewModelProviderFactory=MainViewModelProviderFactory(application,mainRepository)
        mainViewModel=ViewModelProvider(this,viewModelProviderFactory).get(MainViewModel::class.java)

        aboutButton=findViewById(R.id.about_button)

        aboutButton.setOnClickListener {
            val aboutIntent=Intent(this, AboutActivity::class.java).apply {

            }
            startActivity(aboutIntent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this,R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
    }

    fun showLoading() {
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progress_bar.visibility=View.VISIBLE
    }

    fun hideLoading() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progress_bar.visibility=View.GONE
    }
}