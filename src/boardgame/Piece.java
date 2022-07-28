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

//M�todo respons�vel pelos movimentos poss�veis das pe�as.
 public abstract boolean[][] possibleMoves();

//M�todo que testa se o movimento � poss�vel ou n�o.
 public boolean possibleMove(Position position) {
  return possibleMoves()[position.getRow()][position.getColumn()];
 }
 
 //M�todo que checa se h� algum movimento poss�vel para a pe�a.
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
