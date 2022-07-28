package boardgame;

//Tabuleiro do jogo

public class Board {
 private int rows;
 private int columns;
 private Piece[][] pieces;
 	 
 public Board(int rows, int columns) {
  //Para criar o tabuleiro, precisa-se de pelo menos uma coluna
	 if(rows < 1 || columns < 1) {
		 throw new BoardException("Erro criando o tabuleiro: Deve haver pelo menos 1 linha e 1 coluna!");
	 }
	this.rows = rows;
	this.columns = columns;
	pieces = new Piece[rows][columns];
}

 
public int getRows() {
	return rows;
}

public int getColumns() {
	return columns;
}

//Metodo que cria uma peca e atribui ela a pieces
public Piece piece(int row, int column) {
	if (!positionExists(row,column)) {
		throw new BoardException("Posicao nao existente!");
	}
	return pieces[row][column];
}

//Metodo que cria uma peca atraves de uma posicao
public Piece piece(Position position) {
	if (!positionExists(position)) {
		throw new BoardException("Posicao nao existente!");
	}
	return pieces[position.getRow()][position.getColumn()];
 }

 /* Posiciona uma peca no tabuleiro. 
    Vai pegar o pieces[][] e atribuir a position e piece a ele */
public void placePiece(Piece piece, Position position) {
	if(thereIsAPiece(position)) {
		throw new BoardException("Ja existe uma peca na posicao " + position);
	}
	pieces[position.getRow()][position.getColumn()] = piece;
	piece.position = position;
}

//Remove a peca de uma posicao.

public Piece removePiece (Position position) {
	if(!positionExists(position)) {
		throw new BoardException("Essa nao e uma posicao do tabuleiro!");
	}
	if(position == null) {
	  return null;
	}
	Piece aux = piece(position);
	pieces[position.getRow()][position.getColumn()] = null;
	return aux;
}


//Retorna um valor booleano falando se a posicao existe ou nao

 public boolean positionExists(int row, int column) {
	 return row >= 0 && row < rows && column >= 0 && column < columns;
 }
 public boolean positionExists(Position position) {
	 return positionExists(position.getRow(), position.getColumn());
 }

//Recebe uma posicao e retorna um booleano falando se nessa posicao existe uma peca
  public boolean thereIsAPiece(Position position) {
    if (!positionExists(position)) {
	 throw new BoardException("Posicao nao existente!");
	}
	  return piece(position) != null;
  }
}
