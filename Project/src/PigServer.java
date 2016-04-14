import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


public class PigServer implements PigIO {
	ServerSocket server;
	private PigGUI gui;
	private ArrayList<Player> clients;
	private Lobby lobby;
	private PigEngine engine;

	public PigServer(PigGUI gui) {
		this.gui = gui;
		lobby = new Lobby();
		lobby.start();
		clients = new ArrayList<Player>();
	}

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
		PigEngine.start();
	}

	private class Lobby extends Thread {
		public boolean running;
		ServerSocket server;
		public void run() {
			running = true;
			try {
				server = new ServerSocket(9898);
				server.setSoTimeout(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			while (running) {
				try {
					(new Player(server.accept())).start();
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
		private String[] stats;

		public Player(Socket socket) throws SocketException {
			this.socket = socket;
			this.socket.setSoTimeout(30000);
		}
		
		public void run() {
				try {
					input = new ObjectInputStream(socket.getInputStream());
					output = new ObjectOutputStream(socket.getOutputStream());
					//TODO collect stats
					clients.add(this);
					this.socket.setSoTimeout(0);
					while (true) {
						parse()
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		public void send(String[] str) {
			//TODO printing to client
		}
		
		public void read() {
			//TODO reading from client
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

	@Override
	public String[] getStats(int playerID) {
		return clients.get(playerID).stats;
	}
	
	/*
	 * The following commands will be accessed by the Engine.
	 */
	
	public void setTurn(int playerID) {
		//TODO engine action
	}
	
	public void setOrder(int[] playerIDs) {
		//TODO engine action
	}
	
	public boolean roll(int playerID, int previousValue, int newValue) {
		//TODO engine action
	}
	
	public void reveal(int[] lastValues) {
		//TODO engine action
	}
	
}