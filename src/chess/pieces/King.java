package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

//Classe responsavel pelo rei
public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "K";
	}
}
