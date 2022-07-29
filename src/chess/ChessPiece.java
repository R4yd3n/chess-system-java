package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

//Se trata das pecas do jogo de xadrez em execucao

public abstract class ChessPiece extends Piece {
  
	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	//Nao tem setColor() porque nao quero que ninguem mude as cores das pecas durante o jogo
	
	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
    
	public void increaseMoveCount() {
		moveCount++;
	}
	
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	public ChessPosition getChessPosition() {
	 return ChessPosition.fromPosition(position);
	}
	protected boolean isThereOpponentPiece(Position position) {
	  ChessPiece p = (ChessPiece)getBoard().piece(position);
	  return p != null && p.getColor() != color;
	}
	
}
