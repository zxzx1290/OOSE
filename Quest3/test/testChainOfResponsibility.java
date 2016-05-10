abstract class Handler {
    private Handler successor=null;
    public void setSuccessor(Handler h){
        this.successor=h;
    }
    public void handleRequest(Email e){
        if(this.successor!=null){
            successor.handleRequest(e);
        }
    }
}

class ConcreteHandler1 extends Handler{
    public void handleRequest(Email e){
        if(e.getType()==Email.T1){
            System.out.println("ConcreteHandler1 handle");
        }else{
            System.out.println("ConcreteHandler1 can't handle");
            super.handleRequest(e);
        }
    }
}

class ConcreteHandler2 extends Handler{
    public void handleRequest(Email e){
        if(e.getType()==Email.T2){
            System.out.println("ConcreteHandler2 handle");
        }else{
            System.out.println("ConcreteHandler2 can't handle");
            super.handleRequest(e);
        }
    }
}

class ConcreteHandler3 extends Handler{
    public void handleRequest(Email e){
        System.out.println("ConcreteHandler3 handle");
    }
}

class Email {
    public static final String T1 = "T1";
    public static final String T2 = "T2";
    private String type;
    public Email(String s){
        this.setType(s);
    }
    public String getType(){
        return this.type;
    }
    public void setType(String s){
        this.type=s;
    }
}

public class testChainOfResponsibility {
    public static void main(String[] args) {
        Handler c1 = new ConcreteHandler1();
        Handler c2 = new ConcreteHandler2();
        Handler c3 = new ConcreteHandler3();
        c1.setSuccessor(c2);
        c2.setSuccessor(c3);
        System.out.println("Send T1");
        c1.handleRequest(new Email(Email.T1));
        System.out.println("\nSend T2");
        c1.handleRequest(new Email(Email.T2));
        System.out.println("\nSend T3");
        c1.handleRequest(new Email("NEW"));
    }
}
