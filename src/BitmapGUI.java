import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.Color;

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

        //create grid layout for the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, cols));
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Pixels pixel = bitmap.getPixel(i, j );
                PButton button = new PButton(pixel, i, j);
                panel.add(button);

                //Here is the action listener to listen for the button clicks.
                button.addActionListener(e -> {

                    //Get the pixel associated with the clicked button
                    Pixels clickedPixel = bitmap.getPixel(button.getRow(), button.getCol());

                    //Here,ask for new RGB values
                    String redInput = JOptionPane.showInputDialog(this, "Enter the red value (0-255):");
                    String greenInput = JOptionPane.showInputDialog(this, "Enter the green value (0-255):");
                    String blueInput = JOptionPane.showInputDialog(this, "Enter the blue value (0-255):");

                    //Parse the user input to integers
                    int red = Integer.parseInt(redInput);
                    int green = Integer.parseInt(greenInput);
                    int blue = Integer.parseInt(blueInput);

                    //Set the new RGB values for the pixel
                    clickedPixel.setRed(red);
                    clickedPixel.setGreen(green);
                    clickedPixel.setBlue(blue);

                    //Update the button's background color like PButton class constructor.
                    button.setBackground(new Color(clickedPixel.getRed(), clickedPixel.getGreen(), clickedPixel.getBlue()));
                });
            }
        }
        add(panel, BorderLayout.CENTER);
        //Do this last so that the GUI is visible after all the components are added and set up.
        setVisible(true);

    }

}
