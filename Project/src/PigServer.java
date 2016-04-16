import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * 
 * @author Nathan Sears
 *
 */
public class PigServer implements PigIO {
	ServerSocket server;
	private PigGUI gui;
	private ArrayList<Player> clients;
	private Lobby lobby;
	private PigEngine engine;

	/**
	 * @param gui The associated GUI
	 * @param stats The host's stats.
	 */
	public PigServer(PigGUI gui, PigStats stats) {
		this.gui = gui;
		lobby = new Lobby();
		lobby.start();
		clients = new ArrayList<Player>();
		clients.add(new Player(stats));
	}

	/*
	 * CLASSES ACCESSED BY GUI
	 */
	
	/* (non-Javadoc)
	 * @see PigIO#exit()
	 */
	@Override
	public void exit() {
		for (Player p : clients) {
			p.close();
		}
		clients.removeAll(clients);
	}

	/**
	 * Called by the host GUI to end the lobby and start the game.
	 */
	public void startGame() {
		lobby.running = false;
		lobby.interrupt();
		engine = new PigEngine(this, clients.size());
		//engine.start();
	}
	
	/*
	 * UTILITY CLASSES
	 */

	/**
	 * @author Nathan Sears
	 * Creates Players as people connect.
	 */
	private class Lobby extends Thread {
		public boolean running;
		ServerSocket server;
		public void run() {
			running = true;
			try {
				server = new ServerSocket(PORT);
				server.setSoTimeout(10000);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			while (running) {
				try {
					Player p = new Player(server.accept());
					sendAll(new PigMsg(PLAYER_JOINED, p.stats));
				} catch (Exception e) {
				}
			}
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @author Nathan Sears
	 * Handles communication to and from a single player.
	 */
	private class Player extends Thread {

		private boolean running;
		private Socket socket;
		private ObjectInputStream input;
		private ObjectOutputStream output;
		protected PigStats stats;

		public Player(Socket socket) throws IOException, ClassNotFoundException {
			this.socket = socket;
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
			stats = (PigStats) input.readObject();
			for (int i = 0; i < clients.size(); i++) {
				output.writeObject(new PigMsg(PLAYER_JOINED, clients.get(i).stats));
			}
			clients.add(this);
			this.start();
		}
		
		public Player(PigStats stats) {
			socket = null;
			this.stats = stats;
		}
		
		public void run() {
				running = true;
				try {
					while (running) {
						serverParse((PigMsg) input.readObject(), clients.indexOf(this));
					}
				} catch (Exception e) {
					if (running) serverParse(new PigMsg(PLAYER_LEFT, 0), clients.indexOf(this));
				}
		}
		
		/**
		 * Attempts to send a message to the player. If fails, notifies the Server.
		 * @param msg Message to be sent to the player.
		 * @return true if success, false if fail
		 */
		public boolean send(PigMsg msg) {
			//disconnected player
			if (!running) {
				if (msg.command.equals(SELF_ROLL)) {
					serverParse(new PigMsg(ROLL_AGAIN, 1), clients.indexOf(this));
				}
				return true;
			}
			//host player
			if (socket == null) {
				clientParse(msg);
				return true;
			}
			//connected player
			try {
				output.writeObject(msg);
				return true;
			} catch (IOException e) {
				serverParse(new PigMsg(PLAYER_LEFT, 0), clients.indexOf(this));
				return false;
			}
		}
		
		/**
		 * Shuts the player communications down, disconnects.
		 */
		public void close() {
			running = false;
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
		}
	}
	
	/*
	 * UTILITY METHODS
	 */
	
	/**
	 * @param msg The message sent to the server from a player.
	 * @param playerID The player who sent the message.
	 */
	private void serverParse(PigMsg msg, int playerID) {
		switch (msg.command) {
		case ROLL_AGAIN:
			//TODO
			break;
		case PLAYER_LEFT:
			clients.get(playerID).close();
			if (lobby.running == true) clients.remove(playerID);
			msg.args[0] = playerID;
			sendAll(msg);
		}
	}
	
	/*
	 * Takes a message from the server to send to the host player.
	 * If you change this, change PigClient.parse() as well.
	 */
	private void clientParse(PigMsg msg) {
		switch (msg.command) {
		case PLAYER_JOINED:
			gui.join(msg.stats);
		case PLAYER_LEFT:
			gui.leave(msg.args[0]);
		case SET_TURN:
			gui.setTurn(msg.args[0]);
		case SET_ORDER:
			gui.setOrder(msg.args);
		case OTHER_ROLL:
			gui.rollOther(msg.args[0]);
		case SELF_ROLL:
			gui.roll(msg.args[0]);
		}
	}
	
	/*
	 * Sends a message to all players.
	 */
	private void sendAll(PigMsg msg) {
		for (int i = 0; i < clients.size(); i++) {
			if (!clients.get(i).send(msg))
				i--; // because failure means the player is removed
		}
	}
	
	/*
	 * COMMANDS ACCESSED BY ENGINE
	 */
	
	/**
	 * @param playerID
	 */
	public void setTurn(int playerID) {
		//TODO engine action
	}
	
	/**
	 * @param playerIDs
	 */
	public void setOrder(int[] playerIDs) {
		//TODO engine action
	}
	
	/**
	 * @param playerID
	 * @param previousValue
	 * @param newValue
	 */
	public void roll(int playerID, int previousValue, int newValue) {
		//TODO engine action
	}
	
	/**
	 * @param lastValues
	 */
	public void reveal(int[] lastValues) {
		//TODO engine action
	}
	
}