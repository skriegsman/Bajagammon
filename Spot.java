/*
  The Spot class is a datatype to store the type and number of tokens on a given spike
    - Defaults: 0 Empty Color tokens
*/
public class Spot{
  private Color color = Color.E;
  private int count = 0;
  private int index;

  Spot(){} //Constructor
  Spot(Spot s) {
      this.color = s.color;
      this.count = s.count;
  }

  //Methods to get and set the color
  public Color getColor() { return this.color; }
  public void setColor(Color color) { this.color = color; }

  //Methods to get and set the index in the board
  public Color getIndex() { return this.index; }
  public void setIndex(int index) { this.index = index; }

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
