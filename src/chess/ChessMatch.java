package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
  private int turn;
  private Color currentPlayer;
  private Board board;
  private boolean check;
  private List<Piece> piecesOnTheBoard = new ArrayList<>();
  private List<Piece> capturedPieces = new ArrayList<>();
  
  //Crio um tabuleiro 8x8 e atribui o setup inicial
  public ChessMatch() {
	this.board = new Board(8,8);
	turn = 1;
	currentPlayer = Color.WHITE;
	initialSetup();
  }
  
  public int getTurn() {
	  return turn;
  }
  
  public Color getCurrentPlayer() {
	  return currentPlayer;
  }
  
  public boolean getCheck() {
	  return check;
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
  
  //Mostra as posições disponíveis para se mover.
   public boolean[][] possibleMoves(ChessPosition sourcePosition){
	   Position position = sourcePosition.toPosition();
	   validateSourcePosition(position);
	   return board.piece(position).possibleMoves();
	   }
  
  //Metodo responsavel por realizar o movimento de xadrez
   public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
	   Position source = sourcePosition.toPosition();
	   Position target = targetPosition.toPosition();
	   validateSourcePosition(source);
	   validadeTargetPosition(source,target);
	   Piece capturedPiece = makeMove(source,target);
	   
	   if(testCheck(currentPlayer)) {
		   undoMove(source,target,capturedPiece);
		   throw new ChessException("Voce nao pode se colocar em cheque!");
	   }
	   
	   check = (testCheck(opponent(currentPlayer))) ? true : false ;
	   
	   
	   nextTurn();
	   return (ChessPiece)capturedPiece;
   }
   
   //Metodo auxiliar para mover a peca
   private Piece makeMove(Position source, Position target)   {
	   Piece p = board.removePiece(source);
	   Piece capturedPiece = board.removePiece(target);
	   board.placePiece(p, target);
	   if(capturedPiece != null) {
		   piecesOnTheBoard.remove(capturedPiece);
		   capturedPieces.add(capturedPiece);
	   }
	   return capturedPiece;
   }
   
   //Desfaz o movimento da peça.
   
   private void undoMove(Position source, Position target, Piece capturedPiece) {
	 Piece p = board.removePiece(target);
	 board.placePiece(p, source);
	 if(capturedPiece != null) {
		 board.placePiece(capturedPiece, target);
		 capturedPieces.remove(capturedPiece);
		 piecesOnTheBoard.add(capturedPiece);
	 }
   }
   
  //Verifica se a posicao de origem é valida.
  private void validateSourcePosition(Position position) {
	  if (!board.thereIsAPiece(position)) {
		  throw new ChessException("Nao existe peca na posicao de origem");
	  }
	  if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
		  throw new ChessException("A peca escolhida nao e sua!");
	  }
	  if (!board.piece(position).isThereAnyPossibleMove()) {
		  throw new ChessException("Nao existe movimento possivel!");
	  }
  }
  
  //Verifica se a posição final da peça é valida.
 private void validadeTargetPosition(Position source, Position target) {
	 if(!board.piece(source).possibleMove(target)) {
		 throw new ChessException("Esse movimento nao pode ser feito pela peca escolhida!");
	 }
 }
 
 //Troca de turno
 public void nextTurn() {
	 turn++;
	 currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
 }
 
 //Retorna uma cor oposta a do jogador atual.
  private Color opponent(Color color) {
	  if(color == Color.WHITE) {
		  return Color.BLACK;
	  }
	  else {
		  return Color.WHITE;
	  }
  }
  
  //Localiza o rei de uma determinada cor.
  private ChessPiece king(Color color) {
	  List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
	  for(Piece p:list) {
		  if(p instanceof King) {
			  return (ChessPiece)p;
		  }
	  }
	  throw new IllegalStateException("Nao tem rei " + color + " no tabuleiro");
  }
 
  /* Percorre as peças adversárias, checando se algum 
     movimento delas cairá na casa do rei*/
  private boolean testCheck(Color color) {
	  Position kingPosition = king(color).getChessPosition().toPosition();
	  List<Piece> opponentPieces =  piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
      for(Piece p: opponentPieces) {
    	  boolean[][] mat = p.possibleMoves();
    	  if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {
    		  return true;
    	  }
    	  
      }
      return false;
  }
  
  //Metodo que vai receber as coordenadas do xadrez
  private void  placeNewPiece(char column, int row, ChessPiece piece) {
	  board.placePiece(piece, new ChessPosition(column,row).toPosition());
	  piecesOnTheBoard.add(piece);
  }
  //Metodo responsavel por iniciar a partida de xadrez
  private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
  }
}
