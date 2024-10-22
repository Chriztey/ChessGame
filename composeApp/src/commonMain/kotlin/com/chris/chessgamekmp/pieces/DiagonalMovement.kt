package com.chris.chessgamekmp.pieces

import androidx.compose.ui.unit.IntOffset
import com.chris.chessgame.pieces.Piece
import com.chris.chessgame.pieces.getMoves

enum class DiagonalMovement {
    UpLeft,
    UpRight,
    DownLeft,
    DownRight
}

fun Piece.getDiagonalMoves(
    pieces: List<Piece>,
    movement: DiagonalMovement,
    maxMovement: Int = 7,
    canCapture: Boolean = true,
    captureOnly: Boolean = false
) : Set<IntOffset> {
    return getMoves(
        pieces = pieces,
        getPosition = {
            when (movement) {
                DiagonalMovement.UpLeft ->
                    IntOffset(
                        x = position.x - it,
                        y = position.y + it
                    )

                DiagonalMovement.UpRight ->
                    IntOffset(
                        x = position.x + it,
                        y = position.y + it
                    )

                DiagonalMovement.DownLeft ->
                    IntOffset(
                        x = position.x - it,
                        y = position.y - it
                    )

                DiagonalMovement.DownRight ->
                    IntOffset(
                        x = position.x + it,
                        y = position.y - it
                    )
            }
        },
        maxMovement = maxMovement,
        canCapture = canCapture,
        captureOnly = captureOnly
    )
}