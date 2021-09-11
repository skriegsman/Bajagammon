/*
  The Spot class is a datatype to store the type and number of tokens on a given spike
    - Defaults: 0 Empty Color tokens
*/
public class Spot{
  private Color color = Color.E;
  private int count = 0;

  Spot(){} //Constructor

  //Methods to get and set the color
  public Color getColor() { return color; }
  public void setColor(Color color) { this.color = color; }

  //Methods to adjust the counts
  public int getCount() { return count; }
  public void setCount(int count) { this.count = count; }
  public void incCount(Color color) {
    setColor(color); count++;
  }
  public boolean decCount() {
    if(count == 0) { return false; }            //Cannot have negative count
    else if( count == 1) { setColor(Color.E);}  //If count lowers to 0, change color to E
    count--;
    return true;
  }

  //Returns blank for empty spikes, otherwise
  //Returns Count as a string combined with the Color name
  public String toString() {
    if ( count == 0) { return "  "; }
    return String.valueOf(count) + color.name();
  }
}
