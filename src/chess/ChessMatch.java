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
  
  //Metodo que vai receber as coordenadas do xadrez
  private void  placeNewPiece(char column, int row, ChessPiece piece) {
	  board.placePiece(piece, new ChessPosition(column,row).toPosition());
  }
  //Metodo responsavel por iniciar a partida de xadrez
  private void initialSetup() {
	  placeNewPiece('b', 6, new Rook(board, Color.WHITE));
	  placeNewPiece('e', 8, new King(board, Color.BLACK));
	  placeNewPiece('e', 1, new King(board,Color.WHITE));
  }
}
