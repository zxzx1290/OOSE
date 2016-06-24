import java.util.*;

abstract class UserCommand
{
    abstract public void excute(DataBase db);
    abstract public void undo();
}
class EvalResult extends UserCommand
{
    public void excute(DataBase db)
    {
        db.evalResult();
    }
    public void undo()
    {
        System.out.println("undo command \"EvalResult\"");
    }
}
class FormatDiagram extends UserCommand
{
    public void excute(DataBase db)
    {
        db.formatDiagram();
    }
    public void undo()
    {
        System.out.println("undo command \"FormatDiagram\"");
    }
}
class PrintResult extends UserCommand
{
    public void excute(DataBase db)
    {
        db.printResult();
    }
    public void undo()
    {
        System.out.println("undo command \"PrintResult\"");
    }
}
abstract class MacroCommand extends UserCommand
{
    public void excute(DataBase db)
    {
        this.promptUser(db);
        this.diagnostics(db);
        this.retriveInput(db);
        this.connectDB(db);
        this.disconnectDB(db);
    }
    public void undo()
    {
        System.out.println("undo command \"MacroCommand\"");
    }
    abstract public void promptUser(DataBase db);
    abstract public void diagnostics(DataBase db);
    public void retriveInput(DataBase db)
    {
        db.retriveInput();
    }
    public void connectDB(DataBase db)
    {
        db.connectDB();
    }
    public void disconnectDB(DataBase db)
    {
		db.disconnectDB();
    }
}
class VerboseMode extends MacroCommand
{
    public void promptUser(DataBase db)
    {
        db.verboseModePromptUser();
    }
    public void diagnostics(DataBase db)
    {
        db.verboseModeDiagnostics();
    }
}
class SuccinctMode extends MacroCommand
{
    public void promptUser(DataBase db)
    {
        db.succinctModePromptUser();
    }
    public void diagnostics(DataBase db)
    {
        db.succinctModeDiagnostics();
    }
}
abstract class CommandFactory
{
    protected CommandFactory commandFactory;
    abstract public UserCommand makeUserCommand(String command);
    public void setNextFactory(CommandFactory f)
    {
        this.commandFactory = f;
    }
}
class EvalResultFactory extends CommandFactory
{
    public UserCommand makeUserCommand(String command)
    {
        if(command.equals("evalResult"))
            return new EvalResult();
        else
            return this.makeUserCommand(command);
    }
}
class FormatDiagramFactory extends CommandFactory
{
    public UserCommand makeUserCommand(String command)
    {
        if(command.equals("formatDiagram"))
            return new FormatDiagram();
        else
            return this.makeUserCommand(command);
    }
}
class PrintResultFactory extends CommandFactory
{
    public UserCommand makeUserCommand(String command)
    {
        if(command.equals("printResult"))
            return new PrintResult();
        else
            return this.makeUserCommand(command);
    }
}
class VerboseModeFactory extends CommandFactory
{
    public UserCommand makeUserCommand(String command)
    {
        if(command.equals("macroCommandVerboseMode"))
            return new VerboseMode();
        else
            return this.makeUserCommand(command);
    }
}
class SuccinctModeFactory extends CommandFactory
{
    public UserCommand makeUserCommand(String command)
    {
        if(command.equals("macroCommandSuccinctMode"))
            return new SuccinctMode();
        else
            return null;
    }
}
abstract class DataBase
{
    abstract public void evalResult();
    abstract public void formatDiagram();
    abstract public void printResult();
    abstract public void verboseModePromptUser();
    abstract public void verboseModeDiagnostics();
    abstract public void succinctModePromptUser();
    abstract public void succinctModeDiagnostics();
    abstract public void retriveInput();
    abstract public void connectDB();
    abstract public void disconnectDB();
}
class RDB extends DataBase
{
    public void evalResult()
    {
        System.out.println("excute RDB command \"EvalResult\"");
    }
    public void formatDiagram()
    {
        System.out.println("excute RDB command \"FormatDiagram\"");
    }
    public void printResult()
    {
        System.out.println("excute RDB command \"PrintResult\"");
    }
    public void verboseModePromptUser()
    {
        System.out.println("RDB VerboseMode's prompt user");
    }
    public void verboseModeDiagnostics()
    {
        System.out.println("RDB VerboseMode's diagnostics");
    }
    public void succinctModePromptUser()
    {
        System.out.println("RDB SuccinctMode's prompt user");
    }
    public void succinctModeDiagnostics()
    {
        System.out.println("RDB SuccinctMode's diagnostics");
    }
    public void retriveInput()
    {
        System.out.println("RDB retrive input");
    }
    public void connectDB()
    {
        System.out.println("RDB connect database");
    }
    public void disconnectDB()
    {
        System.out.println("RDB disconnect database");
    }
}
class LDAP extends DataBase
{
    public void evalResult()
    {
        System.out.println("excute LDAP command \"EvalResult\"");
    }
    public void formatDiagram()
    {
        System.out.println("excute LDAP command \"FormatDiagram\"");
    }
    public void printResult()
    {
        System.out.println("excute LDAP command \"PrintResult\"");
    }
    public void verboseModePromptUser()
    {
        System.out.println("LDAP VerboseMode's prompt user");
    }
    public void verboseModeDiagnostics()
    {
        System.out.println("LDAP VerboseMode's diagnostics");
    }
    public void succinctModePromptUser()
    {
        System.out.println("LDAP SuccinctMode's prompt user");
    }
    public void succinctModeDiagnostics()
    {
        System.out.println("LDAP SuccinctMode's diagnostics");
    }
    public void retriveInput()
    {
        System.out.println("LDAP retrive input");
    }
    public void connectDB()
    {
        System.out.println("LDAP connect database");
    }
    public void disconnectDB()
    {
        System.out.println("LDAP disconnect database");
    }
}
class Invoker
{
    private HashMap<String,UserCommand> rdbuc = new HashMap<String,UserCommand>();
    private HashMap<String,UserCommand> ldapuc = new HashMap<String,UserCommand>();
    private UserCommand c;
    public void excute(DataBase db,CommandFactory cf,String command)
    {
        if(db.getClass().getSimpleName().equals("RDB"))
        {
            c = cf.makeUserCommand(command);
            c.excute(db);
            rdbuc.put(command,c);
        }
        else if(db.getClass().getSimpleName().equals("LDAP"))
        {
            c = cf.makeUserCommand(command);
            c.excute(db);
            ldapuc.put(command,c);
        }
        else
            System.out.println("Error");
    }
    public void undo(DataBase db,String command)
    {
        if(db.getClass().getSimpleName().equals("RDB"))
        {
            c = rdbuc.get(command);
            c.undo();
        }
        else if(db.getClass().getSimpleName().equals("LDAP"))
        {
            c = ldapuc.get(command);
            c.undo();
        }
        else
            System.out.println("Error");
    }
}
class Q41_OC
{
    public static void main(String[] args)
    {
        DataBase rdb = new RDB();
        DataBase ldap = new LDAP();
        CommandFactory er = new EvalResultFactory();
        CommandFactory fd = new FormatDiagramFactory();
        CommandFactory pr = new PrintResultFactory();
        CommandFactory vm = new VerboseModeFactory();
        CommandFactory sm = new SuccinctModeFactory();
        er.setNextFactory(fd);
        fd.setNextFactory(pr);
        pr.setNextFactory(vm);
        vm.setNextFactory(sm);
        Invoker invoker = new Invoker();
        invoker.excute(rdb,er,"evalResult");
        invoker.excute(rdb,fd,"formatDiagram");
        invoker.excute(rdb,pr,"printResult");
        invoker.excute(rdb,vm,"macroCommandVerboseMode");
        invoker.excute(rdb,sm,"macroCommandSuccinctMode");
        //RDB and LDAP is receiver
    }
}
