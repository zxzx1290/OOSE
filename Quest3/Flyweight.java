import java.util.*;

interface FlyweightInterface {
    public void operation(String state);
}

class FlyweightFactory {
    private Map<String,FlyweightInterface> files = new HashMap<String,FlyweightInterface>();
    public FlyweightInterface factory(String state){
        FlyweightInterface fly = files.get(state);
        if(fly == null){
            fly = new ConcreteFlyweight(state);
            files.put(state,fly);
            System.out.println("FlyweightFactory create object"+state);
        }
        return fly;
    }
}

class ConcreteFlyweight implements FlyweightInterface {
    private String intrinsicState;
    public ConcreteFlyweight(String state) {
        this.intrinsicState = state;
    }
    public void operation(String state) {
        System.out.println("Intrinsic State = "+this.intrinsicState);
        System.out.println("Extrinsic State = "+state);
    }
}

public class Flyweight {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        FlyweightInterface fly;
        
        System.out.println("Try Get Object a");
        fly = factory.factory("a");
        fly.operation("red call\n");

        System.out.println("Try Get Object b");
        fly = factory.factory("b");
        fly.operation("blue call\n");

        System.out.println("Try Get Object a");
        fly = factory.factory("a");
        fly.operation("black call\n");
    }
}
