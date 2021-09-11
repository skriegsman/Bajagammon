public class Spot{
  private Color color = Color.E;
  private int count = 0;

  Spot(){}

  public Color getColor() { return color; }
  public void setColor(Color color) { this.color = color; }

  public int getCount() { return count; }
  public void setCount(int count) { this.count = count; }
  public void incCount(Color color) {
    setColor(color);
    count++;
  }
  public boolean decCount() {
    if(count == 0) { return false; }
    else if( count == 1) { setColor(Color.E);}
    count--;
    return true;
  }

  public String toString() {
    if ( count == 0) { return "  "; }
    return String.valueOf(count) + color.name();
  }
}
