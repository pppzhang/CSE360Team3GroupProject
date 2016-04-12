import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class PigServer implements PigIO {
	ServerSocket server;
	private PigGUI gui;
	private ArrayList<Player> clients;
	private Lobby lobby;

	public PigServer(PigGUI gui) {
		server = new ServerSocket(9898);
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
	}

	private class Lobby extends Thread {
		public boolean running;
		public void run() {
			running = true;
			server.setSoTimeout(10000);
			while (running) {
				try {
					(new Player(server.accept())).start();
				} finally {
				}
			}
		}
	}

	private class Player extends Thread {

		private Socket socket;
		private BufferedReader input;
		private PrintWriter output;
		private String[] stats;

		public Player(Socket socket) {
			this.socket = socket;
			this.socket.setSoTimeout(30000);
		}
		
		public void run() {
			if (socket != null) {
				try {
					input = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
					output = new PrintWriter(socket.getOutputStream(), true);
					//TODO collect stats
					clients.add(this);
				} finally {
					
				}
			}
			else {
				
			}
		}
		
		public void ask()
		
		public void close() {
			output.close();
			input.close();
			socket.close();
		}
	}

	@Override
	public void rollAgain(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getStats(int playerID) {
		return clients.get(playerID).stats;
	}
}