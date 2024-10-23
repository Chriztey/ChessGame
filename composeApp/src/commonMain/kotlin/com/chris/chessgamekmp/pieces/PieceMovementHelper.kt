package com.chris.chessgame.pieces

import androidx.compose.ui.unit.IntOffset
import com.chris.chessgame.board.BoardXCoordinates
import com.chris.chessgame.board.BoardYCoordinates




fun Piece.getMoves(
    pieces: List<Piece>,
    getPosition: (Int) -> IntOffset,
    maxMovement: Int,
    canCapture: Boolean = true,
    captureOnly: Boolean
) : Set<IntOffset> {

    val moves = mutableSetOf<IntOffset>()

    for (i in 1 .. maxMovement) {

        val targetPosition = getPosition(i)

        if (targetPosition.x !in BoardXCoordinates || targetPosition.y !in BoardYCoordinates)
            break

        val targetPiece = pieces.find { it.position == targetPosition }

        if (targetPiece != null) {
            if (targetPiece.color != this.color && canCapture) {
                moves.add(targetPosition)
            }
            break
        } else if (captureOnly) {
            break
        } else {
            moves.add(targetPosition)
        }

    }

    return moves

}

fun Piece.getLMoves(
    pieces: List<Piece>
) : Set<IntOffset> {
    val moves = mutableSetOf<IntOffset>()

    val offsets = listOf (
        IntOffset(-1,-2),
        IntOffset(1,-2),
        IntOffset(-2,-1),
        IntOffset(2,-1),
        IntOffset(-2,1),
        IntOffset(2,1),
        IntOffset(-1,2),
        IntOffset(1,2)
    )

    for (offset in offsets) {
        val targetPosition = position + offset

        if (targetPosition.x !in BoardXCoordinates || targetPosition.y !in BoardYCoordinates)
            continue

        val targetPiece = pieces.find { it.position == targetPosition }
        if (targetPiece == null || targetPiece.color != this.color) {
            moves.add(targetPosition)
        }

    }

    return moves
}