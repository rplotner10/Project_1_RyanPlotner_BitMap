import java.util.ArrayList;

public class IconEdit {

    private ArrayList<BitmapGUI> observers = new ArrayList<>();
    //Singleton Instance of the IconEdit class
    private static IconEdit instance = new IconEdit();


    public static IconEdit getInstance() {
        return instance;
    }
    //Attatch an observer
    public void attach(BitmapGUI observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    //Detach an observer
    public void detach(BitmapGUI observer) {
        observers.remove(observer);

    }

    //Notify all observers of a change in the icon
    public void notifyObservers(BitmapGUI source, int row, int col, int red, int green, int blue) {
        for (BitmapGUI observer : observers) {
            observer.updateEdit(row, col, red, green, blue);
            if (observer != source){
                observer.updateEdit(row, col, red, green, blue);
            }
        }
    }

}