import java.util.ArrayList;

public class Pixels{
    //Constructor that sets the RGB values.
    public Pixels(int r, int g, int b){
        setRed(r);
        setGreen(g);
        setBlue(b);
    }
    //Created a private int variable to hold the RGB value instead of having seperate values (RGB).
    private int rgb = 0;

    //r should be between 0 - 255 (Bounds checker for Red Value)
    public void setRed(int r){
        if(r < 0){
            r = 0;
        }
        if(r > 255){
            r = 255;
        }
        //I cleared the red value from the rgb variable and set it to the new value.
        //I also used bitwise operators to put the new value in the correct positon of 0.
        //0-7 bits are red.
        rgb = (rgb & 0xFFFFFF00) | ((r & 0xFF) << 0); 
    }   
    //Will return between 0 - 255 
    public int getRed(){
        // I used bitwise operators to get the red value.
        return (rgb >> 0) & 0xFF;
    }        
    //g should be between 0 - 255  (Bounds checker for Green Value)
    public void setGreen(int g) {
        if (g < 0) {
            g = 0;
        }
        if (g > 255){
            g = 255;
        }
        //I cleared the green value from the rgb variable and set it to the new value.
        //I also used bitwise operators to put the new value in the correct position of 8.
        //8-15 bits are green.
        rgb = (rgb & 0xFF00FF00) | ((g & 0xFF) << 8);
    }
    //Will return between 0 - 255 
    public int getGreen(){
        // I used bitwise operators to get the green value.
        return (rgb >> 8) & 0xFF;
    }      
    //b should be between 0 - 255 (Bounds checker for Blue Value)
    public void setBlue(int b){
        if (b < 0) {
            b = 0;
        }
        if (b > 255){
            b = 255;
        }
        //I cleared the blue value from the rgb variable and set it to the new value.
        //I also used bitwise operators to put the new value in the correct position of 16.
        //16-23 bits are blue.
        rgb = (rgb & 0xFFFF00FF) | ((b & 0xFF) << 16);
    }  
    //will return between 0 - 255
    public int getBlue(){
        // I used bitwise operators to get the blue value.
       return (rgb >> 16) & 0xFF;
    }
    //Same as lab (No change to this method from my lab)       
    public String toString() {
        return "rgb(" + getRed() + "," + getGreen() + "," + getBlue() + ")";
    }
    
    /**
     * Hex string Method
     
     * From what I took notes on in class during questioning, I decided to use the String Builder.
     * I also used the Integer.toHexString() method to convert the RGB values to hex.
     * I added a check to see if the value is less than 16, and if it was, added a 0 to the front of the value.
     */

    public String toStringHex() {
     StringBuilder builder = new StringBuilder();
     builder.append("#");
     if (getRed() < 16) {
        builder.append("0");
     }
     builder.append(Integer.toHexString(getRed()));
     if (getGreen() < 16) {
        builder.append("0");
     }
     builder.append(Integer.toHexString(getGreen()));
     if (getBlue() < 16) {
        builder.append("0");
     }
     builder.append(Integer.toHexString(getBlue()));

     //returns the hex value we got into string format.
     return builder.toString(); //possibly make it uppercase later on 
    }
}

    /*private boolean isRealRGBValue(int val){
            if (isRealRGBValue(b)) {
                rgb = //???;
            }

    }*/

class Icon {
    //Created a private 2D ArrayList hold the Pixel objects
    private ArrayList<ArrayList<Pixels>> pixels;

    //I made a constructor that initializes the 2D ArrayList with pixel objects 
    public Icon() {
        pixels = new ArrayList<ArrayList<Pixels>>();
    
    for (int i = 0; i < 40; i++ ) {
        ArrayList<Pixels> row = new ArrayList<Pixels>();

        for (int j = 0; j < 40; j++){
            row.add(new Pixels(0, 0, 0));
        }
        pixels.add(row);
        }
    }
    public Icon(int rows, int cols) {
        pixels = new ArrayList<ArrayList<Pixels>>();

        for (int i = 0; i < rows; i++ ) {
            ArrayList<Pixels> row = new ArrayList<Pixels>();

            for (int j = 0; j < cols; j++){
                row.add(new Pixels(0, 0, 0));
        }
        pixels.add(row);
        }
        
    }
    //Gets pixel at a specific row w/ column then returns it.
    public Pixels getPixel(int row, int col) {
        return pixels.get(row).get(col);
    }
    //Sets the pixel at a specific row and column to the pixel object passed in by reference.
    public void setPixel(int row, int col, int r, int g, int b) {
        pixels.get(row).set(col, new Pixels(r, g, b));
    }


    public void setRed(int row, int col, int red) {
        if (row >= 0 && row < pixels.size() && col >= 0 && col < pixels.get(row).size()) {
            Pixels pixel = pixels.get(row).get(col);
            pixel.setRed(red);
        }
    }
    public void setGreen(int row, int col, int green) {
        if (row >= 0 && row < pixels.size() && col >= 0 && col < pixels.get(row).size()) {
            Pixels pixel = pixels.get(row).get(col);
            pixel.setGreen(green);
        }
    }
    public void setBlue(int row, int col, int blue) {
        if (row >= 0 && row < pixels.size() && col >= 0 && col < pixels.get(row).size()) {
            Pixels pixel = pixels.get(row).get(col);
            pixel.setBlue(blue);
        }
    }

    public int getRed(int row, int col){
        if (row >= 0 && row < pixels.size() && col >= 0 && col < pixels.get(row).size()) {
            Pixels pixel = pixels.get(row).get(col);
            return pixel.getRed();
        }
        //I added this return to return an invalid value if the row and col are out of bounds.
        return -1;
    }
    public int getGreen(int row, int col){
        if (row >= 0 && row < pixels.size() && col >= 0 && col < pixels.get(row).size()) {
            Pixels pixel = pixels.get(row).get(col);
            return pixel.getGreen();
        }
        return -1;
    }
    public int getBlue(int row, int col){
        if (row >= 0 && row < pixels.size() && col >= 0 && col < pixels.get(row).size()) {
            Pixels pixel = pixels.get(row).get(col);
            return pixel.getBlue();
        }
        return -1;
    }

    public String toString() {
       String result = "";
       for (int i = 0; i < pixels.size(); i++) {
            for (int j = 0; j < pixels.get(i).size(); j++) {
                result += pixels.get(i).get(j).toStringHex() + " ";
            }
            result += "\n";
        }
        return result;
    }
    public void convertLittleEndian2 (int val, ArrayList<Byte>){
        fileBytes.add((byte)val);
        fileBytes.add((byte)val>>8);
        fileBytes.add((byte)(val>>16));
        fileBytes.add((byte)(val>>24));
    }
    public void createBitmapFile() {
    //Calculate file size
    int width = pixels.get(0).size();
    int height = pixels.size();
    //int fileSize = 




    }


}
