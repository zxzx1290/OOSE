abstract class Handler {
    Handler successor = null;
    public void handleRequest(Email email){
        if(successor != null){
            successor.handleRequest(email);
        }
    }
    public void setSuccessor(Handler successor){
        this.successor=successor;
    }
}

class SpamHandler extends Handler {
    public void handleRequest(Email email){
        if(email.getType()==Email.SPAM){
            System.out.println("SpamHandler handle email");
        }else{
            System.out.println("SpamHandler can't handle");
            super.handleRequest(email);
        }
    }
}

class FanHandler extends Handler {
    public void handleRequest(Email email){
        if(email.getType()==Email.FAN){
            System.out.println("FanHandler handle email");
        }else{
            System.out.println("FanHandler can't handle");
            super.handleRequest(email);
        }
    }
}

class ComplainHandler extends Handler {
    public void handleRequest(Email email){
        if(email.getType()==Email.COMPLAN){
            System.out.println("ComplainHandler handle email");
        }else{
            System.out.println("ComplainHandler can't handle");
            super.handleRequest(email);
        }
    }
}

class NewLocationHandler extends Handler {
    public void handleRequest(Email email){
        System.out.println("NewLocationHandler handle email");
    }
}

class Email {
    public static final String SPAM = "SPAM";
    public static final String FAN = "FAN";
    public static final String COMPLAN = "COMPLAN";
    private String type;
    public Email(String t){
        this.setType(t);
    }
    public String getType(){
        return type;
    }
    public void setType(String t){
        this.type=t;
    }
}

public class ChainOfResponsibility{
    public static void main(String[] args) {
        Handler sp = new SpamHandler();
        Handler fa = new FanHandler();
        Handler co = new ComplainHandler();
        Handler ne = new NewLocationHandler();
        sp.setSuccessor(fa);
        fa.setSuccessor(co);
        co.setSuccessor(ne);
        System.out.println("Send Spam");
        sp.handleRequest(new Email(Email.SPAM));
        System.out.println("\nSend Fan");
        sp.handleRequest(new Email(Email.FAN));
        System.out.println("\nSend Complan");
        sp.handleRequest(new Email(Email.COMPLAN));
        System.out.println("\nSend New");
        sp.handleRequest(new Email("New"));
    }
}
