package com.example.endlessrunner.models

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.example.endlessrunner.utils.Constants

class Obstacle(context: Context) {

    private val paint = Paint().apply { color = Color.RED }
    private var x = Constants.SCREEN_WIDTH.toFloat()
    private val y = Constants.GROUND_Y
    private val width = 100
    private val height = 100
    private val speed = 15f

    fun update() {
        x -= speed
        if (x + width < 0) {
            x = Constants.SCREEN_WIDTH.toFloat()
        }
    }

    fun draw(canvas: Canvas) {
        canvas.drawRect(x, y, x + width, y + height, paint)
    }

    fun getRect(): Rect {
        return Rect(x.toInt(), y.toInt(), (x + width).toInt(), (y + height).toInt())
    }
}
