import java.awt.Color;

public class VerticalInvert implements BitMapEdit {

    private BitMapEdit edit;
    private int height;

    public VerticalInvert(BitMapEdit edit, int height) {
        this.edit = edit;
        this.height = height;
    }

    @Override
    public int getRow() {
        return height - 1 - edit.getRow();
    }

    @Override
    public int getCol() {
        return edit.getCol();
    }

    @Override
    public Color getColor() {
        return edit.getColor();
    }
}