/**
 * @author Al Manrique and Daniel Alvarado
 */
package chess;

import java.util.*;

import Board.Board;

/**
 * This is the main class where the board is called to be created, where all the
 * moves are passed through each method to see if they are legal, and where the
 * board is updated.
 *
 */
public class Chess {
//start with printing the board
//then ask player 1 what moves it wants maybe ask for onw move then the next. we can save it into seperate variables.
	static int wStartX = 0, wStartY = 0, wEndX = 0, wEndY = 0, bStartX = 0, bStartY = 0, bEndX = 0, bEndY = 0;
	static public boolean drawRequested;

	public static void main(String[] args) {

		boolean whiteTurn = true;
		Board board = new Board();
		board.boardInit();
		board.printSquare();
		String whiteMove = "";
		String blackMove = "";
		Scanner scn = new Scanner(System.in);
		while (true) { // PRESSING ENTER MAKES IT BUG OUT
			while (whiteTurn) {
				System.out.println("");
				System.out.print("White's move: ");
				whiteMove = scn.nextLine();
				System.out.println("");
				if (drawRequested) {
					if (whiteMove.equals("Draw") || whiteMove.equals("draw")) {
						System.exit(0);
					} else {
						while (!(whiteMove.equals("draw"))) {
							System.out.println("Illegal Move, try again");
							System.out.println("");
							System.out.print("White's move: ");
							whiteMove = scn.nextLine();
							System.out.println("");
						}
						System.exit(0);
						// System.out.println("Illegal Move, try again");
						// board.updateSuccess = false;
					}
				}
				if (whiteMove.equals("resign")) {
					System.out.println("Black wins");
					System.exit(0);
				} else if (whiteMove.equals("quit")) {
					System.exit(0);
				}
				if (convert(whiteMove, whiteTurn) && !(whiteMove.equals(""))) {
					// board.updateBoard(board.board[wStartX][wStartY], board.board[wEndX][wEndY],
					// whiteMove, whiteTurn);
//	
//					if (containsDraw(whiteMove)) {
//						drawRequested = true;
//						
//						whiteTurn = false;
//						break;
//					}
					board.updateBoard(board.board[wStartX][wStartY], board.board[wEndX][wEndY], whiteMove, whiteTurn);

				} else {
					System.out.println("Illegal Move, try again");
					board.updateSuccess = false;
				}
				if (board.updateSuccess) {
					board.printSquare();
					if (containsDraw(whiteMove)) {
						drawRequested = true;

						whiteTurn = false;
						break;
					}
					whiteTurn = false;
				}

			}

			while (whiteTurn == false) {
				System.out.println("");
				System.out.print("Black's move: ");
				// System.out.println("");
				blackMove = scn.nextLine();
				System.out.println("");
				if (drawRequested) {
					if (blackMove.equals("Draw") || blackMove.equals("draw")) {
						System.exit(0);
					} else {
						while (!(blackMove.equals("draw"))) {
							System.out.println("Illegal Move, try again");
							System.out.println("");
							System.out.print("Black's move: ");
							blackMove = scn.nextLine();
							System.out.println("");
						}
						System.exit(0);
						// board.updateSuccess = false;

					}
				}
				if (blackMove.equals("resign")) {
					System.out.println("White wins");
					System.exit(0);
				}
				if (convert(blackMove, whiteTurn) && !(blackMove.equals(""))) {
//					if (containsDraw(blackMove)) {
//						drawRequested = true;
//						whiteTurn = true;
//						break;
//					}

					board.updateBoard(board.board[bStartX][bStartY], board.board[bEndX][bEndY], blackMove, whiteTurn);

				} else {
					System.out.println("Illegal Move, try again");
					board.updateSuccess = false;
				}
				if (board.updateSuccess) {
					board.printSquare();
					if (containsDraw(blackMove)) {
						drawRequested = true;
						whiteTurn = true;
						break;
					}
					whiteTurn = true;
				}
			}

		}

	}

	/**
	 * 
	 * @param move is the input of the user 
	 * @param whiteTurn checks who's turn it is
	 * @return This method converts the input of the ending move to where the board
	 *         can parse it as an array.
	 */
	public static boolean convert(String move, boolean whiteTurn) {// of it is false conversion was not a success
		if (move.length() > 11 || move.length() < 5) {
			return false;
		}

		if (whiteTurn == true) {

			wStartX = 8 - Character.getNumericValue(move.charAt(1));// Number
			wStartY = letterToNum(move.charAt(0));// Letter
			wEndX = 8 - Character.getNumericValue(move.charAt(4));// number
			wEndY = letterToNum(move.charAt(3));// letter
			if (inRange(wStartX, wStartY) && inRange(wEndX, wEndY)) {
				return true;
			}

		} else {
			bStartX = 8 - Character.getNumericValue(move.charAt(1));// Number
			bStartY = letterToNum(move.charAt(0));// Letter
			bEndX = 8 - Character.getNumericValue(move.charAt(4));// number
			bEndY = letterToNum(move.charAt(3));// letter

			if (inRange(bStartX, bStartY) && inRange(bEndX, bEndY)) {
				return true;
			}
		}

		return false;

	}

	/**
	 * This method converts the letters to numbers according to their specified
	 * location
	 * 
	 * @param c the letter of the board
	 * @return an number to identify the index
	 */
	public static int letterToNum(char c) {

		if (c == 'a') {
			return 0;
		} else if (c == 'b') {
			return 1;
		} else if (c == 'c') {
			return 2;
		} else if (c == 'd') {
			return 3;
		} else if (c == 'e') {
			return 4;
		} else if (c == 'f') {
			return 5;
		} else if (c == 'g') {
			return 6;
		} else if (c == 'h') {
			return 7;
		} else {
			return -1;
		}

	}

	/**
	 * This checks if the ending move is in range of the board
	 * 
	 * @param x is the x coordinate
	 * @param y is the y coordinate
	 * @return returns a boolean to see if the move is within the range of the chess
	 *         board
	 */
	public static boolean inRange(int x, int y) {
		if (x < 8 && x > -1 && y < 8 && y > -1) {
			return true;

		}
		return false;
	}

	/**
	 * This method checks if one of the players requests a draw
	 * 
	 * @param move the move that the player input
	 * @return true if it contains Draw otherwise false.
	 */

	public static boolean containsDraw(String move) {
		String[] moves = move.split(" ");

		for (int x = 0; x < moves.length; x++) {
			if ((moves[x].equals("Draw?")) || (moves[x].equals("draw?"))) {
				return true;
			}

		}
		return false;

	}

}
