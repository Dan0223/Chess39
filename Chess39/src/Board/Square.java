/**
 * @author Al Manrique and Daniel Alvarado
 * 
 */
package Board;

import Pieces.Piece;

/**
 * 
 * This is an abstract class of the chess board where all the pieces are created
 * and updated. X is the x coordinate y is the y coordinate Symbol is the symbol
 * of the letters on the board currPiece is the current piece that is being
 * moved Occupied is a flag to check if the place on the board is being occupied
 * by another piece
 *
 */
public class Square {
	int x;
	int y;
	String Symbol;
	public Piece currPiece;
	public boolean Occupied;

	/**
	 * 
	 * @param x is the x coordinate
	 * @param y is the y coordinate
	 */
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @return the symbol of the piece
	 */
	public String getSymbol() {
		return Symbol;
	}

	/**
	 * 
	 * @param symbol the string format of the piece
	 */
	public void setSymbol(String symbol) {
		Symbol = symbol;
	}
}