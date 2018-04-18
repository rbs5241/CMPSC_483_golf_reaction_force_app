package golf.golf_app_2

import android.annotation.TargetApi
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
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
    private lateinit var forceView:ForceView
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
        nav_view.setNavigationItemSelectedListener(this) //Set the listener for navView

        forceView = findViewById<View>(R.id.arrowCanvas) as ForceView
        imgView = findViewById(R.id.imgView)
        spinnerView = findViewById(R.id.progressBar)
        swing = Swing(forceView,imgView)

        setNextAndPrevButtons()
    }

//    override fun onWindowFocusChanged(hasFocus:Boolean) {
//        val location = IntArray(2)
//        mCanvas.getLocationOnScreen(location)
//        bitmapX = location[0]
//        bitmapY = location[1]
//        focusChanged = 1
//    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setNextAndPrevButtons(){
        val forwardButton = findViewById<Button>(R.id.forward) as Button
        val backwardButton = findViewById<Button>(R.id.backward) as Button
        val changeViewButton = findViewById<Button>(R.id.changeViewButton) as Button

        forwardButton.setText(R.string.next)
        forwardButton.setOnClickListener({
            swing.curFrame = (swing.curFrame + 1) % 7
            swing.switchFrame(swing.curFrame)
        })
        backwardButton.setText(R.string.previous)
        backwardButton.setOnClickListener({
            swing.curFrame = (swing.curFrame - 1) % 7
            swing.switchFrame(swing.curFrame)
        })
        changeViewButton.setText(R.string.overhead)
        changeViewButton.setOnClickListener({
            if(swing.curView == "front") {
                swing.curView = "top"
            } else {
                swing.curView = "front"
            }
            swing.switchView(swing.curView)
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val swingTitle = findViewById<TextView>(R.id.swingTitle)
        val swingTitles = arrayOf(R.string.swing1, R.string.swing2, R.string.swing3, R.string.swing4, R.string.swing5, R.string.swing6)
        when (item.itemId) {
            R.id.nav_swing_1 -> {
                swingTitle.setText(swingTitles[0])
                swing.switchSwing(0)
            }
            R.id.nav_swing_2 -> {
                swingTitle.setText(swingTitles[1])
                swing.switchSwing(1)
            }
            R.id.nav_swing_3 -> {
                swingTitle.setText(swingTitles[2])
                swing.switchSwing(2)
            }
            R.id.nav_swing_4 -> {
                swingTitle.setText(swingTitles[3])
                swing.switchSwing(3)
            }
            R.id.nav_swing_5 -> {
                swingTitle.setText(swingTitles[4])
                swing.switchSwing(4)
            }
            R.id.nav_swing_6 -> {
                swingTitle.setText(swingTitles[5])
                swing.switchSwing(5)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
} // End MainActivity