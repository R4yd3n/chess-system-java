package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
  private Board board;
  
  //Crio um tabuleiro 8x8 e atribui o setup inicial
  public ChessMatch() {
	this.board = new Board(8,8);
	initialSetup();
  }
  
  //Coloca as pecas no tabuleiro
  public ChessPiece[][] getPieces(){
	  ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
	  for(int i=0;i<board.getRows();i++) {
		  for(int j=0;j<board.getColumns();j++) {
			  mat[i][j] = (ChessPiece)board.piece(i,j);
		  }
	  
	  }
	  return mat;
  }
  
  //Metodo responsavel por iniciar a partida de xadrez
  private void initialSetup() {
	  board.placePiece(new Rook(board, Color.WHITE), new Position(2,1));
	  board.placePiece(new King(board, Color.BLACK), new Position (0,4));
	  board.placePiece(new King(board,Color.WHITE), new Position (7,4));
  }
}
