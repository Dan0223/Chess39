/**
 * @author Al Manrique and Daniel Alvarado
 */
package Pieces;
import java.util.*;
import Board.Square;
/**
 * 
 * This is an abstract class with an abstract method called getLegalMoves
 * that each piece has to have implemented in.
 *
 */
public abstract class Piece {
//legal moves 
	/**
	 * Symbol - is the pieces symbol
	 * white - what side the piece is on
	 * originalPos - to know if the piece has moved
	 *  
	 */
	public String Symbol;
	public boolean white;
	public boolean originalPos;
	public Piece(boolean white) {
		this.white = white;
	}
	/**
	 * gets the pieces symbol
	 * @return the Symbol of the Piece
	 */
	public String getSymbol() {
		return Symbol;
	}
	/**
	 * sets the symbol equal to what we gave it.
	 * @param symbol the symbol that it should represent
	 */
	public void setSymbol(String symbol) {
		Symbol = symbol;
	}
	/**
	 * returns a list of Legal Moves.
	 * @param board the main board 
	 * @param x the x coordinate of the start square
	 * @param y the y coordinate of the start square
	 * @return an ArrayList of Square contianing the legal moves of the Piece
	 */
	public abstract ArrayList<Square> getLegalMoves(Square[][] board,int x,int y);

	/**
	 * checks if x and y are within the board.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return true if x and y are within the board.
	 */
	public boolean inRange(int x, int y) {
		if (x < 8 && x > -1 && y < 8 && y > -1) {
			return true;

		}
		return false;
	}

}
