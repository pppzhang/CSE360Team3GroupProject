import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class PigClient implements PigIO {
	
	private PigGUI gui;
	
	private PigClient(PigGUI gui, PigStats stats) {
		this.gui = gui;
		
	}
	
	public PigClient getPigClient(PigGUI gui, PigStats stats) {
		String serverAddress = JOptionPane.showInputDialog("Enter the host's IP Address: ");
		try {
			Socket s = new Socket(serverAddress, 9898);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (new PigClient(gui, stats));
	}


	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PigStats getStats(int playerID) {
		// TODO Auto-generated method stub
		return null;
	}}
