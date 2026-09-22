
public class Driver {
    public static void main(String[] args) {
        //create an icon
        Icon icon = new Icon(2, 2);
        
        icon.setPixel(0, 0, 0, 0, 255); //blue
        icon.setPixel(0, 1, 0, 255, 0); //green
        icon.setPixel(1, 0, 255, 0, 0); //red
        icon.setPixel(1, 1, 255, 255, 255); //white
    
        //save the icon as a bitmap file in the current working directory
        //icon.createBitmapFile(filename)
        icon.createBitmapFile("icon.bmp");
    }
}