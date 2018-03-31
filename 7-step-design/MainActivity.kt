package golf.golf_app_2

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color.BLUE
import android.graphics.Color.RED
import android.graphics.Paint
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


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mediaController:MediaController? = null
    private var frame = 0
    private var isSw1Front = false
    private var isSw2Front = false
    private var isSw3Front = false
    private var isSw4Front = false
    private var isSw5Front = false
    private var isSw6Front = true

    private var isSw1Top = false
    private var isSw2Top = false
    private var isSw3Top = false
    private var isSw4Top = false
    private var isSw5Top = false
    private var isSw6Top = false



    //array pointing to the images for front swing
    private val sw2Front = arrayOf(R.raw.sw2_front_1, R.raw.sw2_front_2, R.raw.sw2_front_3,R.raw.sw2_front_4,
            R.raw.sw2_front_5, R.raw.sw2_front_6,R.raw.sw2_front_7)
    //array pointing the images for top swing
    private val sw2Top = arrayOf(R.raw.sw2_top_1, R.raw.sw2_top_2, R.raw.sw2_top_3,R.raw.sw2_top_4,
            R.raw.sw2_top_5, R.raw.sw2_top_6,R.raw.sw2_top_7)

    //array pointing to the images for front swing
    private val sw6Front = arrayOf(R.raw.sw6_front_1, R.raw.sw6_front_2, R.raw.sw6_front_3,R.raw.sw6_front_4,
            R.raw.sw6_front_5, R.raw.sw6_front_6,R.raw.sw6_front_7)
    //array pointing the images for top swing
    private val sw6Top = arrayOf(R.raw.sw6_top_1, R.raw.sw6_top_2, R.raw.sw6_top_3,R.raw.sw6_top_4,
            R.raw.sw6_top_5, R.raw.sw6_top_6,R.raw.sw6_top_7)

    private val swingStrings = arrayOf (R.string.takeAway, R.string.ebs, R.string.lbs,
            R.string.top, R.string.eds, R.string.lds, R.string.impact)

    private val sw6LeftX = arrayOf(280.205616, 262.431496,217.527115,247.680046,228.995918,245.533455,291.902302)
    private val sw6LeftY = arrayOf(135.052826, 141.040527, 192.400925, 273.311798,228.903,56.069366,78.753601)
    private val sw6RightX = arrayOf(240.765366,240.730515,235.659164,234.3778,233.759819,230.079994,229.079224)
    private val sw6RightY = arrayOf(130.360718,121.531616,84.934265,91.81366,96.833923,114.87207,113.382813)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        //configureVideoView(R.raw.swing1, R.string.front) //RS - Configuring the video playback of animation
        //configureSeekBar()   //RS - Configuring the seek bar, see method for Listener
        configureDrawing()
        configureImageView(R.raw.sw6_front_1)
        setNextAndPrevButtons(0)
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

    /* configureSeekBar (RS) - PROBLEM - VIDEO ENCODEC NOT SUPPORTING ACCURATE SCRUBBINB
     * Sets the property isLooping to true for videoView1
     * button1 - toggle play and pause
     */
    private fun configureImageView(image : Int){
        val imageView1 = findViewById<ImageView>(R.id.imView1)
        //val path = "android.resource://" + getPackageName() + "/" + image
        //imageView1.setImageURI(Uri.parse(path))
        imageView1.setImageResource(image)


    }

    /* configureDrawing (RS) -
     *
     *
     */
    private fun setNextAndPrevButtons(count:Int){
        frame = count
        val forwardButton = findViewById<Button>(R.id.forward) as Button
        val backwardButton = findViewById<Button>(R.id.backward) as Button
        val changeViewButton = findViewById<Button>(R.id.changeViewButton) as Button

        val swingString = findViewById<TextView>(R.id.swingString)

        forwardButton.setText(R.string.next)
        forwardButton.setOnClickListener({
            if (frame == 6){ frame = 0 } else { frame += 1 }
            configureDrawing()
            if (isSw2Front){configureImageView(sw2Front[frame])}
            if (isSw6Front){configureImageView(sw6Front[frame])}
            if (isSw2Top){configureImageView(sw2Top[frame])}
            if (isSw6Top){configureImageView(sw6Top[frame])}
            swingString.setText(swingStrings[frame])
        })
        backwardButton.setText(R.string.previous)
        backwardButton.setOnClickListener({
            if (frame == 0){ frame = 6 } else { frame -= 1 }
            configureDrawing()
            if (isSw2Front){configureImageView(sw2Front[frame])}
            if (isSw6Front){configureImageView(sw6Front[frame])}

            if (isSw2Top){configureImageView(sw2Top[frame])}
            if (isSw6Top){configureImageView(sw6Top[frame])}
            swingString.setText(swingStrings[frame])
        })
        changeViewButton.setText(R.string.overhead)
        changeViewButton.setOnClickListener({
            if (isSw2Front){/*top*/
                changeViewButton.setText(R.string.front)
                isSw2Front = false
                isSw2Top = true
                configureImageView(sw2Top[frame])
            } else if (isSw2Top) {/*front*/
                changeViewButton.setText(R.string.overhead)
                isSw2Front = true
                isSw2Top = false
                configureImageView(sw2Front[frame])
            }
            if (isSw6Front){/*top*/
                changeViewButton.setText(R.string.front)
                isSw6Front = false
                isSw6Top = true
                configureImageView(sw6Top[frame])
            } else if (isSw6Top){/*front*/
                changeViewButton.setText(R.string.overhead)
                isSw6Front = true
                isSw6Top = false
                configureImageView(sw6Front[frame])
            }
        })
    }
    private fun configureDrawing(){


        val mImageView = findViewById<ImageView>(R.id.imageView2) as ImageView
        val bitmap = Bitmap.createBitmap(
                500,
                500,
                Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.color = RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 8.toFloat()
        paint.isAntiAlias = true

        canvas.drawLine(
                280.toFloat(),  //start X
                430.toFloat(),  //start Y
                sw6LeftX[frame].toFloat(), //stop  X
                sw6LeftY[frame].toFloat(), //stop  Y
                paint
        )
        paint.color = BLUE

        canvas.drawLine(
                180.toFloat(), //start x
                430.toFloat(), //start y
                sw6RightX[frame].toFloat(), //stop x
                sw6RightY[frame].toFloat(), //stop y
                paint
        )
        mImageView.setImageBitmap(bitmap)
        mImageView.bringToFront()

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_swing_1 -> {
                //configureImageView(sw2Top[frame])
                isSw1Front = false
                isSw2Front = false
                isSw3Front = false
                isSw4Front = false
                isSw5Front = false
                isSw6Front = false

                isSw1Top = false
                isSw2Top = false
                isSw3Top = false
                isSw4Top = false
                isSw5Top = false
                isSw6Top = false
            }
            R.id.nav_swing_2 -> {
                configureImageView(sw2Front[0])
                isSw1Front = false
                isSw2Front = true
                isSw3Front = false
                isSw4Front = false
                isSw5Front = false
                isSw6Front = false

                isSw1Top = false
                isSw2Top = false
                isSw3Top = false
                isSw4Top = false
                isSw5Top = false
                isSw6Top = false
            }
            R.id.nav_swing_3 -> {
                // Handle swing 3 button action
                isSw1Front = false
                isSw2Front = false
                isSw3Front = false
                isSw4Front = false
                isSw5Front = false
                isSw6Front = false

                isSw1Top = false
                isSw2Top = false
                isSw3Top = false
                isSw4Top = false
                isSw5Top = false
                isSw6Top = false
            }
            R.id.nav_swing_4 -> {
                // Handle swing 4 button action
                isSw1Front = false
                isSw2Front = false
                isSw3Front = false
                isSw4Front = false
                isSw5Front = false
                isSw6Front = false

                isSw1Top = false
                isSw2Top = false
                isSw3Top = false
                isSw4Top = false
                isSw5Top = false
                isSw6Top = false
            }
            R.id.nav_swing_5 -> {
                // Handle swing 5 button action
                isSw1Front = false
                isSw2Front = false
                isSw3Front = false
                isSw4Front = false
                isSw5Front = false
                isSw6Front = false

                isSw1Top = false
                isSw2Top = false
                isSw3Top = false
                isSw4Top = false
                isSw5Top = false
                isSw6Top = false
            }
            R.id.nav_swing_6 -> {
                // Handle swing 6 button action
                configureImageView(sw6Front[0])
                isSw1Front = false
                isSw2Front = false
                isSw3Front = false
                isSw4Front = false
                isSw5Front = false
                isSw6Front = true

                isSw1Top = false
                isSw2Top = false
                isSw3Top = false
                isSw4Top = false
                isSw5Top = false
                isSw6Top = false
            }
            R.id.nav_swing_7 -> {
                // Handle swing 7 button action
                isSw1Front = false
                isSw2Front = false
                isSw3Front = false
                isSw4Front = false
                isSw5Front = false
                isSw6Front = false

                isSw1Top = false
                isSw2Top = false
                isSw3Top = false
                isSw4Top = false
                isSw5Top = false
                isSw6Top = false
            }
            R.id.nav_settings -> {
                // Handle settings button action
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
} // End MainActivity