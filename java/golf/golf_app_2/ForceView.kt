package golf.golf_app_2

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import java.lang.Math.abs
import java.util.HashMap


class ForceView @TargetApi(Build.VERSION_CODES.KITKAT)
constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {
    private var mBitmap: Bitmap? = null
    private var mCanvas: Canvas? = null
    private lateinit var spinnerAnimator: ObjectAnimator

    private val leftStartX: Int = 510
    private val rightStartX: Int = 680
    private val sumStartX: Int = 595
    private val panel: Int = 850

    private val leftForce: ForceLine = ForceLine()
    private val rightForce: ForceLine = ForceLine()
    private val sumForce: ForceLine = ForceLine()
    private var selectedForce: ForceLine? = null

    private val leftForcePaint: Paint = Paint()
    private val rightForcePaint: Paint = Paint()
    private val sumForcePaint: Paint = Paint()
    private val leftCyclePaint: Paint = Paint()
    private val rightCyclePaint: Paint = Paint()

    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0)

    init {
        configPaint()
        configSpinner()
    }

    private fun configPaint() {
        leftForcePaint.color = Color.RED
        leftForcePaint.strokeWidth = 8f
        leftForce.setStartXY(leftStartX, panel)
        leftForce.setEndXY(leftStartX, panel)

        rightForcePaint.color = Color.GREEN
        rightForcePaint.strokeWidth = 8f
        rightForce.setStartXY(rightStartX, panel)
        rightForce.setEndXY(rightStartX, panel)

        sumForcePaint.color = Color.YELLOW
        sumForcePaint.strokeWidth = 8f
        sumForce.setStartXY(sumStartX, panel)
        sumForce.setEndXY(sumStartX, panel)

        leftCyclePaint.color = Color.RED
        rightCyclePaint.color = Color.GREEN
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec) // Trick to make the view square
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Log.d("Size", "onSizeChanged: $w $h $oldw $oldh")
        if (w != oldw || h != oldh) {
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            mBitmap!!.eraseColor(Color.TRANSPARENT)
            mCanvas = Canvas(mBitmap!!)
        }
        super.onSizeChanged(w, h, oldw, oldh)
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
                15f,
                leftCyclePaint)

        /* drawing the right drag point */
        mCanvas!!.drawCircle(
                (rightForce.endXY.cood_x).toFloat(),
                (rightForce.endXY.cood_y).toFloat(),
                15f,
                rightCyclePaint)

        canvas.drawBitmap(mBitmap!!, 0f, 0f, null)
    }

    internal fun draw(frame: HashMap<String, Int>) {
        val leftX = frame["leftX"]
        val leftY = frame["leftY"]
        val rightX = frame["rightX"]
        val rightY = frame["rightY"]
        val sumX = sumStartX + (leftX!! - leftStartX) + (rightX!! - rightStartX)
        val sumY = panel - (leftY!! + rightY!!)
        sumForce.setEndXY(sumX, sumY)
        leftForce.setEndXY(leftX, leftY)
        rightForce.setEndXY(rightX, rightY)
        //spinnerAnimator.duration = calSpeed(sumX, sumY)
        invalidate()
    }

    private fun configSpinner() {
        spinnerAnimator = ObjectAnimator.ofFloat(findViewById(R.id.progressBar),
                "rotation", 0f, 360f)
        spinnerAnimator.duration = 0
        spinnerAnimator.repeatCount = ValueAnimator.INFINITE
        spinnerAnimator.interpolator = LinearInterpolator()
        spinnerAnimator.start()
    }

    private fun draw(force: ForceLine, coord: Coordinates) {
        force.setEndXY(coord.cood_x, coord.cood_y)
        val sumX = sumStartX + (leftForce.endXY.cood_x - leftStartX) + (rightForce.endXY.cood_x - rightStartX)
        val sumY = panel - (leftForce.endXY.cood_y + rightForce.endXY.cood_y)
        sumForce.setEndXY(sumX, sumY)
        //spinnerAnimator.duration = calSpeed(sumX, sumY)
        spinnerAnimator.start()
        invalidate()
    }

    /*private fun calSpeed(x: Int, y: Int): Long {
        x.toDouble()
        y.toDouble()
        return (2000 - Math.sqrt((x - sumStartX) * (x - sumStartX) + (y - panel) * (y - panel))).toLong()
    }*/

    private fun inRegion(touch: Coordinates, force: Coordinates): Boolean {
        return abs(force.cood_x - touch.cood_x) < 25 && abs(force.cood_y - touch.cood_y) < 25
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchPoint: Coordinates
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchPoint = Coordinates(event.x.toInt(), event.y.toInt())
                if (inRegion(touchPoint, leftForce.endXY)) {
                    selectedForce = leftForce
                } else if (inRegion(touchPoint, rightForce.endXY)) {
                    selectedForce = rightForce
                }
            }
            MotionEvent.ACTION_MOVE -> {
                touchPoint = Coordinates(event.x.toInt(), event.y.toInt())
                if (selectedForce != null)
                    draw(selectedForce!!, touchPoint)
            }
            MotionEvent.ACTION_UP -> selectedForce = null
        }
        return true
    }
}