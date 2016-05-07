import java.util.*;

interface Flyweight {
    public void operate(String exstate);
}

class FlyweightFactory {
    private HashMap<String,Flyweight> files = new HashMap<String,Flyweight>();
    public Flyweight factory(String state){
        Flyweight fly = files.get(state);
        if(fly==null){
            fly = new ConcreteFlyweight(state);
            files.put(state,fly);
            System.out.println("create flyweight"+state+" : "+fly);
        }
        return fly;
    }
}

class ConcreteFlyweight implements Flyweight {
    private String intrinsic;
    public ConcreteFlyweight(String s){
        this.intrinsic=s;
    }
    public void operate(String exstate){
        System.out.println("intrinsic : "+this.intrinsic+"\nextrinsic : "+exstate);
    }
}

public class testFlyweight {
    public static void main(String[] args) {
        FlyweightFactory ff = new FlyweightFactory();
        Flyweight fly=ff.factory("a");
        fly.operate("1");
        fly=ff.factory("b");
        fly.operate("2");
        fly=ff.factory("a");
        fly.operate("3");
    }
}
