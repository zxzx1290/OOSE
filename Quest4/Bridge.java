abstract class RemoteControl{
    //Abstraction
    protected TV tv;
    public abstract void on();
    public abstract void off();
    public abstract void next();
    RemoteControl(TV tv){
        this.tv=tv;
    }
    public void setTV(TV tv){
        this.tv=tv;
    }
}

class ConcreteRemote extends RemoteControl{
    //RefinedAbstraction
    ConcreteRemote(TV tv){
        super(tv);
    }
    public void on(){
        tv.turnOn();
    }
    public void off(){
        tv.turnOff();
    }
    public void next(){
        tv.nextChannel();
    }
}

interface TV{
    //Implementor
    public void turnOn();
    public void turnOff();
    public void nextChannel();
}

class Samung_tv implements TV{
    //ConcreteImplementor
    Samung_tv(){
        System.out.println("Samung_tv constructed");
    }
    public void turnOn(){
        System.out.println("Samung_tv turn on");
    }
    public void turnOff(){
        System.out.println("Samung_tv turn off");
    }
    public void nextChannel(){
        System.out.println("Samung_tv next Channel");
    }
}

class Sony_tv implements TV{
    //ConcreteImplementor
    Sony_tv(){
        System.out.println("Sony_tv constructed");
    }
    public void turnOn(){
        System.out.println("Sony_tv turn on");
    }
    public void turnOff(){
        System.out.println("Sony_tv turn off");
    }
    public void nextChannel(){
        System.out.println("Sony_tv next Channel");
    }
}

class Bridge{
    public static void main(String[] args) {
        RemoteControl remoteControl = new ConcreteRemote(new Samung_tv());
        remoteControl.on();
        remoteControl.next();
        remoteControl.off();

        remoteControl.setTV(new Sony_tv());
        remoteControl.on();
        remoteControl.next();
        remoteControl.off();
    }
}
