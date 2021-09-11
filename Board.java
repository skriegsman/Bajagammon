
public class Board {
  final private boardSize = 26;
  private Spot[] board = new Spot[boardSize];

  private int bPurg = 0;
  private int wPurg = 0;

  Board() {
    for(int i=0; i<boardSize; i++) { board[i] = new Spot(); }
    // set checkers in non-empty spots at beginning of game
    board[1].setCount(2);   board[1].setColor(Color.B);
    board[6].setCount(5);   board[6].setColor(Color.W);
    board[8].setCount(3);   board[8].setColor(Color.W);
    board[12].setCount(5);  board[12].setColor(Color.B);
    board[13].setCount(5);  board[13].setColor(Color.W);
    board[17].setCount(5);  board[17].setColor(Color.B);
    board[19].setCount(5;)  board[19].setColor(Color.B);
    board[24].setCount(2);  board[24].setColor(Color.W);
  }

  public Color getSpotType(int spot) { return board[spot].getColor(); }
  public int getSpotCount(int spot) { return board[spot].getCount(); }
  public boolean TokenTopurg(int spot) {}
  public boolean purgToSpot(Color color, int spot) {}
  public boolean moveToken(int spotA, int spotB) {

  }

  public String toString() {
      String s = "";
      s+="|" +  board[25].toString() + "|" +  board[0].toString() + "|" + 0 + "\n";
      s+="=======\n";
      for ( i = 1 ; i < (boardSize -2)/4+1; i++) {
          s+="|" + board[boardSize-1-i].toString()  + "|" + board[i].toString() + "|" + i + "\n";
          s+="-------\n";
      }
      s+="=======\n";
      for ( i = (boardSize -2)/4+1 ; i < boardSize/2+1; i++) {
          s+="|" + board[boardSize-1-i].toString()  + "|" + board[i].toString() + "|" + i + "\n";
          s+="-------\n";
      }
      s+="=======\n";
      s+="|" + " " + bPurg + "|" + " " + wPurg + "\n";
      return s;
  }
  public static void main (String[] args ) {

    Board b = new Board();
    System.out.println(b.toString());
  }

}
