import java.util.Scanner;
import java.util.Random;

public class Player {
    private String type;
    private Color color;

    Player(String type, Color color) {
        this.type = type;
        this.color = color;
    }
    public Color getColor() { return this.color; }
    public Move move(Game g) {
        switch(type){
            case "2":
                return randomMove(g);
            // case "3":
            //     return runningMove(g);
            default:
                return humanMove(g);
        }
    }

    public Move randomMove(Game g) {
        Random rn = new Random();
        int index = rn.nextInt(29);
        int roll = rn.nextInt(g.getRolls().length+1);
        return new Move(index, roll, this.color);
    }

    // public int[] runningMove(Game g) {return new int[];}

    public Move humanMove(Game g) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Row:");
        int row = sc.nextInt();
        System.out.print("Enter Column:");
        String column = sc.next();
        System.out.print("Enter Roll:");
        int roll = sc.nextInt();
        int index = 0;
        if (column.equals("B")) {
            index = row;
        }
        else {
            index = 27-row;
        }

        return new Move(index, roll, this.color);
    }
}
