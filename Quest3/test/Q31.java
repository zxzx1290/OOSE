import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class StateButtonListener implements ActionListener {
    private EditDiagramController edc;
    public StateButtonListener(EditDiagramController e){
        this.edc=e;
    }
    public void actionPerformed(ActionEvent e){
        System.out.println("StateButton Clicked");
        edc.stateBtnClicked(e);
    }
}

class TransButtonListener implements ActionListener {
    private EditDiagramController edc;
    public TransButtonListener(EditDiagramController e){
        this.edc=e;
    }
    public void actionPerformed(ActionEvent e){
        System.out.println("TransButton Clicked");
        edc.transBtnClicked(e);
    }
}

class EditorGUI extends JFrame{
    public JPanel gpanel;
    private JButton stateBtn;
    private JButton transBtn;
    public EditorGUI(EditDiagramController edc){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,100,200);
        gpanel = new JPanel();
        getContentPane().add(gpanel);

        transBtn = new JButton("Trans");
        transBtn.addActionListener(new TransButtonListener(edc));
        gpanel.add(transBtn);
        
        stateBtn = new JButton("States");
        stateBtn.addActionListener(new StateButtonListener(edc));
        gpanel.add(stateBtn);
    }
}

class EditDiagramController {
    private DiagramElement component;
    public void stateBtnClicked(ActionEvent e){
        //Controller call model(DiagramElement) to do somthing
        System.out.println("stateBtnClicked Controller received");
    }
    public void transBtnClicked(ActionEvent e){
        //Controller call model(DiagramElement) to do somthing
        System.out.println("transBtnClicked Controller received");
    }
}

class Q31 {
    public static void main(String[] args) {
        EditDiagramController edc = new EditDiagramController();
        EditorGUI gui = new EditorGUI(edc);
        gui.setVisible(true);
    }
}

interface DiagramElement { /*DiagramElement interface*/ }
