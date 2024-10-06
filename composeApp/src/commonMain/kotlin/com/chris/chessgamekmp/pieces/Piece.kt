package com.chris.chessgame.pieces

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import org.jetbrains.compose.resources.DrawableResource

interface Piece {

    val color: Color

    enum class Color {
        White,
        Black;

        val isWhite: Boolean
            get() = this == White


        val isBlack: Boolean
            get() = this == Black

    }

    val drawable: DrawableResource
    val position: IntOffset

    fun getAvailableMoves(pieces : List<Piece>): Set<IntOffset>

}