package one;


public class SignalRun {

	public static void main(String[] args) throws InterruptedException {
		SignalWindow window = new SignalWindow();
		Signal s = new Signal(window.getTextArea());
		s.addSignalObserver(window);
		s.addSignalObserver(new StarObserver());
		s.setSampler(new SinusSampler(0.0, 0.1));

	}

}
