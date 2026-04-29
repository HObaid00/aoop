import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterFace implements ActionListener{

    private JButton green;
    private JButton red;
    private JButton blue;
    private Color c = new Color(255,0,0);

    private JLabel label;

    public void screen() {
        JFrame f = new JFrame();
        JLabel message = new JLabel("Please choose the following colors");

        Circle circle = new Circle(c);
        label = new JLabel(circle);

        green = new JButton("Green");
        red = new JButton("Red");
        blue = new JButton("Blue");

        green.addActionListener(this);
        red.addActionListener(this);
        blue.addActionListener(this);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new FlowLayout());
        f.add(message);
        f.add(green);
        f.add(red);
        f.add(blue);
        f.add(label);
        f.pack();
        f.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == green){
            c = Color.green;
        }
        else if(e.getSource() == blue){
            c = Color.blue;
        }
        else if(e.getSource() == red){
            c = Color.red;
        }
        label.setIcon(new Circle(c));
    }


}




