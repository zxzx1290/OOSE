import java.util.ArrayList;

interface Iterator{
    public boolean hasNext();
    public Object next();
}
interface Menu{
    public Iterator iterator();
}
class VegetableIterator implements Iterator{
    private String[] menuItems;
    private int position=0;
    public VegetableIterator(String[] mu){
        this.menuItems=mu;
    }
    public boolean hasNext(){
        if(menuItems == null || position >= menuItems.length || menuItems[position] == null){
            return false;
        }
        return true;
    }
    public Object next(){
        if(menuItems != null && position < menuItems.length){
            return menuItems[position++];
        }
        return null;
    }
}
class MeatIterator implements Iterator{
    private ArrayList<String> menuItems;
    private int position=0;
    public MeatIterator(ArrayList<String> mu){
        this.menuItems=mu;
    }
    public boolean hasNext(){
        if(menuItems == null || menuItems.size() <= position || menuItems.get(position) == null){
            return false;
        }
        return true;
    }
    public Object next(){
        if(menuItems != null && menuItems.size() > position){
            return menuItems.get(position++);
        }
        return null;
    }
}
class VegetableMenu implements Menu{
    private String[] menuItems;
    private int position=0;
    private int MAX_SIZE=10;
    public VegetableMenu(){
        menuItems=new String[MAX_SIZE];
        addItem("Vegetable1");
        addItem("Vegetable2");
    }
    public void addItem(String s){
        if(position >= MAX_SIZE){
            System.out.println("full!");
        }else{
            menuItems[position++]=s;
        }
    }
    public Iterator iterator(){
        return new VegetableIterator(menuItems);
    }
}
class MeatMenu implements Menu{
    private ArrayList<String> menuItems;
    public MeatMenu(){
        menuItems=new ArrayList<String>();
        addItem("Meat1");
        addItem("Meat2");
    }
    public void addItem(String s){
        menuItems.add(s);
    }
    public Iterator iterator(){
        return new MeatIterator(menuItems);
    }
}
public class testIterator{
    public static void main(String[] args) {
        VegetableMenu vm = new VegetableMenu();
        MeatMenu mm = new MeatMenu();
        printMenu(vm);
        printMenu(mm);
    }
    public static void printMenu(Menu m){
        Iterator i = m.iterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }
    }
}
