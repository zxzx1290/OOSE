import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginSystem{
    public static void main(String[] args) {
        LoginPage page=new LoginPage();
        DBMgr dbmgr=new DBMgr();
        LoginController controller=new LoginController(page,dbmgr);
        page.setVisible(true);
    }
}

class LoginPage extends JFrame {
    private JPanel panel;
    private JButton submit;
    private JTextField uid,password;
    private JLabel label1,label2,msg;
    public LoginPage(){
        panel=new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,200);
        label1=new JLabel("uid");
        uid=new JTextField(10);
        label2=new JLabel("password");
        password=new JTextField(10);
        submit=new JButton("login");
        msg=new JLabel("click to login");
        panel.add(label1);
        panel.add(uid);
        panel.add(label2);
        panel.add(password);
        panel.add(submit);
        panel.add(msg);
        this.add(panel);
    }
    public String getUid(){
        return uid.getText();
    }
    public String getPassword(){
        return password.getText();
    }
    public void close(){
        this.dispose();
    }
    public void setMsg(String msg){
        this.msg.setText(msg);
    }
    public void addLoginListener(ActionListener listener){
        submit.addActionListener(listener);
    }
}
class LoginController {
    private LoginPage page;
    private DBMgr dbmgr;
    public LoginController(LoginPage page, DBMgr dbmgr){
        this.page=page;
        this.dbmgr=dbmgr;
        page.addLoginListener(new LoginListener());
    }
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            boolean b=verify(page.getUid(),page.getPassword());
            if(!b){
                page.setMsg("login failed.");
            }else{
                Welcome welcome=new Welcome();
                page.close();
                welcome.setVisible(true);
            }
        }
    }

    public Boolean verify(String uid,String password){
        User user=dbmgr.getUser(uid);
        if(user==null){
            System.out.println("user not found");
            return false;
        }
        boolean b=user.verify(password);
        return (!b) ? false : true;
    }
}
class User{
    private String uid;
    private String password;
    public User(String uid,String password){
        this.uid=uid;
        this.password=password;
    }
    public Boolean verify(String password){
        return (this.password.equals(password)) ? true : false;
    }
}
class DBMgr{
    public DBMgr(){
        DB DB=new DB();
    }
    public User getUser(String uid){
        if(! DB.users.containsKey(uid)){
            return null;
        }
        return DB.users.get(uid);
    }
}
class DB{
    public static Map<String,User> users=new HashMap<String,User>();
    public DB(){
        User u1=new User("luke","123");
        users.put("luke",u1);
    }
}
class Welcome extends JFrame{
    private JPanel panel;
    private JLabel label;
    public Welcome(){
        panel=new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,200);
        label=new JLabel("Welcome");
        panel.add(label);
        this.add(panel);
    }
}
