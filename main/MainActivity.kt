package golf.golf_app_2

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

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
            R.id.nav_swing1 -> {
                val videoView1 = findViewById<VideoView>(R.id.videoView1) as VideoView
                val sw1_path = "android.resource://" + getPackageName() + "/" + R.raw.swing1
                videoView1.setVideoURI(Uri.parse(sw1_path))
            }
            R.id.nav_swing2 -> {
                val videoView1 = findViewById<VideoView>(R.id.videoView1) as VideoView
                val sw2_path = "android.resource://" + getPackageName() + "/" + R.raw.swing2
                videoView1.setVideoURI(Uri.parse(sw2_path))
            }
            R.id.nav_slideshow -> {
            }
            R.id.nav_manage -> {
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    /* configurevideoview
     * sets the path of the raw video,
     * button1 - toggle play and pause
     */
    private fun configureVideoView() {
        val videoView1 = findViewById<VideoView>(R.id.videoView1) as VideoView
        val path = "android.resource://" + getPackageName() + "/" + R.raw.swing1       //the path for the video, in project tree under res directory create a raw directory and place video here
        videoView1.setVideoURI(Uri.parse(path))                                        //setting the video uri
        //videoView1.start()
       // mediaController = MediaController(this)
        //mediaController.setAnchorView(videoView1)
        //videoView1.setMediaController(mediaController
        //)
        videoView1.requestFocus()
        videoView1.setOnPreparedListener{ mp ->
            mp.isLooping = true
            //Log.i("Swing1", "Duration = " + videoView1.duration)

            videoView1.start()
        }

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
        //seekBar1.max = 300
        val videoView1 = findViewById<VideoView>(R.id.videoView1) as VideoView

        seekBar1.setOnSeekBarChangeListener (object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar1:SeekBar, progress:Int, fromUser:Boolean ){

            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // called when tracking the seekbar is started
                videoView1.pause()
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // called when tracking the seekbar is stopped
                Toast.makeText(this@MainActivity, "Progress is " + seekBar.progress + "%", Toast.LENGTH_SHORT).show()

            }
        })


    }
}
