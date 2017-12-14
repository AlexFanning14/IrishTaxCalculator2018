package com.example.alexfanning.irishtaxcalculator2018.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.alexfanning.irishtaxcalculator2018.R
import com.example.alexfanning.irishtaxcalculator2018.fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState?.get("navPos")== null){
            val menuItem = navigationView.menu.findItem(R.id.nav_basic_calculater) as MenuItem

            onNavigationItemSelected(menuItem)
        }else{
            val menuId : Int = savedInstanceState.getInt("navPos")
            val menuItem = navigationView.menu.findItem(menuId) as MenuItem

            onNavigationItemSelected(menuItem)
        }
    }




    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings ->{

                startActivity(Intent(this,SettingsActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        sNavId = item.itemId
        return navSelect()

    }

    private fun navSelect(): Boolean{
        var fragment : android.support.v4.app.Fragment? = null
        var title : String = ""
        when (sNavId) {
            0, R.id.nav_basic_calculater -> {
                nav_view.setCheckedItem(R.id.nav_basic_calculater)
                fragment = BasicFragment()
                title = "Basic Calculator"
            }
            R.id.nav_status -> {
                nav_view.setCheckedItem(R.id.nav_status)
                fragment = StatusFragment()
                title = getString(R.string.nav_drawer_status)
            }
            R.id.nav_income -> {
                nav_view.setCheckedItem(R.id.nav_income)
                fragment = IncomeFragment()
                title = getString(R.string.nav_drawer_income)
            }
            R.id.nav_benefit -> {
                nav_view.setCheckedItem(R.id.nav_benefit)
                fragment = BenefitFragment()

                title = getString(R.string.nav_drawer_benefits)
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit()
        setTitle(title)

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt("navId", sNavId)
    }

    override fun onStop() {
        super.onStop()
        val b = Bundle()
        b.putInt("navId", sNavId)
        intent.putExtras(b)


    }

    override fun onResume() {
        super.onResume()
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        val b = intent.extras


        if (b != null){
            val navId = b.getInt("navId")
            val menuItem = navigationView.menu.findItem(navId) as MenuItem
            menuItem.isChecked = true
            onNavigationItemSelected(menuItem)
        }
    }


    companion object {
        var sNavId : Int = 0
    }

}



