
public class Game {
    private Board board = new Board(); //Makes a new board ready to go
    private Player p1; private Player p2;
    private boolean currentPlayer;
    public boolean gameOver = false;
    private int[] rolls = new int[4];
    private final int numPlayerTokens = 15;

    Game(Player p1, Player p2) {
        this.p1 = p1; this.p2 = p2;
    }

    public void pickFirst() {
        //sets first player by setting currentPlayer to true or false
        currentPlayer = ((Math.round(Math.random())) == 1) ? true : false;
    }
    public void doDiceRoll() {
        rolls[0] = (int)(Math.random()*6) + 1;
        rolls[1] = (int)(Math.random()*6) + 1;
        if(rolls[0]==rolls[1]) { rolls[2]=rolls[0]; rolls[3]=rolls[0]; }
    }
    public int tokenShift(int index, int roll, Color color) {
        if(color == Color.B) {
            return Math.min(index+roll, 25);
        } else {
            return Math.max(0,index-roll);
        }
    }
    public void repl() {}
    public boolean validMove(Color c) {
        //Check if purgatory --> if so, separate case

        int numFilledSpots = 0;
        for(int i=1; i<board.boardSize-1; i++) {
            if(board.getSpotType(i) == c) {numFilledSpots++;}
        }
        Move[] moves = new Move[numFilledSpots];
        for(int i=1, j=0; i<board.boardSize-1; i++) {
            if(board.getSpotType(i) == c) {
                moves[j] = new Move(i, c);
            }
        }
        if (rolls[0] != rolls[1]) {
            int[] tempRolls = {rolls[0], rolls[1]};
        }
        validMoveRec(moves, tempRolls); // need global move list thingy
    }
    public int[] spliceRolls(int[] dice, int i) {
        int[] temp = new int[dice.length - 1];
        for(int j = 0 ; j < dice.length; j++ ) {
            if(j<i) { temp[j] = dice[j]; }
            else if(j>i) { temp[j-1] = dice[j]; }
        }
        return temp;
    }
    public int[] validMoveRec(Move[] move, int[] dice, Color color) {
        for(int i=0; i<dice.length; i++) {
            Move[] temp = new Move[move.length];
            for(int j=0; j<move.length; j++) {
                int index = tokenShift(move[j].spotNum(), dice[j], color);
                //Is that a valid place to move it (your color or blank, or one other color)
            }
            if (rolls[0]+ rolls[1] + rolls[2] + rolls[3] != 0 ) {
                validMoveRec(temp, spliceRolls(dice, i), color);
            }
        }
    }
    public boolean gameOver() {
        if (board.getSpotCount(board.bHome()) == numPlayerTokens || board.getSpotCount(board.wHome()) == numPlayerTokens) {
            return true;
        } return false;
        //Check for stalemate
    }
    public static void main(String[] args) {
        //REPL to set initial Parameters
        // - Whos playing (AI or human)
        Game g = new Game(new Player(p1, Color.W), new Player(p2, Color.B)); //need player class
        pickFirst();
        while(!g.gameOver) {
            Player p = g.currentPlayer ? p1 : p2;
            doDiceRoll();

            //While any dice not 0:
            while( rolls[0]+ rolls[1] + rolls[2] + rolls[3] != 0 && !g.gameOver) {
                move = p.move(g);
                //check move valid, if not return false and try again
                if (g.validMove()) {
                    //execute move, and make dice 0
                    g.gameOver();
                }
            }

            g.currentPlayer = !g.currentPlayer;
        }
   }
}
