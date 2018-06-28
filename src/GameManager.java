import java.awt.BorderLayout;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Semaphore;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import players.Human;
import players.Player;
import players.nonHuman;

public class GameManager {
	public static Semaphore getd;
	public static Semaphore getais;
	public static Direction direction;
	public static Player[] players;

	public static enum Direction {
		NORTH, SOUTH, EAST, WEST
	}

	public void start() {
		getDirection();
		players = new Player[4];
		if (direction == Direction.NORTH)
			setUpNorth();
		else
			setUpOther();

	}

	public void getDirection() {
		getd = new Semaphore(0);
		JFrame hq = new JFrame("select seat");
		hq.setLayout(new BorderLayout());
		hq.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton north = new JButton("Host");
		north.addActionListener(new Listeners.directionListener(Direction.NORTH));
		hq.add(north, BorderLayout.NORTH);
		north.setSize(100, 100);
		JButton east = new JButton("East");
		east.addActionListener(new Listeners.directionListener(Direction.EAST));
		hq.add(east, BorderLayout.EAST);
		east.setSize(100, 100);
		JButton south = new JButton("South");
		south.addActionListener(new Listeners.directionListener(Direction.SOUTH));
		hq.add(south, BorderLayout.SOUTH);
		south.setSize(100, 100);
		JButton west = new JButton("West");
		west.addActionListener(new Listeners.directionListener(Direction.WEST));
		hq.add(west, BorderLayout.WEST);
		west.setSize(100, 100);
		hq.setSize(300, 200);
		hq.setVisible(true);
		try {
			getd.acquire(1);
		} catch (InterruptedException e) {
		}
		hq.dispose();
	}

	public void setUpNorth() {
		JRadioButton[][] choices = new JRadioButton[3][2];
		String[] names = { "east: ", "south: ", "west: " };
		getais = new Semaphore(0);
		JFrame frame = new JFrame("Configure players");
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		for (int i = 0; i < 3; i++) {
			JPanel panel = new JPanel();
			ButtonGroup group = new ButtonGroup();
			JRadioButton ai = new JRadioButton();
			JRadioButton human = new JRadioButton();
			group.add(ai);
			group.add(human);
			human.setSelected(true);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			panel.add(new JLabel(names[i]));
			panel.add(human);
			panel.add(new JLabel("human"));
			panel.add(ai);
			panel.add(new JLabel("ai"));
			choices[i][0] = human;
			choices[i][1] = ai;
			frame.add(panel);
			panel.setVisible(true);
		}
		JButton done = new JButton("Done");
		done.addActionListener(new Listeners.SemListener(getais));
		frame.add(done);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			getais.acquire(1);
		} catch (Exception e) {}
		frame.dispose();
		for (int i=0;i<3;i++){
			if (choices[i][0].isSelected()){
				players[i] = new Human();}
			else{
				players[i] = new nonHuman();}
		}
	}

	public void setUpOther() {
	}

}
