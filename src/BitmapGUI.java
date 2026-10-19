import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;

public class BitmapGUI extends JFrame {

    //create a private Icon that holds bitmap data.
    private Icon bitmap;

    //Constructor for BitmapGUI class
    public BitmapGUI() {
        //Ask for dimensions
        String rowsInput = JOptionPane.showInputDialog(this, "Enter the number of rows:");
        String colsInput = JOptionPane.showInputDialog(this, "Enter the number of columns:");

        //Parse the user input to integers
        int rows = Integer.parseInt(rowsInput);
        int cols = Integer.parseInt(colsInput);

        //Create a new Icon object with the entered dimensions
        bitmap = new Icon(rows, cols);

        //Set up the JFrame
        setTitle("Bitmap GUI");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
