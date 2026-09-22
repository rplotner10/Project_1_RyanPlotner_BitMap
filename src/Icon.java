import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.IOException;

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
    public void convertLittleEndian2 (int val, ArrayList<Byte> fileBytes) {
        fileBytes.add((byte)(val & 0xFF));
        fileBytes.add((byte)((val >> 8) & 0xFF));
        fileBytes.add((byte)((val >> 16) & 0xFF));
        fileBytes.add((byte)((val >> 24) & 0xFF));
    }

    public void createBitmapFile(String filename) {
    //Calculate file size
    int width = pixels.get(0).size();
    int height = pixels.size();
    int fileSize = 54 + ((width * 3 + (4 - (width * 3) % 4) % 4) * height);
    int rowSizeWithoutPadding = width * 3;
    int padding = (4 - (rowSizeWithoutPadding % 4)) % 4;
    int imageSize = (rowSizeWithoutPadding + padding) * height;

    ArrayList<Byte> fileBytes = new ArrayList<Byte>();
    //convertLittleEndian2(fileSize, fileSizeBytes);

    //Here is where I wrote the 14 byte header
    //Going off the layout presented in class, I was able to write the header layout.
    //These first 2 reserved bytes are the signature for a bitmap file essentially.
    fileBytes.add((byte) 'B');
    fileBytes.add((byte) 'M');
    //The next 4 bytes I wrote are in little endian format. Bitwise operations helped bitshifting to correct order.
    fileBytes.add((byte) (fileSize & 0xFF));
    fileBytes.add((byte) ((fileSize >> 8) & 0xFF));
    fileBytes.add((byte) ((fileSize >> 16) & 0xFF));
    fileBytes.add((byte) ((fileSize >> 24) & 0xFF));
    //The next 4 bytes are reserved and set to 0.
    fileBytes.add((byte)0);
    fileBytes.add((byte)0);
    fileBytes.add((byte)0);
    fileBytes.add((byte)0);
    //The next 4 were tricky, but they just offset the pixel data from the start of the file.
    fileBytes.add((byte) (54 & 0xFF));
    fileBytes.add((byte) ((54 >> 8) & 0xFF));
    fileBytes.add((byte) ((54 >> 16) & 0xFF));
    fileBytes.add((byte) ((54 >> 24) & 0xFF));


    //Here is where I wrote the 40 byte DIB header.
    //The first 4 bytes are the size of the DIB header. (40 bytes)
    fileBytes.add((byte) (40 & 0xFF));
    fileBytes.add((byte) ((40 >> 8) & 0xFF));
    fileBytes.add((byte) ((40 >> 16) & 0xFF));
    fileBytes.add((byte) ((40 >> 24) & 0xFF));

    fileBytes.add((byte) width);
    fileBytes.add((byte) (width >> 8));
    fileBytes.add((byte) (width >> 16));
    fileBytes.add((byte) (width >> 24));

    fileBytes.add((byte) height);
    fileBytes.add((byte) (height >> 8));
    fileBytes.add((byte) (height >> 16));
    fileBytes.add((byte) (height >> 24));

    fileBytes.add((byte) 1);
    fileBytes.add((byte) 0);
    fileBytes.add((byte) 24);
    fileBytes.add((byte) 0);

    fileBytes.add((byte) imageSize);
    fileBytes.add((byte) (imageSize >> 8));
    fileBytes.add((byte) (imageSize >> 16));
    fileBytes.add((byte) (imageSize >> 24));

    //
    fileBytes.add((byte) 0);
    fileBytes.add((byte) 0);
    fileBytes.add((byte) 0);
    fileBytes.add((byte) 0);

    //
    fileBytes.add((byte) 0);
    fileBytes.add((byte) 0);
    fileBytes.add((byte) 0);
    fileBytes.add((byte) 0);
    

    //Wrote pixel data with a nested for loop
    //It's nested because I wanted it to go through each col and row of the 2D arraylist.
    //The first line I used -- because the bitmap file stores the pixel data upside down.
    for (int row = height - 1; row >= 0; row--){ 
        for (int col = 0; col < width; col++){
            Pixels p = pixels.get(row).get(col);

            //I used BGR because it has to be backwards for the file format.
            fileBytes.add((byte)p.getBlue());
            fileBytes.add((byte)p.getGreen());
            fileBytes.add((byte)p.getRed());
        }
        //padding bytes
            for (int i = 0; i < padding; i++){
                fileBytes.add((byte)0);
            }
    }

    try (FileOutputStream fos = new FileOutputStream(filename)){
        for (byte b : fileBytes){
            fos.write(b);
        }
    } catch (IOException e){
    e.printStackTrace();
    }
  }
}