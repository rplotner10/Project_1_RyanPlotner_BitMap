
public class Driver {
    public static void main(String[] args) {
        //create an icon
        Icon icon = new Icon(5, 5);
        
        //update the corner pixels
        //icon.setPixel(row#, col#, red-val, green-val, blue-val)
        icon.setPixel(0, 0, 255, 0, 0); //red
        icon.setPixel(0, 4, 0, 255, 0); //green
        icon.setPixel(4, 0, 0, 0, 255); //blue
        icon.setPixel(4, 4, 255, 255, 255); //white
    
        //save the icon as a bitmap file in the current working directory
        //icon.createBitmapFile(filename)
        icon.createBitmapFile("icon.bmp");
    }
}