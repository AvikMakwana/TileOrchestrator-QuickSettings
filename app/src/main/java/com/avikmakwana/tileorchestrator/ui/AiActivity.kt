package com.avikmakwana.tileorchestrator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.avikmakwana.tileorchestrator.ui.theme.TileOrchestratorQuickSettingsTheme

class AiActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TileOrchestratorQuickSettingsTheme() {
                AiScreen()
            }
        }
    }
}