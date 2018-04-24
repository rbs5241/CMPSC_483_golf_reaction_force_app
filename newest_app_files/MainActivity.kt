package golf.golf_app_2

import android.annotation.TargetApi
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var imgView: ImageView
    private lateinit var forceView: ForceView
    private lateinit var spinnerView: ProgressBar
    private lateinit var swing: Swing

    @TargetApi(18)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        forceView = findViewById<View>(R.id.arrowCanvas) as ForceView
        imgView = findViewById(R.id.imgView)
        spinnerView = findViewById(R.id.progressBar)
        swing = Swing(forceView,imgView)

        setNextAndPrevButtons()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setNextAndPrevButtons(){
        val forwardButton = findViewById<Button>(R.id.forward) as Button
        val backwardButton = findViewById<Button>(R.id.backward) as Button
        val changeViewButton = findViewById<Button>(R.id.changeViewButton) as Button

        val swingName = findViewById<TextView>(R.id.swingString)
        val swingNames = arrayOf("TAKE AWAY", "EBS", "LBS", "TOP", "EDS", "LDS", "IMPACT")

        forwardButton.setText(R.string.next)
        forwardButton.setOnClickListener({
            swing.curFrame = (swing.curFrame + 1) % 7
            swing.switchFrame(swing.curFrame)
            swingName.text = swingNames[swing.curFrame]
        })
        backwardButton.setText(R.string.previous)
        backwardButton.setOnClickListener({
            swing.curFrame = (swing.curFrame - 1 + 7) % 7
            swing.switchFrame(swing.curFrame)
            swingName.text = swingNames[swing.curFrame]
        })

        changeViewButton.setOnClickListener({
            if (swing.curView == "front") {
                swing.curView = "top"
            } else {
                swing.curView = "front"
            }
            swing.switchView(swing.curView)
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val swingTitle = findViewById<TextView>(R.id.swingTitle)
        val swingTitles = arrayOf("SWING 1", "SWING 2", "SWING 3", "SWING 4", "SWING 5", "SWING 6", "SWING 7")
        when (item.itemId) {
            R.id.nav_swing_1 -> {
                swingTitle.text = swingTitles[0]
                swing.switchSwing(0)
            }
            R.id.nav_swing_2 -> {
                swingTitle.text = swingTitles[1]
                swing.switchSwing(1)
            }
            R.id.nav_swing_3 -> {
                swingTitle.text = swingTitles[2]
                swing.switchSwing(2)
            }
            R.id.nav_swing_4 -> {
                swingTitle.text = swingTitles[3]
                swing.switchSwing(3)
            }
            R.id.nav_swing_5 -> {
                swingTitle.text = swingTitles[4]
                swing.switchSwing(4)
            }
            R.id.nav_swing_6 -> {
                swingTitle.text = swingTitles[5]
                swing.switchSwing(5)
            }
        }
        val swingString = findViewById<TextView>(R.id.swingString)
        swingString.text = "TAKE AWAY"
        swing.switchView("front")
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
} // End MainActivity
