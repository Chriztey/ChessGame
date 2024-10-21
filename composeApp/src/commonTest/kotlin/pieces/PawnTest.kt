package pieces

import androidx.compose.ui.unit.IntOffset
import com.chris.chessgame.board.BoardXCoordinates
import com.chris.chessgame.board.BoardYCoordinates
import com.chris.chessgame.pieces.Pawn
import com.chris.chessgame.pieces.Piece
import com.chris.chessgame.pieces.getMoves
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * 1. if is first move, can move forward 2 times
 * 2. if not first move, can only move forward 1 times
 * 3. we can capture enemies in diagonal forward
 */

class PawnTest {
    private val demoWhitePiece: Piece =
        Pawn(
            color = Piece.Color.White,
            position = IntOffset(
                x = BoardXCoordinates.first(),
                y = BoardYCoordinates.first()
            )
        )

    private val demoBlackPiece: Piece =
        Pawn(
            color = Piece.Color.Black,
            position = IntOffset(
                x = BoardXCoordinates.first(),
                y = BoardYCoordinates.first()
            )
        )


    @Test
    fun testFirstMoveForward() {
        val pawn = Pawn(
            color = Piece.Color.White,
            position = IntOffset(
                x = 'A'.code,
                y = 2
            )
        )

        val moves = pawn.getAvailableMoves(listOf(pawn))

        assertTrue (moves.isNotEmpty())
        assertEquals(
            IntOffset(
                x = 'A'.code,
                y = 3
            ),
            moves.first()
        )

    }

    @Test
    fun secondFirstMoveForwardTrue() {
        val pawn = Pawn(
            color = Piece.Color.White,
            position = IntOffset(
                x = 'A'.code,
                y = 2
            )
        )

        val moves = pawn.getAvailableMoves(listOf(pawn)).toList()

        assertEquals(2, moves.size)

        assertTrue (moves.isNotEmpty())

        assertTrue(
            IntOffset(
                x = 'A'.code,
                y = 3
            ) in moves
        )

        assertTrue(
            IntOffset(
                x = 'A'.code,
                y = 4
            ) in moves
        )

    }

    @Test
    fun secondFirstMoveForwardFalse() {
        val pawn = Pawn(
            color = Piece.Color.White,
            position = IntOffset(
                x = 'A'.code,
                y = 3
            )
        )

        val moves = pawn.getAvailableMoves(listOf(pawn)).toList()

        assertEquals(1, moves.size)

        assertTrue (moves.isNotEmpty())
        assertTrue(
            IntOffset(
                x = 'A'.code,
                y = 4
            ) in moves
        )
    }

    @Test
    fun testNoPossibleMoves() {
        val pawn = Pawn(
            color = Piece.Color.White,
            position = IntOffset(
                x = 'A'.code,
                y = 3
            )
        )

        demoBlackPiece.position = IntOffset(
            x = 'A'.code,
            y = 4
        )

        val pieces = listOf(
            pawn,
            demoBlackPiece
        )

        val moves = pawn.getAvailableMoves(pieces)

        assertTrue (moves.isEmpty())
    }

}