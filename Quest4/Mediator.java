import java.util.*;

interface ChatMediator{
    public void sendMessage(String msg,UserColleague user);
    public void addUser(UserColleague user);
}

class UserListMediator implements ChatMediator{
    private List<UserColleague> users;
    UserListMediator(){
        this.users=new ArrayList<>();
    }
    public void sendMessage(String msg,UserColleague user){
        for(UserColleague u : this.users){
            if(u!=user){
                u.receive(msg);
            }
        }
    }
    public void addUser(UserColleague user){
        this.users.add(user);
    }
}

abstract class UserColleague{
    protected ChatMediator mediator;
    protected String name;
    UserColleague(ChatMediator cmediator,String name){
        this.mediator=cmediator;
        this.name=name;
    }
    public abstract void send(String msg);
    public abstract void receive(String msg);
}

class UserImpl extends UserColleague{
    UserImpl(ChatMediator cmediator,String name){
        super(cmediator,name);
    }
    public void send(String msg){
        System.out.println(this.name+" : send msg="+msg);
        mediator.sendMessage(msg,this);
    }
    public void receive(String msg){
        System.out.println(this.name+" : receive msg"+msg);
    }
}

class Mediator{
    public static void main(String[] args) {
        ChatMediator mediator = new UserListMediator();
        UserColleague user1 = new UserImpl(mediator,"AAA");
        UserColleague user2 = new UserImpl(mediator,"BBB");
        UserColleague user3 = new UserImpl(mediator,"CCC");
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        user1.send("Hi All");
    }
}
