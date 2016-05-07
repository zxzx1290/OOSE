interface State {
    public void change(Context c);
}

class Context {
    private State state;
    public Context(State s){
        this.state=s;
    }
    public void setState(State s){
        this.state=s;
    }
    public void change(){
        this.state.change(this);
    }
}

class S1 implements State {
    public void change(Context c){
        System.out.println("Now in S1");
        c.setState(new S2());
    }
}

class S2 implements State {
    public void change(Context c){
        System.out.println("Now in S2");
        c.setState(new S3());
    }
}

class S3 implements State {
    public void change(Context c){
        System.out.println("Now in S3");
        c.setState(new S1());
    }
}

public class testState {
    public static void main(String[] args) {
        Context c = new Context(new S1());
        c.change();
        c.change();
        c.change();
        c.change();
        c.change();
    }
}
