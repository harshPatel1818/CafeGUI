package smProject4;

public class Coffee extends MenuItem implements Customizable {
	private final int SHORT  = 1;
	private final int TALL   = 2;
	private final int GRANDE = 3;
	private final int VENTI  = 4;

	private int addons = 0;

	public boolean add(Object obj) {
		return true;
	}

	public boolean remove(Object obj) {
		return false;
	}
}
