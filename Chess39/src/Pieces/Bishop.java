/**
 * @author Al Manrique and Daniel Alvarado
 */
package Pieces;

import java.util.ArrayList;

import Board.Square;

public class Bishop extends Piece {

	public Bishop(boolean white) {
		super(white);

	}

	/**
	 * This method will add all the legal moves of the current location of this piece to an arraylist that will be traversed to see if the move is allowed or not
	 * The function  is made to check if it will go diagonally in all directions, top left, top right, bottom right, bottom left
	 */
	public ArrayList<Square> getLegalMoves(Square[][] board, int x, int y) {
		ArrayList<Square> result = new ArrayList<Square>();
		int i = x - 1;
		int j = y + 1;
		int buffer = y + 1;
		Square start = board[x][y];// original square
		Square curr = null;
		// System.out.print(i + "," + j);
	
		for (; i > -1; i = i - 1) {
			
			j = buffer;
			for (; j < 8; j++) {
			
		

				if (inRange(i, j)) {
					curr = board[i][j];
					if (curr.currPiece != null && !(start.currPiece.white == curr.currPiece.white) && inRange(i, j)) { // Pieces
																														// are
																														// opposite
																														// side.
						result.add(curr);
						i = -1;
						j = 8;// break out of double loop

					} else if (curr.currPiece != null && start.currPiece.white == curr.currPiece.white
							&& inRange(i, j)) {
						// they are the same side

						i = -1;
						j = 8;
					} else {

						result.add(curr);
						buffer = j + 1;
						j = 8;
					}
				}
			}

		}
		// a1 f4 might cause a bug. dont think so
	
		buffer = y - 1;
		i = x - 1;
		j = y - 1;
		for (; i > -1; i = i - 1) {
			// System.out.println(i);
			j = buffer;
			for (; j > -1; j = j - 1) {

				if (inRange(i, j)) {
					curr = board[i][j];
					if (curr.currPiece != null && !(start.currPiece.white == curr.currPiece.white) && inRange(i, j)) { // Pieces
																														// are
																														// opposite
																														// //
																														// side.
						result.add(curr);
						i = -1;
						j = -1;// break out of double loop

					} else if (curr.currPiece != null && start.currPiece.white == curr.currPiece.white
							&& inRange(i, j)) {
						// they are the same side

						i = -1;
						j = -1;
					} else {

						result.add(curr);
						buffer = j - 1;
						j = 0;
					}
				}
			}

		}

		buffer = y + 1;
		i = x + 1;
		j = y + 1;
		for (; i < 8; i = i + 1) {
			// System.out.println(i);
			j = buffer;
			for (; j < 8; j = j + 1) {
				

				if (inRange(i, j)) {
					curr = board[i][j];
					if (curr.currPiece != null && !(start.currPiece.white == curr.currPiece.white) && inRange(i, j)) { // Pieces
																														// are
																														// opposite
																														// //
																														// side.
						result.add(curr);
						i = 8;
						j = 8;// break out of double loop
						
					} else if (curr.currPiece != null && start.currPiece.white == curr.currPiece.white
							&& inRange(i, j)) {
						// they are the same side
					
						i = 8;
						j = 8;
					} else {

						result.add(curr);
						buffer = j + 1;
						j = 8;
					}
				}
			}

		}
	
		buffer = y - 1;
		i = x + 1;
		j = y - 1;
		for (; i < 8; i = i + 1) {
			// System.out.println(i);
			j = buffer;
			for (; j > -1; j = j - 1) {

				if (inRange(i, j)) {
					curr = board[i][j];
					if (curr.currPiece != null && !(start.currPiece.white == curr.currPiece.white) && inRange(i, j)) { // Pieces
																														// are
																														// opposite
																														// side.
						result.add(curr);
						i = 8;
						j = -1;// break out of double loop

					} else if (curr.currPiece != null && start.currPiece.white == curr.currPiece.white
							&& inRange(i, j)) {
						// they are the same side

						i = 8;
						j = -1;
					} else {

						result.add(curr);
						buffer = j - 1;
						j = -1;
					}
				}
			}

		}

		return result;
	}

}
