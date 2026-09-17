import java.util.ArrayList;

public class Pixels{
    //Created a private int variable to hold the RGB value using bitwise operations
    private int rgb = 0;

    // r should be between 0 - 255 
    public void setRed(int r){
        if(r < 0){
            r = 0;
        }
        if(r > 255){
            r = 255;
        }
        
        rgb = (rgb & 0xFFFFFF00) | ((r & 0xFF) << 0); 
    }   
     // will return between 0 - 255 
    public int getRed(){
        return (rgb >> 0) & 0xFF;
    }        
    // g should be between 0 - 255 
    public void setGreen(int g) {
        if (g < 0) {
            g = 0;
        }
        if (g > 255){
            g = 255;
        }
        rgb = (rgb & 0xFF00FF00) | ((g & 0xFF) << 8);
    }
    // will return between 0 - 255 
    public int getGreen(){
        return (rgb >> 8) & 0xFF;
    }      
    // b should be between 0 - 255 
    public void setBlue(int b){
        if (b < 0) {
            b = 0;
        }
        if (b > 255){
            b = 255;
        }
        rgb = (rgb & 0xFFFF00FF) | ((b & 0xFF) << 16);
    }  
    // will return between 0 - 255
    public int getBlue(){
       return (rgb >> 16) & 0xFF;
    }
    //same as lab        
    public String toString() {
        return "rgb(" + getRed() + "," + getGreen() + "," + getBlue() + ")";
    }
    //hex string
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

     return builder.toString(); //possibly make it uppercase later on 
    }
}

    /*private boolean isRealRGBValue(int val){
            if (isRealRGBValue(b)) {
                rgb = //???;
            }

    }*/

class Icon{
    private int 
}
