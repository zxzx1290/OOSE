interface CommandInterface{
    //Command
    public void execute();
}

class RemoteControl {
    //Invoker
    CommandInterface slot;
    public void setCommand(CommandInterface command){
        this.slot=command;
    }
    public void buttonWasPressed(){
        slot.execute();
    }
}

class LightOffCommand implements CommandInterface{
    //ConcreteCommand
    Light light;
    LightOffCommand(Light light){
        this.light=light;
    }
    public void execute(){
        light.off();
    }
}

class LightOnCommand implements CommandInterface{
    //ConcreteCommand
    Light light;
    LightOnCommand(Light light){
        this.light=light;
    }
    public void execute(){
        light.on();
    }
}

class TVOffCommand implements CommandInterface{
    //ConcreteCommand
    TV tv;
    TVOffCommand(TV tv){
        this.tv=tv;
    }
    public void execute(){
        tv.off();
    }
}

class TVOnCommand implements CommandInterface{
    //ConcreteCommand
    TV tv;
    TVOnCommand(TV tv){
        this.tv=tv;
    }
    public void execute(){
        tv.on();
    }
}

class Light{
    //receiver
    public void on(){
        System.out.println("Light On");
    }
    public void off(){
        System.out.println("Light off");
    }
}

class TV{
    //receiver
    public void on(){
        System.out.println("TV On");
    }
    public void off(){
        System.out.println("TV Off");
    }
}

class Command{
    public static void main(String[] args) {
        Light light = new Light();
        TV tv = new TV();

        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        TVOnCommand tvOnCommand = new TVOnCommand(tv);
        TVOffCommand tvOffCommand = new TVOffCommand(tv);

        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(lightOnCommand);
        remoteControl.buttonWasPressed();
        remoteControl.setCommand(lightOffCommand);
        remoteControl.buttonWasPressed();
        remoteControl.setCommand(tvOnCommand);
        remoteControl.buttonWasPressed();
        remoteControl.setCommand(tvOffCommand);
        remoteControl.buttonWasPressed();
    }
}
