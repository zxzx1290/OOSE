import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

interface Colleague {
    public void setMediator(Mediator m);
}
interface Mediator {
    public void setStateNameText(Colleague c);
    public void setOkButton(Colleague c);
    public void setCancelButton(Colleague c);
    public void stateNameTextChanged(String s);
}

class DialogMediator implements Mediator{
    private Colleague snt;
    private Colleague ob;
    private Colleague cb;
    public void setStateNameText(Colleague c){
        c.setMediator(this);
        this.snt=c;
    }
    public void setOkButton(Colleague c){
        c.setMediator(this);
        this.ob=c;
    }
    public void setCancelButton(Colleague c){
        c.setMediator(this);
        this.cb=c;
    }
    public void stateNameTextChanged(String s){
        if(s.equals("")){
            ((JButton)ob).setEnabled(false);
        }else{
            ((JButton)ob).setEnabled(true);
        }
    }
}

class StateNameText extends JTextField implements Colleague {
    private Mediator m;
    StateNameText(String s,int col){
        super(s,col);
        addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                m.stateNameTextChanged(getText());
            }
        });
    }
    public void setMediator(Mediator m){
        this.m=m;
    }
}

class OkButton extends JButton implements Colleague {
    private Mediator m;
    OkButton(String s){
        super(s);
    }
    public void setMediator(Mediator m){
        this.m=m;
    }
}

class CancelButton extends JButton implements Colleague {
    private Mediator m;
    CancelButton(String s){
        super(s);
    }
    public void setMediator(Mediator m){
        this.m=m;
    }
}

class EditStateDialog extends JFrame {
    EditStateDialog(){
        super("ddsf");
        Mediator m = new DialogMediator();
        StateNameText snt = new StateNameText("aaa",10);
        OkButton ob = new OkButton("ok");
        CancelButton cb = new CancelButton("cancel");
        m.setStateNameText(snt);
        m.setOkButton(ob);
        m.setCancelButton(cb);
        setLayout(new FlowLayout());
        add(snt);
        add(ob);
        add(cb);
        setSize(400,100);
        setVisible(true);
    }
}

class testQ42 extends JFrame{
    public static void main(String[] args) {
        new EditStateDialog();
    }
}
