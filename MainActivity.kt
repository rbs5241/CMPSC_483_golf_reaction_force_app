package golf.golf_app_2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color.BLUE
import android.graphics.Color.RED
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mediaController:MediaController? = null
    private val sw1lfx = arrayOf(271.829069,271.6631145,271.568115,271.567608,271.4260045,271.3314895,
        271.331297,271.331661,271.2378235,271.16687,271.215069,271.215555,271.240347,271.2880345,271.3114985,
        271.2884905,271.288584,271.336384,271.170557,271.099476,271.0287535,270.9818345,271.00461,270.839773,
        270.839073,270.6736125,270.554842,270.4598695,270.5540065,270.4363115,270.5539645,270.6951655,270.6488935,
        270.5550005,270.7679995,270.627386,270.768835,270.6507645,270.862156,270.9088155,270.8854905,270.861721,
        271.0259745,271.1193125,271.261385,271.3554575,271.425947,271.520397,271.544317,271.4494955,271.637804,
        271.6144065,271.685852,271.6149425,271.520605,271.4256975,271.2372015,271.2616425,271.2137125,271.3089065,
        271.239231,271.168806,271.0754985,271.0757275,270.9350185,270.8886185,270.770521,270.7940905,270.440941,
        270.4654275,270.560272,270.5133875,270.420309,270.492069,270.469021,270.515852,270.634487,270.7059535,
        270.752924,270.753168,270.730095,270.776943,270.964838,270.91811,271.084015,271.131243,271.060547,271.2005195,
        271.2241705,271.1290705,271.1049825,270.986784,271.105812,270.9636515, 270.9627955, 270.940132, 270.822624,
        270.917389, 270.9408035, 270.9645385, 271.17778, 270.8473605, 271.0357475, 271.060045, 271.2020625, 271.295578,
        271.272888, 271.225336, 271.437828, 271.3437555, 271.2960645, 271.4849875, 271.3435705, 271.3437635, 271.4613035,
        271.5317135, 271.436941, 271.5547905, 271.5543615, 271.671818, 271.625036, 271.7193165, 271.931507, 271.978792,
        272.145092, 272.1925695, 272.286207, 272.3569525, 272.4515455, 272.4520475, 272.451847, 272.451868, 272.4770375,
        272.54809, 272.4771595, 272.5722885, 272.5023005, 272.549263, 272.596901, 272.7381495, 272.7145195, 272.714712,
        272.714798, 272.572445, 272.7372495, 272.737715, 272.831064, 272.712576, 272.8307725, 272.9492205, 272.9251155,
        272.9488775, 272.9961145, 273.1858595, 273.16337, 273.399555, 273.6838305, 273.448658, 273.6604135, 273.9919585,
        273.945488, 274.087681, 274.1590425, 274.1595, 274.0894815, 274.230877, 274.3251135, 274.441944, 274.701231,
        274.8191415, 274.937254, 275.102808, 275.268837, 275.3634165, 275.33889, 275.338648, 275.2908725, 275.243332,
        275.266651, 275.2665865, 275.3382035, 275.2435665, 275.361679, 275.3379535, 275.456209, 275.5039655, 275.5523395,
        275.788004, 275.882841, 275.9308145, 276.0962965, 276.120552, 276.31024, 276.4047755, 276.452524, 276.4528025,
        276.736534, 276.666689, 276.8561685, 277.021574, 277.187784, 277.236866, 277.37895, 277.734411, 277.9247835,
        278.044651, 278.210329, 278.5178335, 278.849245, 278.922085, 279.370678, 279.5839845, 279.8688715, 280.129614,
        280.6731035, 280.72138, 280.95722, 281.0764295, 280.9354725, 280.913397, 280.891449, 280.727974, 280.682291,
        280.6359595, 280.44783, 280.3073655, 280.260599, 280.096388, 280.0738565, 279.979912, 279.862234, 279.863142,
        279.9345205, 279.8888185, 279.984398, 279.961237, 280.033701, 280.0820505, 280.177244, 280.178509, 280.322012,
        280.3931655, 280.5828955, 280.724617, 280.890524, 280.867283, 281.1030905, 281.2452795, 281.4099275, 281.5050925,
        281.6462515, 281.8348465, 281.952978, 281.975996, 281.929062, 281.716776, 281.6701185, 281.268749, 281.1025545,
        280.9841825, 280.559904, 280.3945215, 280.1334305, 279.8256015, 279.494354, 279.2580395, 279.021879, 278.7373045,
        278.382248, 278.1694985, 277.955017, 277.669935, 277.385805, 277.170269, 276.9087565, 276.599163, 276.24242,
        275.8617745, 275.5990145, 275.0055865, 274.6248035, 274.220867, 273.8169575, 273.6966455, 273.363529, 273.1253035,
        272.862234, 272.457546, 271.863518, 271.270729, 270.6298695, 269.800556, 269.18433, 268.5435485, 268.090624,
        267.5203855, 266.997589, 266.215748, 265.787735, 265.029793, 264.129635, 263.606724, 262.9663125, 262.396763,
        261.6389925, 261.141407, 260.524235, 259.8851585, 259.340595, 258.655617, 257.993614, 257.4490775, 256.126148,
        255.0870285, 254.354019, 253.0304525, 252.3920095, 251.7531205, 251.1836015, 250.709984, 250.0936125, 249.241802,
        248.8152885, 248.27026, 247.983238, 247.557926, 247.484333, 246.984783, 246.8157655, 246.339897, 245.935261, 245.530731,
        245.2443735, 244.8633425, 244.529503, 244.0782015, 243.8850935, 243.7636605, 243.8305815, 243.777115, 243.7021715,
        243.885391, 244.0678215, 244.4179075, 244.721245, 244.9769325, 244.87764, 244.566601, 244.2042425, 243.747196,
        243.7635575, 243.13752, 242.727253, 242.272175, 241.1097945, 239.6886785, 238.8579445, 238.7106935, 238.330288,
        238.1348075, 239.144123, 240.392128, 242.9621355, 246.0756035, 248.9100645, 250.961235, 254.4682655, 255.1974295,
        256.154133, 258.3729895, 258.840023, 258.515667, 256.738308, 256.7496185, 253.56406, 251.7067415, 249.497959,
        245.696514, 240.347931, 234.297058, 229.040489, 224.482544, 219.7507475, 216.4348525, 213.715126, 211.6439285, 211.9155275,
        212.9951555, 216.440178, 220.494652, 225.2334215, 231.5346605, 237.0354005, 242.963703, 248.7058485, 253.6155435,
        257.7667275, 261.538418, 264.0072785, 266.4965475, 268.275505, 269.788391, 271.0572775, 270.714432, 272.6893405, 273.7389565,
        276.206045, 277.516863, 280.2246665, 280.951151, 283.929018, 285.1115475, 288.165146, 290.3956995, 291.9911965, 296.8314875,
        300.419476, 303.34245, 305.837495, 308.0246765, 309.931525, 310.8976165, 311.8415155, 312.4294605, 312.1222295,311.8810655, 311.637807,
        311.2719125, 310.3906395, 309.895095, 310.046294, 310.849741, 311.177433, 312.0237905, 312.514612, 313.3121365,
        314.8176345, 316.3240545, 318.0217705, 320.051615, 322.3440265,324.2610835,328.2341425,330.534683,332.620331,
        334.778473,336.296875,337.2457465,338.17276,338.1541825,337.992996,337.400997,337.07119,336.195675,335.124668,
        334.103218,333.0096205,332.12537,331.669121,330.8352775,330.1687925,329.5256195,328.8576395,327.2460955 )

    private val sw1lfy = arrayOf(147.6013795,147.5085295,147.0437165,147.229767,147.136795,147.0438385,146.4859925,146.7648775,146.2069395,
        146.578903,146.1138305,146.4856875,146.5785065,146.2994535,146.1135255,146.485367,146.3923645,146.113327,145.4625855,144.9977265,
        145.4626465,145.1836395,144.718872,144.904785,144.532974,144.1611025,143.975296,143.6964265,143.417511,143.9753875,144.5332945,
        144.812256,144.533203,144.998062,144.8119505,144.9048155,145.3697355,145.55571,145.369873,145.3699495,145.7418365,146.020813,
        146.206894,146.299988,146.671829,146.6718595,146.7648775,147.136795,147.136734,147.1368105,147.508789,147.4157715,148.1595155,
        147.415695,147.787628,147.229843,147.0438385,146.95076,147.0438385,146.671768,146.299698,145.927719,146.020569,146.1135255,
        145.5555115,145.183487,145.1835175,144.997574,144.8115235,144.99736,144.439377,143.6954955,143.8812865,143.881134,143.97406,
        143.6951295,143.6950225,143.88089,143.7879335,143.9738615,144.6246645,144.4387205,144.9037475,145.182617,145.6474,145.833374,
        145.089508,145.7405855,145.3686525,145.647705,145.2758635,144.625046,145.0897825,144.1600645,144.160187,143.974106,144.345978,
        143.973984,144.159958,144.3459165,144.345749,144.066849,144.2528535,144.6246645,144.624588,145.1825715,144.8105165,145.2754975,
        145.2754515,145.275421,145.554428,145.7403565,145.461426,146.019272,145.1824645,145.461487,145.08963,145.5545195,145.5545805,
        145.5546415,145.461624,146.112488,145.5545805,146.1124725,146.39122,146.1122285,146.763199,147.1351165,147.0420835,147.5069275,
        147.599945,147.042038,147.506714,147.785614,147.5996855,147.5065765,147.8784025,147.04158,147.134506,147.6924285,147.5064695,
        148.0643465,147.2274935,147.1346285,147.4136045,146.948639,147.134735,147.4137575,147.320755,147.5066225,147.6926725,148.064575,
        148.3434905,148.9941865,149.9238585,149.8308105,150.66745,150.388397,150.4814455,150.3882295,150.946045,150.666992,150.852844,
        151.0387575,151.2246095,150.666733,151.038666,151.13179,151.3177645,152.154602,152.247528,152.526413,152.4332735,152.2472535,
        151.7825165,151.596573,151.3177185,150.480942,150.759918,149.64415,150.1089325,148.52829,148.6212465,149.1791685,148.80719,149.6439055,
        149.6437225,149.643738,149.7366485,149.9225005,149.7364655,149.8293455,150.108124,149.643173,149.736084,149.4571075,149.5499725,
        149.456848,149.084793,149.084732,149.456497,149.1772765,148.8982085,148.8049775,147.96788,147.5956575,147.316635,147.130493,146.2005005,
        145.549301,144.8983305,143.224518,142.480377,141.643326,140.8062895,140.1552735,139.225418,138.7603455,138.0164185,137.4582975,
        136.342285,136.3420565,135.0401155,134.389099,134.482025,133.459091,132.064316,132.1571655,131.413162,130.85524,130.1113585,129.7393035,
        129.367279,128.6232145,127.972168,127.321213,127.0420225,126.855896,126.483841,125.646805,125.553528,125.088547,124.2515715,123.693619,
        123.507538,123.0425875,122.577652,122.2986145,121.5548095,120.9967805,120.996826,120.4389495,120.6248625,120.3460085,119.974045,
        119.2302095,119.230133,118.672348,118.3935545,118.0217135,117.7427675,117.1849365,116.441345,115.8836365,115.7908325,115.047058,
        114.582245,113.187744,112.444092,112.1652525,110.398941,109.1904905,107.8889465,106.2157595,104.8213655,103.2411345,101.288971,
        99.42984,97.5707245,95.2467955,94.5964355,92.6443025,90.8780975,89.018814,87.2526245,85.2074125,83.441284,81.489273,79.444351,
        79.165909,77.3999175,74.9829865,73.6817475,71.7297515,69.963852,68.4767915,66.431793,64.4797365,61.5048675,59.3668215,56.67099,
        53.882187,51.5582125,48.9552765,46.538269,43.8421325,40.5884095,38.543213,36.5909425,33.8946535,32.7792055,29.618164,27.3869935,
        25.7135925,23.20343,22.367096,21.7164915,20.8800355,21.252472,20.9738465,21.5322265,22.090637,23.299713,25.996521,28.2286985,
        31.669159,34.3660585,38.4579165,41.433899,45.339752,49.338516,53.988159,59.102646,65.890854,71.2843325,76.584778,82.629242,88.8595125,
        95.740677,103.738022,110.898224,118.6164705,127.450592,134.889633,142.5145875,150.604492,157.113922,163.530319,169.6683805,175.992447,
        181.2004625,185.7584765,190.037018,193.1064455,195.525017,198.594391,200.1758955,201.0135805,202.1298295,203.897476,204.9209135,
        206.780754,209.756096,211.708458,214.3111265,216.6356355,218.02771,219.236717,221.283745,222.3991015,221.655899,221.6585085,220.2685775,
        217.0160215,212.091881,207.7244645,199.4515,189.9682695,177.5083465,162.165283,150.1706085,140.3157655,132.600754,127.6750335,122.748276,
        117.819763,113.7267915,110.376709,109.628952,110.090454,111.5746615,113.9877165,114.4480285,115.0008545,115.181366,114.2472075,113.034683,
        111.171646,109.4024965,106.425049,104.0989075,102.7965545,102.33226,104.1937715,105.124344,107.2645415,109.869217,113.496887,117.5885315,
        124.3768005,129.0247805,138.3223725,145.1082,151.2428285,155.7036135,158.209549,158.763321,160.8984835,164.800827,168.610367,170.7459105,
        174.7407305,177.433609,179.104042,181.8901595,184.5839845,189.69561,191.0886995,193.4111025,192.8532865,198.895668,200.4760665,205.3114395,
        208.2871855,210.891289,213.6813125,214.518631,213.682129,215.9136275,212.101036,209.589691,206.892128,200.288597,193.498932,187.825424,
        178.5253985,175.4556045,172.0144195,165.9694975,161.691635,158.436737,155.6476595, 153.0441435,152.9516295,153.88295,154.0700225,158.0695955,
        161.046753,161.4199065,158.817703,159.190567,157.703827,158.820755,160.9605715)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        configureVideoView(R.raw.swing1, R.string.front) //RS - Configuring the video playback of animation
        configureSeekBar()   //RS - Configuring the seek bar, see method for Listener
        configureDrawing(0)
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
                configureVideoView(R.raw.swing1, R.string.front)
            }
            R.id.nav_swing_2 -> {
                configureVideoView(R.raw.swing2, R.string.front)
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
    private fun configureVideoView(vidname : Int, view : Int) {
        val videoView1 = findViewById<VideoView>(R.id.videoView1) as VideoView
        val isPlaying = videoView1.isPlaying

        val path = "android.resource://" + getPackageName() + "/" + vidname      //the path for the video, in project tree under res>raw directory
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

            button.setText(if (isPlaying) R.string.play else R.string.pause)
            val msg = getString(if (isPlaying) R.string.paused else R.string.playing)
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()

            if(isPlaying){
                videoView1.pause()
            } else {
                videoView1.start()
            }
        })




        //Setting up a click listener for changeviewbutton, call configureVideoView based on what view is set to
        val viewbutton = findViewById<Button>(R.id.changeviewbutton)
        viewbutton.setText(if (view == R.string.front) R.string.top else R.string.front)
        viewbutton.setOnClickListener({
            if (view == R.string.front) configureVideoView(vidname, R.string.top) else configureVideoView(vidname, R.string.front)
        })
    } // End configureVideoVIew
    /* configureSeekBar - PROBLEM - VIDEO ENCODEC NOT SUPPORTING ACCURATE SCRUBBINB
     * Sets the property isLooping to true for videoView1
     * button1 - toggle play and pause
     */
    private fun configureSeekBar() {
        val seekBar1 = findViewById<SeekBar>(R.id.seekBar1) as SeekBar
        val vv = findViewById<VideoView>(R.id.videoView1) as VideoView
        seekBar1.max = 5000

        vv.setOnPreparedListener { mp ->
            mp.setOnSeekCompleteListener {
                val isPlaying = vv.isPlaying
                if(!isPlaying) {
                    it.seekTo(seekBar1.progress)
                }
            }
        }

        seekBar1.setOnSeekBarChangeListener (object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar1:SeekBar, progress:Int, fromUser:Boolean ){
                vv.seekTo(seekBar1.progress)
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
    } // End configureSeekBar

    private fun configureDrawing(count:Int){
        val videoView1 = findViewById<VideoView>(R.id.videoView1) as VideoView
        val isPlaying = videoView1.isPlaying

        val mImageView = findViewById<ImageView>(R.id.imageView2) as ImageView
        val bitmap = Bitmap.createBitmap(
            500,
            300,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.color = RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 8.toFloat()
        paint.isAntiAlias = true

        canvas.drawLine(
                310.toFloat(),  //start X
                300.toFloat(),  //start Y
                255.5.toFloat(), //stop  X
                91.toFloat(), //stop  Y
                paint
        )
        paint.color = BLUE

        canvas.drawLine(
                240.toFloat(),
                300.toFloat(),
                sw1lfx[0].toFloat(),
                sw1lfy[0].toFloat(),
                paint
        )
        mImageView.setImageBitmap(bitmap)
        mImageView.bringToFront()

    }
} // End MainActivity
