package golf.golf_app_2

import android.widget.ImageView

class Swing(private var forceView: ForceView, imageView: ImageView) {
    internal var curFrame = 0
    private var curSwing = 0
    internal var curView = "front"

    private var imgView = imageView

    // swing images
    private val sw1FrontImg: Array<Int> = arrayOf(R.drawable.sw1_front_1, R.drawable.sw1_front_2, R.drawable.sw1_front_3,R.drawable.sw1_front_4, R.drawable.sw1_front_5, R.drawable.sw1_front_6, R.drawable.sw1_front_7)
    private val sw1TopImg: Array<Int> = arrayOf(R.drawable.sw1_top_1, R.drawable.sw1_top_2, R.drawable.sw1_top_3,R.drawable.sw1_top_4, R.drawable.sw1_top_5, R.drawable.sw1_top_6, R.drawable.sw1_top_7)
    private val sw1Img: HashMap<String, Array<Int>> = hashMapOf("front" to sw1FrontImg, "top" to sw1TopImg)

    private val sw2FrontImg: Array<Int> = arrayOf(R.drawable.sw2_front_1, R.drawable.sw2_front_2, R.drawable.sw2_front_3,R.drawable.sw2_front_4, R.drawable.sw2_front_5, R.drawable.sw2_front_6, R.drawable.sw2_front_7)
    private val sw2TopImg: Array<Int> = arrayOf(R.drawable.sw2_top_1, R.drawable.sw2_top_2, R.drawable.sw2_top_3,R.drawable.sw2_top_4, R.drawable.sw2_top_5, R.drawable.sw2_top_6, R.drawable.sw2_top_7)
    private val sw2Img: HashMap<String, Array<Int>> = hashMapOf("front" to sw2FrontImg, "top" to sw2TopImg)

    private val sw3FrontImg: Array<Int> = arrayOf(R.drawable.sw3_front_1, R.drawable.sw3_front_2, R.drawable.sw3_front_3,R.drawable.sw3_front_4, R.drawable.sw3_front_5, R.drawable.sw3_front_6, R.drawable.sw3_front_7)
    private val sw3TopImg: Array<Int> = arrayOf(R.drawable.sw3_top_1, R.drawable.sw3_top_2, R.drawable.sw3_top_3,R.drawable.sw3_top_4, R.drawable.sw3_top_5, R.drawable.sw3_top_6, R.drawable.sw3_top_7)
    private val sw3Img: HashMap<String, Array<Int>> = hashMapOf("front" to sw3FrontImg, "top" to sw3TopImg)

    private val sw4FrontImg: Array<Int> = arrayOf(R.drawable.sw4_front_1, R.drawable.sw4_front_2, R.drawable.sw4_front_3,R.drawable.sw4_front_4, R.drawable.sw4_front_5, R.drawable.sw4_front_6, R.drawable.sw4_front_7)
    private val sw4TopImg: Array<Int> = arrayOf(R.drawable.sw4_top_1, R.drawable.sw4_top_2, R.drawable.sw4_top_3,R.drawable.sw4_top_4, R.drawable.sw4_top_5, R.drawable.sw4_top_6, R.drawable.sw4_top_7)
    private val sw4Img: HashMap<String, Array<Int>> = hashMapOf("front" to sw4FrontImg, "top" to sw4TopImg)

    private val sw5FrontImg: Array<Int> = arrayOf(R.drawable.sw5_front_1, R.drawable.sw5_front_2, R.drawable.sw5_front_3,R.drawable.sw5_front_4, R.drawable.sw5_front_5, R.drawable.sw5_front_6, R.drawable.sw5_front_7)
    private val sw5TopImg: Array<Int> = arrayOf(R.drawable.sw5_top_1, R.drawable.sw5_top_2, R.drawable.sw5_top_3,R.drawable.sw5_top_4, R.drawable.sw5_top_5, R.drawable.sw5_top_6, R.drawable.sw5_top_7)
    private val sw5Img: HashMap<String, Array<Int>> = hashMapOf("front" to sw5FrontImg, "top" to sw5TopImg)

    private val sw6FrontImg: Array<Int> = arrayOf(R.drawable.sw6_front_1, R.drawable.sw6_front_2, R.drawable.sw6_front_3,R.drawable.sw6_front_4, R.drawable.sw6_front_5, R.drawable.sw6_front_6, R.drawable.sw6_front_7)
    private val sw6TopImg: Array<Int> = arrayOf(R.drawable.sw6_top_1, R.drawable.sw6_top_2, R.drawable.sw6_top_3,R.drawable.sw6_top_4, R.drawable.sw6_top_5, R.drawable.sw6_top_6, R.drawable.sw6_top_7)
    private val sw6Img: HashMap<String, Array<Int>> = hashMapOf("front" to sw6FrontImg, "top" to sw6TopImg)

    private val swingImg: HashMap<Int, HashMap<String, Array<Int>>> = hashMapOf(0 to sw1Img, 1 to sw2Img, 2 to sw3Img, 3 to sw4Img, 4 to sw5Img, 5 to sw6Img)

    // swing frames
    private val sw1FrontFrame1: HashMap<String, Double> = hashMapOf("leftX" to 403.60938, "leftY" to 666.9884973, "rightX" to 489.69558, "rightY" to 663.9133245)
    private val sw1FrontFrame2: HashMap<String, Double> = hashMapOf("leftX" to 460.3741527, "leftY" to 545.2661964, "rightX" to 556.30849, "rightY" to 772.0259927)
    private val sw1FrontFrame3: HashMap<String, Double> = hashMapOf("leftX" to 459.9356082, "leftY" to 554.4063773, "rightX" to 578.3204082, "rightY" to 821.6139773)
    private val sw1FrontFrame4: HashMap<String, Double> = hashMapOf("leftX" to 483.1427973, "leftY" to 624.4238282, "rightX" to 618.2107318, "rightY" to 823.7485436)
    private val sw1FrontFrame5: HashMap<String, Double> = hashMapOf("leftX" to 495.9087236, "leftY" to 585.4331555, "rightX" to 510.5346818, "rightY" to 245.0886118)
    private val sw1FrontFrame6: HashMap<String, Double> = hashMapOf("leftX" to 401.3407655, "leftY" to 813.6020873, "rightX" to 433.3239745, "rightY" to 178.4925982)
    private val sw1FrontFrame7: HashMap<String, Double> = hashMapOf("leftX" to 453.60938, "leftY" to 895.0579073, "rightX" to 529.1250055, "rightY" to 560.5403555)
    private val sw1Front: HashMap<Int, HashMap<String, Double>> = hashMapOf(0 to sw1FrontFrame1, 1 to sw1FrontFrame2, 2 to sw1FrontFrame3, 3 to sw1FrontFrame4, 4 to sw1FrontFrame5, 5 to sw1FrontFrame6, 6 to sw1FrontFrame7)
    private val sw1TopFrame1: HashMap<String, Double> = hashMapOf("leftX" to 403.60938, "leftY" to 666.9884973, "rightX" to 489.69558, "rightY" to 663.9133245)
    private val sw1TopFrame2: HashMap<String, Double> = hashMapOf("leftX" to 460.3741527, "leftY" to 545.2661964, "rightX" to 556.30849, "rightY" to 772.0259927)
    private val sw1TopFrame3: HashMap<String, Double> = hashMapOf("leftX" to 459.9356082, "leftY" to 554.4063773, "rightX" to 578.3204082, "rightY" to 821.6139773)
    private val sw1TopFrame4: HashMap<String, Double> = hashMapOf("leftX" to 483.1427973, "leftY" to 624.4238282, "rightX" to 618.2107318, "rightY" to 823.7485436)
    private val sw1TopFrame5: HashMap<String, Double> = hashMapOf("leftX" to 495.9087236, "leftY" to 585.4331555, "rightX" to 510.5346818, "rightY" to 245.0886118)
    private val sw1TopFrame6: HashMap<String, Double> = hashMapOf("leftX" to 401.3407655, "leftY" to 813.6020873, "rightX" to 433.3239745, "rightY" to 178.4925982)
    private val sw1TopFrame7: HashMap<String, Double> = hashMapOf("leftX" to 453.60938, "leftY" to 895.0579073, "rightX" to 529.1250055, "rightY" to 560.5403555)
    private val sw1Top: HashMap<Int, HashMap<String, Double>> = hashMapOf(0 to sw1TopFrame1, 1 to sw1TopFrame2, 2 to sw1TopFrame3, 3 to sw1TopFrame4, 4 to sw1TopFrame5, 5 to sw1TopFrame6, 6 to sw1TopFrame7)
    private val swing1: HashMap<String, HashMap<Int, HashMap<String, Double>>> = hashMapOf("front" to sw1Front, "top" to sw1Top)

    private val sw2FrontFrame1: HashMap<String, Double> = hashMapOf("leftX" to 488.00, "leftY" to 451.00, "rightX" to 414.00, "rightY" to 479.00)
    private val sw2FrontFrame2: HashMap<String, Double> = hashMapOf("leftX" to 490.00, "leftY" to 364.00, "rightX" to 410.00, "rightY" to 547.00)
    private val sw2FrontFrame3: HashMap<String, Double> = hashMapOf("leftX" to 499.00, "leftY" to 269.00, "rightX" to 437.00, "rightY" to 664.00)
    private val sw2FrontFrame4: HashMap<String, Double> = hashMapOf("leftX" to 513.00, "leftY" to 242.00, "rightX" to 446.00, "rightY" to 682.00)
    private val sw2FrontFrame5: HashMap<String, Double> = hashMapOf("leftX" to 417.00, "leftY" to 583.00, "rightX" to 322.00, "rightY" to 225.00)
    private val sw2FrontFrame6: HashMap<String, Double> = hashMapOf("leftX" to 428.00, "leftY" to 417.00, "rightX" to 429.00, "rightY" to 326.00)
    private val sw2FrontFrame7: HashMap<String, Double> = hashMapOf("leftX" to 480.00, "leftY" to 159.00, "rightX" to 514.00, "rightY" to 609.00)
    private val sw2Front: HashMap<Int, HashMap<String, Double>> = hashMapOf(0 to sw2FrontFrame1, 1 to sw2FrontFrame2, 2 to sw2FrontFrame3, 3 to sw2FrontFrame4, 4 to sw2FrontFrame5, 5 to sw2FrontFrame6, 6 to sw2FrontFrame7)
    private val sw2TopFrame1: HashMap<String, Double> = hashMapOf("leftX" to 573.00, "leftY" to 658.00, "rightX" to 469.00, "rightY" to 625.00)
    private val sw2TopFrame2: HashMap<String, Double> = hashMapOf("leftX" to 575.00, "leftY" to 638.00, "rightX" to 466.00, "rightY" to 661.00)
    private val sw2TopFrame3: HashMap<String, Double> = hashMapOf("leftX" to 584.00, "leftY" to 662.00, "rightX" to 491.00, "rightY" to 629.00)
    private val sw2TopFrame4: HashMap<String, Double> = hashMapOf("leftX" to 597.00, "leftY" to 693.00, "rightX" to 500.00, "rightY" to 626.00)
    private val sw2TopFrame5: HashMap<String, Double> = hashMapOf("leftX" to 505.00, "leftY" to 836.00, "rightX" to 381.00, "rightY" to 342.00)
    private val sw2TopFrame6: HashMap<String, Double> = hashMapOf("leftX" to 516.00, "leftY" to 742.00, "rightX" to 483.00, "rightY" to 596.00)
    private val sw2TopFrame7: HashMap<String, Double> = hashMapOf("leftX" to 565.00, "leftY" to 678.00, "rightX" to 565.00, "rightY" to 728.00)
    private val sw2Top: HashMap<Int, HashMap<String, Double>> = hashMapOf(0 to sw2TopFrame1, 1 to sw2TopFrame2, 2 to sw2TopFrame3, 3 to sw2TopFrame4, 4 to sw2TopFrame5, 5 to sw2TopFrame6, 6 to sw2TopFrame7)
    private val swing2: HashMap<String, HashMap<Int, HashMap<String, Double>>> = hashMapOf("front" to sw2Front, "top" to sw2Top)

    private val sw3FrontFrame1: HashMap<String, Double> = hashMapOf("leftX" to 334.00, "leftY" to 676.00, "rightX" to 429.00, "rightY" to 467.00)
    private val sw3FrontFrame2: HashMap<String, Double> = hashMapOf("leftX" to 390.00, "leftY" to 286.00, "rightX" to 492.00, "rightY" to 795.00)
    private val sw3FrontFrame3: HashMap<String, Double> = hashMapOf("leftX" to 366.00, "leftY" to 424.00, "rightX" to 523.00, "rightY" to 807.00)
    private val sw3FrontFrame4: HashMap<String, Double> = hashMapOf("leftX" to 442.00, "leftY" to 466.00, "rightX" to 542.00, "rightY" to 815.00)
    private val sw3FrontFrame5: HashMap<String, Double> = hashMapOf("leftX" to 409.00, "leftY" to 570.00, "rightX" to 444.00, "rightY" to 144.00)
    private val sw3FrontFrame6: HashMap<String, Double> = hashMapOf("leftX" to 337.00, "leftY" to 612.00, "rightX" to 398.00, "rightY" to 349.00)
    private val sw3FrontFrame7: HashMap<String, Double> = hashMapOf("leftX" to 273.00, "leftY" to 677.00, "rightX" to 460.00, "rightY" to 720.00)
    private val sw3Front: HashMap<Int, HashMap<String, Double>> = hashMapOf(0 to sw3FrontFrame1, 1 to sw3FrontFrame2, 2 to sw3FrontFrame3, 3 to sw3FrontFrame4, 4 to sw3FrontFrame5, 5 to sw3FrontFrame6, 6 to sw3FrontFrame7)
    private val sw3TopFrame1: HashMap<String, Double> = hashMapOf("leftX" to 424.00, "leftY" to 613.00, "rightX" to 509.00, "rightY" to 587.00)
    private val sw3TopFrame2: HashMap<String, Double> = hashMapOf("leftX" to 480.00, "leftY" to 553.00, "rightX" to 572.00, "rightY" to 651.00)
    private val sw3TopFrame3: HashMap<String, Double> = hashMapOf("leftX" to 456.00, "leftY" to 599.00, "rightX" to 603.00, "rightY" to 594.00)
    private val sw3TopFrame4: HashMap<String, Double> = hashMapOf("leftX" to 532.00, "leftY" to 659.00, "rightX" to 622.00, "rightY" to 547.00)
    private val sw3TopFrame5: HashMap<String, Double> = hashMapOf("leftX" to 499.00, "leftY" to 758.00, "rightX" to 524.00, "rightY" to 481.00)
    private val sw3TopFrame6: HashMap<String, Double> = hashMapOf("leftX" to 427.00, "leftY" to 619.00, "rightX" to 478.00, "rightY" to 534.00)
    private val sw3TopFrame7: HashMap<String, Double> = hashMapOf("leftX" to 363.00, "leftY" to 622.00, "rightX" to 540.00, "rightY" to 591.00)
    private val sw3Top: HashMap<Int, HashMap<String, Double>> = hashMapOf(0 to sw3TopFrame1, 1 to sw3TopFrame2, 2 to sw3TopFrame3, 3 to sw3TopFrame4, 4 to sw3TopFrame5, 5 to sw3TopFrame6, 6 to sw3TopFrame7)
    private val swing3: HashMap<String, HashMap<Int, HashMap<String, Double>>> = hashMapOf("front" to sw3Front, "top" to sw3Top)

    private val sw4FrontFrame1: HashMap<String, Double> = hashMapOf("leftX" to 460.00, "leftY" to 626.00, "rightX" to 530.00, "rightY" to 686.00)
    private val sw4FrontFrame2: HashMap<String, Double> = hashMapOf("leftX" to 466.00, "leftY" to 461.00, "rightX" to 559.00, "rightY" to 818.00)
    private val sw4FrontFrame3: HashMap<String, Double> = hashMapOf("leftX" to 491.00, "leftY" to 529.00, "rightX" to 553.00, "rightY" to 823.00)
    private val sw4FrontFrame4: HashMap<String, Double> = hashMapOf("leftX" to 531.00, "leftY" to 520.00, "rightX" to 578.00, "rightY" to 823.00)
    private val sw4FrontFrame5: HashMap<String, Double> = hashMapOf("leftX" to 498.00, "leftY" to 729.00, "rightX" to 527.00, "rightY" to 430.00)
    private val sw4FrontFrame6: HashMap<String, Double> = hashMapOf("leftX" to 362.00, "leftY" to 902.00, "rightX" to 473.00, "rightY" to 73.00)
    private val sw4FrontFrame7: HashMap<String, Double> = hashMapOf("leftX" to 357.00, "leftY" to 897.00, "rightX" to 548.00, "rightY" to 450.00)
    private val sw4Front: HashMap<Int, HashMap<String, Double>> = hashMapOf(0 to sw4FrontFrame1, 1 to sw4FrontFrame2, 2 to sw4FrontFrame3, 3 to sw4FrontFrame4, 4 to sw4FrontFrame5, 5 to sw4FrontFrame6, 6 to sw4FrontFrame7)
    private val sw4TopFrame1: HashMap<String, Double> = hashMapOf("leftX" to 460.00, "leftY" to 625.00, "rightX" to 530.00, "rightY" to 663.00)
    private val sw4TopFrame2: HashMap<String, Double> = hashMapOf("leftX" to 466.00, "leftY" to 591.00, "rightX" to 559.00, "rightY" to 714.00)
    private val sw4TopFrame3: HashMap<String, Double> = hashMapOf("leftX" to 491.00, "leftY" to 606.00, "rightX" to 553.00, "rightY" to 687.00)
    private val sw4TopFrame4: HashMap<String, Double> = hashMapOf("leftX" to 531.00, "leftY" to 647.00, "rightX" to 578.00, "rightY" to 648.00)
    private val sw4TopFrame5: HashMap<String, Double> = hashMapOf("leftX" to 498.00, "leftY" to 729.00, "rightX" to 527.00, "rightY" to 464.00)
    private val sw4TopFrame6: HashMap<String, Double> = hashMapOf("leftX" to 362.00, "leftY" to 690.00, "rightX" to 473.00, "rightY" to 624.00)
    private val sw4TopFrame7: HashMap<String, Double> = hashMapOf("leftX" to 357.00, "leftY" to 663.00, "rightX" to 548.00, "rightY" to 664.00)
    private val sw4Top: HashMap<Int, HashMap<String, Double>> = hashMapOf(0 to sw4TopFrame1, 1 to sw4TopFrame2, 2 to sw4TopFrame3, 3 to sw4TopFrame4, 4 to sw4TopFrame5, 5 to sw4TopFrame6, 6 to sw4TopFrame7)
    private val swing4: HashMap<String, HashMap<Int, HashMap<String, Double>>> = hashMapOf("front" to sw4Front, "top" to sw4Top)

    private val sw5FrontFrame1: HashMap<String, Double> = hashMapOf("leftX" to 422.00, "leftY" to 626.00, "rightX" to 453.00, "rightY" to 537.00)
    private val sw5FrontFrame2: HashMap<String, Double> = hashMapOf("leftX" to 452.00, "leftY" to 393.00, "rightX" to 492.00, "rightY" to 719.00)
    private val sw5FrontFrame3: HashMap<String, Double> = hashMapOf("leftX" to 436.00, "leftY" to 383.00, "rightX" to 524.00, "rightY" to 802.00)
    private val sw5FrontFrame4: HashMap<String, Double> = hashMapOf("leftX" to 475.00, "leftY" to 443.00, "rightX" to 579.00, "rightY" to 677.00)
    private val sw5FrontFrame5: HashMap<String, Double> = hashMapOf("leftX" to 493.00, "leftY" to 692.00, "rightX" to 420.00, "rightY" to 472.00)
    private val sw5FrontFrame6: HashMap<String, Double> = hashMapOf("leftX" to 351.00, "leftY" to 892.00, "rightX" to 435.00, "rightY" to -146.00)
    private val sw5FrontFrame7: HashMap<String, Double> = hashMapOf("leftX" to 319.00, "leftY" to 918.00, "rightX" to 444.00, "rightY" to -37.00)
    private val sw5Front: HashMap<Int, HashMap<String, Double>> = hashMapOf(0 to sw5FrontFrame1, 1 to sw5FrontFrame2, 2 to sw5FrontFrame3, 3 to sw5FrontFrame4, 4 to sw5FrontFrame5, 5 to sw5FrontFrame6, 6 to sw5FrontFrame7)
    private val sw5TopFrame1: HashMap<String, Double> = hashMapOf("leftX" to 510.00, "leftY" to 631.00, "rightX" to 507.00, "rightY" to 655.00)
    private val sw5TopFrame2: HashMap<String, Double> = hashMapOf("leftX" to 538.00, "leftY" to 607.00, "rightX" to 544.00, "rightY" to 697.00)
    private val sw5TopFrame3: HashMap<String, Double> = hashMapOf("leftX" to 523.00, "leftY" to 647.00, "rightX" to 575.00, "rightY" to 659.00)
    private val sw5TopFrame4: HashMap<String, Double> = hashMapOf("leftX" to 561.00, "leftY" to 707.00, "rightX" to 628.00, "rightY" to 564.00)
    private val sw5TopFrame5: HashMap<String, Double> = hashMapOf("leftX" to 578.00, "leftY" to 741.00, "rightX" to 475.00, "rightY" to 551.00)
    private val sw5TopFrame6: HashMap<String, Double> = hashMapOf("leftX" to 441.00, "leftY" to 700.00, "rightX" to 490.00, "rightY" to 627.00)
    private val sw5TopFrame7: HashMap<String, Double> = hashMapOf("leftX" to 411.00, "leftY" to 671.00, "rightX" to 498.00, "rightY" to 571.00)
    private val sw5Top: HashMap<Int, HashMap<String, Double>> = hashMapOf(0 to sw5TopFrame1, 1 to sw5TopFrame2, 2 to sw5TopFrame3, 3 to sw5TopFrame4, 4 to sw5TopFrame5, 5 to sw5TopFrame6, 6 to sw5TopFrame7)
    private val swing5: HashMap<String, HashMap<Int, HashMap<String, Double>>> = hashMapOf("front" to sw5Front, "top" to sw5Top)

    private val sw6FrontFrame1: HashMap<String, Double> = hashMapOf("leftX" to 417.00, "leftY" to 719.00, "rightX" to 504.00, "rightY" to 679.00)
    private val sw6FrontFrame2: HashMap<String, Double> = hashMapOf("leftX" to 434.00, "leftY" to 551.00, "rightX" to 541.00, "rightY" to 827.00)
    private val sw6FrontFrame3: HashMap<String, Double> = hashMapOf("leftX" to 477.00, "leftY" to 774.00, "rightX" to 591.00, "rightY" to 924.00)
    private val sw6FrontFrame4: HashMap<String, Double> = hashMapOf("leftX" to 448.00, "leftY" to 851.00, "rightX" to 464.00, "rightY" to 486.00)
    private val sw6FrontFrame5: HashMap<String, Double> = hashMapOf("leftX" to 466.00, "leftY" to 808.00, "rightX" to 377.00, "rightY" to 184.00)
    private val sw6FrontFrame6: HashMap<String, Double> = hashMapOf("leftX" to 450.00, "leftY" to 644.00, "rightX" to 376.00, "rightY" to 221.00)
    private val sw6FrontFrame7: HashMap<String, Double> = hashMapOf("leftX" to 406.00, "leftY" to 665.00, "rightX" to 549.00, "rightY" to 940.00)
    private val sw6Front: HashMap<Int, HashMap<String, Double>> = hashMapOf(0 to sw6FrontFrame1, 1 to sw6FrontFrame2, 2 to sw6FrontFrame3, 3 to sw6FrontFrame4, 4 to sw6FrontFrame5, 5 to sw6FrontFrame6, 6 to sw6FrontFrame7)
    private val sw6TopFrame1: HashMap<String, Double> = hashMapOf("leftX" to 447.00, "leftY" to 594.00, "rightX" to 534.00, "rightY" to 589.00)
    private val sw6TopFrame2: HashMap<String, Double> = hashMapOf("leftX" to 464.00, "leftY" to 605.00, "rightX" to 571.00, "rightY" to 631.00)
    private val sw6TopFrame3: HashMap<String, Double> = hashMapOf("leftX" to 507.00, "leftY" to 685.00, "rightX" to 621.00, "rightY" to 532.00)
    private val sw6TopFrame4: HashMap<String, Double> = hashMapOf("leftX" to 478.00, "leftY" to 718.00, "rightX" to 494.00, "rightY" to 356.00)
    private val sw6TopFrame5: HashMap<String, Double> = hashMapOf("leftX" to 496.00, "leftY" to 765.00, "rightX" to 407.00, "rightY" to 311.00)
    private val sw6TopFrame6: HashMap<String, Double> = hashMapOf("leftX" to 480.00, "leftY" to 657.00, "rightX" to 406.00, "rightY" to 485.00)
    private val sw6TopFrame7: HashMap<String, Double> = hashMapOf("leftX" to 436.00, "leftY" to 663.00, "rightX" to 579.00, "rightY" to 623.00)
    private val sw6Top: HashMap<Int, HashMap<String, Double>> = hashMapOf(0 to sw6TopFrame1, 1 to sw6TopFrame2, 2 to sw6TopFrame3, 3 to sw6TopFrame4, 4 to sw6TopFrame5, 5 to sw6TopFrame6, 6 to sw6TopFrame7)
    private val swing6: HashMap<String, HashMap<Int, HashMap<String, Double>>> = hashMapOf("front" to sw6Front, "top" to sw6Top)

    private val swingFrame: HashMap<Int, HashMap<String, HashMap<Int, HashMap<String, Double>>>> = hashMapOf(0 to swing1, 1 to swing2, 2 to swing3, 3 to swing4, 4 to swing5, 5 to swing6)

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
