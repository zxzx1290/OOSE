interface Factory{
    //AbstractFactory
    public Mobile genMobile();
    public Pad genPad();
    public Notebook genNotebook();
}

class SamsungFactory implements Factory{
    //ConcreteFactory1
    public Mobile genMobile(){
        return new Galaxy();
    }
    public Pad genPad(){
        return new GalaxyNote();
    }
    public Notebook genNotebook(){
        return new SamsungNoteBook();
    }
}

class AppleFactory implements Factory{
    //ConcreteFactory2
    public Mobile genMobile(){
        return new Iphone();
    }
    public Pad genPad(){
        return new Ipad();
    }
    public Notebook genNotebook(){
        return new MacBook();
    }
}

interface Notebook{
    //AbstractProductA
    public void work();
}
class SamsungNoteBook implements Notebook{
    //AbstractProductA1
    public void work(){
        System.out.println("Using Samsung Notebook at work!");
    }
}
class MacBook implements Notebook{
    //AbstractProductA2
    public void work(){
        System.out.println("Using Macbook at work!");
    }
}

interface Mobile{
    //AbstractProductB
    public void call();
}
class Galaxy implements Mobile{
    //AbstractProductB1
    public void call(){
        System.out.println("I make phone calls with Samsung Galaxy!");
    }
}
class Iphone implements Mobile{
    //AbstractProductB2
    public void call(){
        System.out.println("I make phone calls with iPhone!");
    }
}

interface Pad{
    //AbstractProductC
    public void touch();
}
class GalaxyNote implements Pad{
    //AbstractProductC1
    public void touch(){
        System.out.println("Oh Yeah! I got the Samsung GalaxyNote");
    }
}
class Ipad implements Pad{
    //AbstractProductC2
    public void touch(){
        System.out.println("Oh Yeah! I got the iPad Air.");
    }
}

public class AbstractFactory{
    public static void main(String[] args) {
        Factory iFactory = new AppleFactory();
        Iphone iphone = (Iphone)iFactory.genMobile();
        iphone.call();
        MacBook mac = (MacBook)iFactory.genNotebook();
        mac.work();
        Ipad ipad = (Ipad)iFactory.genPad();
        ipad.touch();

        Factory sFactory = new SamsungFactory();
        Galaxy galaxy = (Galaxy)sFactory.genMobile();
        galaxy.call();
        SamsungNoteBook sun = (SamsungNoteBook)sFactory.genNotebook();
        sun.work();
        GalaxyNote note = (GalaxyNote)sFactory.genPad();
        note.touch();
    }
}
