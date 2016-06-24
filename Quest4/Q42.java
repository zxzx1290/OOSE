import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

interface Colleague {
    public void setMediator(Mediator m);
}

interface Mediator {
    public void setStateNameText(Colleague c);
    public void setOkButton(Colleague c);
    public void setCancelButton(Colleague c);
    public void stateNameTextChanged(String t);
}

class DialogMediator implements Mediator {
    private Colleague stateNameText;
    private Colleague okButton;
    private Colleague cancelButton;
    public void setStateNameText(Colleague c){
        c.setMediator(this);
        stateNameText=c;
    }
    public void setOkButton(Colleague c){
        c.setMediator(this);
        okButton=c;
    }
    public void setCancelButton(Colleague c){
        c.setMediator(this);
        cancelButton=c;
    }
    public void stateNameTextChanged(String t){
        if(t.equals("")){
            System.out.println("null text");
            ((JButton)okButton).setEnabled(false);
        }else{
            System.out.println("text : "+t);
            ((JButton)okButton).setEnabled(true);
        }
    }
}

class StateNameText extends JTextField implements Colleague {
    private Mediator mediator;
    StateNameText(String text, int columns){
        super(text,columns);
        getDocument().addDocumentListener(new DocumentListener() {
            //notify when key in or delete
            public void insertUpdate(DocumentEvent e){
                mediator.stateNameTextChanged(getText());
            }
            public void removeUpdate(DocumentEvent e){
                mediator.stateNameTextChanged(getText());
            }
            public void changedUpdate(DocumentEvent e){}
        });
        addActionListener(new ActionListener(){
            //notify when press enter
            public void actionPerformed(ActionEvent e){
                mediator.stateNameTextChanged(getText());
            }
        });
    }
    public void setMediator(Mediator m){
        mediator=m;
    }
}

class OkButton extends JButton implements Colleague {
    private Mediator mediator;
    OkButton(String text){
        super(text);
    }
    public void setMediator(Mediator m){
        mediator=m;
    }
}

class CancelButton extends JButton implements Colleague {
    private Mediator mediator;
    CancelButton(String text){
        super(text);
    }
    public void setMediator(Mediator m){
        mediator=m;
    }
}

class EditStateDialog extends JFrame{
    EditStateDialog(String title){
        super(title);
        Mediator mediator = new DialogMediator();
        StateNameText stateNameText = new StateNameText("state",10);
        OkButton okButton = new OkButton("Ok");
        CancelButton cancelButton = new CancelButton("Cancel");
        setLayout(new FlowLayout());
        mediator.setStateNameText(stateNameText);
        mediator.setOkButton(okButton);
        mediator.setCancelButton(cancelButton);
        add(stateNameText);
        add(okButton);
        add(cancelButton);
        setSize(400,100);
        setVisible(true);
    }
}

class Q42 {
    public static void main(String[] args) {
        new EditStateDialog("Q2");
    }
}
