
public class Game {
    private Board board = new Board(); //Makes a new board ready to go
    private Player p1; private Player p2;
    private boolean currentPlayer;
    public boolean gameOver = false;
    private int d1 = 0; private int d2 = 0; private int d3 = 0; private int d4 = 0;
    private final int numPlayerTokens = 15;

    Game(Player p1, Player p2) {
        this.p1 = p1; this.p2 = p2;
    }

    public void pickFirst() {
        //sets first player by setting currentPlayer to true or false
        currentPlayer = ((Math.round(Math.random())) == 1) ? true : false;
    }
    public void doDiceRoll() {
        d1 = (int)(Math.random()*6) + 1;
        d2 = (int)(Math.random()*6) + 1;
        if(d1==d2) { d3=d1; d4=d1; }
    }
    public void repl() {}
    public boolean validMove() {
        // https://usbgf.org/learn-backgammon/backgammon-rules-and-terms/rules-of-backgammon/
        ruleOne(); //calls for each rule, make function for each


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
            while( g.d1 + g.d2 + g.d3 + g.d4 != 0 && !g.gameOver) {
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
