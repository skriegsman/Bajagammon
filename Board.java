
public class Board {
  final public int boardSize = 26;
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
    board[17].setCount(3);  board[17].setColor(Color.B);
    board[19].setCount(5);  board[19].setColor(Color.B);
    board[24].setCount(2);  board[24].setColor(Color.W);
  }
  Board(Board b) {
      this.bPurg = b.bPurg;
      this.wPurg = b.wPurg;
      this.board = b.getDeepBoard();
  }

  public Spot[] getDeepBoard() {
      Spot[] temp = new Spot[boardSize];
      for(int i=0; i<boardSize; i++) {
          temp[i] = new Spot(board[i]);
      }
      return temp;
  }

  public int wHome() { return 0; }
  public int bHome() { return boardSize-1; }

  public Color getSpotType(int spot) { return board[spot].getColor(); }
  public int getSpotCount(int spot) { return board[spot].getCount(); }

  /**
    Parameters: index of spot to remove a token from and take to purgatory

    Checks the color of what's in the spot, increments the correct purgatory,
    and then decrements the board spot. (This will assign empty if count goes to zero)

    Should not be called on a spot which already has count==0, this will incorrectly increment bPurg.
  */
  public void tokenToPurg(int spot) {
    if(board[spot].getColor() == Color.W) { wPurg++; }
    else { bPurg++; }
    board[spot].decCount();
  }
  /**
    Parameters: the color of the token in prugatory and the index of the spot to where the token is moved to

    Checks the color of the token in purgatory, decrements the count in respective purgatory
    Increments the count of the spot
  */
  public void purgToSpot(Color color, int spot) {
    if(color == Color.W){ wPurg--; }else{ bPurg--; }
    board[spot].incCount(color);
  }
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
      tokenToPurg(spotB);
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
      s+="\t| " +  board[25].toString() + " | " +  board[0].toString() + " | " + 0 + "\n";
      s+=doubleDash;
      for(int i=1; i<(boardSize-2)/2+1; i++) {
          s+="\t| " + board[boardSize-1-i].toString()  + " | " + board[i].toString() + " | " + i + "\n";
          if ( i != (boardSize -2)/4 && i != (boardSize-2)/2) {
            s+=singleDash;
          } else { s+=doubleDash;}
      }
      s+="\t| " + " " + bPurg + " | " + " " + wPurg + " | " + 13 + "\n";
      s+= singleDash;
      return s;
  }
}
