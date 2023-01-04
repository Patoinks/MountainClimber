package ipca.grupo2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.CountDownTimer
import android.os.Handler
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlinx.coroutines.delay
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timer

class Oximetro  : View {

    private var _desenho = 0F
    var desenho : Float
        get() {
            return _desenho
        }
        set(value) {
            _desenho = value
            if(_desenho == 0F)
            {
                Timer().schedule(5000) {
                    desenho++
                }
            }

            if(_desenho == 1F)
            {
                Timer().schedule(6000) {
                    desenho++

                }
            }
            invalidate()
        }

    private var onValueChange : ((Float)->Unit)? = null

    fun setOnValueChangeListener (callback :(Float)->Unit ) {
        onValueChange = callback
    }


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()
        val rect = Rect(0,0,width,height)
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20F)
        canvas?.drawRect(rect, paint)


            paint.setStyle(Paint.Style.FILL)
            paint.color = Color.YELLOW
            canvas?.drawCircle(width.toFloat() / 2, height.toFloat() / 2, 180F, paint)
            paint.color = Color.RED
            canvas?.drawCircle(width.toFloat() / 2, height.toFloat() / 4.5F, 180F, paint)


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)

        return false

    }
}

