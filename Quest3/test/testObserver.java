import java.util.ArrayList;

interface Subject {
    public void attatch(Observer o);
    public void detach(Observer o);
    public void nnotify();
}

interface Observer {
    public void update(Subject s);
}

class ConcreteSubject implements Subject {
    private ArrayList<String> states = new ArrayList<String>();
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    public void attatch(Observer o){
        observers.add(o);
    }
    public void detach(Observer o){ /*remove Observer*/ }
    public void addState(String s){
        states.add(s);
        nnotify();
    }
    public void nnotify(){
        for(Observer o : observers){
            o.update(this);
        }
    }
    public String toString(){
        return states.toString();
    }
}

class ConcreteObserver implements Observer {
    private String observerName;
    public ConcreteObserver(String s){
        this.observerName=s;
    }
    public void update(Subject s){
        System.out.println("Observer "+this.observerName+" updated , notified by Subject "+s);
    }
}

public class testObserver {
    public static void main(String[] args) {
        ConcreteSubject s = new ConcreteSubject();
        s.attatch(new ConcreteObserver("O1"));
        s.attatch(new ConcreteObserver("O2"));
        s.addState("S1");
        s.addState("S2");
    }
}
