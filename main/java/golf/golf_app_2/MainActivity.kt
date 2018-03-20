package golf.golf_app_2

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mediaController:MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        configureVideoView() //RS - Configuring the video playback of animation
        configureSeekBar()   //RS - Configuring the seek bar, see method for Listener
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
            R.id.nav_swing_1 -> {
                setSwing1AsPath()
            }
            R.id.nav_swing_2 -> {
                setSwing2AsPath()
            }
            R.id.nav_swing_3 -> {
                // Handle swing 3 button action
            }
            R.id.nav_swing_4 -> {
                // Handle swing 4 button action
            }
            R.id.nav_swing_5 -> {
                // Handle swing 5 button action
            }
            R.id.nav_swing_6 -> {
                // Handle swing 6 button action
            }
            R.id.nav_swing_7 -> {
                // Handle swing 7 button action
            }
            R.id.nav_settings -> {
                // Handle settings button action
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    /* configureVideoView - called onCreate, sets swing1 as the path.
     * Sets the property isLooping to true for videoView1
     * button1 - toggle play and pause
     */
    private fun configureVideoView() {
        val videoView1 = findViewById<VideoView>(R.id.videoView1) as VideoView
        val path = "android.resource://" + getPackageName() + "/" + R.raw.swing1       //the path for the video, in project tree under res>raw directory
        val seekBar1 = findViewById<SeekBar>(R.id.seekBar1) as SeekBar
        videoView1.setVideoURI(Uri.parse(path))                                        //setting the video uri

        //Setting up a media controller to control the playback of the video
        mediaController = MediaController(this)
        mediaController?.setAnchorView(videoView1)
        videoView1.setMediaController(mediaController)

        //Setting up the OnPreparedListener to continually loop playback each time the video reaches the end
        videoView1.requestFocus()
        videoView1.setOnPreparedListener{ mp ->
            mp.isLooping = true
        }
        videoView1.start()

        //Setting up a click listener for button1, if isPlaying -> pause, else -> play
        val button = findViewById<Button>(R.id.button1)
        button.setText(R.string.pause)
        button.setOnClickListener({
            val isPlaying = videoView1.isPlaying
            button.setText(if (isPlaying) R.string.play else R.string.pause)
            val msg = getString(if (isPlaying) R.string.paused else R.string.playing)
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()

            if(isPlaying){
                videoView1.pause()
            } else {
                videoView1.start()
            }
        })
    }
    private fun configureSeekBar() {
        val seekBar1 = findViewById<SeekBar>(R.id.seekBar1) as SeekBar
        val vv = findViewById<VideoView>(R.id.videoView1) as VideoView
        seekBar1.max = 300

        seekBar1.setOnSeekBarChangeListener (object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar1:SeekBar, progress:Int, fromUser:Boolean ){
            }
            override fun onStartTrackingTouch(seekBar1: SeekBar) {
                // called when tracking the seekbar is started
                vv.pause()
            }
            override fun onStopTrackingTouch(seekBar1: SeekBar) {
                // called when tracking the seekbar is stopped
                Toast.makeText(this@MainActivity, "Progress is " + seekBar1.progress, Toast.LENGTH_SHORT).show()

            }
        })
    }

    private fun setSwing1AsPath(){
        val videoView1 = findViewById<VideoView>(R.id.videoView1) as VideoView
        val path = "android.resource://" + getPackageName() + "/" + R.raw.swing1       //the path for the video, in project tree under res directory create a raw directory and place video here
        videoView1.setVideoURI(Uri.parse(path))
    }
    private fun setSwing2AsPath(){
        val videoView1 = findViewById<VideoView>(R.id.videoView1) as VideoView
        val path = "android.resource://" + getPackageName() + "/" + R.raw.swing2       //the path for the video, in project tree under res directory create a raw directory and place video here
        videoView1.setVideoURI(Uri.parse(path))
    }
}
