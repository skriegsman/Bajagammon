public class Move{
    private int initIndex;
    private int finalIndex;
    private int[] rolls = new int[4];
    private Color color;

    Move(int initIndex, Color color) {
        this.initIndex = this.finalIndex = initIndex;
        this.color = color;
    }

    public int spotNum() { return initIndex; }
    public boolean addDie(int roll) {
        //Add the roll to rolls
        //
    }
}
