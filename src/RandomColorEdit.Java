import java.awt.Color;
import java.util.Random;

public class RandomColorBitmapEdit implements BitmapEdit {

    private BitmapEdit edit;
    private Random random = new Random();

    //Constructor
    public RandomColorBitmapEdit(BitmapEdit edit) {
        this.edit = edit;
    }
    //Getting values for this should not change for this class
    @Override
    public int getRow() {
        return edit.getRow();
    }

    //Getting values for this should not change for this class
    @Override
    public int getCol() {
        return edit.getCol();
    }

    @Override
    public Color getColor() {
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        return new Color(red, green, blue);
    }
}