public class Player {
    private String type;
    private Color color;

    Player() {}

    public int[] move(Game g) {
        switch(type){
            case "random":
                return randomMove(g);
            case "running":
                return runningMove(g);
            default:
                return humanMove(g);
        }
    }

    public int[] randomMove(Game g) {return new int[];}

    public int[] runningMove(Game g) {return new int[];}

    public int[] humanMove(Game g){return new int[];}
}
