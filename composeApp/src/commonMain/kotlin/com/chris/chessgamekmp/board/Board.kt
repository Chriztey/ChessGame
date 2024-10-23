package com.chris.chessgamekmp.board

import androidx.compose.runtime.clearCompositionErrors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntOffset
import com.chris.chessgame.pieces.Piece

class Board {
    private val _pieces = mutableStateListOf<Piece>()
    val pieces get() = _pieces.toList()

    var selectedPiece by mutableStateOf<Piece?>(null)
        private set

    var selectedPieceMoves by mutableStateOf(emptySet<IntOffset>())
        private set

    var playerTurn by mutableStateOf(Piece.Color.White)

    /**
     * User events
     */

    fun selectPiece(piece: Piece) {
        if (piece.color != playerTurn)
            return
        if (piece == selectedPiece) {
            clearSelection()
        } else {
            selectedPiece = piece
            selectedPieceMoves = piece.getAvailableMoves(pieces = pieces)
        }
    }

    fun moveSelectedPiece(x: Int, y: Int) {
        selectedPiece?.let { piece ->

            if (!isAvailableMove(x = x, y = y))
                return

            if (piece.color != playerTurn)
                return

            movePiece(
                piece = piece,
                position = IntOffset(x,y)
            )

            clearSelection()

            switchPlayerTurn()

        }
    }

    fun isAvailableMove(x: Int, y: Int) : Boolean =
        selectedPieceMoves.any {it.x == x && it.y == y}



    /**
     * Private Method
     */

    private fun movePiece (
        piece: Piece,
        position: IntOffset
    ) {
        val targetPiece = pieces.find {it.position == position }

        if (targetPiece != null)
            removePiece(targetPiece)

        piece.position = position

    }

    private fun removePiece(piece: Piece) {
        _pieces.remove(piece)
    }

    private fun clearSelection() {
        selectedPiece = null
        selectedPieceMoves = emptySet()
    }

    private fun switchPlayerTurn() {
        playerTurn =
            if (playerTurn.isWhite)
                Piece.Color.Black else Piece.Color.White
    }


}