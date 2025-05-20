package com.example.endlessrunner

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.MotionEvent
import com.example.endlessrunner.models.Player
import com.example.endlessrunner.models.Obstacle

class GameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private val gameThread: GameThread
    private val player = Player(context)
    private val obstacle = Obstacle(context)

    init {
        holder.addCallback(this)
        gameThread = GameThread(holder, this)
        isFocusable = true
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        gameThread.setRunning(true)
        gameThread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        gameThread.setRunning(false)
        while (retry) {
            try {
                gameThread.join()
                retry = false
            } catch (e: InterruptedException) {}
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            player.jump()
        }
        return true
    }

    fun update() {
        player.update()
        obstacle.update()
        if (player.getRect().intersect(obstacle.getRect())) {
            // Handle collision (e.g., end game or reset)
            gameThread.setRunning(false)
        }
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawColor(Color.WHITE)
        player.draw(canvas)
        obstacle.draw(canvas)
    }
}
