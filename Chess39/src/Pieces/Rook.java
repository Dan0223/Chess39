/**
 * @author Al Manrique and Daniel Alvarado
 */
package Pieces;

import java.util.ArrayList;

import Board.Square;

public class Rook extends Piece {

	public Rook(boolean white) {
		super(white);
		// TODO Auto-generated constructor stub
	}
	/**
	 * This method will add all the legal moves of the current location of this piece to an arraylist that will be traversed to see if the move is allowed or not
	 */
	@Override
	public ArrayList<Square> getLegalMoves(Square[][] board, int x, int y) {
		// TODO Auto-generated method stub
		ArrayList <Square> result = new ArrayList<Square>();
		int p = 0;
		Square start = board[x][y];
		Square curr;
		for(; p < 8 ; p++) {
			
			if(inRange((x-1)-p,y)) {
			curr = board[(x-1) - p][y];
			if(curr.currPiece != null && !(start.currPiece.white== curr.currPiece.white)&& inRange(p,y)) { //if piece on square is opposite color
				result.add(curr);
				
				break;
			}else if(curr.currPiece != null && start.currPiece.white== curr.currPiece.white&& inRange(p,y)){
				
				break;
			}else {
				//Empty Square
				result.add(curr);
			}
			}
			
		}
		p = 0;
		for(; p < 8 ; p++) {
			
			if(inRange((x+1)+p,y)) {
			
			curr = board[(x+1)+p][y];
			if(curr.currPiece != null && !(start.currPiece.white == curr.currPiece.white)&& inRange(p,y)) {
				result.add(curr);
				
				break;
			}else if(curr.currPiece != null && start.currPiece.white== curr.currPiece.white&& inRange(p,y)){
				
				
					
				
				break;
			}else {
				result.add(curr);
			}
			}
			
		}
		
		p = 0;
		for(; p < 8 ; p++) {
			
			if(inRange(x,(y-1)-p)) {
			
			curr = board[x][(y-1)-p];
			if(curr.currPiece != null && !(start.currPiece.white== curr.currPiece.white)&& inRange(x,p)) {
				result.add(curr);
				
				break;
			}else if(curr.currPiece != null && start.currPiece.white== curr.currPiece.white&& inRange(x,p)){
				
				
				
				break;
			}else {
				result.add(curr);
			}
			}
			
		
			
		}
		p = 0;
		for(; p < 8 ; p++) {
			
			if(inRange(x,(y+1)+p)) {
		
			curr = board[x][(y+1)+p];
			if(curr.currPiece != null && !(start.currPiece.white== curr.currPiece.white)&& inRange(x,p)) {
				result.add(curr);
			
				break;
			}else if(curr.currPiece != null && start.currPiece.white== curr.currPiece.white&& inRange(x,p)){
				
				break;
			}else {
				result.add(curr);
			}
			}
			
		
			
		}
		
		
		
		return result;
	}
}
