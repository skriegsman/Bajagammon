import java.util.Scanner;

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
        System.out.println(board.toString());
    }

    public void pickFirst() {
        //sets first player by setting currentPlayer to true or false
        currentPlayer = ((Math.round(Math.random())) == 1) ? true : false;
        if(currentPlayer) {
            System.out.println("White Goes First");
        } else {
            System.out.println("Black Goes First");
        }
    }
    public void doDiceRoll() {
        this.rolls = new int[4];
        rolls[0] = (int)(Math.random()*6) + 1;
        rolls[1] = (int)(Math.random()*6) + 1;
        if(rolls[0] == rolls[1]) {
            rolls[2]=rolls[0]; rolls[3]=rolls[0];
        } else {
            int[] temp = new int[2];
            temp[0] = rolls[0]; temp[1] = rolls[1];
            this.rolls = new int[2];
            this.rolls[0] = temp[0];
            this.rolls[1] = temp[1];
        }
    }
    public int tokenShift(int index, int roll, Color color) {
        if(color == Color.B) {
            return Math.min(index+roll, 26);
        } else {
            return Math.max(1,index-roll);
        }
    }
    public boolean validMove(Color c, Move m) {
        System.out.println("Checking move is valid bozo");
        availMoves = new Move[256];
        availMovesCount = 0;
        if(c == Color.W && board.getSpotCount(board.wPurg()) > 0){
            System.out.println("Section 1");
            availMoves[0] = new Move(board.bHome(), Color.W);
            validMoveRec(availMoves, rolls, c);
        } else if(c == Color.B && board.getSpotCount(board.bPurg()) > 0) {
            System.out.println("Section 2");
            availMoves[0] = new Move(board.wHome(), Color.B);
            validMoveRec(availMoves, rolls, c);
        } else {
            System.out.println("Section 3");
            int numFilledSpots = 0;
            for(int i=2; i<board.boardSize-2; i++) {
                if(board.getSpotType(i) == c) {numFilledSpots++;}
            }
            System.out.println("NumFilledSpots: " + numFilledSpots);
            Move[] moves = new Move[numFilledSpots];
            for(int i=2, j=0; i<board.boardSize-2; i++) {
                if(board.getSpotType(i) == c) {
                    moves[j] = new Move(i, c);
                }
            }
            validMoveRec(moves, rolls, c); // need global move list thingy
        }

        String out = "AvailMoves: ";
        for(int i=0; i<availMoves.length; i++) {
            if(availMoves[i] != null) {
                out += availMoves[i].toString();
                out += ", ";
            }
        }
        System.out.println(out);

        //now add the part where we compare the player's move to the list
        for (int i = 0; i < availMoves.length; i++) {
            if (availMoves[i] != null && m.compareTo(availMoves[i])) {
                System.out.println("VALID MOVE!!!!!");
                return true;
            }
        }
        System.out.println("Your move was not valid RIP");
        return false;

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
                    count+=board.getSpotCount(i);
                }
            }
        }
        else {
            for(int i=board.bHome(); i <= board.bHome()-6; i--){
                if(board.getSpotType(i) == Color.B){
                    count+=board.getSpotCount(i);
                }
            }
        }
        return count == 15;
    }
    public void validMoveRec(Move[] move, int[] dice, Color color) {
        for(int i=0; i<dice.length; i++) {
            System.out.println("DiceLength: " + dice.length);
            // Move[] temp = new Move[move.length]; int k = 0;
            for(int j=0; j<move.length; j++) {
                if(move[j] != null) {
                    int index = tokenShift(move[j].getInitIndex(), dice[i], color);
                    //Is that a valid place to move it (your color or blank, or one other color)
                    if(board.getSpotType(index) != color && board.getSpotCount(index) > 1) { continue; }
                    //Cant move to home if all things are not in home quarter
                    if(index == board.wHome() && !canGoHome(Color.W)) {
                        continue;
                    } else if(index == board.bHome() && !canGoHome(Color.B)) {
                        continue;
                    }
                    availMoves[availMovesCount++] = new Move(move[j], dice[i]);
                    System.out.println("POOOOP: " + availMoves[availMovesCount-1].toString());
                    // temp[k++] = new Move()

                    //ADD A THING TO PASS IF NO VALID MOVES
                }
                else {
                    break;
                }
            }
            if (dice.length > 1 ) {
                validMoveRec(availMoves, spliceRolls(dice, i), color);
            }
        }
    }
    public void executeMove(Move m) {
        board.moveToken(m.getInitIndex(),m.getFinalIndex());
        for(int i=0; i<rolls.length; i++) {
            if(rolls[i] == m.getRoll(0)){
                spliceRolls(this.rolls, i);
                break;
            }
        }
        System.out.println(board.toString());
    }
    public boolean gameOver() {
        if (board.getSpotCount(board.bHome()) == numPlayerTokens || board.getSpotCount(board.wHome()) == numPlayerTokens) {
            return true;
        } return false;
        //Check for stalemate
    }

    public int sumOfRolls(){
        int sum = 0;
        for(int i = 0; i < rolls.length; i++){
            sum += rolls[i];
        }
        return sum;
    }
    public String rollsToString() {
        String out = "Dice: ";
        for(int i=0; i<rolls.length-1; i++) {
            out += rolls[i] + ", ";
        }
        out += rolls[rolls.length-1];
        return out;
    }

    public int[] getRolls() {return this.rolls; }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //REPL to set initial Parameters
        // - Whos playing (AI or human) (set p1 and p2 strings)
        System.out.println(" --- Welcome to Bajagammon --- \n");
        System.out.println("Select the players:");
        System.out.println("\t1. Human Player");
        System.out.println("\t2. Random Bot");
        System.out.println("\t3. MiniMax Bot");
        System.out.print("Enter the number of the desired player type (Player 1): ");
        String p1 = sc.nextLine();
        System.out.print("Enter the number of the desired player type (Player 2): ");
        String p2 = sc.nextLine();

        Game g = new Game(new Player(p1, Color.W), new Player(p2, Color.B));
        g.pickFirst();
        while(!g.gameOver) {
            Player p = g.currentPlayer ? g.p1 : g.p2;
            g.doDiceRoll();
            System.out.println(g.rollsToString());
            //While any dice not 0:
            while( g.sumOfRolls() != 0 && !g.gameOver) {
                Move move = p.move(g);
                if (g.validMove(p.getColor(),move)) { //check move valid, if not return false and try again
                    g.executeMove(move);
                    System.out.println(g.rollsToString());
                    g.gameOver();
                }
            }

            g.currentPlayer = !g.currentPlayer;
        }
   }
}
