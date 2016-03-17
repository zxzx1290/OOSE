import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginSystem{
    public static void main(String[] args){
        LoginPage page = new LoginPage();
        DBMgr dbmgr = new DBMgr();
        LoginController controller = new LoginController(page,dbmgr);
        page.setVisible(true);
    }
}

class LoginPage extends JFrame{
    private JButton submit;
    private JLabel msg,label1,label2;
    private JTextField uid,password;
    private LoginController controller;
    public LoginPage(){
        JPanel loginPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);
        label1 = new JLabel("uid");
        uid = new JTextField(10);
        label2 = new JLabel("password");
        password = new JTextField(10);
        submit = new JButton("login");
        msg = new JLabel("click submit to login");
        loginPanel.add(label1);
        loginPanel.add(uid);
        loginPanel.add(label2);
        loginPanel.add(password);
        loginPanel.add(submit);
        loginPanel.add(msg);
        this.add(loginPanel);
    }
    public String getUid() {
        return uid.getText();
    }
    public String getPassword(){
        return password.getText();
    }
    public void setMsg(String msg){
        this.msg.setText(msg);
    }
    public void close() {
        this.dispose();
    }
    public void addLoginListener(ActionListener listener) {
        submit.addActionListener(listener);
    }
}

class LoginController {
    private LoginPage page;
    private DBMgr dbmgr;

    public LoginController(LoginPage page, DBMgr dbmgr){
        this.page=page;
        this.dbmgr=dbmgr;
        this.page.addLoginListener(new LoginListener());
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String uid, password, msg;
            boolean b = verify(page.getUid(),page.getPassword());

            if (!b) {
                page.setMsg("Login failed.");
            } else {
                WelcomePage welcome = new WelcomePage();
                page.close();
                welcome.setVisible(true);
            }
        }
    }

    public Boolean verify(String uid,String password){
        String msg;
        User user=dbmgr.getUser(uid);
        if(user==null){
            System.out.println("user not found");
            return false;
        }
        boolean b =user.verify(password);
        if(!b){
            return false;
        }else{
            return true;
        }
    }
}

class DBMgr {
    public DBMgr(){
        DB DB = new DB();
    }
    public User getUser(String uid) {
        if ( ! DB.users.containsKey(uid)) {
            return null;
        }
        User user = DB.users.get(uid);
        return user;
    }
}

class User {
    private String uid;
    private String password;

    public User(String uid,String password) {
        this.uid = uid;
        this.password=password;
    }

    public Boolean verify(String password) {
        return (this.password.equals(password)) ? true : false;
    }
}

class DB {
    public static Map<String, User> users = new HashMap<String, User>();
    public DB(){
        User d1 = new User("luke","123");
        User d2 = new User("orange","456");
        users.put("luke", d1);
        users.put("orange", d2);
    }
}

class WelcomePage extends JFrame {
    private JLabel label;
    public WelcomePage() {
        JPanel welcomePanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);
        label = new JLabel("Welcome!");
        welcomePanel.add(label);
        this.add(welcomePanel);
    }
}
