package boardgame;

/*Classe das pecas do tabuleiro. Quero que somente subclasses 
 e classes no mesmo pacote tenham acesso a ela */

public abstract class Piece {
  protected Position position;
  private Board board;
  
public Piece(Board board) {
	this.board = board;
}

protected Board getBoard() {
	return board;
}

//Método responsável pelos movimentos possíveis das peças.
 public abstract boolean[][] possibleMoves();

//Método que testa se o movimento é possível ou não.
 public boolean possibleMove(Position position) {
  return possibleMoves()[position.getRow()][position.getColumn()];
 }
 
 //Método que checa se há algum movimento possível para a peça.
 public boolean isThereAnyPossibleMove() {
	 boolean[][]mat = possibleMoves();
	 for(int i=0;i<mat.length;i++) {
		 for(int j=0;j<mat.length;j++) {
			 if(mat[i][j]) {
				 return true;
			 }
		 }
	 }
	 return false;
	 
 }
}
