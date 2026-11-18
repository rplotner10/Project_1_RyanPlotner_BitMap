import java.awt.Color;

public class HorizontalInvertBitmapEdit implements BitmapEdit {

    private BitmapEdit edit;
    private int width;

    //Constructor
    public HorizontalInvertBitmapEdit(BitmapEdit edit, int width){
        this.edit = edit;
        this.width = width;
    }
    //Getting values for this should not change for this class
    @Override
    public int getRow(){
        return edit.getRow();
    }

    //We need to gather the values of the row so we can vertically invert.
    @Override
    public int getCol(){
        return width - 1 - edit.getCol();
    }
    //Getting values for this should not change for this class
    @Override
    public Color getColor(){
        return edit.getColor();
    }
}
