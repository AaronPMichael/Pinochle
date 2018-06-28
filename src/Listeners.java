import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class Listeners {
	public static class directionListener implements ActionListener {
		public GameManager.Direction n;

		public directionListener(GameManager.Direction n) {
			this.n = n;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			GameManager.direction = n;
			GameManager.getd.release();
		}
	}

	public static class SemListener implements ActionListener {

		public Semaphore sem;
		public SemListener(Semaphore sem){
			this.sem = sem;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			sem.release();
		}

	}
}
