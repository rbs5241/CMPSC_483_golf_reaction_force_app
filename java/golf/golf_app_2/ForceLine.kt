package golf.golf_app_2

class ForceLine {
    /* xy coordinates of force from where it starts */
    var startXY: Coordinates
        internal set
    /* xy coordinates of force to where it stops */
    var endXY: Coordinates
        internal set

    init {
        startXY = Coordinates()
        endXY = Coordinates()
    }

    fun setStartXY(startX: Double, startY: Double) {
        this.startXY.cood_x = startX
        this.startXY.cood_y = startY
    }

    fun setEndXY(endX: Double, endY: Double) {
        this.endXY.cood_x = endX
        this.endXY.cood_y = endY
    }
}
