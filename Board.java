
public class Board {
  final public int boardSize = 28;
  private Spot[] board = new Spot[boardSize];

  Board() {
    for(int i=0; i<boardSize; i++) { board[i] = new Spot(); }
    // set checkers in non-empty spots at beginning of game
    board[2].setCount(2);   board[2].setColor(Color.B);
    board[7].setCount(5);   board[7].setColor(Color.W);
    board[9].setCount(3);   board[9].setColor(Color.W);
    board[13].setCount(5);  board[13].setColor(Color.B);
    board[14].setCount(5);  board[14].setColor(Color.W);
    board[18].setCount(3);  board[18].setColor(Color.B);
    board[20].setCount(5);  board[20].setColor(Color.B);
    board[25].setCount(2);  board[25].setColor(Color.W);
  }
  Board(Board b) { this.board = b.getDeepBoard(); }

  public Spot[] getDeepBoard() {
      Spot[] temp = new Spot[boardSize];
      for(int i=0; i<boardSize; i++) {
          temp[i] = new Spot(board[i]);
      }
      return temp;
  }

  public int wHome() { return 1; }
  public int wPurg() { return 0;}
  public int bHome() { return boardSize-2; }
  public int bPurg() { return boardSize-1; }

  public Color getSpotType(int spot) { return board[spot].getColor(); }
  public int getSpotCount(int spot) { return board[spot].getCount(); }

  /**
    Parameters: the indices of the spot to be moved from (A) and the spot to be moved to (B)

    Checks whether a token in spot B must be moved to purgatory.
    if color of spotA isn't the same as color of spotB, AND spotB isn't empty,
    then move the token in B to purgatory.

    NOTE: doesn't check whether spot B only has one token, this must be done by the game

    Then, after evaluating purgatory stuff, move one token from A to B and assign A's color to B
  */
  public void moveToken(int spotA, int spotB) {
    if(board[spotA].getColor() != board[spotB].getColor() && board[spotB].getColor() != Color.E) {
        if(board[spotB].getColor() == Color.W){
            board[wPurg()].incCount(Color.W);
        } else {
            board[bPurg()].incCount(Color.B);
        }
        board[spotB].decCount();
    }
    board[spotA].decCount();
    board[spotB].incCount(board[spotA].getColor());
  }
  /**
    Parameters: none
    return: String

    Converts backgammon board state into a string
  */
  public String toString() {
    String doubleDash = "\t===========\n";
    String singleDash = "\t-----------\n";
    String s = "\t  A     B\n";
      s+= singleDash;
      s+="\t| " +  board[bHome()].toString() + " | " +  board[wHome()].toString() + " | " + "Home" + "\n";
      s+=doubleDash;
      for(int i=2; i<boardSize/2; i++) {
          s+="\t| " + board[boardSize-1-i].toString()  + " | " + board[i].toString() + " | " + (i-1)+ "\n";
          if ( i != (boardSize -2)/4+1 && i != (boardSize-2)/2) {
            s+=singleDash;
          } else { s+=doubleDash;}
      }
      s+="\t| "  + board[bPurg()].toString() + " | " +  board[wPurg()].toString() + " | " + "Bar" + "\n";
      s+= singleDash;
      return s;
  }

  public static void main(String[] args) {
      Board b = new Board();
      System.out.println(b.toString());
  }
}
