package com.chris.chessgamekmp.pieces

import androidx.compose.ui.unit.IntOffset
import chessgame.composeapp.generated.resources.Res
import chessgame.composeapp.generated.resources.bishop_black
import chessgame.composeapp.generated.resources.bishop_white
import chessgame.composeapp.generated.resources.queen_black
import chessgame.composeapp.generated.resources.queen_white
import chessgame.composeapp.generated.resources.rook_black
import chessgame.composeapp.generated.resources.rook_white
import com.chris.chessgame.pieces.Piece
import org.jetbrains.compose.resources.DrawableResource

class Queen (
    override val color: Piece.Color,
    override var position: IntOffset
): Piece {
    override val drawable: DrawableResource =
        if (color.isWhite)
            Res.drawable.queen_white else Res.drawable.queen_black

    override fun getAvailableMoves(pieces: List<Piece>): Set<IntOffset> =
        getPieceMoves(
            pieces
        ) {
            straightMoves()
            diagonalMoves()}

}