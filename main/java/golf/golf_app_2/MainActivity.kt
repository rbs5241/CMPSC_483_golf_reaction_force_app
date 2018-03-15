package golf.golf_app_2

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PixelFormat
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import golf.golf_app_2.R.id.videoView1
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    public var corx = 0f;
    public var cory = 0f;

    private lateinit var background: Canvass;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        window.setFormat(PixelFormat.UNKNOWN)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        /*
        val layout1 = findViewById<View>(R.id.constraintLayout) as android.support.constraint.ConstraintLayout
        val background = Canvass(this)
        layout1.addView(background)
        background.setOnTouchListener { view, motionEvent ->
            corx = motionEvent.x
            cory = motionEvent.y
            background.invalidate()
            true
        }*/

        configureVideoView()

    }

    private fun configureVideoView() {
        val videoView1 = findViewById<VideoView>(R.id.videoView1) as VideoView
        val path = "android.resource://" + getPackageName() + "/" + R.raw.swing1
        videoView1.setVideoURI(Uri.parse(path))

        videoView1.start()
    }


    inner class Canvass(context: Context) : View(context) {

        override fun onDraw(canvas: Canvas) {
            canvas.drawRGB(255, 255, 0)
            val paint = Paint()
            paint.setARGB(255, 255, 0, 0)
            paint.setStrokeWidth(4f)
            paint.setStyle(Paint.Style.STROKE)
            canvas.drawLine(100f, 1500f, corx, cory, paint)
        }
    }


    override fun onBackPressed() {                           //When back is pressed close the drawer
        if (drawer_layout.isDrawerOpen(GravityCompat.START)){ //If layout drawer is open close it
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()                           //Else do nothing
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
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}