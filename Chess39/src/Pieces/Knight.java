/**
 * @author Al Manrique and Daniel Alvarado
 */
package Pieces;

import java.util.*;

import Board.Square;

public class Knight extends Piece {

	public Knight(boolean white) {
		super(white);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method will add all the legal moves of the current location of this
	 * piece to an arraylist that will be traversed to see if the move is allowed or
	 * not
	 * 
	 * @param board is the chess board
	 * @param x     the x coordinate
	 * @param y     the y coordinate
	 * @return returns the lists of all possible moves that is allowed
	 */
	@Override
	public ArrayList<Square> getLegalMoves(Square[][] board, int Startx, int Starty) {
		// TODO Auto-generated method stub

		ArrayList<Square> result = new ArrayList<Square>();

		if (inRange(Startx - 1, Starty - 2)) {
			result.add(board[Startx - 1][Starty - 2]);
		}
		if (inRange(Startx - 2, Starty - 1)) {
			result.add(board[Startx - 2][Starty - 1]);
		}
		if (inRange(Startx - 2, Starty + 1)) {
			result.add(board[Startx - 2][Starty + 1]);
		}
		if (inRange(Startx - 1, Starty + 2)) {
			result.add(board[Startx - 1][Starty + 2]);
		}
		if (inRange(Startx + 1, Starty + 2)) {
			result.add(board[Startx + 1][Starty + 2]);
		}
		if (inRange(Startx + 2, Starty + 1)) {
			result.add(board[Startx + 2][Starty + 1]);
		}
		if (inRange(Startx + 2, Starty - 1)) {
			result.add(board[Startx + 2][Starty - 1]);
		}
		if (inRange(Startx + 1, Starty - 2)) {
			result.add(board[Startx + 1][Starty - 2]);

		}
		return result;
	}
}
