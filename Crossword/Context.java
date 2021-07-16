import java.awt.*;

public class Context {
    private Word word;
    private Graphics graphics;
    private static Context context;

    private Context(){}

    public static Context getInstance(){
        if (context == null){
            context = new Context();
        }
        return context;
    }

    public void setGraphics(Graphics graphics){this.graphics = graphics;}

    public void drawBox(Point start){
        graphics.fillRect(start.x ,start.y ,20,20);                                                                     // draw box for letters
    }

    public void drawNumber(Point start, int i){
        graphics.drawString(String.valueOf(i),(start.x+8),(start.y+15));                                                // draw number for word boxes to match clues
    }

    public void drawLetter(Point start,char letter){
        graphics.setColor(Color.blue);                                                                                  // change color to blue
        graphics.drawString(String.valueOf(letter),(start.x+8),(start.y+15));                                           // draw letter
        graphics.setColor(Color.WHITE);                                                                                 // change color back to white
    }

    public void drawClue(Point start,String clue, int number){
        graphics.setColor(Color.white);                                                                                 // set color to white
        graphics.drawString(String.valueOf(number), start.x ,start.y);                                                  // write number of clue
        graphics.drawString(clue,start.x+15,start.y);                                                                   // write clue
        graphics.setColor(Color.white);                                                                                 // set color to white
    }

}
