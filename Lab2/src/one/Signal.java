package one;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.event.*;
import java.util.*;

public class Signal {
	private final int SAMPLING = 1000;

	Sampler sampler = new DefaultSampler();

	public void setSampler(Sampler s) {
		sampler = s;
	}

	private List<SignalObserver> observers = new ArrayList<>();

	public void addSignalObserver(SignalObserver so) {
		observers.add(so);
	}

	public Signal(JTextArea textArea) {
		Timer t = new Timer(SAMPLING, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double amplitude = sampler.read();
				// jta.append(""+String.format("%.6f", amplitude)+"\n");
				for(SignalObserver so : observers)
					so.updateSignal(amplitude);
			}
		});
		t.start();
	}

}