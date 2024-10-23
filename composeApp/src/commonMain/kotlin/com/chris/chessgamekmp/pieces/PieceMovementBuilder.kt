package com.chris.chessgamekmp.pieces

import androidx.compose.ui.unit.IntOffset
import com.chris.chessgame.pieces.Piece
import com.chris.chessgame.pieces.getLMoves

fun Piece.getPieceMoves(

    pieces: List<Piece>,
    block: PieceMovementBuilder.() -> Unit
): Set<IntOffset> {
    val builder = PieceMovementBuilder(
        piece = this,
        pieces = pieces
    )
    builder.block()
    return builder.build()
}

class PieceMovementBuilder(
    private val piece: Piece,
    private val pieces: List<Piece>
) {
    private val moves = mutableSetOf<IntOffset>()

    fun straightMoves(

        maxMovement: Int = 7,
        canCapture: Boolean = true,
        captureOnly: Boolean = false
    ) {
        StraightMovement.entries.forEach { movement ->
            straightMoves(
                movement = movement,
                maxMovement = maxMovement,
                canCapture = canCapture,
                captureOnly = captureOnly
            )
        }

    }

    fun straightMoves(
        movement: StraightMovement,
        maxMovement: Int = 7,
        canCapture: Boolean = true,
        captureOnly: Boolean = false
    ) {
        moves.addAll(
            piece.getStraightMoves(
                pieces = pieces,
                movement = movement,
                maxMovement = maxMovement,
                canCapture = canCapture,
                captureOnly = captureOnly
            )
        )
    }

    fun diagonalMoves(
        maxMovement: Int = 7,
        canCapture: Boolean = true,
        captureOnly: Boolean = false
    ) {
        DiagonalMovement.entries.forEach { movement ->
            diagonalMoves(
                movement = movement,
                maxMovement = maxMovement,
                canCapture = canCapture,
                captureOnly = captureOnly
            )
        }
    }

    fun diagonalMoves(
        movement: DiagonalMovement,
        maxMovement: Int = 7,
        canCapture: Boolean = true,
        captureOnly: Boolean = false
    ) {
        moves.addAll(
            piece.getDiagonalMoves(
                pieces = pieces,
                movement = movement,
                maxMovement = maxMovement,
                canCapture = canCapture,
                captureOnly = captureOnly
            )
        )
    }

    fun getLMoves() {
        moves.addAll(
            piece.getLMoves(
                pieces
            )
        )
    }

    fun build(): Set<IntOffset> = moves.toSet()
}