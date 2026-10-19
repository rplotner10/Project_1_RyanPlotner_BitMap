import javax.swing.JButton;
import java.awt.Color;

public class PButton extends JButton {

    private Pixels pixel;
    private int row;
    private int col;

    //The constructor will take Pixels row and column as parameters
    public PButton(Pixels pixel, int row, int col) {
        this.pixel = pixel;
        this.row = row;
        this.col = col;
        //Just sets the background color of the button to the pixel's RGB color values we set with getters.
        setBackground(new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue()));
    }

    //Getters for row and column
    public int getRow(){
    return row;
    }

    public int getCol(){
    return col;
    }

    
}