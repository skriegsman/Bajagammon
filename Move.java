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

    public boolean compareTo(Move m){
        if (this.getInitIndex()!=m.getInitIndex()) {
            return false;
        }
        if (this.getFinalIndex()!=m.getFinalIndex()) {
            return false;
        }
        if(this.getColor()!=m.getColor()) {
            return false;
        }
        if (this.getRoll(0) != m.getRoll(0)) {
            return false;
        }
        return true;
    }

    public int getInitIndex() { return this.initIndex; }
    public int getFinalIndex() { return this.finalIndex; }
    public Color getColor() { return this.color; }
    public int getRoll(int i) { return rolls[i]; }
}
