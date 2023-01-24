package ipca.grupo2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

class CheckpointSliderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    // constants for the checkpoint radius, line width, and text size
    private val checkpointRadius = 20f
    private val lineWidth = 5f
    private val textSize = 24f

    // constants for the snap radius and snap distance
    private val snapRadius = 30f
    private val snapDistance = snapRadius * 2

    // variables for the paint objects and the current position
    private val checkpointPaint = Paint()
    private val linePaint = Paint()
    private val textPaint = Paint()
    private var currentPosition = 50f

    // variables for the number of checkpoints and the checkpoint positions
    private val numCheckpoints = 4
    private val checkpointPositions = floatArrayOf(0.08f, 0.33f, 0.66f, 0.98F)

    init {
        // set the paint colors
        checkpointPaint.color = ContextCompat.getColor(context, R.color.verde)
        linePaint.color = ContextCompat.getColor(context, R.color.verde)
        textPaint.color = ContextCompat.getColor(context, R.color.verde)

        // set the paint styles
        checkpointPaint.style = Paint.Style.FILL
        linePaint.style = Paint.Style.STROKE
        textPaint.style = Paint.Style.FILL

        // set the line width and text size
        linePaint.strokeWidth = lineWidth
        textPaint.textSize = textSize

        textPaint.typeface = Typeface.DEFAULT_BOLD
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // draw the checkpoints at the specified positions
        for (i in 0 until numCheckpoints) {
            var x = if (i == 0) checkpointRadius else checkpointPositions[i] * width
            x = if (i == numCheckpoints - 1) width - checkpointRadius else checkpointPositions[i] * width
            canvas.drawCircle(x, height / 2f, checkpointRadius, checkpointPaint)
            // draw the checkpoint number on top of the checkpoint
            canvas.drawText((i).toString(), x - textSize / 4, height / 2f - checkpointRadius - textSize / 2, textPaint)
        }

        // draw the line between the checkpoints, starting and ending after the first and last checkpoints
        canvas.drawLine(checkpointRadius * 2, height / 2f, width - checkpointRadius * 2, height / 2f, linePaint)

        // draw the current position indicator
        canvas.drawCircle(currentPosition , height / 2f, checkpointRadius, checkpointPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // handle the user touching down on the view
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                // handle the user moving their finger along the view
                // update the current position based on the touch event coordinates
                currentPosition = event.x

                if (currentPosition < checkpointPositions[0] * width) {
                    // if the current position is less than the x position of the first checkpoint, snap the current position to the first checkpoint
                    currentPosition = checkpointPositions[0] * width
                } else if (currentPosition > checkpointPositions[numCheckpoints - 1] * width) {
                    // if the current position is greater than the x position of the last checkpoint, snap the current position to the last checkpoint
                    currentPosition = checkpointPositions[numCheckpoints - 1] * width
                } else {
                    for (i in 0 until numCheckpoints) {
                        val checkpointX = checkpointPositions[i] * width
                        if (Math.abs(checkpointX - currentPosition) < snapRadius) {
                            // if the current position is within the snap radius of a checkpoint, snap the current position to the checkpoint
                            currentPosition = checkpointX
                            break
                        }
                    }
                }
                // redraw the view to update the current position indicator
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP -> {
                // handle the user lifting their finger off the view
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    fun getCurrentCheckpoint(): Int {
        for (i in 0 until numCheckpoints) {
            val checkpointX = checkpointPositions[i] * width
            if (Math.abs(checkpointX - currentPosition) < snapRadius) {
                return i
            }
        }
        return -1
    }

}
