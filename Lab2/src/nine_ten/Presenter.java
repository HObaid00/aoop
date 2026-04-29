package nine_ten;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public abstract class Presenter {

	private JTextArea textArea;
	public abstract JComponent createCenterComponent();

	public abstract void northButtonPressed();
	public abstract void eastButtonPressed();
	public abstract void southButtonPressed();
	public abstract void westButtonPressed();
	public Presenter() {
		JFrame frame = new JFrame();
		frame.setSize(2100,1600);
		// setup layout manager for frame
		frame.setLayout(new BorderLayout());/* BorderLayout() = 5 areas, Center, North, South, East, West*/


		JButton northButton = new JButton("North");
		northButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				northButtonPressed();
			}
		});
		JPanel northPanel = new JPanel();
		northPanel.add(northButton);
		frame.add(northPanel, BorderLayout.NORTH);


		// create other components (text component, e.g.), add them to frame
		JButton southButton = new JButton("South");
		southButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				southButtonPressed();
			}
		});
		JPanel southPanel = new JPanel();
		southPanel.add(southButton);
		frame.add(southPanel, BorderLayout.SOUTH);


		JButton westButton = new JButton("West");
		westButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				westButtonPressed();
				Container container = frame.getContentPane();
				Component centerComponent = container.getComponent(4);
				container.remove(centerComponent);
				JComponent newCenterComponent = createCenterComponent();
				container.add(newCenterComponent, BorderLayout.CENTER);
				container.revalidate();
			}
		});
		JPanel westPanel = new JPanel();
		westPanel.add(westButton);
		frame.add(westPanel, BorderLayout.WEST);


		JButton eastButton = new JButton("East");
		eastButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Container container = frame.getContentPane();
				Component centerComponent = container.getComponent(4);
				container.remove(centerComponent);
				eastButtonPressed();
				JComponent newCenterComponent = createCenterComponent();
				container.add(newCenterComponent, BorderLayout.CENTER);
				container.revalidate();
			}
		});
		JPanel eastPanel = new JPanel();
		eastPanel.add(eastButton);
		frame.add(eastPanel, BorderLayout.EAST);


		// add centerComponent to frame
		JPanel centerPanel = new JPanel();
		JComponent centerComponent = createCenterComponent();
		centerPanel.add(centerComponent);
		frame.add(centerPanel, BorderLayout.CENTER);


		// show frame
		//frame.pack();
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame .EXIT_ON_CLOSE);

	}

	public void showText(String text, JTextArea textArea1) {
		// update the text component
		textArea1.setText(text);
		System.out.println("showText is active");
	}
}
