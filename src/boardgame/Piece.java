package boardgame;

/*Classe das pecas do tabuleiro. Quero que somente subclasses 
 e classes no mesmo pacote tenham acesso a ela */

public class Piece {
  protected Position position;
  private Board board;
  
public Piece(Board board) {
	this.board = board;
}

protected Board getBoard() {
	return board;
}

  
  
  
}
