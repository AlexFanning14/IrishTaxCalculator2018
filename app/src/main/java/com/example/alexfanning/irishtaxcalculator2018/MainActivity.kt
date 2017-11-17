package com.example.alexfanning.irishtaxcalculator2018

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.alexfanning.irishtaxcalculator2018.navdrawer.DrawerItemCustomAdapter
import com.example.alexfanning.irishtaxcalculator2018.navdrawer.NavDrawerItem

class MainActivity : AppCompatActivity() {

    private var mNavigationDrawerItemTitles:Array<String>? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var mDrawerList: ListView? = null
    internal var toolbar: Toolbar? = null
    private var mDrawerTitle:String = ""
    private var mTitle:String = ""
    internal var mDrawerToggle: ActionBarDrawerToggle? = null
    companion object {
        private var sNavPosition = 0
        private val NAV_POSITION_KEY = "navKey"
        private val TAG = MainActivity::class.java.getSimpleName()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDrawerTitle = getTitle().toString()
        mTitle = getTitle().toString()
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.drawer_fragment_items)

        mDrawerLayout = findViewById(R.id.drawer_layout)
        setUpToolbar()
        val navDrawerItems = arrayOfNulls<NavDrawerItem>(5)
        navDrawerItems[0] = NavDrawerItem(getString(R.string.nav_drawer_status))
        navDrawerItems[1] = NavDrawerItem(getString(R.string.nav_drawer_income))
        navDrawerItems[2] = NavDrawerItem(getString(R.string.nav_drawer_benefits))
        navDrawerItems[3] = NavDrawerItem(getString(R.string.nav_drawer_status))
        val adapter = DrawerItemCustomAdapter(this, R.layout.drawer_list_item, navDrawerItems)
        val header = getLayoutInflater().inflate(R.layout.drawer_header, null)
        mDrawerList = findViewById<ListView>(R.id.left_drawer)
        mDrawerList?.addHeaderView(header)
        mDrawerList?.setAdapter(adapter)
        mDrawerList?.setOnItemClickListener(DrawerItemClickListener())
        mDrawerLayout.addDrawerListener(mDrawerToggle)
        setUpDrawerToggle()
        if (savedInstanceState != null && savedInstanceState.containsKey(NAV_POSITION_KEY))
        {
            sNavPosition = savedInstanceState.getInt(NAV_POSITION_KEY)
        }
        DrawerItemClickListener().selectItem(sNavPosition)
    }
    private fun setUpDrawerToggle() {
        mDrawerToggle = object:ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            override fun onDrawerClosed(drawerView:View) {
                super.onDrawerClosed(drawerView)
                getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
            }
        }
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
        mDrawerToggle.setDrawerIndicatorEnabled(true)
        mDrawerToggle.syncState()
        mDrawerLayout.addDrawerListener(mDrawerToggle)
    }
    inner class DrawerItemClickListener:ListView.OnItemClickListener {
        fun onItemClick(parent: AdapterView<*>, view: View, position:Int, id:Long) {
            selectItem(position - 1)
        }
        private fun selectItem(position:Int) {
            val fragment:android.support.v4.app.Fragment = null
            when (position) {
                0 -> fragment = HomeFragment()
                1 -> fragment = AimsFragment()
                2 -> fragment = ArttrailFragment()
                3 -> fragment = SubmissionFragment()
                4 -> fragment = GalleryFragment()
                else -> {}
            }
            sNavPosition = position
            if (fragment != null)
            {
                val fragmentManager = getSupportFragmentManager()
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit()
                mDrawerList.setItemChecked(position, true)
                mDrawerList.setSelection(position)
                val title = mNavigationDrawerItemTitles[position]
                if (title == getString(R.string.nav_submission))
                {
                    setTitle(getString(R.string.submission_header))
                }
                else
                {
                    setTitle(title)
                }
                mDrawerLayout.closeDrawer(mDrawerList)
            }
        }
    }
    fun onOptionsItemSelected(item: MenuItem):Boolean {
        if (mDrawerToggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    fun setTitle(title:CharSequence) {
        mTitle = title.toString()
        getSupportActionBar().setTitle(mTitle)
    }
    protected fun onPostCreate(savedInstanceState:Bundle) {
        super.onPostCreate(savedInstanceState)
        mDrawerToggle.syncState()
    }
    private fun setUpToolbar() {
        //getSupportActionBar().hide();
        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }
    fun onSaveInstanceState(outState:Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt(NAV_POSITION_KEY, sNavPosition)
    }

}
