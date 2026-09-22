
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
        rgb = (rgb & 0xFFFF00FF) | ((g & 0xFF) << 8);
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
        rgb = (rgb & 0xFF00FFFF) | ((b & 0xFF) << 16);
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

    

