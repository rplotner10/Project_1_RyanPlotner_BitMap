
public class Driver {
    public static void main(String[] args) {
        //Create a 5x5 icon 
         Icon icon = new Icon(5, 5);

        //Top-left would be red
        icon.setPixel(0, 0, 255, 0, 0);

        //Top-right would be green
        icon.setPixel(0, 4, 0, 255, 0);

        // Bottom-left would be blue
        icon.setPixel(4, 0, 0, 0, 255);

        //The black pixel would be bottom-right too but we put nothing in it.
        System.out.println(icon);

        icon.createBitmapFile("icon.bmp");

       
    }
}