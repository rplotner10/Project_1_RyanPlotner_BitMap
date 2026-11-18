import java.awt.Color;

public class VerticalInvert implements BitMapEdit {

    private BitMapEdit edit;
    private int height;

    //Constructor
    public VerticalInvert(BitMapEdit edit, int height) {
        this.edit = edit;
        this.height = height;
    }
    //We need to gather the values of the row so we can vertically invert.
    @Override
    public int getRow() {
        return height - 1 - edit.getRow();
    }

    //Getting values for this should not change for this class
    @Override
    public int getCol() {
        return edit.getCol();
    }

    //Getting values for this should not change for this class
    @Override
    public Color getColor() {
        return edit.getColor();
    }
}