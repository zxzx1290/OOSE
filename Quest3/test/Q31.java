import java.util.ArrayList;
interface ActionListener {
    public void actionPerformed(ActionEvent e);
    public String getName();
}

class StateButtonListener implements ActionListener {
    private EditDiagramController edc;
    private String name;
    public StateButtonListener(String s, EditDiagramController edc){
        this.name=s;
        this.edc=edc;
    }
    public String getName(){
        return this.name;
    }
    public void actionPerformed(ActionEvent e){
        System.out.println("StateButton Clicked");
        edc.stateBtnClicked(e);
    }
}

class TransButtonListener implements ActionListener {
    private EditDiagramController edc;
    private String name;
    public TransButtonListener(String s, EditDiagramController edc){
        this.name=s;
        this.edc=edc;
    }
    public String getName(){
        return this.name;
    }
    public void actionPerformed(ActionEvent e){
        System.out.println("TransButtonButton Clicked");
        edc.transBtnClicked(e);
    }
}

class EditorGUI {
    private ArrayList<ActionListener> al = new ArrayList<ActionListener>();
    public void setListener(ActionListener a){
        al.add(a);
    }
    public void jButtonClick(String ss){
        for(ActionListener a : al){
            if(a.getName().equals(ss)){
                a.actionPerformed(new ActionEvent());
            }
        }
    }
}

class EditDiagramController {
    public void stateBtnClicked(ActionEvent e){
        System.out.println("stateBtnClicked Controller received");
    }
    public void transBtnClicked(ActionEvent e){
        System.out.println("transBtnClicked Controller received");
    }
}

class Q31 {
    public static void main(String[] args) {
        EditorGUI gui = new EditorGUI();
        EditDiagramController edc = new EditDiagramController();
        gui.setListener(new StateButtonListener("State",edc));
        gui.setListener(new TransButtonListener("Trans",edc));
        gui.jButtonClick("State");
        gui.jButtonClick("Trans");
    }
}

class ActionEvent {}
