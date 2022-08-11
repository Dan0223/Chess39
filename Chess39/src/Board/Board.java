/**
 * @author Al Manrique and Daniel Alvarado
 */
package Board;

import java.util.ArrayList;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import Pieces.King.global;

/**
 * 
 * This class creates the entire board when it is initially created and sets up
 * each piece to its respectful spot, and also updates the board after each
 * piece is moved. The user will also be able to check if their moves are legal
 * or not.
 */
public class Board {

	public Square[][] board = new Square[8][8];
	public boolean updateSuccess;
	public boolean inCheck;

	/**
	 * Initializes a 2d array that represents our chess board. It places the pieces
	 * in the right places.
	 * 
	 * 
	 * 
	 */
	public void boardInit() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				board[x][y] = new Square(x, y);

				board[x][y].currPiece = null;

				if (x % 2 == 0) { // black starts at an even rank then all even y should be black
					if (y % 2 != 0) {
						board[x][y].setSymbol("##");

					} else {
						board[x][y].setSymbol("  ");
					}

				} else if (x % 2 != 0) {
					if (y % 2 == 0) {
						board[x][y].setSymbol("##");
					} else {
						board[x][y].setSymbol("  ");
					}
				}

			}
		}

		for (int j = 0; j < 8; j++) {
			if (j == 0 || j == 7) {
				board[0][j].currPiece = new Rook(false);
				board[0][j].currPiece.setSymbol("bR");
				board[7][j].currPiece = new Rook(true);
				board[7][j].currPiece.setSymbol("wR");

			}
			if (j == 6 || j == 1) {
				board[0][j].currPiece = new Knight(false);
				board[0][j].currPiece.setSymbol("bN");
				board[7][j].currPiece = new Knight(true);
				board[7][j].currPiece.setSymbol("wN");
			}
			if (j == 2 || j == 5) {
				board[0][j].currPiece = new Bishop(false);
				board[0][j].currPiece.setSymbol("bB");
				board[7][j].currPiece = new Bishop(true);
				board[7][j].currPiece.setSymbol("wB");

			}
			if (j == 3) {
				board[0][j].currPiece = new Queen(false);
				board[0][j].currPiece.setSymbol("bQ");
				board[7][j].currPiece = new Queen(true);
				board[7][j].currPiece.setSymbol("wQ");
			}
			if (j == 4) {
				board[0][j].currPiece = new King(false);
				board[0][j].currPiece.setSymbol("bK");
				board[7][j].currPiece = new King(true);
				board[7][j].currPiece.setSymbol("wK");

			}
//			board[1][6].currPiece= new Pawn(true);
//			board[1][6].currPiece.setSymbol("wP");;

			// The Pawns
			board[1][j].currPiece = new Pawn(false);
			board[1][j].currPiece.setSymbol("bP");
			board[1][j].currPiece.originalPos = true;
			board[6][j].currPiece = new Pawn(true);
			board[6][j].currPiece.setSymbol("wP");
			board[6][j].currPiece.originalPos = true;

		}
//		board[6][0].currPiece = new Rook(true);
//		board[6][0].currPiece.setSymbol("wR");

	}

	/**
	 * This method prints out the board and pieces.
	 */
	public void printSquare() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (board[x][y].currPiece == null) {
					System.out.print(board[x][y].Symbol + " ");
				} else {
					System.out.print(board[x][y].currPiece.getSymbol() + " ");

				}
			}
			System.out.println(8 - x);
		}
		System.out.println(" a" + "  b" + "  c" + "  d" + "  e" + "  f" + "  g" + "  h");
	}

	/**
	 * This method will allow the board to be updated and check if the move that was
	 * inputed is legal or not. Then it will check if the move that was made is
	 * allowed or not which goes through a series of checks. Also checks if en
	 * passant is being called on any part of the board.
	 * 
	 * @param start     The start Square
	 * @param end       The destination Square
	 * @param move      The move passed in from the user
	 * @param whiteTurn who's turn it is.
	 * 
	 * 
	 */
	public void updateBoard(Square start, Square end, String move, Boolean whiteTurn) {
		// takes in coordinates of start and end.
		// check if there is a piece on that square. If there is not then error
		// if there is find all of the legal moves and if one of them matches the
		// destination tile and move the piece to that position
		// I dont think we have to return anything since the start is going to reprint
		// the board every move.
		// if the destination contains a piece that is not opposite side then error.
		// if the destination is not in the list of legal moves error.

		if (start.currPiece == null) { // no Piece in selected square
			System.out.println("Illegal move, try again");
			updateSuccess = false;
			return;
		}

		ArrayList<Square> legalMoves = start.currPiece.getLegalMoves(board, start.x, start.y);

		if (end.currPiece != null && ((end.currPiece.white == start.currPiece.white)
				|| (end.currPiece.white == false && start.currPiece.white == false))) {// if piece is trying to eat same
																						// piece.
			System.out.println("Illegal move, try again");
			updateSuccess = false;
			return;
		}

		if (start.currPiece == null) {// if player selected empty square
			System.out.println("Illegal move, try again");
			updateSuccess = false;
			return;
		}
		if (whiteTurn && start.currPiece.white == false) {
			System.out.println("Illegal move, try again");
			updateSuccess = false;
			return;
		} else if (whiteTurn == false && start.currPiece.white == true) {
			System.out.println("Illegal move, try again");
			updateSuccess = false;
			return;
		}

		// en passant
		if (start.currPiece instanceof Pawn) {
			if (whiteTurn) {// white turn
				if (inRange(3, start.y - 1) && board[3][start.y - 1].currPiece instanceof Pawn
						&& board[3][start.y - 1].currPiece.white != whiteTurn
						&& ((Pawn) board[3][start.y - 1].currPiece).moveCounter == 1) {
					if (end.x == start.x - 1 && end.y == start.y - 1) {
						board[3][start.y - 1].currPiece = null;
						board[start.x - 1][start.y - 1].currPiece = start.currPiece;
						start.currPiece = null;
						updateSuccess = true;
						return;
					}
				}

				if (inRange(3, start.y + 1) && board[3][start.y + 1].currPiece instanceof Pawn
						&& board[3][start.y + 1].currPiece.white != whiteTurn
						&& ((Pawn) board[3][start.y + 1].currPiece).moveCounter == 1) {
					if (end.x == start.x - 1 && end.y == start.y + 1) {
						board[3][start.y + 1].currPiece = null;
						board[start.x - 1][start.y + 1].currPiece = start.currPiece;
						start.currPiece = null;
						updateSuccess = true;
						return;
					}
				}
			} else { // black turn
				if (inRange(4, start.y - 1) && board[4][start.y - 1].currPiece instanceof Pawn
						&& board[4][start.y - 1].currPiece.white != whiteTurn
						&& ((Pawn) board[4][start.y - 1].currPiece).moveCounter == 1) {
					if (end.x == start.x + 1 && end.y == start.y - 1) {
						board[4][start.y - 1].currPiece = null;
						board[start.x + 1][start.y - 1].currPiece = start.currPiece;
						start.currPiece = null;
						updateSuccess = true;
						return;
					}
				}

				if (inRange(4, start.y + 1) && board[4][start.y + 1].currPiece instanceof Pawn
						&& board[4][start.y + 1].currPiece.white != whiteTurn
						&& ((Pawn) board[4][start.y + 1].currPiece).moveCounter == 1) {
					if (end.x == start.x + 1 && end.y == start.y + 1) {
						board[4][start.y + 1].currPiece = null;
						board[start.x + 1][start.y + 1].currPiece = start.currPiece;
						start.currPiece = null;
						updateSuccess = true;
						return;
					}
				}
			}
		}

		if (!legalMoves.contains(end)) {// checks if end square(destination) is in the list of Legal moves it isnt then
										// return.
			System.out.println("Illegal move, try again");
			updateSuccess = false;
			return;
		}
		if (isAttacked(end, start.currPiece.white) && start.currPiece instanceof King) {
			System.out.println("Illegal move, try again");
			updateSuccess = false;
			return;
		}

//		if (containsDraw(move)) {
//			drawAsked= true;	
//			updateSuccess = false;
//			return;
//		}
		String promo = containsPromotion(move);
		if (start.currPiece instanceof Pawn) {
			if (whiteTurn == true && end.x == 0) {
				// White promote
				if (promo.equals("N")) {
					end.currPiece = new Knight(true);
					end.currPiece.setSymbol("wN");
					start.currPiece = null;
				} else if (promo.equals("R")) {
					end.currPiece = new Rook(true);
					end.currPiece.setSymbol("wR");
					start.currPiece = null;
				} else if (promo.equals("B")) {
					end.currPiece = new Bishop(true);
					end.currPiece.setSymbol("wB");
					start.currPiece = null;
				} else if (promo.equals("Q") || promo.equals(null)) {
					end.currPiece = new Queen(true);
					end.currPiece.setSymbol("wQ");
					start.currPiece = null;
				} else {
					end.currPiece = new Queen(true);
					end.currPiece.setSymbol("wQ");
					start.currPiece = null;
				}

				updateSuccess = true;
				return;
			} else if (whiteTurn == true && end.x == 7) {
				if (promo.equals("N")) {
					end.currPiece = new Knight(false);
					end.currPiece.setSymbol("bN");
					start.currPiece = null;
				} else if (promo.equals("R")) {
					end.currPiece = new Rook(false);
					end.currPiece.setSymbol("bR");
					start.currPiece = null;
				} else if (promo.equals("B")) {
					end.currPiece = new Bishop(false);
					end.currPiece.setSymbol("bB");
					start.currPiece = null;
				} else if (promo.equals("Q")) {
					end.currPiece = new Queen(false);
					end.currPiece.setSymbol("bQ");
					start.currPiece = null;
				} else {
					end.currPiece = new Queen(true);
					end.currPiece.setSymbol("wQ");
					start.currPiece = null;
				}
				updateSuccess = true;
				return;
			}

		}

		// move king one space and check if it is attacked. If it is attacked put it
		// back to original position and print Illegal move.
		// if it is ok
		if (legalMoves.contains(board[7][6]) && end.x == 7 && end.y == 6 && global.a == 0) { // white castling king side
			if (isAttacked(board[7][6], whiteTurn)) {
				System.out.println("Illegal move, try again");
				updateSuccess = false;
				return;
			}
			board[7][7].currPiece = null;
			board[7][5].currPiece = new Rook(true);
			board[7][5].currPiece.setSymbol("wR");
			global.a = 1;
		}

		if (legalMoves.contains(board[7][2]) && end.x == 7 && end.y == 2 && global.a == 0) { // white castling queen
																								// side
			if (isAttacked(board[7][3], whiteTurn) || isAttacked(board[7][2], whiteTurn)) {
				System.out.println("Illegal move, try again");
				updateSuccess = false;
				return;
			}
			board[7][0].currPiece = null;
			board[7][3].currPiece = new Rook(true);
			board[7][3].currPiece.setSymbol("wR");
			global.a = 1;
		}

		if (legalMoves.contains(board[0][6]) && end.x == 0 && end.y == 6 && global.b == 0) { // black castling king side
			if (isAttacked(board[0][6], whiteTurn)) {
				System.out.println("Illegal move, try again");
				updateSuccess = false;
				return;
			}
			board[0][7].currPiece = null;
			board[0][5].currPiece = new Rook(false);
			board[0][5].currPiece.setSymbol("bR");
			global.b = 1;
		}

		if (legalMoves.contains(board[0][2]) && end.x == 0 && end.y == 2 && global.b == 0) { // black castling queen
																								// side
			if (isAttacked(board[0][3], whiteTurn) || isAttacked(board[0][2], whiteTurn)) {
				System.out.println("Illegal move, try again");
				updateSuccess = false;
				return;
			}
			board[0][0].currPiece = null;
			board[0][3].currPiece = new Rook(false);
			board[0][3].currPiece.setSymbol("bR");
			global.b = 1;
		}

		if (start.currPiece instanceof Pawn) {
			((Pawn) start.currPiece).moveCounter++;
			((Pawn) start.currPiece).originalPos = false;
		}

		end.currPiece = start.currPiece;
		// sets start square
		start.currPiece = null;
		
		if (isAttacked(getKing(whiteTurn), whiteTurn)) {
			System.out.println("Illegal move, try again");
			start.currPiece = end.currPiece;
			end.currPiece = null;
			updateSuccess = false;
			return;
		}
		
		
		Square kingSquare = getKing(!whiteTurn); // Square that contains the other player's king
		if (isAttacked(kingSquare, !whiteTurn)) { // if other player's king is attacked.
			ArrayList<Square> kingMoves = ((King) kingSquare.currPiece).getLegalMoves(board, kingSquare.x,
					kingSquare.y);
			boolean checkMate = false;
			for(int x = 0; x < kingMoves.size();x++) {
				if(isAttacked(kingMoves.get(x),!whiteTurn)) {
					checkMate = true;
				}else {
					checkMate= false;
					break;
					//yes
				}
				
			}
			if (checkMate) {
				printSquare();
				System.out.println("Checkmate");
				if (whiteTurn) {
					System.out.println("White Wins");
				} else {
					System.out.println("Black Wins");
				}
				System.exit(0);
			} else {
				System.out.println("Check");
			}

		}
//		if (isAttacked(getKing(whiteTurn), whiteTurn)) {
//			System.out.println("Illegal move, try again");
//			start.currPiece = end.currPiece;
//			end.currPiece = null;
//			updateSuccess = false;
//			return;
//		}

		updateSuccess = true;

	}

	/**
	 * This method checks if a square is attacked by gathering the legal moves of
	 * the opponent and seeing if the square we are checking is in one of them.
	 * 
	 * @param square The square we are checking
	 * @param turn   who's turn it is
	 * @return id square is attacked it returns true otherwise false
	 */
	public boolean isAttacked(Square square, boolean turn) {
		ArrayList<Square> result = new ArrayList<Square>();
		Piece currPiece = null;
		Square curr = null;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {

				curr = board[x][y];
				currPiece = curr.currPiece;
				if (currPiece != null && currPiece.white != turn && inRange(x, y)) {
					if (currPiece instanceof Pawn) {
						result.addAll(((Pawn) currPiece).attackPawn(board, x, y));// this is for all the attacking
																					// squares of pawns.
					} else {

						result.addAll(currPiece.getLegalMoves(board, x, y));
					}
				}
			}
		}
		// printArray(result);
		if (result.contains(square)) {
			return true;
		} else {

			return false;
		}
	}

	/**
	 * checks if the two numbers are in the range of the board.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return This checks if the end move is in the range of the board and does not
	 *         go out of bound
	 */
	public static boolean inRange(int x, int y) {
		if (x < 8 && x > -1 && y < 8 && y > -1) {
			return true;

		}
		return false;
	}

	/**
	 * Prints array(used for debugging)
	 * 
	 * @param legalMoves holds all the possible moves in an arraylist
	 */
	public void printArray(ArrayList<Square> legalMoves) {
		int i = 0;
		while (i < legalMoves.size()) {
			System.out.println("X:" + legalMoves.get(i).x + "Y:" + legalMoves.get(i).y);
			i++;
		}
	}

	/**
	 * Returns the promotion symbol within a move.
	 * 
	 * @param move the move the player inputed
	 * @return the string of the promotion piece
	 */
	public String containsPromotion(String move) {
		String[] moves = move.split(" ");

		for (int x = 0; x < moves.length; x++) {
			if (moves[x].equals("Q") || moves[x].equals("N") || moves[x].equals("B") || moves[x].equals("R")) {
				return moves[x];

			}

		}
		return "Q";
	}

	/**
	 * Returns the square of the player's king.
	 * 
	 * @param turn player turn
	 * @return the square that contains the player's king
	 */
	public Square getKing(boolean turn) {
		Square curr = null;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				curr = board[x][y];
				if (curr.currPiece instanceof King && curr.currPiece.white == turn) {
					return curr;
				}
			}

		}
		return null;
	}

}
