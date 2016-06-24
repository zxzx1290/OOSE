import java.util.*;
abstract class UserCommand {
    public abstract void execute();
    public void undo(){ System.out.println("undo"); }
}

class EvalResult extends UserCommand {
    private DataBase d;
    EvalResult(DataBase d){
        this.d=d;
    }
    public void execute(){
        d.evalResult();
    }
}

class FormatDiagram extends UserCommand {
    private DataBase d;
    FormatDiagram(DataBase d){
        this.d=d;
    }
    public void execute(){
        d.formatDiagram();
    }
}

class PrintResult extends UserCommand {
    private DataBase d;
    PrintResult(DataBase d){
        this.d=d;
    }
    public void execute(){
        d.printResult();
    }
}

abstract class MacroCommand extends UserCommand {
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

class VerboseMode extends MacroCommand {
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

class SuccinctMode extends MacroCommand {
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

abstract class Factory {
    protected Invoker in;
    protected DataBase database;
    protected void makeUserCommand(String c){
        switch(c){
            case "EvalResult": in.setCommand(new EvalResult(database)); in.run(); break;
            case "FormatDiagram": in.setCommand(new FormatDiagram(database)); in.run(); break;
            case "PrintResult": in.setCommand(new PrintResult(database)); in.run(); break;
            case "VerboseMode": in.setCommand(new VerboseMode(database)); in.run(); break;
            case "SuccinctMode": in.setCommand(new SuccinctMode(database)); in.run(); break;
            default: System.out.println("error"); break;
        }
    }
}

class RDBCommandsFactory extends Factory{
    RDBCommandsFactory(){
        in = new Invoker();
        database = new RDB();
    }
}

class LDAPCommandsFactory extends Factory{
    LDAPCommandsFactory(){
        in = new Invoker();
        database = new LDAP();
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
    //receiver
    RDB(){
        System.out.println("new RDB");
    }
    public void evalResult(){
        System.out.println("RDB EvalResult");
    }
    public void formatDiagram(){
        System.out.println("RDB FormatDiagram");
    }
    public void printResult(){
        System.out.println("RDB PrintResult");
    }
    public void promptUserVerbose(){
        System.out.println("RDB Verbose promptUser");
    }
    public void promptUserSuccinct(){
        System.out.println("RDB Succinct promptUser");
    }
    public void retrieveInput(){
        System.out.println("RDB retrieveInput");
    }
    public void connectDB(){
        System.out.println("RDB connectDB");
    }
    public void diagnosticsVerbose(){
        System.out.println("RDB Verbose diagnostics");
    }
    public void diagnosticsSuccinct(){
        System.out.println("RDB Succinct diagnostics");
    }
    public void disconnectDB(){
        System.out.println("RDB disconnectDB");
    }
}

class LDAP extends DataBase {
    LDAP(){
        System.out.println("new LDAP");
    }
    public void evalResult(){
        System.out.println("LDAP EvalResult");
    }
    public void formatDiagram(){
        System.out.println("LDAP FormatDiagram");
    }
    public void printResult(){
        System.out.println("LDAP PrintResult");
    }
    public void promptUserVerbose(){
        System.out.println("LDAP Verbose promptUser");
    }
    public void promptUserSuccinct(){
        System.out.println("LDAP Succinct promptUser");
    }
    public void retrieveInput(){
        System.out.println("LDAP retrieveInput");
    }
    public void connectDB(){
        System.out.println("LDAP connectDB");
    }
    public void diagnosticsVerbose(){
        System.out.println("LDAP Verbose diagnostics");
    }
    public void diagnosticsSuccinct(){
        System.out.println("LDAP Succinct diagnostics");
    }
    public void disconnectDB(){
        System.out.println("LDAP disconnectDB");
    }
}

class Invoker {
    private ArrayList<UserCommand> arr = new ArrayList<>();
    private UserCommand c;
    public void setCommand(UserCommand c){
        this.c=c;
    }
    public void undoImp(){
        /*undo Implemeation*/
    }
    public void run(){
        arr.add(c);
        c.execute();
    }
}

class Q41 {
    public static void main(String[] args) {
        Factory factory = new RDBCommandsFactory();
        factory.makeUserCommand("PrintResult");
        factory.makeUserCommand("FormatDiagram");
        factory.makeUserCommand("SuccinctMode");
        Factory factory2 = new LDAPCommandsFactory();
        factory2.makeUserCommand("PrintResult");
        factory2.makeUserCommand("FormatDiagram");
        factory2.makeUserCommand("VerboseMode");
    }
}
