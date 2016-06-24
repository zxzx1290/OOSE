abstract class UserCommand {
    public abstract void execute();
    public void undo(){ System.out.println("undo"); };
}

class EvalResult extends UserCommand {
    public void execute(){
        System.out.println("exec EvalResult");
    }
}

class FormatDiagram extends UserCommand {
    public void execute(){
        System.out.println("exec FormatDiagram");
    }
}

class PrintResult extends UserCommand {
    public void execute(){
        System.out.println("exec PrintResult");
    }
}

abstract class MacroCommand extends UserCommand {
    public void execute(){
        promptUser();
        retrieveInput();
        connectDB();
        diagnostics();
        disconnectDB();
    }
    public void promptUser(){
        System.out.println("exec promptUser");
    }
    public void diagnostics(){
        System.out.println("exec diagnostics");
    }
    public abstract void retrieveInput();
    public abstract void connectDB();
    public abstract void disconnectDB();
}

class VerboseMode extends MacroCommand {
    public void retrieveInput(){
        System.out.println("exec retrieveInput VerboseMode");
    }
    public void connectDB(){
        System.out.println("exec connectDB VerboseMode");
    }
    public void disconnectDB(){
        System.out.println("exec disconnectDB VerboseMode");
    }
}

class SuccinctMode extends MacroCommand {
    public void retrieveInput(){
        System.out.println("exec retrieveInput SuccinctMode");
    }
    public void connectDB(){
        System.out.println("exec connectDB SuccinctMode");
    }
    public void disconnectDB(){
        System.out.println("exec disconnectDB SuccinctMode");
    }
}

class Factory {
    private Invoker in;
    Factory(){
        in = new Invoker();
    }
    public void makeUserCommand(String c){
        switch(c){
            case "EvalResult": in.setCommand(new EvalResult()); in.run(); break;
            case "FormatDiagram": in.setCommand(new FormatDiagram()); in.run(); break;
            case "PrintResult": in.setCommand(new PrintResult()); in.run(); break;
            case "VerboseMode": in.setCommand(new VerboseMode()); in.run(); break;
            case "SuccinctMode": in.setCommand(new SuccinctMode()); in.run(); break;
            default: System.out.println("error"); break;
        }
    }
}

class RDB {
    RDB(){
        System.out.println("new RDB");
    }
}

class LDAP {
    LDAP(){
        System.out.println("new LDAP");
    }
}

class Invoker {
    private UserCommand c;
    public void setCommand(UserCommand c){
        this.c=c;
    }
    public void run(){
        c.execute();
    }
}

class Q41_O {
    public static void main(String[] args) {
        Factory factory = new Factory();
        factory.makeUserCommand("PrintResult");
    }
}
