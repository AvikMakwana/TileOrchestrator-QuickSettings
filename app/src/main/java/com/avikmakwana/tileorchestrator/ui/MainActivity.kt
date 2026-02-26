package com.avikmakwana.tileorchestrator.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.avikmakwana.tileorchestrator.ui.theme.TileOrchestratorQuickSettingsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TileOrchestratorQuickSettingsTheme() {
                MainScreen(
                    onOpenAi = {
                        startActivity(
                            Intent(this, AiActivity::class.java)
                        )
                    }
                )
            }
        }
    }
}

