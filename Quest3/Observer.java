import java.util.*;

interface Subject {
    public void registerObserver(ObserverInterface o);
    public void removeObserver(ObserverInterface o);
    public void notifyAllObserver();
}

interface ObserverInterface {
    public void update(Subject s);
}

class HeadHunter implements Subject {
    private ArrayList<ObserverInterface> userList = new ArrayList<ObserverInterface>();
    private ArrayList<String> jobs = new ArrayList<String>();
    public void registerObserver(ObserverInterface o) {
        userList.add(o);
    }
    public void removeObserver(ObserverInterface o) {
        userList.remove(o);
    }
    public void notifyAllObserver(){
        for (ObserverInterface o : userList) {
            o.update(this);
        }
    }
    public void addJob(String j){
        this.jobs.add(j);
        notifyAllObserver();
    }
    public String toString() {
        //ArrayList auto/manually toString
        return jobs.toString();
    }
}

class JobSeeker implements ObserverInterface {
    private String name;
    public JobSeeker(String name) {
        this.name = name;
    }
    public void update(Subject s) {
        System.out.println(this.name+" got notified!");
        //Subject s do something
        System.out.println(s.toString());
    }
}

public class Observer {
    public static void main(String[] args) {
        HeadHunter h = new HeadHunter();
        h.registerObserver(new JobSeeker("Mike"));
        h.registerObserver(new JobSeeker("Chris"));
        h.registerObserver(new JobSeeker("Jeff"));
        h.addJob("Google Job");
        h.addJob("Yahoo Job");
    }
}
