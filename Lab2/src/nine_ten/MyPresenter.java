package nine_ten;

import javax.swing.*;
import java.awt.*;

public class MyPresenter extends Presenter {

    public MyPresenter() {
        super();
    }

    @Override
    public JComponent createCenterComponent() {
        ImageIcon icon = new ImageIcon("image2.jpg");
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        return label;
    }

    @Override
    public void northButtonPressed() {
        System.out.println("North Button Pressed");
    }

    @Override
    public void eastButtonPressed() {
        System.out.println("East 3232 Button Pressed");
    }

    @Override
    public void southButtonPressed() {
        System.out.println("South Button Pressed");
    }

    @Override
    public void westButtonPressed() {
        System.out.println("West Button Pressed");
    }

    public static void main(String[] args) {
        MyPresenter presenter = new MyPresenter();
    }
}