import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Circle implements Icon {
    int size;
    Color color;

    public Circle(Color c){
        size = 40;
        this.color = c;
    }
    public int getIconWidth(){
        return size;
    }
    public int getIconHeight(){
        return size;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Float circle = new Ellipse2D.Float(x,y,size,size);
        g2.setColor(color);
        g2.fill(circle);
    }
}
