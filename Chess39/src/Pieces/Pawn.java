/**
 * @author Al Manrique and Daniel Alvarado
 */
package Pieces;

import java.util.ArrayList;

import Board.Square;

public class Pawn extends Piece {
	public int moveCounter;

	public Pawn(boolean white) {
		super(white);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * This method will add all the legal moves of the current location of this
	 * piece to an arraylist that will be traversed to see if the move is allowed or
	 * not
	 * 
	 * @param board is the chess board
	 * @param x     the x coordinate
	 * @param y     the y coordinate
	 * @return returns the lists of all possible moves that is allowed
	 * 
	 */
	@Override
	public ArrayList<Square> getLegalMoves(Square[][] board, int x, int y) {
		// TODO Auto-generated method stub
		ArrayList<Square> result = new ArrayList<Square>();
		if (white == true) {
			if (originalPos == true) {
				result.add(board[x - 2][y]);
			}
			if (board[x - 1][y].currPiece == null) {
				result.add(board[x - 1][y]);
			}
			if (inRange(x - 1, y + 1) && board[x - 1][y + 1].currPiece != null
					&& board[x - 1][y + 1].currPiece.white == false) {
				result.add(board[x - 1][y + 1]);
			}
			if (inRange(x - 1, y - 1) && board[x - 1][y - 1].currPiece != null
					&& board[x - 1][y - 1].currPiece.white == false) {
				result.add(board[x - 1][y - 1]);
			}
		} else {
			if (originalPos == true) {

				result.add(board[x + 2][y]);
			}
			if (board[x + 1][y].currPiece == null) {
				result.add(board[x + 1][y]);
			}
			if (inRange(x + 1, y - 1) && board[x + 1][y - 1].currPiece != null
					&& board[x + 1][y - 1].currPiece.white == true) {
				result.add(board[x + 1][y - 1]);
			}
			if (inRange(x + 1, y + 1) && board[x + 1][y + 1].currPiece != null
					&& board[x + 1][y + 1].currPiece.white == true) {
				result.add(board[x + 1][y + 1]);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param board is the chess board
	 * @param x     the x coordinate
	 * @param y     the y coordinate
	 * @return returns the lists of all possible moves that is allowed This method
	 *         checks if the pawn can attack in a diagonal move, first it checks if
	 *         there is a piece there or not, and adds the moves to the legal moves
	 *         array.
	 */
	public ArrayList<Square> attackPawn(Square board[][], int x, int y) {
		ArrayList<Square> result = new ArrayList<>();
		// System.out.print(white);
		if (white == true) {
			if (inRange(x - 1, y + 1)) {
				result.add(board[x - 1][y + 1]);
			}
			if (inRange(x - 1, y - 1)) {
				result.add(board[x - 1][y - 1]);
			}
		}
		if (white == false) {
			if (inRange(x + 1, y - 1)) {
				result.add(board[x + 1][y - 1]);
			}
			if (inRange(x + 1, y + 1)) {
				result.add(board[x + 1][y + 1]);
			}
		}

		return result;
	}
}
