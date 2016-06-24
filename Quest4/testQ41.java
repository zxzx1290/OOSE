import java.util.*;

abstract class UserCommand {
    public abstract void execute();
    public void undo(){}
}

class evalResult extends UserCommand{
    private DataBase d;
    evalResult(DataBase d){
        this.d=d;
    }
    public void execute(){
        d.evalResult();
    }
}

class formatDiagram extends UserCommand{
    private DataBase d;
    formatDiagram(DataBase d){
        this.d=d;
    }
    public void execute(){
        d.formatDiagram();
    }
}

class printResult extends UserCommand {
    private DataBase d;
    printResult(DataBase d){
        this.d=d;
    }
    public void execute(){
        d.printResult();
    }
}

abstract class macroCommand extends UserCommand {
    protected DataBase d;
    public void execute(){
        promptUser();
        retrieveInput();
        connectDB();
        diagnostics();
        disconnectDB();
    }
    public abstract void promptUser();
    public abstract void diagnostics();
    public void retrieveInput(){
        d.retrieveInput();
    }
    public void connectDB(){
        d.connectDB();
    }
    public void disconnectDB(){
        d.disconnectDB();
    }
}

class VerboseMode extends macroCommand {
    VerboseMode(DataBase d){
        this.d=d;
    }
    public void promptUser(){
        d.promptUserVerbose();
    }
    public void diagnostics(){
        d.diagnosticsVerbose();
    }
}

class SuccinctMode extends macroCommand {
    SuccinctMode(DataBase d){
        this.d=d;
    }
    public void promptUser(){
        d.promptUserSuccinct();
    }
    public void diagnostics(){
        d.diagnosticsSuccinct();
    }
}

abstract class DataBase{
    public abstract void evalResult();
    public abstract void formatDiagram();
    public abstract void printResult();
    public abstract void promptUserVerbose();
    public abstract void promptUserSuccinct();
    public abstract void retrieveInput();
    public abstract void connectDB();
    public abstract void diagnosticsVerbose();
    public abstract void diagnosticsSuccinct();
    public abstract void disconnectDB();
}

class RDB extends DataBase {
    public void evalResult(){
        System.out.println("RDB evalResult");
    }
    public void formatDiagram(){
        System.out.println("RDB formatDiagram");
    }
    public void printResult(){
        System.out.println("RDB printResult");
    }
    public void promptUserVerbose(){
        System.out.println("RDB promptUserVerbose");
    }
    public void promptUserSuccinct(){
        System.out.println("RDB promptUserSuccinct");
    }
    public void retrieveInput(){
        System.out.println("RDB retrieveInput");
    }
    public void connectDB(){
        System.out.println("RDB connectDB");
    }
    public void diagnosticsVerbose(){
        System.out.println("RDB diagnosticsVerbose");
    }
    public void diagnosticsSuccinct(){
        System.out.println("RDB diagnosticsSuccinct");
    }
    public void disconnectDB(){
        System.out.println("RDB disconnectDB");
    }
}
class LDAP extends DataBase {
    public void evalResult(){
        System.out.println("LDAP evalResult");
    }
    public void formatDiagram(){
        System.out.println("LDAP formatDiagram");
    }
    public void printResult(){
        System.out.println("LDAP printResult");
    }
    public void promptUserVerbose(){
        System.out.println("LDAP promptUserVerbose");
    }
    public void promptUserSuccinct(){
        System.out.println("LDAP promptUserSuccinct");
    }
    public void retrieveInput(){
        System.out.println("LDAP retrieveInput");
    }
    public void connectDB(){
        System.out.println("LDAP connectDB");
    }
    public void diagnosticsVerbose(){
        System.out.println("LDAP diagnosticsVerbose");
    }
    public void diagnosticsSuccinct(){
        System.out.println("LDAP diagnosticsSuccinct");
    }
    public void disconnectDB(){
        System.out.println("LDAP disconnectDB");
    }
}

class Invoker {
    private ArrayList<UserCommand> arr = new ArrayList<>();
    private UserCommand c;
    public void run(){
        arr.add(c);
        c.execute();
    }
    public void undo(){
        /*implement undo*/
    }
    public void setCommand(UserCommand c){
        this.c=c;
    }
}

class CommandFactory {
    protected DataBase d;
    protected Invoker in;
    public void makeUserCommand(String s){
        switch (s){
            case "evalResult" : in.setCommand(new evalResult(d)); in.run(); break;
            case "formatDiagram" : in.setCommand(new formatDiagram(d)); in.run(); break;
            case "printResult" : in.setCommand(new printResult(d)); in.run(); break;
            case "VerboseMode" : in.setCommand(new VerboseMode(d)); in.run(); break;
            case "SuccinctMode" : in.setCommand(new SuccinctMode(d)); in.run(); break;
            default : System.out.println("Error");
        }
    }
}

class RDBCommandsFactory extends CommandFactory {
    RDBCommandsFactory(){
        in = new Invoker();
        d = new RDB();
    }
}

class LDAPCommandsFactory extends CommandFactory {
    LDAPCommandsFactory(){
        in = new Invoker();
        d = new LDAP();
    }
}

class testQ41 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please input DB name: ");
        String s;
        CommandFactory cf;
        while(true){
            s=sc.next();
            if(s.equals("RDB")){
                cf = new RDBCommandsFactory();
                break;
            }else if(s.equals("LDAP")){
                cf = new LDAPCommandsFactory();
            }else{
                System.out.println("Error , try again");
            }
        }
        System.out.print("Please input Command: ");
        while(true){
            s=sc.next();
            cf.makeUserCommand(s);
        }
    }
}
