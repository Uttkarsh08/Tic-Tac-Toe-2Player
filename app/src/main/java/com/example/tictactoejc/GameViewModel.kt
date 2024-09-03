package com.example.tictactoejc

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key.Companion.D
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    var state by mutableStateOf(GameState())

    val boardItems:  MutableMap<Int, BoardCellValue> = mutableMapOf(
        1 to BoardCellValue.NONE,
        2 to BoardCellValue.NONE,
        3 to BoardCellValue.NONE,
        4 to BoardCellValue.NONE,
        5 to BoardCellValue.NONE,
        6 to BoardCellValue.NONE,
        7 to BoardCellValue.NONE,
        8 to BoardCellValue.NONE,
        9 to BoardCellValue.NONE,
    )

    fun onAction(action: UserAction){
        when(action){
            is UserAction.BoardTapped -> {
                addValueToBoard(action.cellNo)

            }
            UserAction.PlayAgainButtonClicked -> {
                gameReset()
            }
        }
    }

    private fun gameReset() {
        boardItems.forEach{(i, _) ->
            boardItems[i] = BoardCellValue.NONE
        }
        state= state.copy(
            hintText = "Player 'O' turn",
            currentTurn = BoardCellValue.CIRCLE,
            victoryType = VictoryType.NONE,
            hasWon = false
        )
    }

    private fun addValueToBoard(cellNo: Int) {
        if(boardItems[cellNo] != BoardCellValue.NONE){
            return
        }
        if(state.currentTurn == BoardCellValue.CIRCLE){
            boardItems[cellNo] = BoardCellValue.CIRCLE
            if(checkForVictory(BoardCellValue.CIRCLE)){
                state = state.copy(
                    hintText ="Game Ended",
                    playerWon = "Player 'O' Won",
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            }else if(isBoardFull()){
                state = state.copy(
                    hintText = "Game Draw",
                )
            }else{
                state = state.copy(
                    hintText = "Player 'X' turn",
                    currentTurn = BoardCellValue.CROSS
                )
            }

        }else if(state.currentTurn == BoardCellValue.CROSS){
            boardItems[cellNo] = BoardCellValue.CROSS
            if(checkForVictory(BoardCellValue.CROSS)){
                state = state.copy(
                    hintText ="Game Ended",
                    playerWon = "Player 'X' Won",
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            }else if(isBoardFull()){
                state = state.copy(
                    hintText = "Game Draw",
                )
            }else{
                state = state.copy(
                    hintText = "Player 'O' turn",
                    currentTurn = BoardCellValue.CIRCLE
                )
            }

        }
    }

    private fun checkForVictory(boardvalue: BoardCellValue): Boolean {
        when{
            boardItems[1] == boardvalue && boardItems[2] == boardvalue && boardItems[3] == boardvalue ->{
                state = state.copy(
                    victoryType = VictoryType.HORIZONTAL1
                )
                return true
            }
            boardItems[4] == boardvalue && boardItems[5] == boardvalue && boardItems[6] == boardvalue ->{
                state = state.copy(
                    victoryType = VictoryType.HORIZONTAL2
                )
                return true
            }
            boardItems[7] == boardvalue && boardItems[8] == boardvalue && boardItems[9] == boardvalue ->{
                state = state.copy(
                    victoryType = VictoryType.HORIZONTAL3
                )
                return true
            }
            boardItems[1] == boardvalue && boardItems[4] == boardvalue && boardItems[7] == boardvalue ->{
                state = state.copy(
                    victoryType = VictoryType.VERTICAL1
                )
                return true
            }
            boardItems[2] == boardvalue && boardItems[5] == boardvalue && boardItems[8] == boardvalue ->{
                state = state.copy(
                    victoryType = VictoryType.VERTICAL2
                )
                return true
            }
            boardItems[3] == boardvalue && boardItems[6] == boardvalue && boardItems[9] == boardvalue ->{
                state = state.copy(
                    victoryType = VictoryType.VERTICAL3
                )
                return true
            }
            boardItems[1] == boardvalue && boardItems[5] == boardvalue && boardItems[9] == boardvalue ->{
                state = state.copy(
                    victoryType = VictoryType.DIAGONAL1
                )
                return true
            }
            boardItems[3] == boardvalue && boardItems[5] == boardvalue && boardItems[7] == boardvalue ->{
                state = state.copy(
                    victoryType = VictoryType.DIAGONAL2
                )
                return true
            }
            else ->{
                return false
            }

        }
    }

    private fun isBoardFull(): Boolean {
        if(boardItems.containsValue(BoardCellValue.NONE)) return false
        return true
    }
}