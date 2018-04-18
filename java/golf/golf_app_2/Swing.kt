package golf.golf_app_2

import android.widget.ImageView

class Swing(forceView: ForceView,imageView: ImageView) {
    internal var curFrame = 0
    internal var curSwing = 0
    internal var curView = "front"
    internal var imgView = imageView
    internal var forceView = forceView

    // swing images
    private val sw1FrontImg: Array<Int> = arrayOf(R.raw.sw1_front_1, R.raw.sw1_front_2, R.raw.sw1_front_3,R.raw.sw1_front_4, R.raw.sw1_front_5, R.raw.sw1_front_6, R.raw.sw1_front_7)
    private val sw1TopImg: Array<Int> = arrayOf(R.raw.sw1_top_1, R.raw.sw1_top_2, R.raw.sw1_top_3,R.raw.sw1_top_4, R.raw.sw1_top_5, R.raw.sw1_top_6, R.raw.sw1_top_7)
    private val sw1Img: HashMap<String, Array<Int>> = hashMapOf("front" to sw1FrontImg, "top" to sw1TopImg)

    private val sw2FrontImg: Array<Int> = arrayOf(R.raw.sw2_front_1, R.raw.sw2_front_2, R.raw.sw2_front_3,R.raw.sw2_front_4, R.raw.sw2_front_5, R.raw.sw2_front_6, R.raw.sw2_front_7)
    private val sw2TopImg: Array<Int> = arrayOf(R.raw.sw2_top_1, R.raw.sw2_top_2, R.raw.sw2_top_3,R.raw.sw2_top_4, R.raw.sw2_top_5, R.raw.sw2_top_6, R.raw.sw2_top_7)
    private val sw2Img: HashMap<String, Array<Int>> = hashMapOf("front" to sw2FrontImg, "top" to sw2TopImg)

    private val sw3FrontImg: Array<Int> = arrayOf(R.raw.sw3_front_1, R.raw.sw3_front_2, R.raw.sw3_front_3,R.raw.sw3_front_4, R.raw.sw3_front_5, R.raw.sw3_front_6, R.raw.sw3_front_7)
    private val sw3TopImg: Array<Int> = arrayOf(R.raw.sw3_top_1, R.raw.sw3_top_2, R.raw.sw3_top_3,R.raw.sw3_top_4, R.raw.sw3_top_5, R.raw.sw3_top_6, R.raw.sw3_top_7)
    private val sw3Img: HashMap<String, Array<Int>> = hashMapOf("front" to sw3FrontImg, "top" to sw3TopImg)

    private val sw4FrontImg: Array<Int> = arrayOf(R.raw.sw4_front_1, R.raw.sw4_front_2, R.raw.sw4_front_3,R.raw.sw4_front_4, R.raw.sw4_front_5, R.raw.sw4_front_6, R.raw.sw4_front_7)
    private val sw4TopImg: Array<Int> = arrayOf(R.raw.sw4_top_1, R.raw.sw4_top_2, R.raw.sw4_top_3,R.raw.sw4_top_4, R.raw.sw4_top_5, R.raw.sw4_top_6, R.raw.sw4_top_7)
    private val sw4Img: HashMap<String, Array<Int>> = hashMapOf("front" to sw4FrontImg, "top" to sw4TopImg)

    private val sw5FrontImg: Array<Int> = arrayOf(R.raw.sw5_front_1, R.raw.sw5_front_2, R.raw.sw5_front_3,R.raw.sw5_front_4, R.raw.sw5_front_5, R.raw.sw5_front_6, R.raw.sw5_front_7)
    private val sw5TopImg: Array<Int> = arrayOf(R.raw.sw5_top_1, R.raw.sw5_top_2, R.raw.sw5_top_3,R.raw.sw5_top_4, R.raw.sw5_top_5, R.raw.sw5_top_6, R.raw.sw5_top_7)
    private val sw5Img: HashMap<String, Array<Int>> = hashMapOf("front" to sw5FrontImg, "top" to sw5TopImg)

    private val sw6FrontImg: Array<Int> = arrayOf(R.raw.sw6_front_1, R.raw.sw6_front_2, R.raw.sw6_front_3,R.raw.sw6_front_4, R.raw.sw6_front_5, R.raw.sw6_front_6, R.raw.sw6_front_7)
    private val sw6TopImg: Array<Int> = arrayOf(R.raw.sw6_top_1, R.raw.sw6_top_2, R.raw.sw6_top_3,R.raw.sw6_top_4, R.raw.sw6_top_5, R.raw.sw6_top_6, R.raw.sw6_top_7)
    private val sw6Img: HashMap<String, Array<Int>> = hashMapOf("front" to sw6FrontImg, "top" to sw6TopImg)

    private val swingImg: HashMap<Int, HashMap<String, Array<Int>>> = hashMapOf(0 to sw1Img, 1 to sw2Img, 2 to sw3Img, 3 to sw4Img, 4 to sw5Img, 5 to sw6Img)

    // swing frames

    //swing 1
    private val sw1FrontFrame1: HashMap<String, Int> = hashMapOf("leftX" to 404, "leftY" to 667, "rightX" to 490, "rightY" to 664)
    private val sw1FrontFrame2: HashMap<String, Int> = hashMapOf("leftX" to 460, "leftY" to 545, "rightX" to 556, "rightY" to 772)
    private val sw1FrontFrame3: HashMap<String, Int> = hashMapOf("leftX" to 460, "leftY" to 554, "rightX" to 578, "rightY" to 822)
    private val sw1FrontFrame4: HashMap<String, Int> = hashMapOf("leftX" to 483, "leftY" to 624, "rightX" to 618, "rightY" to 824)
    private val sw1FrontFrame5: HashMap<String, Int> = hashMapOf("leftX" to 496, "leftY" to 585, "rightX" to 511, "rightY" to 245)
    private val sw1FrontFrame6: HashMap<String, Int> = hashMapOf("leftX" to 401, "leftY" to 814, "rightX" to 433, "rightY" to 178)
    private val sw1FrontFrame7: HashMap<String, Int> = hashMapOf("leftX" to 454, "leftY" to 895, "rightX" to 529, "rightY" to 561)
    private val sw1Front: HashMap<Int, HashMap<String, Int>> = hashMapOf(0 to sw1FrontFrame1, 1 to sw1FrontFrame2, 2 to sw1FrontFrame3, 3 to sw1FrontFrame4, 4 to sw1FrontFrame5, 5 to sw1FrontFrame6, 6 to sw1FrontFrame7)
    private val sw1TopFrame1: HashMap<String, Int> = hashMapOf("leftX" to 504, "leftY" to 640, "rightX" to 540, "rightY" to 654)
    private val sw1TopFrame2: HashMap<String, Int> = hashMapOf("leftX" to 660, "leftY" to 633, "rightX" to 354, "rightY" to 663)
    private val sw1TopFrame3: HashMap<String, Int> = hashMapOf("leftX" to 510, "leftY" to 650, "rightX" to 578, "rightY" to 653)
    private val sw1TopFrame4: HashMap<String, Int> = hashMapOf("leftX" to 533, "leftY" to 688, "rightX" to 618, "rightY" to 638)
    private val sw1TopFrame5: HashMap<String, Int> = hashMapOf("leftX" to 546, "leftY" to 776, "rightX" to 510, "rightY" to 409)
    private val sw1TopFrame6: HashMap<String, Int> = hashMapOf("leftX" to 451, "leftY" to 718, "rightX" to 433, "rightY" to 595)
    private val sw1TopFrame7: HashMap<String, Int> = hashMapOf("leftX" to 417, "leftY" to 684, "rightX" to 529, "rightY" to 704)
    private val sw1Top: HashMap<Int, HashMap<String, Int>> = hashMapOf(0 to sw1TopFrame1, 1 to sw1TopFrame2, 2 to sw1TopFrame3, 3 to sw1TopFrame4, 4 to sw1TopFrame5, 5 to sw1TopFrame6, 6 to sw1TopFrame7)
    private val swing1: HashMap<String, HashMap<Int, HashMap<String, Int>>> = hashMapOf("front" to sw1Front, "top" to sw1Top)

    //swing 2
    private val sw2FrontFrame1: HashMap<String, Int> = hashMapOf("leftX" to 488, "leftY" to 451, "rightX" to 414, "rightY" to 479)
    private val sw2FrontFrame2: HashMap<String, Int> = hashMapOf("leftX" to 490, "leftY" to 364, "rightX" to 410, "rightY" to 547)
    private val sw2FrontFrame3: HashMap<String, Int> = hashMapOf("leftX" to 499, "leftY" to 269, "rightX" to 437, "rightY" to 664)
    private val sw2FrontFrame4: HashMap<String, Int> = hashMapOf("leftX" to 513, "leftY" to 242, "rightX" to 446, "rightY" to 682)
    private val sw2FrontFrame5: HashMap<String, Int> = hashMapOf("leftX" to 417, "leftY" to 583, "rightX" to 322, "rightY" to 225)
    private val sw2FrontFrame6: HashMap<String, Int> = hashMapOf("leftX" to 428, "leftY" to 417, "rightX" to 429, "rightY" to 326)
    private val sw2FrontFrame7: HashMap<String, Int> = hashMapOf("leftX" to 480, "leftY" to 159, "rightX" to 514, "rightY" to 609)
    private val sw2Front: HashMap<Int, HashMap<String, Int>> = hashMapOf(0 to sw2FrontFrame1, 1 to sw2FrontFrame2, 2 to sw2FrontFrame3, 3 to sw2FrontFrame4, 4 to sw2FrontFrame5, 5 to sw2FrontFrame6, 6 to sw2FrontFrame7)
    private val sw2TopFrame1: HashMap<String, Int> = hashMapOf("leftX" to 573, "leftY" to 658, "rightX" to 469, "rightY" to 625)
    private val sw2TopFrame2: HashMap<String, Int> = hashMapOf("leftX" to 575, "leftY" to 638, "rightX" to 466, "rightY" to 661)
    private val sw2TopFrame3: HashMap<String, Int> = hashMapOf("leftX" to 584, "leftY" to 662, "rightX" to 491, "rightY" to 629)
    private val sw2TopFrame4: HashMap<String, Int> = hashMapOf("leftX" to 597, "leftY" to 693, "rightX" to 500, "rightY" to 626)
    private val sw2TopFrame5: HashMap<String, Int> = hashMapOf("leftX" to 505, "leftY" to 836, "rightX" to 381, "rightY" to 342)
    private val sw2TopFrame6: HashMap<String, Int> = hashMapOf("leftX" to 516, "leftY" to 742, "rightX" to 483, "rightY" to 596)
    private val sw2TopFrame7: HashMap<String, Int> = hashMapOf("leftX" to 565, "leftY" to 678, "rightX" to 565, "rightY" to 728)
    private val sw2Top: HashMap<Int, HashMap<String, Int>> = hashMapOf(0 to sw2TopFrame1, 1 to sw2TopFrame2, 2 to sw2TopFrame3, 3 to sw2TopFrame4, 4 to sw2TopFrame5, 5 to sw2TopFrame6, 6 to sw2TopFrame7)
    private val swing2: HashMap<String, HashMap<Int, HashMap<String, Int>>> = hashMapOf("front" to sw2Front, "top" to sw2Top)

    //swing 3
    private val sw3FrontFrame1: HashMap<String, Int> = hashMapOf("leftX" to 334, "leftY" to 676, "rightX" to 429, "rightY" to 467)
    private val sw3FrontFrame2: HashMap<String, Int> = hashMapOf("leftX" to 390, "leftY" to 286, "rightX" to 492, "rightY" to 795)
    private val sw3FrontFrame3: HashMap<String, Int> = hashMapOf("leftX" to 366, "leftY" to 424, "rightX" to 523, "rightY" to 807)
    private val sw3FrontFrame4: HashMap<String, Int> = hashMapOf("leftX" to 442, "leftY" to 466, "rightX" to 542, "rightY" to 815)
    private val sw3FrontFrame5: HashMap<String, Int> = hashMapOf("leftX" to 409, "leftY" to 570, "rightX" to 444, "rightY" to 144)
    private val sw3FrontFrame6: HashMap<String, Int> = hashMapOf("leftX" to 337, "leftY" to 612, "rightX" to 398, "rightY" to 349)
    private val sw3FrontFrame7: HashMap<String, Int> = hashMapOf("leftX" to 273, "leftY" to 677, "rightX" to 460, "rightY" to 720)
    private val sw3Front: HashMap<Int, HashMap<String, Int>> = hashMapOf(0 to sw3FrontFrame1, 1 to sw3FrontFrame2, 2 to sw3FrontFrame3, 3 to sw3FrontFrame4, 4 to sw3FrontFrame5, 5 to sw3FrontFrame6, 6 to sw3FrontFrame7)
    private val sw3TopFrame1: HashMap<String, Int> = hashMapOf("leftX" to 424, "leftY" to 613, "rightX" to 509, "rightY" to 587)
    private val sw3TopFrame2: HashMap<String, Int> = hashMapOf("leftX" to 480, "leftY" to 553, "rightX" to 572, "rightY" to 651)
    private val sw3TopFrame3: HashMap<String, Int> = hashMapOf("leftX" to 456, "leftY" to 599, "rightX" to 603, "rightY" to 594)
    private val sw3TopFrame4: HashMap<String, Int> = hashMapOf("leftX" to 532, "leftY" to 659, "rightX" to 622, "rightY" to 547)
    private val sw3TopFrame5: HashMap<String, Int> = hashMapOf("leftX" to 499, "leftY" to 758, "rightX" to 524, "rightY" to 481)
    private val sw3TopFrame6: HashMap<String, Int> = hashMapOf("leftX" to 427, "leftY" to 619, "rightX" to 478, "rightY" to 534)
    private val sw3TopFrame7: HashMap<String, Int> = hashMapOf("leftX" to 363, "leftY" to 622, "rightX" to 540, "rightY" to 591)
    private val sw3Top: HashMap<Int, HashMap<String, Int>> = hashMapOf(0 to sw3TopFrame1, 1 to sw3TopFrame2, 2 to sw3TopFrame3, 3 to sw3TopFrame4, 4 to sw3TopFrame5, 5 to sw3TopFrame6, 6 to sw3TopFrame7)
    private val swing3: HashMap<String, HashMap<Int, HashMap<String, Int>>> = hashMapOf("front" to sw3Front, "top" to sw3Top)

    //swing 4
    /*private val sw4FrontFrame1: HashMap<String, Int> = hashMapOf("leftX" to 403.60938, "leftY" to 666.9884973, "rightX" to 489.69558, "rightY" to 663.9133245)
    private val sw4FrontFrame2: HashMap<String, Int> = hashMapOf("leftX" to 460.3741527, "leftY" to 545.2661964, "rightX" to 556.30849, "rightY" to 772.0259927)
    private val sw4FrontFrame3: HashMap<String, Int> = hashMapOf("leftX" to 459.9356082, "leftY" to 554.4063773, "rightX" to 578.3204082, "rightY" to 821.6139773)
    private val sw4FrontFrame4: HashMap<String, Int> = hashMapOf("leftX" to 483.1427973, "leftY" to 624.4238282, "rightX" to 618.2107318, "rightY" to 823.7485436)
    private val sw4FrontFrame5: HashMap<String, Int> = hashMapOf("leftX" to 495.9087236, "leftY" to 585.4331555, "rightX" to 510.5346818, "rightY" to 245.0886118)
    private val sw4FrontFrame6: HashMap<String, Int> = hashMapOf("leftX" to 401.3407655, "leftY" to 813.6020873, "rightX" to 433.3239745, "rightY" to 178.4925982)
    private val sw4FrontFrame7: HashMap<String, Int> = hashMapOf("leftX" to 453.60938, "leftY" to 895.0579073, "rightX" to 529.1250055, "rightY" to 560.5403555)
    private val sw4Front: HashMap<Int, HashMap<String, Int>> = hashMapOf(0 to sw4FrontFrame1, 1 to sw4FrontFrame2, 2 to sw4FrontFrame3, 3 to sw4FrontFrame4, 4 to sw4FrontFrame5, 5 to sw4FrontFrame6, 6 to sw4FrontFrame7)
    private val sw4TopFrame1: HashMap<String, Int> = hashMapOf("leftX" to 503.61, "leftY" to 639.90, "rightX" to 539.70, "rightY" to 653.86)
    private val sw4TopFrame2: HashMap<String, Int> = hashMapOf("leftX" to 660.37, "leftY" to 632.71, "rightX" to 353.54, "rightY" to 662.81)
    private val sw4TopFrame3: HashMap<String, Int> = hashMapOf("leftX" to 509.94, "leftY" to 649.70, "rightX" to 578.32, "rightY" to 653.08)
    private val sw4TopFrame4: HashMap<String, Int> = hashMapOf("leftX" to 533.14, "leftY" to 688.30, "rightX" to 618.21, "rightY" to 637.63)
    private val sw4TopFrame5: HashMap<String, Int> = hashMapOf("leftX" to 545.91, "leftY" to 776.34, "rightX" to 510.53, "rightY" to 408.63)
    private val sw4TopFrame6: HashMap<String, Int> = hashMapOf("leftX" to 451.34, "leftY" to 718.07, "rightX" to 433.32, "rightY" to 594.77)
    private val sw4TopFrame7: HashMap<String, Int> = hashMapOf("leftX" to 416.68, "leftY" to 684.10, "rightX" to 529.13, "rightY" to 703.65)
    private val sw4Top: HashMap<Int, HashMap<String, Int>> = hashMapOf(0 to sw4TopFrame1, 1 to sw4TopFrame2, 2 to sw4TopFrame3, 3 to sw4TopFrame4, 4 to sw4TopFrame5, 5 to sw4TopFrame6, 6 to sw4TopFrame7)
    private val swing4: HashMap<String, HashMap<Int, HashMap<String, Int>>> = hashMapOf("front" to sw4Front, "top" to sw4Top)

    //swing 5
    private val sw5FrontFrame1: HashMap<String, Int> = hashMapOf("leftX" to 403.60938, "leftY" to 666.9884973, "rightX" to 489.69558, "rightY" to 663.9133245)
    private val sw5FrontFrame2: HashMap<String, Int> = hashMapOf("leftX" to 460.3741527, "leftY" to 545.2661964, "rightX" to 556.30849, "rightY" to 772.0259927)
    private val sw5FrontFrame3: HashMap<String, Int> = hashMapOf("leftX" to 459.9356082, "leftY" to 554.4063773, "rightX" to 578.3204082, "rightY" to 821.6139773)
    private val sw5FrontFrame4: HashMap<String, Int> = hashMapOf("leftX" to 483.1427973, "leftY" to 624.4238282, "rightX" to 618.2107318, "rightY" to 823.7485436)
    private val sw5FrontFrame5: HashMap<String, Int> = hashMapOf("leftX" to 495.9087236, "leftY" to 585.4331555, "rightX" to 510.5346818, "rightY" to 245.0886118)
    private val sw5FrontFrame6: HashMap<String, Int> = hashMapOf("leftX" to 401.3407655, "leftY" to 813.6020873, "rightX" to 433.3239745, "rightY" to 178.4925982)
    private val sw5FrontFrame7: HashMap<String, Int> = hashMapOf("leftX" to 453.60938, "leftY" to 895.0579073, "rightX" to 529.1250055, "rightY" to 560.5403555)
    private val sw5Front: HashMap<Int, HashMap<String, Int>> = hashMapOf(0 to sw5FrontFrame1, 1 to sw5FrontFrame2, 2 to sw5FrontFrame3, 3 to sw5FrontFrame4, 4 to sw5FrontFrame5, 5 to sw5FrontFrame6, 6 to sw5FrontFrame7)
    private val sw5TopFrame1: HashMap<String, Int> = hashMapOf("leftX" to 503.61, "leftY" to 639.90, "rightX" to 539.70, "rightY" to 653.86)
    private val sw5TopFrame2: HashMap<String, Int> = hashMapOf("leftX" to 660.37, "leftY" to 632.71, "rightX" to 353.54, "rightY" to 662.81)
    private val sw5TopFrame3: HashMap<String, Int> = hashMapOf("leftX" to 509.94, "leftY" to 649.70, "rightX" to 578.32, "rightY" to 653.08)
    private val sw5TopFrame4: HashMap<String, Int> = hashMapOf("leftX" to 533.14, "leftY" to 688.30, "rightX" to 618.21, "rightY" to 637.63)
    private val sw5TopFrame5: HashMap<String, Int> = hashMapOf("leftX" to 545.91, "leftY" to 776.34, "rightX" to 510.53, "rightY" to 408.63)
    private val sw5TopFrame6: HashMap<String, Int> = hashMapOf("leftX" to 451.34, "leftY" to 718.07, "rightX" to 433.32, "rightY" to 594.77)
    private val sw5TopFrame7: HashMap<String, Int> = hashMapOf("leftX" to 416.68, "leftY" to 684.10, "rightX" to 529.13, "rightY" to 703.65)
    private val sw5Top: HashMap<Int, HashMap<String, Int>> = hashMapOf(0 to sw5TopFrame1, 1 to sw5TopFrame2, 2 to sw5TopFrame3, 3 to sw5TopFrame4, 4 to sw5TopFrame5, 5 to sw5TopFrame6, 6 to sw5TopFrame7)
    private val swing5: HashMap<String, HashMap<Int, HashMap<String, Int>>> = hashMapOf("front" to sw5Front, "top" to sw5Top)

    //swing 6
    private val sw6FrontFrame1: HashMap<String, Int> = hashMapOf("leftX" to 403.60938, "leftY" to 666.9884973, "rightX" to 489.69558, "rightY" to 663.9133245)
    private val sw6FrontFrame2: HashMap<String, Int> = hashMapOf("leftX" to 460.3741527, "leftY" to 545.2661964, "rightX" to 556.30849, "rightY" to 772.0259927)
    private val sw6FrontFrame3: HashMap<String, Int> = hashMapOf("leftX" to 459.9356082, "leftY" to 554.4063773, "rightX" to 578.3204082, "rightY" to 821.6139773)
    private val sw6FrontFrame4: HashMap<String, Int> = hashMapOf("leftX" to 483.1427973, "leftY" to 624.4238282, "rightX" to 618.2107318, "rightY" to 823.7485436)
    private val sw6FrontFrame5: HashMap<String, Int> = hashMapOf("leftX" to 495.9087236, "leftY" to 585.4331555, "rightX" to 510.5346818, "rightY" to 245.0886118)
    private val sw6FrontFrame6: HashMap<String, Int> = hashMapOf("leftX" to 401.3407655, "leftY" to 813.6020873, "rightX" to 433.3239745, "rightY" to 178.4925982)
    private val sw6FrontFrame7: HashMap<String, Int> = hashMapOf("leftX" to 453.60938, "leftY" to 895.0579073, "rightX" to 529.1250055, "rightY" to 560.5403555)
    private val sw6Front: HashMap<Int, HashMap<String, Int>> = hashMapOf(0 to sw6FrontFrame1, 1 to sw6FrontFrame2, 2 to sw6FrontFrame3, 3 to sw6FrontFrame4, 4 to sw6FrontFrame5, 5 to sw6FrontFrame6, 6 to sw6FrontFrame7)
    private val sw6TopFrame1: HashMap<String, Int> = hashMapOf("leftX" to 503.61, "leftY" to 639.90, "rightX" to 539.70, "rightY" to 653.86)
    private val sw6TopFrame2: HashMap<String, Int> = hashMapOf("leftX" to 660.37, "leftY" to 632.71, "rightX" to 353.54, "rightY" to 662.81)
    private val sw6TopFrame3: HashMap<String, Int> = hashMapOf("leftX" to 509.94, "leftY" to 649.70, "rightX" to 578.32, "rightY" to 653.08)
    private val sw6TopFrame4: HashMap<String, Int> = hashMapOf("leftX" to 533.14, "leftY" to 688.30, "rightX" to 618.21, "rightY" to 637.63)
    private val sw6TopFrame5: HashMap<String, Int> = hashMapOf("leftX" to 545.91, "leftY" to 776.34, "rightX" to 510.53, "rightY" to 408.63)
    private val sw6TopFrame6: HashMap<String, Int> = hashMapOf("leftX" to 451.34, "leftY" to 718.07, "rightX" to 433.32, "rightY" to 594.77)
    private val sw6TopFrame7: HashMap<String, Int> = hashMapOf("leftX" to 416.68, "leftY" to 684.10, "rightX" to 529.13, "rightY" to 703.65)
    private val sw6Top: HashMap<Int, HashMap<String, Int>> = hashMapOf(0 to sw6TopFrame1, 1 to sw6TopFrame2, 2 to sw6TopFrame3, 3 to sw6TopFrame4, 4 to sw6TopFrame5, 5 to sw6TopFrame6, 6 to sw6TopFrame7)
    private val swing6: HashMap<String, HashMap<Int, HashMap<String, Int>>> = hashMapOf("front" to sw6Front, "top" to sw6Top)
*/
    // TODO: add other swings

    private val swingFrame: HashMap<Int, HashMap<String, HashMap<Int, HashMap<String, Int>>>> = hashMapOf(0 to swing3)

    init {
        setBackground()
    }

    internal fun switchFrame(frame : Int) {
        curFrame = frame
        setBackground()
    }

    internal fun switchView(v : String) {
        curView = v
        setBackground()
    }

    internal fun switchSwing(swing : Int) {
        curFrame = 0
        curSwing = swing
        setBackground()
    }

    private fun setBackground() {
        imgView.setImageResource(swingImg[curSwing]!![curView]!![curFrame])
        forceView.draw(swingFrame[curSwing]!![curView]!![curFrame]!!)
    }
}