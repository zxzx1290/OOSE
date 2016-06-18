/*
 * State Diagram Editor - OOSE Teamwork
 *
 * Patterns implemented by this program: Composite, Strategy, Observer, Adapter, Iterator, Memento
 * Class Diagram https://cacoo.com/diagrams/ :user:indexzexx@gmail.com pwd:Qazwsx123
 * Development environment: jdk 1.7 @ Ubuntu
 * Testing environment: jre1.7 @ Ubuntu / Windows 10
 *
 * Using astyle indent tool: astyle --style=java --indent=tab --indent-switches --indent-cases -y SDE.java
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

enum PaintType {
	RECTANGLE,
	CIRCLE,
	TRANSITION
}

enum Colors {
	RED,
	BLACK,
	BLUE
}

class GlobalVar {
	public static final int radius = 70; //shape radius
	public static final int fontSize = 18;
	public static final int offsetX = -23;
	public static final int offsetY = -3;
}

class SDE {
	public SDE() {
		EditDiagramModel model = new EditDiagramModel();
		EditDiagramController controller = new EditDiagramController(model);
		JFrame frame = new JFrame();

		/* "S"hape panel*/
		JPanel sPanel = new JPanel();
		JButton transBtn = new JButton("Trans");
		JButton rectangleBtn = new JButton("Rectangle");
		JButton circleBtn = new JButton("Circle");
		sPanel.add(model);
		sPanel.add(rectangleBtn);
		sPanel.add(transBtn);
		sPanel.add(circleBtn);

		/* "C"oloring setting panel */
		JPanel cPanel = new JPanel();
		JButton redBtn = new JButton("Red");
		JButton blackBtn = new JButton("Black");
		JButton blueBtn = new JButton("Blue");
		cPanel.add(redBtn);
		cPanel.add(blackBtn);
		cPanel.add(blueBtn);

		/* "N"aming state panel */
		JPanel nPanel = new JPanel();
		JTextField setTextField = new JTextField(20);
		JButton setTextBtn = new JButton("Set Text");
		nPanel.add(setTextField);
		nPanel.add(setTextBtn);

		/* "U"tility panel*/
		JPanel uPanel = new JPanel();
		JButton saveBtn = new JButton("Save");
		JButton undoBtn = new JButton("Undo");
		JButton saveImgBtn = new JButton("Save as png file");
		JButton clearBtn = new JButton("Clear");
		uPanel.add(saveBtn);
		uPanel.add(undoBtn);
		uPanel.add(clearBtn);
		uPanel.add(saveImgBtn);

		/* "M"ove panel */
		JPanel mPanel = new JPanel();
		JButton mvBtn = new JButton("Move Current State");
		JTextField setLineField = new JTextField(20);
		JButton goLineBtn = new JButton("Move through transition");
		mPanel.add(mvBtn);
		mPanel.add(setLineField);
		mPanel.add(goLineBtn);

		/* Panel size & Setting */
		uPanel.setPreferredSize(new Dimension(800, 100));
		cPanel.setPreferredSize(new Dimension(800, 200));
		nPanel.setPreferredSize(new Dimension(800, 200));
		mPanel.setPreferredSize(new Dimension(800, 100));
		sPanel.add(cPanel);
		cPanel.add(nPanel);
		nPanel.add(uPanel);
		uPanel.add(mPanel);
		frame.add(sPanel);

		/* Listeners & Adapter*/
		transBtn.addActionListener(new TransButtonListener(controller));
		rectangleBtn.addActionListener(new RectangleButtonListener(controller));
		circleBtn.addActionListener(new CircleButtonListener(controller));
		redBtn.addActionListener(new RedButtonListener(controller));
		blackBtn.addActionListener(new BlackButtonListener(controller));
		blueBtn.addActionListener(new BlueButtonListener(controller));
		setTextBtn.addActionListener(new TextListener(controller,setTextField));
		saveBtn.addActionListener(new SaveListener(controller));
		undoBtn.addActionListener(new UndoListener(controller));
		mvBtn.addActionListener(new MoveListener(controller));
		clearBtn.addActionListener(new ClearListener(controller));
		goLineBtn.addActionListener(new GoSListener(controller,setLineField));
		saveImgBtn.addActionListener(new saveImgListener(controller));
		MouseAdapter ma = new MouseAdapter(controller);
		sPanel.addMouseListener(ma);
		sPanel.addMouseMotionListener(ma);

		/* JFrame setting*/
		frame.setTitle("State Diagram Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.setResizable(false);
		frame.setVisible(true);
		System.out.println("GUI is ready");
	}

	public static void main(String[] args) {
		new SDE();
	}
}

/*
 * Listener classes
 */

class RectangleButtonListener implements ActionListener {
	private EditDiagramController edc;
	RectangleButtonListener(EditDiagramController e) {
		this.edc = e;
	}
	public void actionPerformed(ActionEvent e) {
		edc.setPaintType(PaintType.RECTANGLE);
	}
}
class TransButtonListener implements ActionListener {
	private EditDiagramController edc;
	TransButtonListener(EditDiagramController e) {
		this.edc = e;
	}
	public void actionPerformed(ActionEvent e) {
		edc.setPaintType(PaintType.TRANSITION);
	}
}
class CircleButtonListener implements ActionListener {
	private EditDiagramController edc;
	CircleButtonListener(EditDiagramController e) {
		this.edc = e;
	}
	public void actionPerformed(ActionEvent e) {
		edc.setPaintType(PaintType.CIRCLE);
	}
}
class RedButtonListener implements ActionListener {
	private EditDiagramController edc;
	RedButtonListener(EditDiagramController e) {
		this.edc = e;
	}
	public void actionPerformed(ActionEvent e) {
		edc.setColor(Colors.RED);
	}
}
class BlackButtonListener implements ActionListener {
	private EditDiagramController edc;
	BlackButtonListener(EditDiagramController e) {
		this.edc = e;
	}
	public void actionPerformed(ActionEvent e) {
		edc.setColor(Colors.BLACK);
	}
}
class BlueButtonListener implements ActionListener {
	private EditDiagramController edc;
	BlueButtonListener(EditDiagramController e) {
		this.edc = e;
	}
	public void actionPerformed(ActionEvent e) {
		edc.setColor(Colors.BLUE);
	}
}
class SaveListener implements ActionListener {
	private EditDiagramController edc;
	SaveListener(EditDiagramController e) {
		this.edc = e;
	}
	public void actionPerformed(ActionEvent e) {
		edc.save();
	}
}
class UndoListener implements ActionListener {
	private EditDiagramController edc;
	UndoListener(EditDiagramController e) {
		this.edc = e;
	}
	public void actionPerformed(ActionEvent e) {
		edc.undo();
	}
}
class MoveListener implements ActionListener {
	private EditDiagramController edc;
	MoveListener(EditDiagramController e) {
		this.edc = e;
	}
	public void actionPerformed(ActionEvent e) {
		edc.moveState();
	}
}
class ClearListener implements ActionListener {
	private EditDiagramController edc;
	ClearListener(EditDiagramController e) {
		this.edc = e;
	}
	public void actionPerformed(ActionEvent e) {
		edc.clear();
	}
}
class saveImgListener implements ActionListener {
	private EditDiagramController edc;
	saveImgListener(EditDiagramController e) {
		this.edc = e;
	}
	public void actionPerformed(ActionEvent e) {
		edc.saveImg();
	}
}

class MouseAdapter implements MouseListener,MouseMotionListener,ActionListener {
	private EditDiagramController edc;
	MouseAdapter(EditDiagramController edc) {
		super();
		this.edc=edc;
	}
	public void mouseClicked(MouseEvent me) {
		if(SwingUtilities.isLeftMouseButton(me)) { //if left click
			edc.mouseClick(me);
		}
		else if (SwingUtilities.isRightMouseButton(me)) {   //if right click
			edc.mouseDelete(me);
		}
		else {
			System.out.println("Waring : error click event "+me);
		}
	}
	public void mouseEntered(MouseEvent mouse) { }
	public void mouseExited(MouseEvent mouse) { }
	public void mousePressed(MouseEvent mouse) {
		edc.setDrag(true); //set drag mode to true
	}
	public void mouseReleased(MouseEvent mouse) {
		edc.setDrag(false); //set drag mode to false
	}
	public void actionPerformed(ActionEvent arg0) { }
	public void mouseDragged(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) { //if left drag
			edc.mouseDrag(e);
		}
	}
	public void mouseMoved(MouseEvent e) {
		edc.mouseMove(e);
	}
}

class TextListener implements ActionListener {
	private EditDiagramController edc;
	private JTextField j;

	TextListener(EditDiagramController e, JTextField j) {
		this.edc = e;
		this.j = j;
	}

	public void actionPerformed(ActionEvent e) {
		edc.setText(j.getText(), j); //send text and pass JTextField to model
	}
}
class GoSListener implements ActionListener {
	private EditDiagramController edc;
	private JTextField j;
	GoSListener(EditDiagramController e,JTextField j) {
		this.edc=e;
		this.j=j;
	}
	public void actionPerformed(ActionEvent e) {
		edc.goText(j.getText(),j);
	}
}

/*
 * Mememto class
 */

class Memento {
	private ArrayList<DiagramElement> shapes = new ArrayList<>();
	private ArrayList<Color> shapesColor = new ArrayList<>();
	private ArrayList<String> shapesString = new ArrayList<>();
	private ArrayList<DiagramElement> lines = new ArrayList<>();
	private ArrayList<String> linesString = new ArrayList<>();
	private DiagramElement nowState;
	private Color nowStateColor;
	private HashMap<DiagramElement,ArrayList<Line>> in = new HashMap<>();
	private HashMap<DiagramElement,ArrayList<Line>> out = new HashMap<>();
	/* Constructor */
	public Memento(ArrayList<DiagramElement> shapes, ArrayList<DiagramElement> lines, ArrayList<String> linesString, ArrayList<Color> shapesColor, ArrayList<String> shapesString, DiagramElement e, Color ec) {
		//set all StateDiagram content to memento
		System.out.println("Save "+shapes+" "+lines+" "+e);
		in.clear();
		out.clear();
		for (DiagramElement d : shapes) {
			ArrayList<Line> temp = new ArrayList<>();
			for (Line ind : d.getIn()) {
				temp.add(ind);
			}
			if (temp!=null) {
				in.put(d,temp);
			}
			ArrayList<Line> temp2 = new ArrayList<>();
			for (Line outd : d.getOut()) {
				temp2.add(outd);
			}
			if (temp2!=null) {
				out.put(d,temp2);
			}
		}
		System.out.println("now relation "+in+" "+out);
		this.shapes = shapes;
		this.shapesColor = shapesColor;
		this.shapesString = shapesString;
		this.lines = lines;
		this.linesString = linesString;
		nowState = e;
		nowStateColor = ec;
	}

	public ArrayList<DiagramElement> getShapes() {
		//return all shapes to StateDiagram
		ArrayList<DiagramElement> a = new ArrayList<>();
		System.out.println("now relation "+in+" "+out);
		for (DiagramElement d : shapes) {
			d.reBuildLine(); //remove all line in shape
			if (in.get(d)!=null) {
				for (Line ind : in.get(d)) {
					d.addIn(ind);
				}
			}
			if (out.get(d)!=null) {
				for (Line outd : out.get(d)) {
					d.addOut(outd);
				}
			}
			a.add(d);
		}
		return a;
	}
	public ArrayList<DiagramElement> getLines() {
		//return all lines to StateDiagram
		ArrayList<DiagramElement> a = new ArrayList<>();
		for (DiagramElement d : lines) {
			a.add(d);
		}
		return a;
	}
	public ArrayList<String> getLinesString() {
		//return all lines string to StateDiagram
		ArrayList<String> a = new ArrayList<>();
		for(String s :linesString) {
			a.add(s);
		}
		return a;
	}
	public ArrayList<Color> getColors() {
		//return all shapes colors to StateDiagram
		ArrayList<Color> a = new ArrayList<>();
		for(Color d : shapesColor) {
			a.add(d);
		}
		return a;
	}
	public ArrayList<String> getString() {
		//return all shapes string to StateDiagram
		ArrayList<String> a = new ArrayList<>();
		for(String d : shapesString) {
			a.add(d);
		}
		return a;
	}
	public DiagramElement getNowState() {
		//retrun now state
		return nowState;
	}
	public Color getNowStateColor() {
		//return now state color
		return nowStateColor;
	}
	public void set(ArrayList<DiagramElement> shapes, ArrayList<DiagramElement> lines, ArrayList<String> linesString, ArrayList<Color> shapesColor, ArrayList<String> shapesString, DiagramElement e, Color ec) {
		//set all StateDiagram content to memento
		System.out.println("Save " + shapes + " " + lines + " " + e);
		in.clear();
		out.clear();
		for (DiagramElement d : shapes) {
			ArrayList<Line> temp = new ArrayList<>();
			for (Line ind : d.getIn()) {
				temp.add(ind);
			}
			if (temp!=null) {
				in.put(d,temp);
			}
			ArrayList<Line> temp2 = new ArrayList<>();
			for (Line outd : d.getOut()) {
				temp2.add(outd);
			}
			if (temp2!=null) {
				out.put(d,temp2);
			}
		}
		System.out.println("now relation " + in + " " + out);
		this.shapes=shapes;
		this.shapesColor=shapesColor;
		this.shapesString=shapesString;
		this.lines=lines;
		this.linesString=linesString;
		nowState=e;
		nowStateColor=ec;
	}
}

abstract class DiagramElement {
	public Shape draw() {
		return null;
	}
	public void addIn(Line l) {
		/*for Line, add input lines*/
	}
	public void addOut(Line l) {
		/*for Line, add out line*/
	}
	public ArrayList<Line> getIn() {
		return null; /*for Line, get all input lines*/
	}
	public ArrayList<Line> getOut() {
		return null; /*for Line, get all output lines*/
	}
	public void set(int x, int y, int r) {
		/*for Shape, set new position*/
	}
	public void removeLine(Line l) {
		/*for Shape, remove speific line*/
	}
	public void reBuildLine() {
		/*for Shape, clear all line in shape*/
	}
}

class StateDiagram extends DiagramElement {
	private ArrayList<DiagramElement> shapes = new ArrayList<>(); //include Rectangle and Circle
	private ArrayList<Color> shapesColor = new ArrayList<>(); //shapes color
	private ArrayList<String> shapesString = new ArrayList<>(); //on shapes text
	private ArrayList<DiagramElement> lines = new ArrayList<>(); //transition line with arrow
	private ArrayList<String> linesString = new ArrayList<>(); //on lines text
	private DiagramElement nowState = null;
	private Color nowStateColor = null;
	private DiagramElement newS, oldS; //old nowState and new nowState
	private final int ARR_SIZE = 4; //arrow size
	public Memento createMemento() {
		return new Memento(this.getShapes(), this.getLines(), this.getLinesString(), this.getColors(), this.getString(), this.getNowState(), this.getNowStateColor());
	}
	public void clearA() {
		//clear all ArrayList and nowstate
		shapes.clear();
		shapesColor.clear();
		shapesString.clear();
		lines.clear();
		linesString.clear();
		nowState = null;
		nowStateColor = null;
	}
	public DiagramElement getNowState() {
		return nowState;
	}
	public Color getNowStateColor() {
		return nowStateColor;
	}
	public void setNowState(DiagramElement e) {
		/* If nowState is null set to same instance then get first state color to nowStateColor */
		if (nowState == null) {
			newS = e;
			oldS = e;
			nowStateColor = shapesColor.get(shapes.indexOf(e));
		}
		else {
			newS = e;
			oldS = nowState;
		}

		/* Try to recover oldS color */
		try {
			shapesColor.set(shapes.indexOf(oldS),nowStateColor);
		}
		catch(Exception ed) {
			/* set color to default(black). */
			shapesColor.set(shapes.indexOf(oldS), new Color(0, 0, 0));
			System.out.println("Can't recover old Color " + oldS + " newS" + newS);
		}

		/* Try to save newS color */
		try {
			nowStateColor=shapesColor.get(shapes.indexOf(newS));
		}
		catch (Exception ed) {
			System.out.println("can't get old Color "+oldS);
		}

		System.out.println("nowStateColor " + nowStateColor);
		shapesColor.set(shapes.indexOf(newS), new Color(255, 128, 0));
		this.nowState = newS;
		System.out.println("Set new State " + nowState);
	}
	/* Add Shape */
	public void addS(DiagramElement e) {
		shapes.add(e);
	}
	/* Add Line */
	public void addL(DiagramElement e) {
		lines.add(e);
	}
	/* Add Shape Color */
	public void addC(Color c) {
		shapesColor.add(c);
	}
	/* Add Shape String */
	public void addSS(String s) {
		shapesString.add(s);
	}
	/* Add Line String */
	public void addLS(String s) {
		linesString.add(s);
	}

	/* Remove line string, in try for s2d(line preview) */
	public void removeLine(DiagramElement e) {
		try {
			linesString.remove(lines.indexOf(e));
		}
		catch(Exception ee) {}
		lines.remove(e);
	}
	/* Remove Element */
	public void removeElement(DiagramElement e) {
		shapesColor.remove(shapes.indexOf(e));
		shapesString.remove(shapes.indexOf(e));
		shapes.remove(e);
	}

	/* Get One Diagram Element */
	public DiagramElement getOneDiagramElement(Shape s) {
		//use shape to get DiagramElement
		for(DiagramElement d : shapes) {
			if(d.draw()==s) {
				return d;
			}
		}
		System.out.println("Warning : No shape found!!!");
		return null;
	}
	/* For memento response, restore to previous status */
	public void set (ArrayList<DiagramElement> shapes,ArrayList<DiagramElement> lines,ArrayList<String> linesString,ArrayList<Color> shapesColor,ArrayList<String> shapesString,DiagramElement e,Color ec) {
		System.out.println("Restore "+shapes+" "+lines+" "+nowState);
		this.shapes=shapes;
		this.shapesColor=shapesColor;
		this.shapesString=shapesString;
		this.lines=lines;
		this.linesString=linesString;
		nowState=e;
		nowStateColor=ec;
	}
	public ArrayList<DiagramElement> getShapes() {
		ArrayList<DiagramElement> a = new ArrayList<>();
		for(DiagramElement d : shapes) {
			a.add(d);
		}
		return a;
	}
	public ArrayList<DiagramElement> getLines() {
		ArrayList<DiagramElement> a = new ArrayList<>();
		for(DiagramElement d : lines) {
			a.add(d);
		}
		return a;
	}
	public ArrayList<Color> getColors() {
		ArrayList<Color> a = new ArrayList<>();
		for(Color d : shapesColor) {
			a.add(d);
		}
		return a;
	}
	public ArrayList<String> getString() {
		ArrayList<String> a = new ArrayList<>();
		for(String d : shapesString) {
			a.add(d);
		}
		return a;
	}
	public ArrayList<String> getLinesString() {
		ArrayList<String> a = new ArrayList<>();
		for(String d : linesString) {
			a.add(d);
		}
		return a;
	}
	public ArrayList<Shape> getRealShapes() {
		ArrayList<Shape> wsde = new ArrayList<>();
		for(DiagramElement d : shapes) {
			wsde.add(d.draw());
		}
		return wsde;
	}
	public void draw(Graphics goo) {
		Graphics2D g2d = (Graphics2D) goo.create();
		Font f = new Font("Comic Sans MS", Font.BOLD, GlobalVar.fontSize);
		g2d.setFont(f);
		Iterator<DiagramElement> it4 = lines.iterator();
		Iterator<String> it5 = linesString.iterator();
		while (it4.hasNext() && it5.hasNext()) { //for line and linesString iterator
			Graphics2D gsec = (Graphics2D)goo.create();
			Line2D ll = ((Line)it4.next()).draw();
			double x1 = ll.getX1();
			double x2 = ll.getX2();
			double y1 = ll.getY1();
			double y2 = ll.getY2();
			double dx = x2 - x1, dy = y2 - y1;
			double angle = Math.atan2(dy, dx);
			int len = (int) Math.sqrt(dx*dx + dy*dy);
			AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
			at.concatenate(AffineTransform.getRotateInstance(angle));
			gsec.transform(at);
			gsec.setPaint(Color.BLACK);
			gsec.drawLine(0, 0, len, 0);
			gsec.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
			g2d.setColor(Color.RED);
			g2d.drawString(it5.next(),(int)((ll.getX1()+ll.getX2())/2.0),(int)((ll.getY1()+ll.getY2())/2.0));
		}

		Iterator<DiagramElement> it1 = shapes.iterator();
		Iterator<Color> it2 = shapesColor.iterator();
		Iterator<String> it3 = shapesString.iterator();
		while (it1.hasNext() && it2.hasNext() && it3.hasNext()) { //for shape and shapesString and shapesColor
			Shape s = (it1.next()).draw();
			int x=(int)((s.getBounds().getWidth()/2.0)+s.getBounds().getX());
			int y=(int)((s.getBounds().getHeight()/2.0)+s.getBounds().getY());
			g2d.setPaint(Color.WHITE);
			if (s instanceof Rectangle2D) {
				g2d.fill(new Rectangle2D.Double(x - (GlobalVar.radius/2), y - (GlobalVar.radius/2), GlobalVar.radius, GlobalVar.radius));
			}
			else {
				g2d.fill(new Ellipse2D.Double(x- (GlobalVar.radius/2), y - (GlobalVar.radius/2), GlobalVar.radius, GlobalVar.radius));
			}
			g2d.setStroke(new BasicStroke(2));
			g2d.setPaint(it2.next());
			g2d.draw(s);
			g2d.drawString(it3.next(),(x - (GlobalVar.radius/2))+4, y);
		}
	}
}
class Rectangle extends DiagramElement {
	private ArrayList<Line> out = new ArrayList<>(); //output transition
	private ArrayList<Line> in = new ArrayList<>(); //input transition
	private Rectangle2D.Double o; //the real shape Rectangle2D
	Rectangle(int x, int y, int r) {
		o = new Rectangle2D.Double(x - r/2, y - r/2, r, r);
		System.out.println("New Rectangle "+this);
	}
	public void reBuildLine() {
		//clear all line
		this.in.clear();
		this.out.clear();
	}
	public void set(int x, int y, int r) {
		//set new position
		x = (x == 0) ? (int)(o.getX()) : x;
		y = (y == 0) ? (int)(o.getY()) : y;
		r = (r == 0) ? (int)(o.getWidth()) : r;
		x += GlobalVar.offsetX;
		y += GlobalVar.offsetY;
		o.setRect(x,y,r,r);
	}
	public ArrayList<Line> getIn() {
		//get all input transition
		return in;
	}
	public ArrayList<Line> getOut() {
		//get all output transition
		return out;
	}
	public void addIn(Line l) {
		//add input transition
		in.add(l);
	}
	public void addOut(Line l) {
		//add output transition
		out.add(l);
	}
	public void removeLine(Line l) {
		//remove line from input or output transition
		if(!(out.remove(l) || in.remove(l))) {
			System.out.println("Waring : Delete line error "+ l);
		}
	}
	public Shape draw() {
		//return real shape
		return o;
	}
}
class Circle extends DiagramElement {
	private ArrayList<Line> out = new ArrayList<>();
	private ArrayList<Line> in = new ArrayList<>();
	private Ellipse2D.Double o;
	Circle(int x, int y, int r) {
		o = new Ellipse2D.Double(x - r/2, y - r/2, r, r);
		System.out.println("New Circle "+this);
	}
	public void reBuildLine() {
		this.in.clear();
		this.out.clear();
	}
	public void set(int x, int y, int r) {
		x = (x == 0) ? (int)(o.getX()) : x;
		y = (y == 0) ? (int)(o.getY()) : y;
		r = (r == 0) ? (int)(o.getWidth()) : r;
		x += GlobalVar.offsetX;
		y += GlobalVar.offsetY;
		o.setFrame(x,y,r,r);
	}
	public ArrayList<Line> getIn() {
		return in;
	}
	public ArrayList<Line> getOut() {
		return out;
	}
	public void addIn(Line l) {
		in.add(l);
	}
	public void addOut(Line l) {
		out.add(l);
	}
	public void removeLine(Line l) {
		if(!(out.remove(l) || in.remove(l))) {
			System.out.println("Waring : Delete line error "+ l);
		}
	}
	public Shape draw() {
		return o;
	}
}
class Line extends DiagramElement {
	private Line2D.Double o;
	private DiagramElement tail; //the start shape connected
	private DiagramElement head; //the end shape connected
	Line(int a, int b, int c,int d,DiagramElement tail,DiagramElement head) {
		o = new Line2D.Double(a,b,c,d);
		this.tail = tail;
		this.head = head;
		if(tail!=null)
			System.out.println("New Line "+this+" set Head "+this.head+" Tail "+tail);
	}
	public DiagramElement getTail() {
		//return head connected DiagramElement
		return tail;
	}
	public DiagramElement getHead() {
		//return tail connected DiagramElement
		return head;
	}
	public void addElement(DiagramElement e) {
		//called by Model draw(), set the head and tail DiagramElement
		if(head==null) {
			head=e;
		}
		else {
			tail=e;
		}
	}
	public void removeHead() {
		//remove head DiagramElement
		head=null;
	}
	public void removeTail() {
		//remove tail DiagramElement
		tail=null;
	}
	public void set(int a, int b, int c,int d) {
		//set line new position
		a = (a == 0) ? (int)(o.getX1()) : a;
		b = (b == 0) ? (int)(o.getY1()) : b;
		c = (c == 0) ? (int)(o.getX2()) : c;
		d = (d == 0) ? (int)(o.getY2()) : d;
		o.setLine(a,b,c,d);
	}
	public Line2D draw() {
		//return real line Object
		return o;
	}
}

interface Strategy {
	public Color setColor();
}
class NowColor {
	private Strategy c;
	NowColor(Strategy c) {
		this.c=c;
	}
	public Color setColor() {
		return c.setColor();
	}
}
class RedColor implements Strategy {
	public Color setColor() {
		return new Color(255, 0, 0);
	}
}
class BlackColor implements Strategy {
	public Color setColor() {
		return new Color(0, 0, 0);
	}
}
class BlueColor implements Strategy {
	public Color setColor() {
		return new Color(0, 0, 255);
	}
}

class EditDiagramController {
	private EditDiagramModel model;
	EditDiagramController(EditDiagramModel m) {
		this.model=m;
	}
	public void mouseClick(MouseEvent me) {
		model.draw(me);
	}
	public void mouseMove(MouseEvent me) {
		model.move(me);
	}
	public void mouseDrag(MouseEvent me) {
		model.drag(me);
	}
	public void mouseDelete(MouseEvent me) {
		model.delete(me);
	}
	public void setPaintType(PaintType e) {
		model.changePaintType(e);
	}
	public void setColor(Colors e) {
		model.changeColor(e);
	}
	public void setDrag(boolean b) {
		//called when drag start and end
		model.setDrag(b);
		if (!b) {
			model.setNowDrag();
		}
	}
	public void setText(String s,JTextField j) {
		model.setText(s,j);
	}
	public void save() {
		model.setdo();
	}
	public void undo() {
		model.undo();
	}
	public void moveState() {
		model.moveState();
	}
	public void clear() {
		model.clear();
	}
	public void goText(String s,JTextField j) {
		model.goText(s,j);
	}
	public void saveImg() {
		model.saveImage();
	}
}

class EditDiagramModel extends JPanel {
	private int clicked = 0,x,y; //clikced : clicked shape count
	private boolean clickedShape = false; //if clicked shape
	private int[] linerec = new int[2]; //draw line used, store start position
	private Dimension dim = new Dimension(750, 370); //set canvas size
	private PaintType paintType;
	private Strategy[] colorlist = new Strategy[3];
	private NowColor nowcolor;
	private DiagramElement s2d = null; //draw line, preview line
	private boolean moving = false; //draw line, if mouse is moving
	private boolean draging = false; //drag shape, if draging, realtime
	private boolean nowDraging = false; //local drag state;
	private DiagramElement dragingDiagramElement;
	private StateDiagram stateDiagram;
	private DiagramElement clickedShape1;
	private DiagramElement clickedShape2;
	private boolean drawLine=false; //if draw line
	private String text="";
	private Memento memento;
	private boolean moveState = false;
	private JTextField j; //input text field
	private JTextField goj; //goTroughLine text field

	EditDiagramModel() {
		stateDiagram = new StateDiagram();
		colorlist[0]=new RedColor();
		colorlist[1]=new BlackColor();
		colorlist[2]=new BlueColor();
		nowcolor = new NowColor(colorlist[1]);
	}
	public void moveState() {
		//real moveState in draw()
		moveState=true;
		System.out.println("MoveState on");
	}
	public void clear() {
		//clear StateDiagram status
		stateDiagram.clearA();
		clearText();
		repaint();
		System.out.println("Clear all complete");
	}
	public void undo() {
		//restore to previous status
		System.out.println("Load Memento");
		stateDiagram.set(memento.getShapes(),memento.getLines(),memento.getLinesString(),memento.getColors(),memento.getString(),memento.getNowState(),memento.getNowStateColor());
		repaint();
		System.out.println("Load Memento complete");
	}
	public void setdo() {
		//save now status
		memento = stateDiagram.createMemento();
		//memento.set(stateDiagram.getShapes(),stateDiagram.getLines(),stateDiagram.getLinesString(),stateDiagram.getColors(),stateDiagram.getString(),stateDiagram.getNowState(),stateDiagram.getNowStateColor());
		System.out.println("Save Memento complete");
	}
	public void setText(String s,JTextField j) {
		//set on shape or line text
		this.j=j;
		this.text=s;
		System.out.println("Text set to :'"+this.text+"'");
	}
	public void clearText() {
		//clear textbox text
		this.text="";
		if(j!=null) {
			j.setText("");
		}
		System.out.println("Text set to :'"+this.text+"'");
	}
	public void goText(String s,JTextField goj) {
		//for move nowState, goTroughLine
		this.goj=goj;
		System.out.println("Try to go next State through line name "+s);
		String[] ss = s.split(" ");
		//separate string
		for(int i = 0; i < ss.length &&
		        goOne(ss[i], goj); i++);

	}
	public boolean goOne(String s, JTextField goj) {
		//called by goText, goTroughLine by separate string
		this.goj=goj;
		System.out.println("Try to goOne next State through line name "+s);
		ArrayList<Line> wl = stateDiagram.getNowState().getOut();
		Iterator<DiagramElement> li= (stateDiagram.getLines()).iterator();
		Iterator<String> lsi= (stateDiagram.getLinesString()).iterator();
		while(li.hasNext() && lsi.hasNext()) {
			DiagramElement ll = li.next();
			String ls = lsi.next();
			if((ls.equals(s)) && (wl.contains(ll))) {
				stateDiagram.setNowState(((Line)ll).getHead());
				repaint();
				return true;
			}
		}
		System.out.println("Error State to move");
		return false;
	}
	public void changePaintType(PaintType p) {
		if((this.paintType==PaintType.TRANSITION) && (p==PaintType.TRANSITION)) {
			//if in TRANSITION and click TRANSITION button again will clear s2d(draw line preview) and set paintType to null
			this.paintType=null;
			clicked = 0;
			moving = false;
			clickedShape = false;
			clickedShape1 = null;
			clickedShape2 = null;
			stateDiagram.removeLine(s2d);
			repaint();
		}
		else {
			this.paintType=p;
		}
		System.out.println("PaintType change to "+this.paintType);
	}
	public void changeColor(Colors c) {
		//set shape color
		System.out.println("Color change to "+c);
		if(c==Colors.RED) {
			nowcolor = new NowColor(colorlist[0]);
		}
		else if(c==Colors.BLACK) {
			nowcolor = new NowColor(colorlist[1]);
		}
		else {
			nowcolor = new NowColor(colorlist[2]);
		}
	}
	public void setClickedShape(Shape s) {
		//set clicked shape for draw line
		DiagramElement d = stateDiagram.getOneDiagramElement(s);
		if(clickedShape1 == null) {
			clickedShape1=d;
		}
		else if(drawLine) {
			clickedShape2=d;
		}
	}
	public void setDrag(boolean b) {
		//aet realtime drag mode
		draging=b;
	}
	public void reDrawLineConcrete(DiagramElement d,int mx,int my) {
		//draging element realtime show line status
		if (d == null) return;
		for(Line l : d.getIn()) {
			int qawsx = mx+(int)(d.draw().getBounds().getWidth())/2;
			int qawsy = my+(int)(d.draw().getBounds().getHeight())/2;
			double dx = qawsx - l.draw().getX1();
			double dy = qawsy - l.draw().getY1();
			double xx = dx/ Math.sqrt(dx * dx + dy *dy);
			double yy = dy/ Math.sqrt(dx * dx + dy *dy);
			xx *= (GlobalVar.radius/2);
			yy *= (GlobalVar.radius/2);
			qawsx -= (int)(xx);
			qawsy -= (int)(yy);
			qawsx += GlobalVar.offsetX;
			qawsy += GlobalVar.offsetY;
			l.set(0,0,qawsx,qawsy);
		}
		for(Line l : d.getOut()) {
			int qawsx = mx+(int)(d.draw().getBounds().getWidth())/2;
			int qawsy = my+(int)(d.draw().getBounds().getHeight())/2;
			double dx = l.draw().getX2() - x;
			double dy = l.draw().getY2() - y;
			double xx = dx/ Math.sqrt(dx * dx + dy *dy);
			double yy = dy/ Math.sqrt(dx * dx + dy *dy);
			xx *= (GlobalVar.radius/2);
			yy *= (GlobalVar.radius/2);
			qawsx += (int)(xx);
			qawsy += (int)(yy);
			qawsx += GlobalVar.offsetX;
			qawsy += GlobalVar.offsetY;
			l.set(qawsx,qawsy,0,0);
		}
	}
	public void reDrawLine() {
		//optimze reDrawLine after drag
		for (Shape s : stateDiagram.getRealShapes()) {
			dragingDiagramElement = stateDiagram.getOneDiagramElement(s);
			System.out.println("reDrawLine object : "+dragingDiagramElement);
			for(Line l : dragingDiagramElement.getIn()) {
				Shape startD = dragingDiagramElement.draw();
				Shape endD = l.getTail().draw();
				int[] ar = optimizeDot(
				               endD,
				               startD,
				               endD.getBounds().getX()+(GlobalVar.radius/2),
				               endD.getBounds().getY()+(GlobalVar.radius/2),
				               startD.getBounds().getX()+(GlobalVar.radius/2),
				               startD.getBounds().getY()+(GlobalVar.radius/2)
				           );
				l.set(ar[0],ar[1],ar[2],ar[3]);
			}
		}
		System.out.println("reDrawLine complete");
		repaint();
	}
	public void setNowDrag() {
		//when mouse release
		if(nowDraging) {
			nowDraging=false;
			System.out.println("Drag mode : "+nowDraging);
			reDrawLine();
		}
	}
	public void drag(MouseEvent me) {
		//draging shape
		if(draging!=nowDraging) {
			nowDraging=draging;
			System.out.println("Drag mode : "+nowDraging);
			for (Shape s : stateDiagram.getRealShapes()) {
				if (s.contains(me.getPoint())) { //if draging a shape
					dragingDiagramElement = stateDiagram.getOneDiagramElement(s);
					System.out.println("Draging object : "+dragingDiagramElement);
				}
			}
		}
		try {
			dragingDiagramElement.set(me.getX()-GlobalVar.radius/2,me.getY()-GlobalVar.radius/2,GlobalVar.radius);
		}
		catch(Exception e) {
			//not draging a shape
			//System.out.println("Object "+dragingDiagramElement+" Not found");
		}
		//optimize line position
		reDrawLineConcrete(dragingDiagramElement, me.getX()-GlobalVar.radius/2, me.getY()-GlobalVar.radius/2);
		repaint();
	}
	public int[] optimizeDot(Shape start,Shape end,double startx,double starty,double endx,double endy) {
		//optimize new line position
		double dx = endx - startx, dy = endy - starty;
		if(start instanceof Rectangle2D) {
			double dxf = Math.abs(dx);
			double dyf = Math.abs(dy);
			double m = dxf/dyf < dyf/dxf ? dxf/dyf : dyf/dxf;
			double t = (GlobalVar.radius/2)*m;
			double f = Math.sqrt(t*t + (Math.pow(GlobalVar.radius/2,2)));
			double xx = dx/ Math.sqrt(dx * dx + dy *dy);
			double yy = dy/ Math.sqrt(dx * dx + dy *dy);
			xx *= f;
			yy *= f;
			startx += (int)(xx);
			starty += (int)(yy);
		}
		else if (start instanceof Ellipse2D) {
			double xx = dx/ Math.sqrt(dx * dx + dy *dy);
			double yy = dy/ Math.sqrt(dx * dx + dy *dy);
			xx *= (GlobalVar.radius/2);
			yy *= (GlobalVar.radius/2);
			startx += (int)(xx);
			starty += (int)(yy);
		}
		if (end instanceof Rectangle2D) {
			double dxf = Math.abs(dx);
			double dyf = Math.abs(dy);
			double m = dxf/dyf < dyf/dxf ? dxf/dyf : dyf/dxf;
			double t = (GlobalVar.radius/2)*m;
			double f = Math.sqrt(t*t + (Math.pow(GlobalVar.radius/2,2)));
			double xx = dx/ Math.sqrt(dx * dx + dy *dy);
			double yy = dy/ Math.sqrt(dx * dx + dy *dy);
			xx *= f;
			yy *= f;
			endx -= (int)(xx);
			endy -= (int)(yy);
		}
		else if (end instanceof Ellipse2D) {
			double xx = dx/ Math.sqrt(dx * dx + dy *dy);
			double yy = dy/ Math.sqrt(dx * dx + dy *dy);
			xx *= (GlobalVar.radius/2);
			yy *= (GlobalVar.radius/2);
			endx -= (int)(xx);
			endy -= (int)(yy);
		}
		int[] a = {(int)startx,(int)starty,(int)endx,(int)endy};
		return a;
	}
	public void draw(MouseEvent me) {
		if(moveState) {
			//if moveState
			DiagramElement nowS = stateDiagram.getNowState();
			for (Shape s : stateDiagram.getRealShapes()) {
				if (s.contains(me.getPoint())) {
					if (s instanceof Shape) {
						DiagramElement ss = stateDiagram.getOneDiagramElement(s);
						System.out.println("Now click is "+ss);
						for(Line l : nowS.getOut()) {
							System.out.println("Try to moving State from Object "+nowS+" to "+l.getHead());
							if(ss==l.getHead()) {
								stateDiagram.setNowState(l.getHead());
								repaint();
								moveState=false;
								System.out.println("MoveState off");
								return;
							}
						}
						System.out.println("Waring : From nowState can't found relation to clicked State");
					}
					else {
						System.out.println("Waring : Click on unknown shape");
					}
				}
			}
			moveState=false;
			System.out.println("MoveState off");
			return;
		}
		//if draw line mode
		drawLine = (paintType == PaintType.TRANSITION) ? true : false;
		System.out.println("Clicked "+me.getX()+" "+me.getY());
		x = me.getX(); //get mouse click position x
		y = me.getY(); //get mouse click position y
		for (Shape s : stateDiagram.getRealShapes()) {
			if (s.contains(me.getPoint())) { //check if click on shape
				if (s instanceof Rectangle2D) { //if shape is a Rectangle
					clickedShape = true;
					if(drawLine) { //if in draw line mode record shape
						setClickedShape(s);
						x=(int)((s.getBounds().getWidth()/2.0)+s.getBounds().getX());
						y=(int)((s.getBounds().getHeight()/2.0)+s.getBounds().getY());
					}
					System.out.println("Clicked on a rectangle");
				}
				else if (s instanceof Ellipse2D) {   //if shape is a circle
					clickedShape = true;
					if(drawLine) {
						setClickedShape(s);
						x=(int)((s.getBounds().getWidth()/2.0)+s.getBounds().getX());
						y=(int)((s.getBounds().getHeight()/2.0)+s.getBounds().getY());
					}
					System.out.println("Clicked on a circle");
				}
				else {
					System.out.println("Click on unknown shape");
				}
			}
		}
		System.out.println("debug : drawLine "+drawLine+" clickedShape "+clickedShape+" paintType "+paintType);
		if (drawLine) {
			//draw line
			if (clicked == 0 && clickedShape == true) {
				linerec[0] = x;
				linerec[1] = y;
				clicked += 1; //set clicked plus 1
				clickedShape = false; //reset clicked shape status
				moving = true; //turn on draw line preview
			}
			else if (clicked == 1 && clickedShape == true) {
				if(clickedShape1==clickedShape2) {
					//if click on same shape don't draw line
					System.out.println("Clicked on same object, reject");
				}
				else {
					//send position to optimze line position
					int[] ar = optimizeDot(clickedShape1.draw(),clickedShape2.draw(),linerec[0],linerec[1],x,y);
					//new a line
					Line l = new Line(ar[0], ar[1] , ar[2] , ar[3] , clickedShape1 , clickedShape2);
					clickedShape1.addOut(l); //add output transition to clicked shape 1
					clickedShape2.addIn(l); //add input transition to clicked shape 2
					stateDiagram.addL(l); //add line to stateDiagram
					stateDiagram.addLS(text); //add on line text to stateDiagram
					clearText();
				}
				//reset some status indicator
				clicked = 0;
				moving = false;
				clickedShape = false;
				clickedShape1 = null;
				clickedShape2 = null;
			}
			else {
				//if not clear on shape reset status
				clicked = 0;
				moving = false;
				clickedShape1 = null;
				clickedShape2 = null;
			}
		}
		else if(paintType == PaintType.RECTANGLE && !clickedShape) {
			//draw rectangle
			DiagramElement t = new Rectangle(x,y,GlobalVar.radius);
			stateDiagram.addC(nowcolor.setColor()); //add color
			stateDiagram.addS(t); //add shape
			stateDiagram.addSS(text); //add shape string
			if(stateDiagram.getNowState()==null) { //if now state null
				stateDiagram.setNowState(t); //set now state
			}
			clearText();
		}
		else if(paintType == PaintType.CIRCLE && !clickedShape) {
			//draw circle
			DiagramElement t = new Circle(x,y,GlobalVar.radius);
			stateDiagram.addC(nowcolor.setColor());
			stateDiagram.addS(t);
			stateDiagram.addSS(text);
			if(stateDiagram.getNowState()==null) {
				stateDiagram.setNowState(t);
			}
			clearText();
		}
		if(!drawLine) {
			clickedShape=false;
		}
		stateDiagram.removeLine(s2d); //always remove draw line preview
		repaint();
	}
	public void move(MouseEvent me) {
		//draw line preview, s2d is a "temp" line, will be destory and new every move
		if(moving) {
			stateDiagram.removeLine(s2d);
			int qawsx = GlobalVar.offsetX + me.getX();
			int qawsy = GlobalVar.offsetY + me.getY();
			s2d = new Line(linerec[0], linerec[1] , qawsx, qawsy,null,null);
			stateDiagram.addL(s2d);
			stateDiagram.addLS("");
			repaint();
		}
	}
	public void delete(MouseEvent me) {
		//delete shape
		for (Shape s : stateDiagram.getRealShapes()) {
			if (s.contains(me.getPoint())) {
				dragingDiagramElement = stateDiagram.getOneDiagramElement(s);
				if(dragingDiagramElement==stateDiagram.getNowState()) {
					System.out.println("Delete on NowState, reject");
					return;
				}
				if (s instanceof Shape) {
					//get input transition and remove it and its relation shape's record
					Iterator<Line> it1 = (dragingDiagramElement.getIn()).iterator();
					while (it1.hasNext()) {
						Line l = it1.next();
						DiagramElement tailO = l.getTail();
						tailO.removeLine(l);
						dragingDiagramElement.removeLine(l);
						stateDiagram.removeLine((DiagramElement)l);
						it1 = (dragingDiagramElement.getIn()).iterator();
					}
					//get output transition and remove it and its relation shape's record
					it1 = (dragingDiagramElement.getOut()).iterator();
					while (it1.hasNext()) {
						Line l = it1.next();
						DiagramElement headO = l.getHead();
						headO.removeLine(l);
						dragingDiagramElement.removeLine(l);
						stateDiagram.removeLine((DiagramElement)l);
						it1 = (dragingDiagramElement.getOut()).iterator();
					}
					stateDiagram.removeElement(dragingDiagramElement);
					System.out.println("Delete Object : "+dragingDiagramElement);
				}
				else {
					System.out.println("Waring : find wrong type "+s);
				}
			}
		}
		repaint();
	}
	public void saveImage() {
		try {
			BufferedImage bufferedImage = new BufferedImage( 590, 370,BufferedImage.TYPE_4BYTE_ABGR_PRE );
			Graphics2D g2d = bufferedImage.createGraphics();
			//call paintComponent to paint
			paintComponent( g2d );
			//output to current location
			File file = new File( "./", "stateDiagram.png" );
			//output to png file
			ImageIO.write( bufferedImage, "png", file );
			System.out.println("saveImage complete : "+file);
		}
		catch ( Exception ee ) {
			System.out.println( "warning : image not saved." );
		}
	}
	protected void paintComponent(Graphics grphcs) {
		//draw operation, call stateDiagram to draw
		super.paintComponent(grphcs);
		stateDiagram.draw(grphcs);
	}
	public Dimension getPreferredSize() {
		//set canvas size
		return dim;
	}
}
