import java.util.ArrayList;

interface IteratorInterface{
    //Iterator
    boolean hasNext();
    Object next();
}

interface Menu{
    //Aggregate
    public IteratorInterface iterator();
}

class MeatIterator implements IteratorInterface{
    //Iterator1
    private String[] menuItems;
    private int position = 0;

    public MeatIterator(String[] mu){
        menuItems=mu;
    }
    public boolean hasNext(){
        if(menuItems == null || position >= menuItems.length || menuItems[position] == null){
            return false;
        }
        return true;
    }
    public String next(){
        if(menuItems != null && position < menuItems.length){
            String menuItem = (String)menuItems[position++];
            return menuItem;
        }
        return null;
    }
}

class VegetableIterator implements IteratorInterface{
    //Iterator2
    private ArrayList<String> menuItems;
    private int position = 0;

    public VegetableIterator(ArrayList<String> mu){
        menuItems=mu;
    }
    public boolean hasNext(){
        if(menuItems == null || position >= menuItems.size() || menuItems.get(position) == null){
            return false;
        }
        return true;
    }
    public String next(){
        if(menuItems != null && position < menuItems.size()){
            String menuItem = (String)menuItems.get(position++);
            return menuItem;
        }
        return null;
    }
}

class MeatMenu implements Menu{
    //Aggregate1
    static final int MAX_ITEMS = 10;
    int currentIndex = 0;
    String[] menuItems;

    public MeatMenu(){
        menuItems = new String[MAX_ITEMS];
        addItem("Meat1");
        addItem("Meat2");
    }
    public void addItem(String meat){
        if(currentIndex >= MAX_ITEMS){
            System.out.println("Meat is full !!");
        }else{
            menuItems[currentIndex++] = meat;
        }
    }
    public String[] getMenuItems(){
        return menuItems;
    }
    public IteratorInterface iterator(){
        return new MeatIterator(menuItems);
    }
}

class VegetableMenu implements Menu{
    //Aggregate2
    ArrayList<String> menuItems;
    public VegetableMenu(){
        menuItems = new ArrayList<String>();
        addItem("Vegetable1");
        addItem("Vegetable2");
    }
    public void addItem(String vegetable){
        menuItems.add(vegetable);
    }
    public ArrayList<String> getMenuItems(){
        return menuItems;
    }
    public IteratorInterface iterator(){
        return new VegetableIterator(menuItems);
    }
}

public class Iterator{
    public static void main(String[] args) {
        MeatMenu meatMenu = new MeatMenu();
        VegetableMenu vegetableMenu = new VegetableMenu();
        printMenu(meatMenu);
        printMenu(vegetableMenu);
    }
    public static void printMenu(Menu m){
        IteratorInterface it = m.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
