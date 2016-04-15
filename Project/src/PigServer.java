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


public class PigServer implements PigIO {
	ServerSocket server;
	private PigGUI gui;
	private ArrayList<Player> clients;
	private Lobby lobby;
	private PigEngine engine;

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
	
	@Override
	public void exit() {
		for (Player p : clients) {
			p.close();
		}
		clients.removeAll(clients);
	}

	public void startGame() {
		lobby.running = false;
		engine = new PigEngine(this, clients.size() + 1);
		engine.start();
	}
	
	@Override
	public PigStats getStats(int playerID) {
		return clients.get(playerID).stats;
	}
	
	/*
	 * UTILITY CLASSES
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
			}
			while (running) {
				try {
					(new Player(server.accept())).start();
					//TODO update existing players of new player
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class Player extends Thread {

		private Socket socket;
		private ObjectInputStream input;
		private ObjectOutputStream output;
		protected PigStats stats;

		public Player(Socket socket) throws SocketException {
			this.socket = socket;
			this.socket.setSoTimeout(30000);
		}
		
		public Player(PigStats stats) {
			socket = null;
			this.stats = stats;
		}
		
		public void run() {
				try {
					input = new ObjectInputStream(socket.getInputStream());
					output = new ObjectOutputStream(socket.getOutputStream());
					stats = (PigStats)input.readObject();
					clients.add(this);
					//TODO update new player of existing players
					this.socket.setSoTimeout(0);
					while (true) {
						serverParse((PigMsg)input.readObject());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		public void send(PigMsg msg) {
			try {
				output.writeObject(msg);
			} catch (IOException e) {
				//TODO disconnect notification if we want it
			}
		}
		
		public void close() {
			try {
				output.close();
				input.close();
				socket.close();
			} catch (IOException e) {
			}
		}
	}
	
	private class PigMsg implements Serializable {
		private static final long serialVersionUID = -7084128846562180341L;
		public String command;
		public int[] args;
		
		public PigMsg(String command, int[] args) {
			this.command = command;
			this.args = args;
		}
	}
	
	/*
	 * UTILITY METHODS
	 */
	
	private void serverParse(PigMsg msg) {
		switch (msg.command) {
		case GET_STATS:
			getStats(msg.args[0]);
			break;
		case ROLL_AGAIN:
			//TODO
			break;
		case PLAYER_LEFT:
			clients.remove(msg.args[0]);
			sendAll(msg);
		}
	}
	
	private void clientParse(PigMsg msg) {
		//TODO
	}
	
	private void sendAll(PigMsg msg) {
		clientParse(msg);
		for (int i = 1; i < clients.size(); i++) {
			clients.get(i).send(msg);
		}
	}
	
	/*
	 * COMMANDS ACCESSED BY ENGINE
	 */
	
	public void setTurn(int playerID) {
		//TODO engine action
	}
	
	public void setOrder(int[] playerIDs) {
		//TODO engine action
	}
	
	public void roll(int playerID, int previousValue, int newValue) {
		//TODO engine action
	}
	
	public void reveal(int[] lastValues) {
		//TODO engine action
	}
	
}