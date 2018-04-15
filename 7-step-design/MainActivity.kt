package golf.golf_app_2

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.annotation.TargetApi
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.Color.*
import android.util.AttributeSet
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //Boolean values to keep track of which swing a view needs to be drawn
    private var frame = 0
    private var isSw1Front = true
    private var isSw2Front = false
    private var isSw3Front = false
    private var isSw4Front = false
    private var isSw5Front = false
    private var isSw6Front = false
    //Boolean values to keep track of which swing a view needs to be drawn
    private var isSw1Top = false
    private var isSw2Top = false
    private var isSw3Top = false
    private var isSw4Top = false
    private var isSw5Top = false
    private var isSw6Top = false

    // get dimensions of bitmap
    private val bitmapWidth = minOf(Resources.getSystem().getDisplayMetrics().heightPixels, Resources.getSystem().getDisplayMetrics().widthPixels)

    // keep track of endpoints of left and right force arrows
    lateinit var swingLeftXY:IntArray
    lateinit var swingRightXY:IntArray

    // keep track of position of arrowCanvas
    var bitmapX = -1
    var bitmapY = -1

    // set to 0 until focus has been changed
    var focusChanged = 0

    lateinit var mCanvas:arrowCanvas

    // used to keep track of drag and drop operations
    // 0: left, 1: right, -1: no force
    private var selectedForce:Int = -1

    //SWING 1 ******************************
    private val sw1Front = arrayOf(R.raw.sw1_front_1, R.raw.sw1_front_2, R.raw.sw1_front_3,R.raw.sw1_front_4,
            R.raw.sw1_front_5, R.raw.sw1_front_6,R.raw.sw1_front_7)
    //array pointing the images for top swing
    private val sw1Top = arrayOf(R.raw.sw1_top_1, R.raw.sw1_top_2, R.raw.sw1_top_3,R.raw.sw1_top_4,
            R.raw.sw1_top_5, R.raw.sw1_top_6,R.raw.sw1_top_7)
    //SWING 2 ********************************
    private val sw2Front = arrayOf(R.raw.sw2_front_1, R.raw.sw2_front_2, R.raw.sw2_front_3,R.raw.sw2_front_4,
            R.raw.sw2_front_5, R.raw.sw2_front_6,R.raw.sw2_front_7)
    //array pointing the images for top swing
    private val sw2Top = arrayOf(R.raw.sw2_top_1, R.raw.sw2_top_2, R.raw.sw2_top_3,R.raw.sw2_top_4,
            R.raw.sw2_top_5, R.raw.sw2_top_6,R.raw.sw2_top_7)
    //SWING 3********************************
    private val sw3Front = arrayOf(R.raw.sw3_front_1, R.raw.sw3_front_2, R.raw.sw3_front_3,R.raw.sw3_front_4,
            R.raw.sw3_front_5, R.raw.sw3_front_6,R.raw.sw3_front_7)
    //array pointing the images for top swing
    private val sw3Top = arrayOf(R.raw.sw3_top_1, R.raw.sw3_top_2, R.raw.sw3_top_3,R.raw.sw3_top_4,
            R.raw.sw3_top_5, R.raw.sw3_top_6,R.raw.sw3_top_7)
    //SWING 4 *******************************
    private val sw4Front = arrayOf(R.raw.sw4_front_1, R.raw.sw4_front_2, R.raw.sw4_front_3,R.raw.sw4_front_4,
            R.raw.sw4_front_5, R.raw.sw4_front_6,R.raw.sw4_front_7)
    //array pointing the images for top swing
    private val sw4Top = arrayOf(R.raw.sw4_top_1, R.raw.sw4_top_2, R.raw.sw4_top_3,R.raw.sw4_top_4,
            R.raw.sw4_top_5, R.raw.sw4_top_6,R.raw.sw4_top_7)
    //SING 5 ********************************
    private val sw5Front = arrayOf(R.raw.sw5_front_1, R.raw.sw5_front_2, R.raw.sw5_front_3,R.raw.sw5_front_4,
            R.raw.sw5_front_5, R.raw.sw5_front_6,R.raw.sw5_front_7)
    //array pointing the images for top swing
    private val sw5Top = arrayOf(R.raw.sw5_top_1, R.raw.sw5_top_2, R.raw.sw5_top_3,R.raw.sw5_top_4,
            R.raw.sw5_top_5, R.raw.sw5_top_6,R.raw.sw5_top_7)
    //SWING 6 *******************************
    private val sw6Front = arrayOf(R.raw.sw6_front_1, R.raw.sw6_front_2, R.raw.sw6_front_3,R.raw.sw6_front_4,
            R.raw.sw6_front_5, R.raw.sw6_front_6,R.raw.sw6_front_7)
    //array pointing the images for top swing
    private val sw6Top = arrayOf(R.raw.sw6_top_1, R.raw.sw6_top_2, R.raw.sw6_top_3,R.raw.sw6_top_4,
            R.raw.sw6_top_5, R.raw.sw6_top_6,R.raw.sw6_top_7)

    private val swingStrings = arrayOf (R.string.takeAway, R.string.ebs, R.string.lbs,
            R.string.top, R.string.eds, R.string.lds, R.string.impact)
    private val swingTitles = arrayOf (R.string.swing1, R.string.swing2, R.string.swing3, R.string.swing4,
            R.string.swing5, R.string.swing6)

    private val sw1FrontBaseX = arrayOf(350,550,450)
    private val sw1FrontBaseY = arrayOf(1000,1000,1000)

    private val sw1FrontLeftX = arrayOf(403.60938,460.3741527,459.9356082,483.1427973,495.9087236,401.3407655,453.60938)
    private val sw1FrontLeftY = arrayOf(666.9884973,545.2661964,554.4063773,624.4238282,585.4331555,813.6020873,895.0579073)

    private val sw1FrontRightX = arrayOf(489.69558,556.30849,578.3204082,618.2107318,510.5346818,433.3239745,529.1250055)
    private val sw1FrontRightY = arrayOf(663.9133245,772.0259927,821.6139773,823.7485436,245.0886118,178.4925982,560.5403555)

    private val sw1FrontResX = arrayOf(418.30496,516.6826427,538.2560164,601.3535291,506.4434055,334.66474,482.7343855)
    private val sw1FrontResY = arrayOf(230.9018218,217.2921891,276.0203545,348.1723718,-269.4782327,-107.9053145,355.5982627)


    private val sw6LeftX = arrayOf(280.205616, 262.431496,217.527115,247.680046,228.995918,245.533455,291.902302)
    private val sw6LeftY = arrayOf(135.052826, 141.040527, 192.400925, 273.311798,228.903,56.069366,78.753601)
    private val sw6RightX = arrayOf(240.765366,240.730515,235.659164,234.3778,233.759819,230.079994,229.079224)
    private val sw6RightY = arrayOf(130.360718,121.531616,84.934265,91.81366,96.833923,114.87207,113.382813)


    //private val newLeftX = 0
    //private val newLeftY = 0
    //private val newRightX = 0
    //private val newRightY = 0


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

        mCanvas = findViewById<View>(R.id.arrowCanvas) as arrowCanvas
       // mCanvas.setOnTouchListener(ChoiceTouchListener())

        // initialize endpoints to initial values for frame one of swing 6
        swingLeftXY = intArrayOf(sw6LeftX[0].toInt(), sw6LeftY[0].toInt())
        swingRightXY = intArrayOf(sw6RightX[0].toInt(), sw6RightY[0].toInt())

        //Draws the initial arrows for swing 1 front Take Away frame
        configureStaticDrawing(sw1FrontBaseX[0],sw1FrontBaseY[0],sw1FrontBaseX[1],sw1FrontBaseY[1],sw1FrontBaseX[2],sw1FrontBaseY[2],
                sw1FrontLeftX[0].toInt(),sw1FrontLeftY[0].toInt(),sw1FrontRightX[0].toInt(),sw1FrontRightY[0].toInt(),sw1FrontResX[0].toInt(),sw1FrontResY[0].toInt())

        //Sets the initial image view in center to swing 1 front Take Away frame
        configureImageView(R.raw.sw1_front_1) //initialize to sw1 front

        //Calls the function which configures the listeners for NEXT, PREV, and ChangeView
        setNextAndPrevButtons(0)
    }

    /* onWindowFocusChanged (GIVEN)
     *
     */
    override fun onWindowFocusChanged(hasFocus:Boolean) {
        val location = IntArray(2)
        mCanvas.getLocationOnScreen(location)
        bitmapX = location[0]
        bitmapY = location[1]
        println("after focused, x: " + bitmapX)
        println("after focused, y: " + bitmapY)
        focusChanged = 1
    }

    /* onBackPressed (GIVEN)
     *
     */
    override fun onBackPressed() {                           //When back is pressed close the drawer

        if (drawer_layout.isDrawerOpen(GravityCompat.START)){ //If layout drawer is open close it
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()                           //Else do nothing
        }
    }

    /* onCreateOptionsMenu (GIVEN)
     *
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /* onOptionsItemSelected (GIVEN)
     *
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /* configureImageView (RS)
     * finds the requested image and display it center screen on the imageView object
     */
    private fun configureImageView(image : Int){
        val imageView1 = findViewById<ImageView>(R.id.imView1)
        val swingString = findViewById<TextView>(R.id.swingString)
        imageView1.setImageResource(image)
        swingString.setText(swingStrings[frame])
    }

    /* setNextAndPrevButtons (RS) -
     * add listeners to the next, previous, and changeview buttons
     * on click either move to the next frame or previous
     * changeview toggles between front and overhead view
     */
    private fun setNextAndPrevButtons(count:Int){
        frame = count //Global var to hold the current frame
        val forwardButton = findViewById<Button>(R.id.forward) as Button
        val backwardButton = findViewById<Button>(R.id.backward) as Button
        val changeViewButton = findViewById<Button>(R.id.changeViewButton) as Button

        forwardButton.setText(R.string.next)
        forwardButton.setOnClickListener({
            if (frame == 6){ frame = 0 } else { frame += 1 }

            if (isSw1Front){
                configureImageView(sw1Front[frame])
                configureStaticDrawing(sw1FrontBaseX[0],sw1FrontBaseY[0],sw1FrontBaseX[1],sw1FrontBaseY[1],sw1FrontBaseX[2],sw1FrontBaseY[2],
                        sw1FrontLeftX[frame].toInt(),sw1FrontLeftY[frame].toInt(),sw1FrontRightX[frame].toInt(),sw1FrontRightY[frame].toInt(),sw1FrontResX[frame].toInt(),sw1FrontResY[frame].toInt())
            }
            if (isSw2Front){
                configureImageView(sw2Front[frame])
            }
            if (isSw3Front){
                configureImageView(sw3Front[frame])
            }
            if (isSw4Front){
                configureImageView(sw4Front[frame])
            }
            if (isSw5Front){
                configureImageView(sw5Front[frame])
            }
            if (isSw6Front){
                configureImageView(sw6Front[frame])
            }
            if (isSw1Top){
                configureImageView(sw1Top[frame])
            }
            if (isSw2Top){
                configureImageView(sw2Top[frame])
            }
            if (isSw3Top){
                configureImageView(sw3Top[frame])
            }
            if (isSw4Top){
                configureImageView(sw4Top[frame])
            }
            if (isSw5Top){
                configureImageView(sw5Top[frame])
            }
            if (isSw6Top){
                configureImageView(sw6Top[frame])
            }
        })
        backwardButton.setText(R.string.previous)
        backwardButton.setOnClickListener({
            if (frame == 0){ frame = 6 } else { frame -= 1 }

            if (isSw1Front){
                configureImageView(sw1Front[frame])
                configureStaticDrawing(sw1FrontBaseX[0],sw1FrontBaseY[0],sw1FrontBaseX[1],sw1FrontBaseY[1],sw1FrontBaseX[2],sw1FrontBaseY[2],
                        sw1FrontLeftX[frame].toInt(),sw1FrontLeftY[frame].toInt(),sw1FrontRightX[frame].toInt(),sw1FrontRightY[frame].toInt(),sw1FrontResX[frame].toInt(),sw1FrontResY[frame].toInt())
            }
            if (isSw2Front){
                configureImageView(sw2Front[frame])
            }
            if (isSw3Front){
                configureImageView(sw3Front[frame])
            }
            if (isSw4Front){
                configureImageView(sw4Front[frame])
            }
            if (isSw5Front){
                configureImageView(sw5Front[frame])
            }
            if (isSw6Front){
                configureImageView(sw6Front[frame])
            }
            if (isSw1Top){
                configureImageView(sw1Top[frame])
            }
            if (isSw2Top){
                configureImageView(sw2Top[frame])
            }
            if (isSw3Top){
                configureImageView(sw3Top[frame])
            }
            if (isSw4Top){
                configureImageView(sw4Top[frame])
            }
            if (isSw5Top){
                configureImageView(sw5Top[frame])
            }
            if (isSw6Top){
                configureImageView(sw6Top[frame])
            }
        })
        changeViewButton.setText(R.string.overhead)
        changeViewButton.setOnClickListener({
            if (isSw1Front){/*top*/
                changeViewButton.setText(R.string.front)
                isSw1Front = false
                isSw1Top = true
                configureImageView(sw1Top[frame])
            } else if (isSw1Top) {/*front*/
                changeViewButton.setText(R.string.overhead)
                isSw1Front = true
                isSw1Top = false
                configureImageView(sw1Front[frame])
                configureStaticDrawing(sw1FrontBaseX[0],sw1FrontBaseY[0],sw1FrontBaseX[1],sw1FrontBaseY[1],sw1FrontBaseX[2],sw1FrontBaseY[2],
                        sw1FrontLeftX[frame].toInt(),sw1FrontLeftY[frame].toInt(),sw1FrontRightX[frame].toInt(),sw1FrontRightY[frame].toInt(),sw1FrontResX[frame].toInt(),sw1FrontResY[frame].toInt())
            }
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
            if (isSw3Front){/*top*/
                changeViewButton.setText(R.string.front)
                isSw3Front = false
                isSw3Top = true
                configureImageView(sw3Top[frame])
            } else if (isSw3Top) {/*front*/
                changeViewButton.setText(R.string.overhead)
                isSw3Front = true
                isSw3Top = false
                configureImageView(sw3Front[frame])
            }
            if (isSw4Front){/*top*/
                changeViewButton.setText(R.string.front)
                isSw4Front = false
                isSw4Top = true
                configureImageView(sw4Top[frame])
            } else if (isSw4Top) {/*front*/
                changeViewButton.setText(R.string.overhead)
                isSw4Front = true
                isSw4Top = false
                configureImageView(sw4Front[frame])
            }
            if (isSw5Front){/*top*/
                changeViewButton.setText(R.string.front)
                isSw5Front = false
                isSw5Top = true
                configureImageView(sw5Top[frame])
            } else if (isSw5Top){/*front*/
                changeViewButton.setText(R.string.overhead)
                isSw5Front = true
                isSw5Top = false
                configureImageView(sw5Front[frame])
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

    private fun configureStaticDrawing(LeftBaseX:Int, LeftBaseY:Int, RightBaseX:Int, RightBaseY:Int, ResBaseX:Int, ResBaseY:Int,
                                       LeftEndX:Int,LeftEndY:Int,RightEndX:Int,RightEndY:Int,ResEndX:Int,ResEndY:Int){
        // bounds not yet accurate
        if (focusChanged == 0) {
            // should allow enough time
            Thread.sleep(1_000)
            focusChanged = 1
        }
        val canvas:Canvas = mCanvas.getMyCanvas()
        val bounds = canvas.clipBounds
        print(bounds)
        println(" bounds to the left")
        print(canvas)
        println("canvas to the left")
        canvas.drawColor(0, PorterDuff.Mode.CLEAR)
        //canvas.drawColor(Color.GREEN)
        val paint = Paint()
        paint.color = RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 8.toFloat()
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        canvas.drawLine(
                RightBaseX.toFloat(),  //start X
                RightBaseY.toFloat(),  //start Y
                RightEndX.toFloat(), //stop  X
                RightEndY.toFloat(), //stop  Y
                paint
        )
        // draw bounding circle
        canvas.drawCircle(RightEndX.toFloat(), RightEndY.toFloat(), (15).toFloat(), paint)
        paint.color = BLUE
        canvas.drawLine(
                LeftBaseX.toFloat(), //start x
                LeftBaseY.toFloat(), //start y
                LeftEndX.toFloat(), //stop x
                LeftEndY.toFloat(), //stop y
                paint
        )
        canvas.drawCircle(LeftEndX.toFloat(), LeftEndY.toFloat(), (15).toFloat(), paint)
        paint.color = YELLOW
        canvas.drawLine(
                ResBaseX.toFloat(), //start x
                ResBaseY.toFloat(), //start y
                ResEndX.toFloat(), //stop x
                ResEndY.toFloat(), //stop y
                paint
        )
        mCanvas.setImageBitmap(mCanvas.getMyBitMap())
        mCanvas.bringToFront()
    }

/*
    private fun configureDrawing(selectedForce: Int, touchPoint:Coordinates,LeftBaseX:Int, LeftBaseY:Int, RightBaseX:Int, RightBaseY:Int, ResBaseX: Int, ResBaseY: Int
    ){

        val canvas:Canvas = mCanvas.getMyCanvas()
        canvas.drawColor(0, PorterDuff.Mode.CLEAR)

        val imageView1 = findViewById<ImageView>(R.id.imView1)
        imageView1.bringToFront()
        val paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 8.toFloat()
        paint.isAntiAlias = true

        // left arrow being dragged
        if (selectedForce == 0) {
            println("left arrow should be moving in configureDrawing")
            paint.color = RED
            val leftX = touchPoint.getCood_X()
            val leftY = touchPoint.getCood_Y()

            val resultantX = ResBaseX + (LeftBaseX-leftY) + (LeftBaseY-leftY)
            val resultantY = ResBaseY - (LeftBaseX-leftX) + (LeftBaseY-leftY)


            println("left X in config: " + leftX)
            println("left Y in config: " + leftY)
            canvas.drawLine(
                    RightBaseX.toFloat(),  //start X
                    RightBaseY.toFloat(),  //start Y
                    leftX.toFloat(), //stop  X
                    leftY.toFloat(), //stop  Y
                    paint
            )
            paint.style = Paint.Style.FILL
            canvas.drawCircle(leftX.toFloat(), leftY.toFloat(), (15).toFloat(), paint)
            // now still have to repaint old blue and yellow arrows
            paint.color = BLUE
            canvas.drawLine(
                    LeftBaseX.toFloat(), //start x
                    LeftBaseY.toFloat(), //start y
                    swingRightXY[0].toFloat(), //stop x
                    swingRightXY[1].toFloat(), //stop y
                    paint
            )
            paint.style = Paint.Style.FILL
            canvas.drawCircle(swingRightXY[0].toFloat(), swingRightXY[1].toFloat(), (15).toFloat(), paint)

            paint.color = YELLOW
            canvas.drawLine(
                    ResBaseX.toFloat(), //start x
                    ResBaseY.toFloat(), //start y
                    resultantX.toFloat(), //stop x
                    resultantY.toFloat(), //stop y
                    paint
            )

        }
        else {
            println("right arrow moving in configureDrawing")
            paint.color = BLUE
            val rightEndpoints = getRightEndpoints()
            val rightX = touchPoint.getCood_X()
            val rightY = touchPoint.getCood_Y()

            val resultantX = ResBaseX + (RightBaseX-rightX) + (RightBaseY-rightX)
            val resultantY = ResBaseY - (RightBaseX-rightX) + (RightBaseY-rightY)

            canvas.drawLine(
                    LeftBaseX.toFloat(), //start x
                    LeftBaseY.toFloat(), //start y
                    rightX.toFloat(), //stop x
                    rightY.toFloat(), //stop y
                    paint
            )
            paint.style = Paint.Style.FILL
            canvas.drawCircle(rightX.toFloat(), rightY.toFloat(), (15).toFloat(), paint)
            // now still have to repaint old red and yellow arrows
            paint.color = RED
            canvas.drawLine(
                    RightBaseX.toFloat(),  //start X
                    RightBaseY.toFloat(),  //start Y
                    swingLeftXY[0].toFloat(), //stop  X
                    swingLeftXY[1].toFloat(), //stop  Y
                    paint
            )
            paint.style = Paint.Style.FILL
            canvas.drawCircle(swingLeftXY[0].toFloat(), swingLeftXY[1].toFloat(), (15).toFloat(), paint)

            paint.color = YELLOW
            canvas.drawLine(
                    ResBaseX.toFloat(), //start x
                    ResBaseY.toFloat(), //start y
                    resultantX.toFloat(), //stop x
                    resultantY.toFloat(), //stop y
                    paint
            )

        }
        mCanvas.bringToFront()
    }
  */

    // Used for drawing arrows
    class arrowCanvas(c: Context, attrs: AttributeSet):ImageView(c, attrs) {
        // get screen size
        val mHeight = Resources.getSystem().getDisplayMetrics().heightPixels
        val mWidth = Resources.getSystem().getDisplayMetrics().widthPixels
        val bitmap = Bitmap.createBitmap( // set to 1000 until it fails
                1000,//minOf(mWidth, mHeight),
                1000,//minOf(mWidth, mHeight),
                Bitmap.Config.ARGB_8888
        )
        val canvas:Canvas
        internal var context:Context
        init {
            context = c
            canvas = Canvas(bitmap)
        }
        fun getMyBitMap():Bitmap{
            return this.bitmap
        }
        fun getMyCanvas():Canvas{
            return this.canvas
        }
    }
    // Used to keep pairs of coordinates

    /*
    class Coordinates {
        var cood_x: Int = 0;
        var cood_y: Int = 0;
        constructor(cood_X:Int, cood_Y:Int) {
            this.cood_x = cood_X
            this.cood_y = cood_Y
        }
        constructor() {}
        fun getCood_X():Int {
            return this.cood_x
        }
        fun getCood_Y():Int {
            return this.cood_y
        }
    }
    private fun getLeftEndpoints():Coordinates {
        return Coordinates(swingLeftXY[0], swingLeftXY[1])
    }
    private fun getRightEndpoints():Coordinates {
        return Coordinates(swingRightXY[0], swingRightXY[1])
    }
    // Couldn't use the built in function for some reason
    fun myAbsVal(val1:Int, val2:Int):Int{
        var value:Int = val1 - val2
        if (value < 0)
            value = value + (-(value * 2))
        return value
    }
    // Used to check if touch point is on end of force arrow
    @TargetApi(16)
    fun inRegion(touch:Coordinates, endpoint:Coordinates):Boolean {
        println("bitmapX: " + bitmapX)
        println("bitmapY: " + bitmapY)
        println("inRegion called")
        println("touch x: " + touch.getCood_X())
        println("endpoint x: " + endpoint.getCood_X())
        println("touch y: " + touch.getCood_Y())
        println("endpoint y: " + endpoint.getCood_Y())

        // if the touch point is within so many pixels, return true
        if ((myAbsVal(endpoint.getCood_X(), touch.getCood_X()) < 50) && (myAbsVal(endpoint.getCood_Y(), touch.getCood_Y()) < 50))
            return true
        return false
    }
    fun inBitmap(touch:Coordinates):Boolean {
        val x = touch.getCood_X()
        val y = touch.getCood_Y()
        println("touch inbitmap: (" + x + ", " + y)
        println("bitmapX: " + bitmapX)
        println("bitmapY: " + bitmapY)

        if ((x < 5) || (x > (bitmapWidth - 5)))
            return false
        else if ((y < 5) || (y > (bitmapWidth - 5)))
            return false
        else return true
    }
    private inner class ChoiceTouchListener : View.OnTouchListener {
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            println("x and y to start: " + event.getX() + "    " + event.getY())
            val xTouch = event.getX()
            val yTouch = event.getY()
            val touchPoint = Coordinates(xTouch.toInt(), yTouch.toInt())
            val leftCoords:Coordinates = getLeftEndpoints()
            val rightCoords:Coordinates = getRightEndpoints()

            if (inBitmap(touchPoint)) {
                bitmapCoords.setText("(" + xTouch.toInt() + ", " + yTouch.toInt() + ")")
            }
            else {
                bitmapCoords.setText("Off")
            }
            when (event.getAction()) {
                MotionEvent.ACTION_DOWN -> {
                    println("ACTION_DOWN")
                    if (inBitmap(touchPoint)) {
                        if (inRegion(touchPoint, leftCoords))
                            selectedForce = 0  // left foot force
                        else if (inRegion(touchPoint, rightCoords))
                            selectedForce = 1  // right foot force

                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    println("ACTION_MOVE")

                    if (selectedForce != -1) {  // don't do anything if no force selected
                        if (inBitmap(touchPoint)) {
                            configureDrawing(selectedForce, touchPoint, sw1FrontBaseX[0],sw1FrontBaseY[0],sw1FrontBaseX[1],sw1FrontBaseY[1],sw1FrontBaseX[2],sw1FrontBaseY[2]) //*******************************************************************************************************************************************
                            v.invalidate()
                        } else { // act as if action_up happened
                            if (selectedForce == 0) {
                                swingLeftXY[0] = xTouch.toInt()
                                swingLeftXY[1] = yTouch.toInt()
                            } else if (selectedForce == 1) {
                                swingRightXY[0] = xTouch.toInt()
                                swingRightXY[1] = yTouch.toInt()
                            }
                            selectedForce = -1
                        }
                        v.invalidate()
                    }
                }
                MotionEvent.ACTION_UP -> {
                    println("ACTION_UP")

                    // update location of endpoints for correct force
                    if (selectedForce == 0) {
                        swingLeftXY[0] = xTouch.toInt()
                        swingLeftXY[1] = yTouch.toInt()
                    }
                    else if (selectedForce == 1) {
                        swingRightXY[0] = xTouch.toInt()
                        swingRightXY[1] = yTouch.toInt()
                    }

                    //reset selected force to -1 (no force selected)
                    selectedForce = -1
                    v.invalidate()
                }
            }
            return true
        }
    }

*/
**/
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val swingTitle = findViewById<TextView>(R.id.swingTitle)
        when (item.itemId) {
            R.id.nav_swing_1 -> {
                swingTitle.setText(swingTitles[0])
                frame = 0
                configureImageView(sw1Front[0])
                isSw1Front = true
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
                swingTitle.setText(swingTitles[1])
                frame = 0
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
                swingTitle.setText(swingTitles[2])
                frame = 0
                configureImageView(sw3Front[0])
                isSw1Front = false
                isSw2Front = false
                isSw3Front = true
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
                swingTitle.setText(swingTitles[3])
                frame = 0
                configureImageView(sw4Front[0])
                isSw1Front = false
                isSw2Front = false
                isSw3Front = false
                isSw4Front = true
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
                swingTitle.setText(swingTitles[4])
                frame = 0
                configureImageView(sw5Front[0])
                isSw1Front = false
                isSw2Front = false
                isSw3Front = false
                isSw4Front = false
                isSw5Front = true
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
                swingTitle.setText(swingTitles[5])
                frame = 0
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
            R.id.nav_settings -> {
                // Handle settings button action
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
} // End MainActivity