package com.example.tictactoejc

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BoardBase(){
    Canvas(modifier = Modifier.size(300.dp)
        .padding(10.dp)){
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(size.width*1/3, 0f),
            end = Offset(size.width*1/3, size.height)
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(size.width*2/3, 0f),
            end = Offset(size.width*2/3, size.height)
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(0f, size.height*1/3),
            end = Offset(size.width, size.height*1/3)
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(0f, size.height*2/3),
            end = Offset(size.width, size.height*2/3)
        )

    }
}

@Preview
@Composable
fun Previ(){
    BoardBase()
}
