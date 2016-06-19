interface Command{
    public void execute();
    public void undo();
    public void redo();
}

class RemoteControl{
    Command slot;
    Command previousCommand;
    public void setCommand(Command command){
        this.slot=command;
    }
    public void buttonWasPressed(){
        slot.execute();
        previousCommand=slot;
    }
    public void undoButtonWasPressed(){
        previousCommand.undo();
    }
    public void redoButtonWasPressed(){
        previousCommand.redo();
    }
}

class Light{
    public static final String ON="ON";
    public static final String OFF="OFF";
    String state="";
    public Light(String state){
        this.state=state;
    }
    public void on(){
        state=Light.ON;
    }
    public void off(){
        state=Light.OFF;
    }
    public Object getMemento(){
        return new Memento(this.state);
    }
    public void setMemento(Object obj){
        Memento memento = (Memento)obj;
        this.state=memento.getState();
    }
    public String toString(){
        return this.state;
    }
}

class Memento{
    private String state;
    Memento(String state){
        this.state=new String(state);
        System.out.println("New memento : "+this+" "+state);
    }
    public String getState(){
        System.out.println("get state from memento : "+this+" "+state);
        return this.state;
    }
    public String getStateSlient(){
        return this.state;
    }
}

class LightOnCommand implements Command{
    Light light;
    Object lightMemento;
    LightOnCommand(Light light){
        this.light=light;
    }
    public void execute(){
        lightMemento=light.getMemento();
        light.on();
        System.out.println("Now state : "+light+"\nNow state memento : "+((Memento)lightMemento)+" "+((Memento)lightMemento).getStateSlient());
    }
    public void undo(){
        Object undoMemento = light.getMemento();
        System.out.println("Now state "+light+" New undoMemento : "+undoMemento+" state : "+((Memento)undoMemento).getStateSlient());
        light.setMemento(lightMemento);
        lightMemento=undoMemento;
        System.out.println("Now state : "+light+"\nNow state memento : "+((Memento)lightMemento)+" "+((Memento)lightMemento).getStateSlient());
    }
    public void redo(){
        Object redoMemento = light.getMemento();
        System.out.println("Now state "+light+" New redoMemento : "+redoMemento+" state : "+((Memento)redoMemento).getStateSlient());
        lightMemento = redoMemento;
        light.setMemento(redoMemento);
        System.out.println("Now state : "+light+"\nNow state memento : "+((Memento)lightMemento)+" "+((Memento)lightMemento).getStateSlient());
    }
}

class LightOffCommand implements Command{
    Light light;
    Object lightMemento;
    LightOffCommand(Light light){
        this.light=light;
    }
    public void execute(){
        lightMemento=light.getMemento();
        light.off();
        System.out.println("Now state : "+light+"\nNow state memento : "+((Memento)lightMemento)+" "+((Memento)lightMemento).getStateSlient());
    }
    public void undo(){
        Object undoMemento = light.getMemento();
        System.out.println("Now state "+light+" New undoMemento : "+undoMemento+" state : "+((Memento)undoMemento).getStateSlient());
        light.setMemento(lightMemento);
        lightMemento=undoMemento;
        System.out.println("Now state : "+light+"\nNow state memento : "+((Memento)lightMemento)+" "+((Memento)lightMemento).getStateSlient());
    }
    public void redo(){
        Object redoMemento = light.getMemento();
        System.out.println("Now state "+light+" New redoMemento : "+redoMemento+" state : "+((Memento)redoMemento).getStateSlient());
        lightMemento = redoMemento;
        light.setMemento(redoMemento);
        System.out.println("Now state : "+light+"\nNow state memento : "+((Memento)lightMemento)+" "+((Memento)lightMemento).getStateSlient());
    }
}

class CommandWithMemento{
    public static void main(String[] args) {
        Light light = new Light(Light.OFF);

        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        RemoteControl remoteControl = new RemoteControl();
        System.out.println("exec on");
        remoteControl.setCommand(lightOnCommand);
        remoteControl.buttonWasPressed();
        System.out.println("\nexec off");
        remoteControl.setCommand(lightOffCommand);
        remoteControl.buttonWasPressed();
        System.out.println("\nexec undo");
        remoteControl.undoButtonWasPressed();
        System.out.println("\nexec redo");
        remoteControl.redoButtonWasPressed();
    }
}
