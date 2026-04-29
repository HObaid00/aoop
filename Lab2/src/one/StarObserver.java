package one;

public class StarObserver implements SignalObserver {

	@Override
	public void updateSignal(double value) {
		for (int i=0;i<(int)value;i++)
			System.out.print('*');
		System.out.println();
	}

}
