package chess;

import boardgame.Board;
import boardgame.Piece;

//Se trata das pecas do jogo de xadrez em execucao

public class ChessPiece extends Piece {
  
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	//Nao tem setColor() porque nao quero que ninguem mude as cores das pecas durante o jogo
	
}
