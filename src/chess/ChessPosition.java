package chess;

import boardgame.Position;

//Classe responsavel por manipular as posicoes das pecas durante o jogo
public class ChessPosition {
  private char column;
  private int row;
  
public ChessPosition(char column, int row) {
   if (column < 'a' || column > 'h' && row < 0 || row > 8) {
	   throw new ChessException("Erro instanciando ChessPosition. Valores so serao validos se forem de a1 ate h8!");
   }
	this.column = column;
	this.row = row;
}
public char getColumn() {
	return column;
}

public int getRow() {
	return row;
}

//Converte uma posicao de xadrez para uma posicao na matriz
 protected Position toPosition() {
	 return new Position(8 - row, column - 'a');
 }
  
 //Converte uma posicao na matriz para uma posicao de xadrez
 protected static ChessPosition fromPosition(Position position) {
	 return new ChessPosition( (char)('a' - position.getColumn()),( 8 - position.getRow()));
 }
 
 //Vai imprimir a posicao de xadrez na ordem (coluna/linha)
 @Override
  public String toString() {
	 return "" + column + row;
 }
 
}
