import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestFile extends JFrame implements MouseListener {
    public TestFile(){
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // addMouseListener(this);
    } 

    public static void main (String[] args) {
        TestFile test = new TestFile();
        
        test.setVisible(true);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Canvas canvas = new Canvas(100,100);
        canvas.setVisible(true);
        test.add(canvas);

    }
 
    // this function is invoked when the mouse is released
    public void mouseReleased(MouseEvent e) {}
 
    // this function is invoked when the mouse exits the component
    public void mouseExited(MouseEvent e) {}
 
    // this function is invoked when the mouse enters the component
    public void mouseEntered(MouseEvent e) {}
 
    // this function is invoked when the mouse is pressed or released
    public void mouseClicked(MouseEvent e) {}
    
}

class Canvas extends JComponent {

    protected int x, y;
    protected int r = 10;

    public Canvas(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillOval(x-r, y-r, 2*r, 2*r);
    }
}
