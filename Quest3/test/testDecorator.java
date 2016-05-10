interface Component {
    public String getMessage();
}

class ConcreteComponent implements Component {
    private String message;
    public ConcreteComponent(String s){
        this.message=s;
    }
    public String getMessage(){
        return this.message;
    }
}

abstract class Decorator implements Component {
    protected Component c;
    public Decorator(Component c){
        this.c=c;
    }
}

class ConcreteDecorator1 extends Decorator{
    public ConcreteDecorator1(Component c){
        super(c);
    }
    public String getMessage(){
        return c.getMessage()+" + ConcreteDecorator1";
    }
}

class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component c){
        super(c);
    }
    public String getMessage(){
        return c.getMessage()+" + ConcreteDecorator2";
    }
}

public class testDecorator {
    public static void main(String[] args) {
        Component cc = new ConcreteComponent("ConcreteComponent");
        System.out.println(cc.getMessage());
        cc = new ConcreteDecorator1(cc);
        cc = new ConcreteDecorator2(cc);
        System.out.println(cc.getMessage());
    }
}
