import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JLabel;

public class BitmapGUI extends JFrame {

    //create a private Icon that holds bitmap data.
    private Icon bitmap;
    private JSlider redSlider;
    private JSlider greenSlider;
    private JSlider blueSlider;
    private JPanel colorPreview;

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

                    //Use the sliders to get the RGB values for the pixel
                    int redSliderValue = redSlider.getValue();
                    int greenSliderValue = greenSlider.getValue();
                    int blueSliderValue = blueSlider.getValue();     
                    

                    //Set the new RGB values for the pixel
                    clickedPixel.setRed(redSliderValue);
                    clickedPixel.setGreen(greenSliderValue);
                    clickedPixel.setBlue(blueSliderValue);

                    //Update the button's background color like PButton class constructor.
                    button.setBackground(new Color(redSliderValue, greenSliderValue, blueSliderValue));
                });
            }
        }

        //Create sliders for RGB values
        redSlider = new JSlider(0,255,0);
        greenSlider = new JSlider(0,255,0);
        blueSlider = new JSlider(0,255,0);

        redSlider.addChangeListener(e -> colorPreviewUpdate());
        greenSlider.addChangeListener(e -> colorPreviewUpdate());
        blueSlider.addChangeListener(e -> colorPreviewUpdate());

        //Create a panel to hold the sliders and labels
        JPanel slidersPanel = new JPanel();
        slidersPanel.setLayout(new GridLayout(8, 1));

        colorPreview = new JPanel();
        colorPreview.setBackground(Color.BLACK);

        slidersPanel.add(new JLabel("Red:"));
        slidersPanel.add(redSlider);

        slidersPanel.add(new JLabel("Green:"));
        slidersPanel.add(greenSlider);

        slidersPanel.add(new JLabel("Blue:"));
        slidersPanel.add(blueSlider);

        slidersPanel.add(new JLabel("Color Preview:"));
        slidersPanel.add(colorPreview);

        //Add the main panel and sliders panel to the JFrame
        add(panel, BorderLayout.CENTER);
        add(slidersPanel, BorderLayout.EAST);

        //Do this last so that the GUI is visible after all the components are added and set up.
        setVisible(true);

    }

    //Method to update the color preview panel based on the slider values, so it does not always stay black.
    private void colorPreviewUpdate(){
        int red = redSlider.getValue();
        int green = greenSlider.getValue();
        int blue = blueSlider.getValue();
        //Creates a new color object baed on the retreived RGB values
        Color newColor = new Color(red, green, blue);
        colorPreview.setBackground(newColor);

    }

}
