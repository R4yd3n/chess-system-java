package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

//classe responsavel pela torre
public class Rook extends ChessPiece{

	public Rook(Board board, Color color) {
		super(board, color);
	}

   @Override
    public String toString() {
    return "R";
   }
 
   //Movimento da torre.
	@Override
	public boolean[][] possibleMoves() {
		boolean mat[][] = new boolean[getBoard().getRows()][getBoard().getColumns()];
		return mat;
	}
	
}
