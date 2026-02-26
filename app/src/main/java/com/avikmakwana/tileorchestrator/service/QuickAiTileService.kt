package com.avikmakwana.tileorchestrator.service

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import com.avikmakwana.tileorchestrator.ui.AiActivity

class QuickAiTileService : TileService() {

    override fun onStartListening() {
        super.onStartListening()
        qsTile?.apply {
            state = Tile.STATE_ACTIVE
            label = "Quick AI"
            updateTile()
        }
    }

    override fun onClick() {
        super.onClick()

        val intent = Intent(this, AiActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {

            val pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            startActivityAndCollapse(pendingIntent)

        } else {
            @Suppress("DEPRECATION")
            startActivityAndCollapse(intent)
        }
    }
}