interface Strategy{
    public void operate();
}
class LowS implements Strategy{
    public void operate(){
        System.out.println("use low");
    }
}
class MidS implements Strategy{
    public void operate(){
        System.out.println("use mid");
    }
}
class HighS implements Strategy{
    public void operate(){
        System.out.println("use high");
    }
}
class Context{
    private Strategy strategy=null;
    public Context(Strategy s){
        this.strategy=s;
    }
    public void operate(){
        this.strategy.operate();
    }
}
public class testStrategy{
    public static void main(String[] args) {
        Context context = new Context(new MidS());
        context.operate();
    }
}
