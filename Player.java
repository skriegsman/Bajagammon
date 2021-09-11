public class Player {
    private String type;
    private Color color;

    Player(String type, Color color) {
        this.type = type;
        this.color = color;
    }
    public Color getColor() { this.color; }
    public int[] move(Game g) {
        switch(type){
            case "2":
                return randomMove(g);
            case "3":
                return runningMove(g);
            default:
                return humanMove(g);
        }
    }

    public int[] randomMove(Game g) {return new int[];}

    public int[] runningMove(Game g) {return new int[];}

    public int[] humanMove(Game g){return new int[];}
}
