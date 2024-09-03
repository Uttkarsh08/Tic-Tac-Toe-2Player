package com.example.tictactoejc

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoejc.ui.theme.Aqua
import com.example.tictactoejc.ui.theme.GrayBackground
import com.example.tictactoejc.ui.theme.GreenishYellow

@Composable
fun GameScreen(
    viewModel: GameViewModel
){
    val state = viewModel.state
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(GrayBackground)
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {


                Text(text = "Tic Tac Toe",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive,
                    color = colorResource(id = R.color.teal_700)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clip(RoundedCornerShape(20.dp))
                        .background(GrayBackground),
                    contentAlignment = Alignment.Center
                ){
                    BoardBase()
                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .aspectRatio(1f),
                        columns = GridCells.Fixed(3)
                    ){
                        viewModel.boardItems.forEach{(cellNo, boardCellValue) ->
                            item{
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clickable(
                                            interactionSource = MutableInteractionSource(),
                                            indication = null
                                        ) {
                                            viewModel.onAction(UserAction.BoardTapped(cellNo))
                                        },
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    AnimatedVisibility(visible = viewModel.boardItems[cellNo] != BoardCellValue.NONE,
                                        enter = scaleIn(tween(1000))

                                    ) {
                                        if(boardCellValue == BoardCellValue.CIRCLE){
                                            circle()
                                        }else if(boardCellValue == BoardCellValue.CROSS){
                                            cross()
                                        }
                                    }

                                }
                            }
                        }
                    }
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        AnimatedVisibility(
                            visible = state.hasWon,
                            enter = fadeIn(tween(2000))
                        ) {
                            drawVictoryLine(state = state)
                        }
                    }

                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    (if(state.hasWon) state.playerWon else state.hintText)?.let { Text(text = it, fontSize = 24.sp, fontStyle = FontStyle.Italic) }
                    Spacer(modifier = Modifier.height(40.dp))
                    Button(onClick = { viewModel.onAction(UserAction.PlayAgainButtonClicked) },
                        shape = RoundedCornerShape(10.dp),
                        elevation = ButtonDefaults.buttonElevation(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.teal_700)
                        ),
                        modifier = Modifier
                            .width(200.dp)
                            .height(50.dp)

                    ) {
                        Text(text = "Play Again", fontSize = 16.sp)
                    }

                }
            }
        }



@Composable
fun cross(){
    Canvas(modifier = Modifier
        .size(60.dp)
        .padding(5.dp)
    ){
        drawLine(
            color = GreenishYellow,
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(0f, 0f),
            end = Offset(size.width, size.height)
        )
        drawLine(
            color = GreenishYellow,
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            start = Offset(0f, size.height),
            end = Offset(size.width, 0f)
        )
    }
}

@Composable
fun circle(){
    Canvas(modifier = Modifier
        .size(60.dp)
        .padding(5.dp)
    ){
        drawCircle(
            color = Aqua,
            style = Stroke(width = 20f)

        )
    }
}

@Composable
fun WinHorizontalLine1(){
    Canvas(modifier = Modifier.size(300.dp)){
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(0f, size.height*1/6),
            end = Offset(size.width, size.height*1/6)
        )
    }
}
@Composable
fun WinHorizontalLine2(){
    Canvas(modifier = Modifier.size(300.dp)){
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(0f, size.height*3/6),
            end = Offset(size.width, size.height*3/6)
        )
    }
}
@Composable
fun WinHorizontalLine3(){
    Canvas(modifier = Modifier.size(300.dp)){
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(0f, size.height*5/6),
            end = Offset(size.width, size.height*5/6)
        )
    }
}

@Composable
fun WinverticalLine1(){
    Canvas(modifier = Modifier.size(300.dp)){
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(size.width*1/6, 0f),
            end = Offset(size.width*1/6, size.height)
        )
    }
}
@Composable
fun WinverticalLine2(){
    Canvas(modifier = Modifier.size(300.dp)){
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(size.width*3/6, 0f),
            end = Offset(size.width*3/6, size.height)
        )
    }
}
@Composable
fun WinverticalLine3(){
    Canvas(modifier = Modifier.size(300.dp)){
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(size.width*5/6, 0f),
            end = Offset(size.width*5/6, size.height)
        )
    }
}
@Composable
fun WinDiagonalLine1(){
    Canvas(modifier = Modifier.size(300.dp)){
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(0f, 0f),
            end = Offset(size.width, size.height)
        )
    }
}
@Composable
fun WinDiagonalLine2(){
    Canvas(modifier = Modifier.size(300.dp)){
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(0f, size.height),
            end = Offset(size.width,0f )
        )
    }
}

@Composable
fun drawVictoryLine(
    state: GameState
){
    when(state.victoryType){
        VictoryType.HORIZONTAL1 -> WinHorizontalLine1()
        VictoryType.HORIZONTAL2 -> WinHorizontalLine2()
        VictoryType.HORIZONTAL3 -> WinHorizontalLine3()
        VictoryType.VERTICAL1 -> WinverticalLine1()
        VictoryType.VERTICAL2 -> WinverticalLine2()
        VictoryType.VERTICAL3 -> WinverticalLine3()
        VictoryType.DIAGONAL1 -> WinDiagonalLine1()
        VictoryType.DIAGONAL2 -> WinDiagonalLine2()
        VictoryType.NONE -> {}
    }
}

@Preview
@Composable
fun Prev(){
    GameScreen(viewModel = GameViewModel())
}