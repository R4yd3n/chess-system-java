package boardgame;

import java.util.ArrayList;
import java.util.List;


//Tabuleiro do jogo

public class Board {
 private int rows;
 private int columns;
 private Piece[][] pieces;
 	 
 public Board(int rows, int columns) {

	this.rows = rows;
	this.columns = columns;
	pieces = new Piece[rows][columns];
}

 
public int getRows() {
	return rows;
}

public void setRows(int rows) {
	this.rows = rows;
}

public int getColumns() {
	return columns;
}

public void setColumns(int columns) {
	this.columns = columns;
}

//Metodo que cria uma peca e atribui ela a pieces
public Piece piece(int row, int column) {
	return pieces[row][column];
}

//Metodo que cria uma peca atraves de uma posicao
public Piece piece(Position position) {
	return pieces[position.getRow()][position.getColumn()];
}

 /* Posiciona uma peca no tabuleiro. 
    Vai pegar o pieces[][] e atribuir a position e piece a ele */
public void placePiece(Piece piece, Position position) {
	pieces[position.getRow()][position.getColumn()] = piece;
}

}
