
public class Game {
    private Board board = new Board(); //Makes a new board ready to go
    private Player p1;
    private Player p2;
    private boolean currentPlayer;

    Game(Player p1, Player p2) {
        this.p1 = p1; this.p2 = p2;
    }

    public void pickFirst() {
        //sets first player by setting currentPlayer to true or false
    }
    public void runTheGame() {
        pickFirst();
        while(!GameOver()) {
            Player p = currentPlayer ? p1 : p2;
            move = p.move();

            //check move valid, if not return false and try again
            //execute move
            //check game over?

            currentPlayer = !currentPlayer;
        }
    }
    public boolean validMove() {
        // https://usbgf.org/learn-backgammon/backgammon-rules-and-terms/rules-of-backgammon/
        ruleOne(); //calls for each rule, make function for each
    }
    public boolean gameOver() {}

    public static void main(String[] args) {
        //REPL to set initial Parameters
        // - Whos playing (AI or human)

        Game game = new Game(new Player(p1, Color.W), new Player(p2, Color.B)); //need player class
        game.runTheGame();
   }
}
