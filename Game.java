import java.util.Scanner;

public class Game {
        private Board board = new Board(); //Makes a new board ready to go
    private Player p1; private Player p2;
    private boolean currentPlayer;
    public boolean gameOver = false;= p2;
        }
    private int[] rolls = new int[4];= p2;
        }
    private final int numPlayerTokens = 15;
    private Move[] availMoves = new Move[256];
    private int availMovesCount = 0;

    this.p1 = p1; this.p2 = p2;
        }

    //sets first player by setting currentPlayer to true or false
    currentPlayer = ((Math.round(Math.random())) == 1) ? true : false;
    rolls[0] = (int)(Math.random()*6) + 1;
    rolls[1] = (int)(Math.random()*6) + 1;
    if(rolls[0]==rolls[1]) { rolls[2]=rolls[0]; rolls[3]=rolls[0]; }
    if(color == Color.B) {
    } else {
    }
    availMoves = new Move[256];
    if (rolls[0] != rolls[1]) {
    }
    } else if(c == Color.B && board.getSpotCount(board.bPurg()) > 0) {
    } else {import java.util.Scanner;

public class Game {
    private Board board = new Board(); //Makes a new board ready to go
    private Player p1; private Player p2;
    private boolean currentPlayer;
    public boolean gameOver = false;
    private int[] rolls = new int[4];
    private final int numPlayerTokens = 15;
    private Move[] availMoves = new Move[256];
    private int availMovesCount = 0;

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
    public boolean validMove(Color c, Move m) {
        availMoves = new Move[256];
        if (rolls[0] != rolls[1]) {
            int[] tempRolls = {rolls[0], rolls[1]};
        }
        if(c == Color.W && board.getSpotCount(board.wPurg()) > 0){
            validMoveRec(new Move(board.bHome(), c), tempRolls, c);
        } else if(c == Color.B && board.getSpotCount(board.bPurg()) > 0) {
            validMoveRec(new Move(board.wHome(), c), tempRolls, c);
        } else {
            int numFilledSpots = 0;
            for(int i=2; i<board.boardSize-2; i++) {
                if(board.getSpotType(i) == c) {numFilledSpots++;}
            }
            Move[] moves = new Move[numFilledSpots];
            for(int i=2, j=0; i<board.boardSize-2; i++) {
                if(board.getSpotType(i) == c) {
                    moves[j] = new Move(i, c);
                }
            }
            validMoveRec(moves, tempRolls); // need global move list thingy
        }
        //now add the part where we compare the player's move to the list

    }
    public int[] spliceRolls(int[] dice, int i) {
        int[] temp = new int[dice.length - 1];
        for(int j = 0 ; j < dice.length; j++ ) {
            if(j<i) { temp[j] = dice[j]; }
            else if(j>i) { temp[j-1] = dice[j]; }
        }
        return temp;
    }
    public boolean canGoHome(Color c) {
        int count = 0;
        if(c == Color.W){
            for(int i=board.wHome(); i <= board.wHome()+6; i++){
                if(board.getSpotType(i) == Color.W){
                    count+=board.getCount(i);
                }
            }
        }
        else {
            for(int i=board.bHome(); i <= board.bHome()-6; i--){
                if(board.getSpotType(i) == Color.B){
                    count+=board.getCount(i);
                }
            }
        }
        return count == 15;
    }
    public int[] validMoveRec(Move[] move, int[] dice, Color color) {
        for(int i=0; i<dice.length; i++) {
            // Move[] temp = new Move[move.length]; int k = 0;
            for(int j=0; j<move.length; j++) {
                int index = tokenShift(move[j].spotNum(), dice[j], color);
                //Is that a valid place to move it (your color or blank, or one other color)
                if(board.getSpotType(index) != color && board.getSpotCount(index) > 1) { continue; }
                //Cant move to home if all things are not in home quarter
                if(index == board.wHome() && !canGoHome(Color.W)) {
                    continue;
                } else if(index == board.bHome() && !canGoHome(Color.B)) {
                    continue;
                }
                availMoves[availMovesCount++] = new Move(move[j], dice[j]);
                // temp[k++] = new Move()
            }
            if (rolls[0]+ rolls[1] + rolls[2] + rolls[3] != 0 ) {
                validMoveRec(temp, spliceRolls(dice, i), color);
            }
        }
    }
    public boolean executeMove(Spot spot, int r) {
        Spot spotB = tokenShift(, roll, color)
        return moveToken(spot, spotB)
    }
    public boolean gameOver() {
        if (board.getSpotCount(board.bHome()) == numPlayerTokens || board.getSpotCount(board.wHome()) == numPlayerTokens) {
            return true;
        } return false;
        //Check for stalemate
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //REPL to set initial Parameters
        // - Whos playing (AI or human) (set p1 and p2 strings)
        System.out.println(" --- Welcome to Bajagammon --- \n");
        System.out.println("Select the players:");
        System.out.println("\t1. Human Player");
        System.out.println("\t2. Random Bot");
        System.out.println("\t2. MiniMax Bot");
        System.out.println("Enter the number of the desired player type (Player 1): ");
        String p1 = sc.nextLine();
        System.out.println("Enter the number of the desired player type (Player 2): ");
        String p2 = sc.nextLine();

        Game g = new Game(new Player(p1, Color.W), new Player(p2, Color.B));
        g.pickFirst();
        while(!g.gameOver) {
            Player p = g.currentPlayer ? g.p1 : g.p2;
            g.doDiceRoll();

            //While any dice not 0:
            while( g.rolls[0]+ g.rolls[1] + g.rolls[2] + g.rolls[3] != 0 && !g.gameOver) {
                if (g.validMove(p.getColor,p.move(g))) { //check move valid, if not return false and try again
                    g.
                    g.gameOver();
                }
            }

            g.currentPlayer = !g.currentPlayer;
        }
   }
}
    }
    }
    //now add the part where we compare the player's move to the list
    for (int i = 0 ; i < availMoves.length; i++) {
    }
    return false;

    int[] temp = new int[dice.length - 1];
    }
    return temp;
    int count = 0;
    }
    }
    else {
    }
    }
    return count == 15;
    for(int i=0; i<dice.length; i++) {
    }
    }
        }
    public boolean executeMove(Spot spot, int r) {
        Spot spotB = tokenShift(, roll, color)
        return moveToken(spot, spotB)
    if (board.getSpotCount(board.bHome()) == numPlayerTokens || board.getSpotCount(board.wHome()) == numPlayerTokens) {
    } return false;
    //Check for stalemate
    Scanner sc = new Scanner(System.in);
    //REPL to set initial Parameters
    // - Whos playing (AI or human) (set p1 and p2 strings)
    System.out.println(" --- Welcome to Bajagammon --- \n");
    System.out.println("Select the players:");
    System.out.println("\t1. Human Player");
    System.out.println("\t2. Random Bot");
    System.out.println("\t2. MiniMax Bot");
    System.out.println("Enter the number of the desired player type (Player 1): ");
    String p1 = sc.nextLine();
    System.out.println("Enter the number of the desired player type (Player 2): ");
    String p2 = sc.nextLine();

    Game g = new Game(new Player(p1, Color.W), new Player(p2, Color.B));
    g.pickFirst();
    while(!g.gameOver) {
                Player p = g.currentPlayer ? g.p1 : g.p2;
            g.doDiceRoll();

    }
            }

    }
       }
}
