/**
 * @author Al Manrique and Daniel Alvarado
 */
package Pieces;

import java.util.ArrayList;

import Board.Square;

public class King extends Piece {

	public King(boolean white) {
		super(white);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This class is created to set a flag to see if castling is allowed or not. The
	 * a variable is the flag to castle for white and the b variable is the flag to
	 * castle for black
	 *
	 */
	public static class global {
		public static int a = 0;
		public static int b = 0;
	}

	@Override
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
	
	public ArrayList<Square> getLegalMoves(Square[][] board, int x, int y) {
		// TODO Auto-generated method stub
		ArrayList<Square> result = new ArrayList<Square>();

		/**
		 * This checks if white is allowed to castle or not. It checks the global class
		 * and sees if a or b has been set to 0, if not, it will check if rook and king
		 * are its in original spot. If this is both true, then it will add this move to
		 * the list of possible moves.
		 */
		// if white can castle
		if (white == true) {
			if (global.a == 0 && ((board[7][0].currPiece instanceof Rook) || (board[7][7].currPiece instanceof Rook))) {
				// System.out.println("It entered here");
				if (board[7][4].currPiece instanceof King) {
					if (board[7][5].currPiece == null && board[7][6].currPiece == null) {
						result.add(board[x][y + 2]); // king can move to [7][6] to castle
					}
					if (board[7][2].currPiece == null && board[7][3].currPiece == null
							&& board[7][1].currPiece == null) {
						result.add(board[x][y - 2]); // king can move to [7][6] to castle
					}

				}
			}
		}

		// if black can castle
		if (white == false) {
			if (global.b == 0 && ((board[0][0].currPiece instanceof Rook) || (board[0][7].currPiece instanceof Rook))) {
				if (board[0][4].currPiece instanceof King) {
					if (board[0][5].currPiece == null && board[0][6].currPiece == null) {
						result.add(board[x][y + 2]); // king can move to [0][6] to castle
					}
					if (board[0][2].currPiece == null && board[0][3].currPiece == null
							&& board[0][1].currPiece == null) {
						result.add(board[x][y - 2]); // king can move to [0][2] to castle
					}
				}
			}
		}
		/**
		 * This is for white king to move in all possible directions and after the else
		 * statement is for the black king to move in all possible directions
		 */
		if (white == true) {
			if (inRange(x - 1, y) && (board[x - 1][y].currPiece == null || board[x - 1][y].currPiece.white == false)) { // move
																														// up

				result.add(board[x - 1][y]);
			}
			if (inRange(x + 1, y) && (board[x + 1][y].currPiece == null || board[x + 1][y].currPiece.white == false)) { // move
																														// down
				result.add(board[x + 1][y]);
			}
			if (inRange(x, y - 1) && (board[x][y - 1].currPiece == null || board[x][y - 1].currPiece.white == false)) { // move
																														// left
				result.add(board[x][y - 1]);
			}
			if (inRange(x, y + 1) && (board[x][y + 1].currPiece == null || board[x][y + 1].currPiece.white == false)) { // move
																														// right
				result.add(board[x][y + 1]);
			}
			if (inRange(x + 1, y + 1)
					&& (board[x + 1][y + 1].currPiece == null || board[x + 1][y + 1].currPiece.white == false)) { // move
																													// down
																													// right
				result.add(board[x + 1][y + 1]);
			}
			if (inRange(x + 1, y - 1)
					&& (board[x + 1][y - 1].currPiece == null || board[x + 1][y - 1].currPiece.white == false)) { // move
																													// down
																													// left
				result.add(board[x + 1][y - 1]);
			}
			if (inRange(x - 1, y - 1)
					&& (board[x - 1][y - 1].currPiece == null || board[x - 1][y - 1].currPiece.white == false)) { // move
																													// up
																													// left
				result.add(board[x - 1][y - 1]);
			}
			if (inRange(x - 1, y + 1)
					&& (board[x - 1][y + 1].currPiece == null || board[x - 1][y + 1].currPiece.white == false)) { // move
																													// up
																													// right
				result.add(board[x - 1][y + 1]);
			}

		} else {

			if (inRange(x - 1, y) && (board[x - 1][y].currPiece == null || board[x - 1][y].currPiece.white == true)) { // move
																														// up

				result.add(board[x - 1][y]);
			}
			if (inRange(x + 1, y) && (board[x + 1][y].currPiece == null || board[x + 1][y].currPiece.white == true)) { // move
																														// down
				result.add(board[x + 1][y]);
			}
			if (inRange(x, y - 1) && (board[x][y - 1].currPiece == null || board[x][y - 1].currPiece.white == true)) { // move
																														// left
				result.add(board[x][y - 1]);
			}
			if (inRange(x, y + 1) && (board[x][y + 1].currPiece == null || board[x][y + 1].currPiece.white == true)) { // move
																														// right
				result.add(board[x][y + 1]);
			}
			if (inRange(x + 1, y + 1)
					&& (board[x + 1][y + 1].currPiece == null || board[x + 1][y + 1].currPiece.white == true)) { // move
																													// down
																													// right
				result.add(board[x + 1][y + 1]);
			}
			if (inRange(x + 1, y - 1)
					&& (board[x + 1][y - 1].currPiece == null || board[x + 1][y - 1].currPiece.white == true)) { // move
																													// down
																													// left
				result.add(board[x + 1][y - 1]);
			}
			if (inRange(x - 1, y - 1)
					&& (board[x - 1][y - 1].currPiece == null || board[x - 1][y - 1].currPiece.white == true)) { // move
																													// up
																													// left
				result.add(board[x - 1][y - 1]);
			}
			if (inRange(x - 1, y + 1)
					&& (board[x - 1][y + 1].currPiece == null || board[x - 1][y + 1].currPiece.white == true)) { // move
																													// up
																													// right
				result.add(board[x - 1][y + 1]);
			}
		}

		return result;

	}

}
