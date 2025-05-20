package com.example.endlessrunner.models

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.example.endlessrunner.utils.Constants

class Player(context: Context) {

    private val paint = Paint().apply { color = Color.BLUE }
    private var x = 100f
    private var y = Constants.GROUND_Y
    private val width = 100
    private val height = 100
    private var velocity = 0f
    private val gravity = Constants.GRAVITY
    private val jumpStrength = Constants.JUMP_STRENGTH

    fun update() {
        velocity += gravity
        y += velocity
        if (y > Constants.GROUND_Y) {
            y = Constants.GROUND_Y
            velocity = 0f
        }
    }

    fun jump() {
        if (y >= Constants.GROUND_Y) {
            velocity = -jumpStrength
        }
    }

    fun draw(canvas: Canvas) {
        canvas.drawRect(x, y, x + width, y + height, paint)
    }

    fun getRect(): Rect {
        return Rect(x.toInt(), y.toInt(), (x + width).toInt(), (y + height).toInt())
    }
}
