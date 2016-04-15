import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class PigClient extends Thread implements PigIO {
	
	private PigGUI gui;
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	private PigClient(PigGUI gui, PigStats stats, Socket socket) throws IOException {
		this.gui = gui;
		this.socket = socket;
		input = new ObjectInputStream(socket.getInputStream());
		output = new ObjectOutputStream(socket.getOutputStream());
		output.writeObject(stats);
	}
	
	/**
	 * Use instead of a constructor. Asks for an IP and attempts to connect. Displays an error message if fail.
	 * @param gui the associated GUI
	 * @param stats the player's statistics
	 * @return Connected and running PigClient if success, null if fail.
	 */
	static public PigClient getPigClient(PigGUI gui, PigStats stats) {
			String serverAddress = JOptionPane.showInputDialog("Enter the host's IP Address: ");
			PigClient result = null;
			try {
				Socket s = new Socket(serverAddress, PORT);
				result = new PigClient(gui, stats, s);
				result.start();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
				result = null;
			}
		return result;
	}

	public void run() {
		try {
			while (true) {
					parse((PigMsg) input.readObject());
			}
		} catch (Exception e) {
			exit();
		}
	}
	
	private void parse(PigMsg msg) {
		switch (msg.command) {
		case PLAYER_JOINED:
			gui.join(msg.stats);
		case PLAYER_LEFT:
			gui.leave(msg.args[0]);
		case SET_TURN:
			//TODO
		case SET_ORDER:
			gui.setOrder(msg.args);
		case OTHER_ROLL:
			gui.rollOther(msg.args[0]);
		case SELF_ROLL:
			gui.roll(msg.args[0]);
		}
	}

	@Override
	public void exit() {
		try {
			output.writeObject(new PigMsg(PLAYER_LEFT, 0));
		} catch (IOException e) {
		}
		try {
			output.close();
		} catch (IOException e) {
		}
		try {
			input.close();
		} catch (IOException e) {
		}
		try {
			socket.close();
		} catch (IOException e) {
		}
		gui.disconnect();
	}

	@Override
	public PigStats getStats(int playerID) {
		// TODO Auto-generated method stub
		return null;
	}}
