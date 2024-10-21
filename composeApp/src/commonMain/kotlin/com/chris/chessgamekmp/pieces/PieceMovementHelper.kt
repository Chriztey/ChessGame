package com.chris.chessgame.pieces

import androidx.compose.ui.unit.IntOffset


enum class StraightMovement {
    Up,
    Down,
    Left,
    Right
}

fun Piece.getStraightMoves(
    pieces: List<Piece>,
    movement: StraightMovement,
    maxMovement: Int = 7,
    canCapture: Boolean = true
) : Set<IntOffset> {
    return getMoves(
        pieces = pieces,
        getPosition = {
            when (movement) {
                StraightMovement.Up ->
                    IntOffset(
                        x = position.x,
                        y = position.y + it
                    )

                StraightMovement.Down ->
                    IntOffset(
                        x = position.x,
                        y = position.y - it
                    )

                StraightMovement.Left ->
                    IntOffset(
                        x = position.x - it,
                        y = position.y
                    )

                StraightMovement.Right ->
                    IntOffset(
                        x = position.x + it,
                        y = position.y
                    )
            }
        },
        maxMovement = maxMovement,
        canCapture = canCapture
    )
}

fun Piece.getMoves(
    pieces: List<Piece>,
    getPosition: (Int) -> IntOffset,
    maxMovement: Int,
    canCapture: Boolean = true
) : Set<IntOffset> {

    val moves = mutableSetOf<IntOffset>()

    for (i in 1 .. maxMovement) {

        val targetPosition = getPosition(i)
        val targetPiece = pieces.find { it.position == targetPosition }

        if (targetPiece != null) {
            if (targetPiece.color != this.color && canCapture) {
                moves.add(targetPosition)
            }
            break
        } else {
            moves.add(targetPosition)
        }

    }

    return moves

}