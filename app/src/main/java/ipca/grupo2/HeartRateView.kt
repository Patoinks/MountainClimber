package ipca.grupo2


import android.animation.ValueAnimator
import android.app.Activity
import kotlinx.coroutines.GlobalScope
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import ipca.grupo2.backend.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Math.min

@RequiresApi(Build.VERSION_CODES.M)
class HeartRateView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

        private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        private var value = 0f
            set(value) {
                field = value
                invalidate()
            }

    private var value2 = 0f
        set(value2) {
            field = value2
            invalidate()
        }



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Calculate the size and position of the circle
        val size = min(width, height)
        val x = (width - size) / 2
        val y = (height - size) / 2

        // Cor baseada no resultado
        GlobalScope.launch {
            var borderColor: Int
            var location = Location(context, (this@HeartRateView.context) as Activity)
            var altitude = Math.round(location!!.getAltitude() - 56)
            ((this@HeartRateView.context) as Activity).runOnUiThread{
                if (value2 ==  0F) {

                    borderColor = Color.parseColor("#64A19D") // red for low values
                } else if (value2  < 0.9F && value2 != 0F) {
                    borderColor = Color.parseColor("#FF7878") // yellow for medium values
                } else {
                    borderColor = Color.parseColor("#64A19D")// green for high values
                }

            }
        }





        // Fundo do circulo
        paint.color = Color.parseColor("#36D8D8D8")
        canvas.drawCircle(x + size / 2f, y + size / 2f, size / 2f, paint)

        //Cor e forma da borda
        paint.strokeCap = Paint.Cap.ROUND
        paint.color = borderColor


        // Desenha o progresso
        val sweepAngle = 360 * value
        canvas.drawArc(x.toFloat(), y.toFloat(), (x + size).toFloat(), (y + size).toFloat(), -90f, sweepAngle, true, paint)

        // Draw a white circle over the progress arc to create a border
        paint.color = Color.WHITE
        canvas.drawCircle(x + size / 2f, y + size / 2f, size / 2f - 10f, paint)
    }

    // Animar o Progresso
    fun setValueAnimated(value: Float, duration: Long) {
        this.value2 = value
        val animator = ValueAnimator.ofFloat(0f, value)
        animator.addUpdateListener {
            this.value = it.animatedValue as Float
        }
        animator.duration = duration
        animator.start()
        }
    }
