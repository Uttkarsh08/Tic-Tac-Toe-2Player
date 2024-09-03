package com.example.tictactoejc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoejc.ui.theme.TicTacToeJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: GameViewModel = viewModel()
            TicTacToeJCTheme {
                GameScreen(viewModel = viewModel)
            }
        }
    }
}

