import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
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
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
    private static IconEdit iconEdit = IconEdit.getInstance(); //Get the singleton instance of IconEdit
    private PButton[][] pixelButtons;//Array to hold the pixel buttons for easy access
    private boolean attached = true;

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

        pixelButtons = new PButton[rows][cols];//Initialize the pixel buttons array

        //Set up the JFrame
        setTitle("Bitmap GUI");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create grid layout for the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, cols));
        
        //Create a checkbox for advanced options
        JCheckBox advancedOptions = new JCheckBox("Advanced Options");
        JCheckBox syncCheckBox = new JCheckBox("Sync Windows", true);

        syncCheckBox.addActionListener(e -> {

        if (syncCheckBox.isSelected()) {
            attachToIconEdit();
        } else {
            detachFromIconEdit();
        }

        

});

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Pixels pixel = bitmap.getPixel(i, j );
                PButton button = new PButton(pixel, i, j);

                pixelButtons[i][j] = button;

                button.addMouseListener(new MouseListener() {
                    
                    //This method is in charge of when the user holds shift it paints wherever the cursor goes.
                    @Override 
                    public void mouseEntered(MouseEvent e) {
                        if (e.isShiftDown()) {
                            PButton hoveredButton = (PButton) e.getSource();

                            Color selectedColor = new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue());

                            sendEdit(hoveredButton.getRow(), hoveredButton.getCol(), selectedColor);

                            addToRecentColors(selectedColor);
                        }
                    }

                    @Override 
                    public void mouseExited(MouseEvent e) {

                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override 
                    public void mousePressed(MouseEvent e) {
                        
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        
                    }
                });

                panel.add(button);
                
    
                //Here is the action listener to listen for the button clicks.
                button.addActionListener(e -> {

                    if (advancedOptions.isSelected()){
                        String[] options = {"Fill", "Import", "Cancel"};

                        int choice = JOptionPane.showOptionDialog(this, "Choose what you would like to do:",
                         "Advanced Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                         if (choice == 0) {
                            //Ask the user how many rows and columns to fill
                            String rowsToFillInput = JOptionPane.showInputDialog(this, "Rows: ");
                            if (rowsToFillInput == null) {
                                return; //User cancelled with button
                            }

                        String colsToFillInput = JOptionPane.showInputDialog(this, "Columns: ");

                        if (colsToFillInput == null) {
                            return; //User cancelled with button
                        }

                        try {
                            int rowsToFill = Integer.parseInt(rowsToFillInput);
                            int colsToFill = Integer.parseInt(colsToFillInput);
                            //Error message
                            if (rowsToFill <= 0 || colsToFill <= 0) {
                                JOptionPane.showMessageDialog(this, "Rows and columns must be greater than 0!");
                            } else {
                            //Get the selected color from the RGB sliders
                            int red = redSlider.getValue();
                            int green = greenSlider.getValue();
                            int blue = blueSlider.getValue();

                            Color fillColor = new Color(red, green, blue);

                            //Remember the pixel where the user clicked
                            int startRow = button.getRow();
                            int startCol = button.getCol();

                            //Stop at the edges if necessary
                            //Use math to make sure we don't go out of bounds
                            int endRow = Math.min(rows, startRow + rowsToFill);
                            int endCol = Math.min(cols, startCol + colsToFill);

                            //Fill the entered in area
                            for (int row = startRow; row < endRow; row++) {
                                for (int col = startCol; col < endCol; col++) {
                                    bitmap.setPixel(row, col, red, green, blue);

                                    PButton pixelButton = (PButton)
                                    panel.getComponent(row * cols + col);

                                    pixelButton.setBackground(fillColor);
                                }
                            }

                            // Add the color to the recent colors list
                            addToRecentColors(fillColor);
                        }
                    }
                        
            catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Please enter valid whole numbers.");
                }
            }
        }

                    //Get the pixel associated with the clicked button
            else {
                  Color selectedColor = new Color(

                  //Use the sliders to get the RGB values for the pixel
                  redSlider.getValue(),
                  greenSlider.getValue(),
                  blueSlider.getValue()     
                  );

                  sendEdit(button.getRow(), button.getCol(), selectedColor);
                  addToRecentColors(selectedColor);                 
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

        //Create the New Window button
        JButton newWindowButton = new JButton("New Window");
        //Action Listener for the button
        newWindowButton.addActionListener(e-> {new BitmapGUI();});
        slidersPanel.add(newWindowButton);
        


        //Add a checkbox for advanced options
        slidersPanel.add(advancedOptions);


        //Add the main panel and sliders panel to the JFrame
        add(panel, BorderLayout.CENTER);
        add(slidersPanel, BorderLayout.EAST);

        slidersPanel.add(syncCheckBox);
        iconEdit.attach(this);
        //Do this last so that the GUI is visible after all the components are added and set up.
        setVisible(true);

        

    }
    //This method will be called by the IconEdit class to update the bitmap when a change occurs. -Observer pattern
    public void updateEdit(int row, int col, int red, int green, int blue) {
        //Update the pixels in the bitmap with the observer pattern
        bitmap.setPixel(row, col, red, green, blue);

        //Update the button's background color to reflect the change
        pixelButtons[row][col].setBackground(new Color(red, green, blue));
    }

    //
    public void attachToIconEdit() {
        if (!attached) {
            iconEdit.attach(this);
            attached = true;
        }
    }

    public void detachFromIconEdit() {
        if (attached) {
            iconEdit.detach(this);
            attached = false;
        }
    }

    private void sendEdit(int row, int col, Color color) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        updateEdit(row, col, red, green, blue);
        iconEdit.notifyObservers(this, row, col, red, green, blue);
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
                recentColorButtons[i].setEnabled(false);
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
