import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.JCheckBox;

public class BitmapGUI extends JFrame {

    //create a private Icon that holds bitmap data.
    private Icon bitmap;
    private JSlider redSlider;
    private JSlider greenSlider;
    private JSlider blueSlider;
    private JPanel colorPreview;
    private ArrayList<Color> recentColorsList = new ArrayList<>(); 
    private JPanel recentColorsPanel;
    private JButton[] recentColorButtons = new JButton[5]; 

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
        
        //Create a checkbox for advanced options
        JCheckBox advancedOptions = new JCheckBox("Advanced Options");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Pixels pixel = bitmap.getPixel(i, j );
                PButton button = new PButton(pixel, i, j);
                panel.add(button);
    
                //Here is the action listener to listen for the button clicks.
                button.addActionListener(e -> {

                    if (advancedOptions.isSelected()){
                        String[] options = {"Fill", "Import", "Cancel"};

                        int choice = JOptionPane.showOptionDialog(this, "Choose what you would like to do:",
                         "Advanced Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                         if (choice == 0) {
                            //get the selected rgb color from the sliders
                            int red = redSlider.getValue();
                            int green = greenSlider.getValue();
                            int blue = blueSlider.getValue();

                            for (int row = button.getRow(); row < rows; row++) {
                                for (int col = button.getCol(); col < cols; col++) {
                                    bitmap.setPixel(row, col, red, green, blue);
                                    PButton pixelButton = (PButton) panel.getComponent(row * cols + col);
                                    pixelButton.setBackground(new Color(red, green, blue));
                                }
                            }
                            
                         } else if (choice == 1) {
                            

                         }
                    }

                    //Get the pixel associated with the clicked button
                    else {
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
                    addToRecentColors(new Color(redSliderValue, greenSliderValue, blueSliderValue));
                    }
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
        slidersPanel.setLayout(new GridLayout(12, 1));

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

        recentColorButtons = new JButton[5];

        recentColorsPanel = new JPanel();
        recentColorsPanel.setLayout(new GridLayout(1, 5));

        //This loop will create 5 buttons for recent colors
        for (int i = 0; i < 5; i++) {
            recentColorButtons[i] = new JButton();
            //Make the initial button background white
            recentColorButtons[i].setBackground(Color.WHITE);
            //Action listener for recent color buttons
            recentColorButtons[i].addActionListener(e -> {
                JButton sourceButton = (JButton) e.getSource();
                //Used sourceButton to get the background color of the clicked recent color
                Color selectedColor = sourceButton.getBackground();
                redSlider.setValue(selectedColor.getRed());
                greenSlider.setValue(selectedColor.getGreen());
                blueSlider.setValue(selectedColor.getBlue());
            });
            recentColorsPanel.add(recentColorButtons[i]);
        }
        //Literally just adding a label on the panel
        slidersPanel.add(new JLabel("Recent Colors:"));
        slidersPanel.add(recentColorsPanel);

        //Create the save button
        JButton saveButton = new JButton("Save BitMap");

        //Create a file chooser 
        JFileChooser fileSelector = new JFileChooser();
        //Action listener for the save button will file choosing.
        saveButton.addActionListener(e -> showSaveDialog(fileSelector));
        //Add the save button to the sliders panel
        slidersPanel.add(saveButton);

        //Add a checkbox for advanced options
        slidersPanel.add(advancedOptions);


        //Add the main panel and sliders panel to the JFrame
        add(panel, BorderLayout.CENTER);
        add(slidersPanel, BorderLayout.EAST);

        //Do this last so that the GUI is visible after all the components are added and set up.
        setVisible(true);

    }

    private void addToRecentColors(Color color) {
        //Simply removes color in list if there is a found duplicate
        recentColorsList.remove(color);

        recentColorsList.add(0, color); //Add the new color to the front of the list (Preference for me)

        if (recentColorsList.size() > 5) {
            recentColorsList.remove(recentColorsList.size() - 1); //Remove the oldest color if the list exceeds 5. Data management
        }

        updateRecentColorsPanel();
    }

    private void updateRecentColorsPanel() {
        for (int i = 0; i < 5; i++) {
            if (i < recentColorsList.size()) {
                recentColorButtons[i].setBackground(recentColorsList.get(i));
            } else {
                recentColorButtons[i].setBackground(Color.WHITE);
                recentColorButtons[i].setEnabled(true);
            }
        }
    }

    //Update the color preview panel based on the slider values, so it does not stay black.
    private void colorPreviewUpdate(){
        int red = redSlider.getValue();
        int green = greenSlider.getValue();
        int blue = blueSlider.getValue();
        //Creates a new color object baed on the retreived RGB values
        Color newColor = new Color(red, green, blue);
        colorPreview.setBackground(newColor);

    }
    //This method will show the save dialog and handle the file saving process
    private void showSaveDialog(JFileChooser fileSelector) {
        int userSelection = fileSelector.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileSelector.getSelectedFile();
            bitmap.createBitmapFile(fileToSave.getAbsolutePath());
        }
    }
}
