package golf.golf_app_2

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.TextView
import java.lang.Math.abs
import java.util.*


class ForceView @TargetApi(Build.VERSION_CODES.KITKAT)
constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {
    private var mCanvas: Canvas? = null
    private lateinit var spinnerAnimator: ObjectAnimator

    private val leftForce: ForceLine = ForceLine()
    private val rightForce: ForceLine = ForceLine()
    private val sumForce: ForceLine = ForceLine()
    private var selectedForce: ForceLine? = null

    private var leftForcePaint: Paint = Paint()
    private var rightForcePaint: Paint = Paint()
    private var sumForcePaint: Paint = Paint()
    private var leftCyclePaint: Paint = Paint()
    private var rightCyclePaint: Paint = Paint()

    private var canvasWidth: Double = 0.0
    private var canvasHeight: Double = 0.0
    private var leftStartX: Double = 0.0
    private var rightStartX: Double = 0.0
    private var sumStartX: Double = 0.0
    private var panel: Double = 0.0

    private var mBitmap: Bitmap? = null


    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)

    private fun configPaint() {
        leftForcePaint.color = Color.BLUE
        leftForcePaint.strokeWidth = 10f
        leftForce.setStartXY(leftStartX, panel)
        leftForce.setEndXY(leftStartX, panel)

        rightForcePaint.color = Color.RED
        rightForcePaint.strokeWidth = 10f
        rightForce.setStartXY(rightStartX, panel)
        rightForce.setEndXY(rightStartX, panel)

        sumForcePaint.color = Color.YELLOW
        sumForcePaint.strokeWidth = 10f
        sumForce.setStartXY(sumStartX, panel)
        sumForce.setEndXY(sumStartX, panel)

        leftCyclePaint.color = Color.BLUE
        rightCyclePaint.color = Color.RED
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Log.d("Size", "onSizeChanged: $w $h $oldw $oldh")
        if (w != oldw || h != oldh) {
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            mBitmap!!.eraseColor(Color.TRANSPARENT)
            mCanvas = Canvas(mBitmap!!)
        }
        super.onSizeChanged(w, h, oldw, oldh)
        initPosition(w, h)
        configPaint()
    }

    override fun onWindowFocusChanged(hasFocus:Boolean) {
        // values for swing 1 frame 1. C
        val leftX = convertX1(403.6)
        val leftY = convertY(666.9)
        val rightX = convertX2(489.69)
        val rightY = convertY(663.9)
        val sumX = sumStartX + (leftX - leftStartX) + (rightX - rightStartX)
        val sumY = panel - ((panel - leftY) + (panel - rightY))
        sumForce.setEndXY(sumX, sumY)
        leftForce.setEndXY(leftX, leftY)
        rightForce.setEndXY(rightX, rightY)
        invalidate()
    }

    fun initPosition(w: Int, h: Int) {
        canvasWidth = w.toDouble()
        canvasHeight = h.toDouble()
        leftStartX = canvasWidth * 0.38
        rightStartX = canvasWidth *0.58
        sumStartX = canvasWidth *0.48
        panel = canvasHeight * 0.88
    }

    override fun onDraw(canvas: Canvas) {
        mCanvas!!.drawColor(0, PorterDuff.Mode.CLEAR)
        /* drawing the left force */
        mCanvas!!.drawLine(
                (leftForce.startXY.cood_x).toFloat(),
                (leftForce.startXY.cood_y).toFloat(),
                (leftForce.endXY.cood_x).toFloat(),
                (leftForce.endXY.cood_y).toFloat(),
                leftForcePaint)

        /* drawing the right force */
        mCanvas!!.drawLine(
                (rightForce.startXY.cood_x).toFloat(),
                (rightForce.startXY.cood_y).toFloat(),
                (rightForce.endXY.cood_x).toFloat(),
                (rightForce.endXY.cood_y).toFloat(),
                rightForcePaint)

        /* drawing the sum force */
        mCanvas!!.drawLine(
                (sumForce.startXY.cood_x).toFloat(),
                (sumForce.startXY.cood_y).toFloat(),
                (sumForce.endXY.cood_x).toFloat(),
                (sumForce.endXY.cood_y).toFloat(),
                sumForcePaint)

        /* drawing the left drag point */
        mCanvas!!.drawCircle(
                (leftForce.endXY.cood_x).toFloat(),
                (leftForce.endXY.cood_y).toFloat(),
                20f,
                leftCyclePaint)

        /* drawing the right drag point */
        mCanvas!!.drawCircle(
                (rightForce.endXY.cood_x).toFloat(),
                (rightForce.endXY.cood_y).toFloat(),
                20f,
                rightCyclePaint)

        canvas.drawBitmap(mBitmap!!, 0f, 0f, null)
    }

    internal fun draw(frame: HashMap<String, Double>) {
        val leftX = convertX1(frame["leftX"])
        val leftY = convertY(frame["leftY"])
        val rightX = convertX2(frame["rightX"])
        val rightY = convertY(frame["rightY"])

        val sumX = sumStartX + (leftX - leftStartX) + (rightX - rightStartX)
        val sumY = panel - ((panel - leftY) + (panel - rightY))
        sumForce.setEndXY(sumX, sumY)
        leftForce.setEndXY(leftX, leftY)
        rightForce.setEndXY(rightX, rightY)
        setTextBox()
        setSpinner(calSpeed(sumX, sumY))
        invalidate()
    }

    private fun draw(force: ForceLine, coord: Coordinates) {
        force.setEndXY(coord.cood_x, coord.cood_y)
        val sumX = sumStartX + (leftForce.endXY.cood_x - leftStartX) + (rightForce.endXY.cood_x - rightStartX)
        val sumY = panel - ((panel - leftForce.endXY.cood_y) + (panel - rightForce.endXY.cood_y))
        sumForce.setEndXY(sumX, sumY)
        setTextBox()
        setSpinner(calSpeed(sumX, sumY))
        invalidate()
    }

    private fun convertX1(x: Double?): Double {
        return leftStartX + sumStartX / canvasHeight * x!!
    }

    private fun convertX2(x: Double?): Double {
        return rightStartX + sumStartX / canvasHeight * x!!
    }

    private fun convertY(y: Double?): Double {
        return panel - sumStartX / canvasHeight * y!!
    }

    private fun calSpeed(x: Double, y: Double): Long {
        return ((2000 - Math.sqrt((x - sumStartX) * (x - sumStartX) + (y - panel) * (y - panel)))).toLong()
    }

    private fun inRegion(touch: Coordinates, force: Coordinates): Boolean {
        return abs(force.cood_x - touch.cood_x) < 40 && abs(force.cood_y - touch.cood_y) < 40
    }

    private fun inBitmap(touch: Coordinates): Boolean {
        if ((touch.cood_y < 15) || (touch.cood_y > canvasHeight - 15))
            return false
        if ((touch.cood_x < 15) || (touch.cood_y > canvasWidth - 15))
            return false
        return true
    }
    
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchPoint: Coordinates
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchPoint = Coordinates(event.x.toDouble(), event.y.toDouble())
                if (inRegion(touchPoint, leftForce.endXY)) {
                    selectedForce = leftForce
                } else if (inRegion(touchPoint, rightForce.endXY)) {
                    selectedForce = rightForce
                }
            }
            MotionEvent.ACTION_MOVE -> {
                touchPoint = Coordinates(event.x.toDouble(), event.y.toDouble())
                println("X: " + touchPoint.cood_x + " Y: " + touchPoint.cood_y)
                if (selectedForce != null) {
                    if (inBitmap(touchPoint))
                        draw(selectedForce!!, touchPoint)
                    else
                        selectedForce = null
                }
            }
            MotionEvent.ACTION_UP -> selectedForce = null
        }
        return true
    }

    private fun setTextBox() {
        val r = this.parent as ConstraintLayout
        val leftTextBox = r.findViewById<TextView>(R.id.editLeftFoot)
        val rightTextBox = r.findViewById<TextView>(R.id.editRightFoot)
        val sumTextBox = r.findViewById<TextView>(R.id.editResultant)
        leftTextBox.text = calMag(leftForce.endXY.cood_x - leftForce.startXY.cood_x,canvasHeight - leftForce.endXY.cood_y).toInt().toString()
        rightTextBox.text = calMag(rightForce.endXY.cood_x - rightForce.startXY.cood_x ,canvasHeight - rightForce.endXY.cood_y).toInt().toString()
        sumTextBox.text = calMag(sumForce.endXY.cood_x,sumForce.endXY.cood_y).toInt().toString()
    }

    private fun calMag(x : Double, y : Double): Double {
        return Math.sqrt(x * x + y * y)
    }

    private fun setSpinner(duration:Long) {
        val r = this.parent as ConstraintLayout
        spinnerAnimator = ObjectAnimator.ofFloat(r.findViewById(R.id.progressBar),
                "rotation", 0f, 360f)
        spinnerAnimator.duration = duration
        spinnerAnimator.repeatCount = ValueAnimator.INFINITE
        spinnerAnimator.interpolator = LinearInterpolator()
        spinnerAnimator.start()
    }
}
