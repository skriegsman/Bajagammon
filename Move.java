public class Move{
    private int initIndex;
    private int finalIndex;
    private int[] rolls;
    private Color color;

    Move(int initIndex, Color color) {
        this.initIndex = this.finalIndex = initIndex;
        this.color = color;
        this.rolls = new int[1];
    }
    Move(int initIndex, int roll, Color color) {
        rolls = new int[1];
        this.color = color;
        this.initIndex = initIndex;
        this.rolls[0] = roll;

        if(color == Color.B) {
            this.finalIndex = Math.min(initIndex+roll, 25);
        } else {
            this.finalIndex = Math.max(0,initIndex-roll);
        }
    }
    Move(Move m, int r) {
        this.initIndex = m.initIndex;
        this.finalIndex = m.finalIndex;
        this.color = m.color;

        if(m.rolls == null) {
            this.rolls = new int[1];
            this.rolls[0] = r;
        } else {
            this.rolls = new int[m.rolls.length+1];
            for(int i=0; i<m.rolls.length; i++) {
                this.rolls[i] = m.rolls[i];
            }
            this.rolls[this.rolls.length-1] = r;
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

    public String toString() {
        return "(" + initIndex + ", " + rolls[0] + ")";
    }
}
