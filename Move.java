public class Move{
    private int initIndex;
    private int finalIndex;
    private int[] rolls = new int[4];
    private Color color;

    Move(int initIndex, Color color) {
        this.initIndex = this.finalIndex = initIndex;
        this.color = color;
    }
    Move(Move m, int r) {
        this.initIndex = m.initIndex;
        this.finalIndex = m.finalIndex;
        this.Color = m.Color;

        for ( int i = 0; i < m.rolls.length ; i++ ) {
            if (m.rolls[i] != 0) {
                this.rolls[i] = m.rolls[i];
            } else {
                this.roll[i] = r; break;
            }
        }

    }

    public int spotNum() { return initIndex; }
}
