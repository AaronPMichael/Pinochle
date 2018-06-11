import java.awt.BorderLayout;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;


public class GameManager {
	public static Semaphore getd;
	public static Direction direction;
	
	
	
	public static enum Direction{
		NORTH,
		SOUTH,
		EAST,
		WEST
	}
	
	
	public void start(){
		getDirection();
		getConnections();
		
		
		
		
	}
	public void getDirection(){
		getd = new Semaphore(0);
		JFrame hq = new JFrame("select seat");
		hq.setLayout(new BorderLayout());
		hq.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton north = new JButton("Host");
		north.addActionListener(new Listeners.directionListener(Direction.NORTH));
		hq.add(north,BorderLayout.NORTH);
		north.setSize(100, 100);
		JButton east = new JButton("East");
		east.addActionListener(new Listeners.directionListener(Direction.EAST));
		hq.add(east,BorderLayout.EAST);
		east.setSize(100, 100);
		JButton south = new JButton("South");
		south.addActionListener(new Listeners.directionListener(Direction.SOUTH));
		hq.add(south,BorderLayout.SOUTH);
		south.setSize(100, 100);
		JButton west = new JButton("West");
		west.addActionListener(new Listeners.directionListener(Direction.WEST));
		hq.add(west,BorderLayout.WEST);
		west.setSize(100, 100);
		hq.setSize(300,200);
		hq.setVisible(true);
		try {
			getd.acquire(1);
		} catch (InterruptedException e) {}
		hq.dispose();
	}
	public void getConnections(){
		try {
			Socket socket = new Socket("192.168.1.70",90);
			socket.getOutputStream();
			
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
