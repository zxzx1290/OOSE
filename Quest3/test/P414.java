import java.util.*;

interface Observer {
    public void update(Observable o, Object arg);
}

class HistogramChart implements Observer {
    public void update(Observable o, Object arg){
        //process event
        System.out.println(arg);
    }
}

class RadarChart implements Observer {
    public void update(Observable o, Object arg){
        //process event
        System.out.println(arg);
    }
}

class Observable {
    private ArrayList<Observer> observers = new ArrayList<>();
    public void add(Observer o){
        observers.add(o);
    }
    public void remove(Observer o){
        //remove observer
    }
    public void notifyObservers(Object arg){
        for(Observer observer : observers){
            observer.update(this,arg);
        }
    }
    public void setChange(){
        //Marks this Observable object as having been changed; the hasChanged method will now return true.
    }
}

class DataTable extends Observable {
    private ArrayList<Object> objects = new ArrayList<>();
    public void addData(Object o){
        objects.add(o);
        notifyObservers(o);
    }
    public DataTable(){
        add(new HistogramChart());
        add(new RadarChart());

        addData(new Object());
        addData(new Object());
    }
}

class P414 {
    public static void main(String[] args) {
        new DataTable();
    }
}
