package com.chris.chessgame.pieces

import androidx.compose.ui.unit.IntOffset
import chessgame.composeapp.generated.resources.Res
import chessgame.composeapp.generated.resources.pawn_black
import chessgame.composeapp.generated.resources.pawn_white
import com.chris.chessgamekmp.pieces.DiagonalMovement
import com.chris.chessgamekmp.pieces.StraightMovement
import com.chris.chessgamekmp.pieces.getPieceMoves


import org.jetbrains.compose.resources.DrawableResource

class Pawn(
    override val color: Piece.Color,
    override var position: IntOffset
) : Piece {
    override val drawable: DrawableResource =
        if (color.isWhite) Res.drawable.pawn_white else
            Res.drawable.pawn_black


    override fun getAvailableMoves(pieces: List<Piece>): Set<IntOffset> {
        val isFirstMove =
            position.y == 2 && color.isWhite ||
                    position.y == 7 && color.isBlack

        return getPieceMoves(pieces) {
            straightMoves(
                movement = if (color.isWhite) StraightMovement.Up else StraightMovement.Down,
                maxMovement = if (isFirstMove) 2 else 1,
                canCapture = false
            )

            diagonalMoves(
                movement = if (color.isWhite) DiagonalMovement.UpRight else DiagonalMovement.DownRight,
                maxMovement = 1,
                captureOnly = true
            )

            diagonalMoves(
                movement = if (color.isWhite) DiagonalMovement.UpLeft else DiagonalMovement.DownLeft,
                maxMovement = 1,
                captureOnly = true
            )
        }

//        return getStraightMoves(
//            pieces = pieces,
//            movement = if (color.isWhite) StraightMovement.Up else StraightMovement.Down,
//            maxMovement = if (isFirstMove) 2 else 1,
//            canCapture = false
//        ) + getDiagonalMoves(
//            pieces = pieces,
//            movement = if (color.isWhite) DiagonalMovement.UpRight else DiagonalMovement.DownRight,
//            maxMovement = 1,
//            captureOnly = true
//        ) + getDiagonalMoves(
//            pieces = pieces,
//            movement = if (color.isWhite) DiagonalMovement.UpLeft else DiagonalMovement.DownLeft,
//            maxMovement = 1,
//            captureOnly = true
//            )
    }
}