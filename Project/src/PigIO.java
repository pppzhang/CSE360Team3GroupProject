import java.io.Serializable;

public interface PigIO {
	
	public static final int PORT = 9898;
	
	public static final String PING = "ping";
	public static final String PLAYER_JOINED = "joined";
	public static final String PLAYER_LEFT = "left";
	public static final String SET_TURN = "turn";
	public static final String SET_ORDER = "order";
	public static final String OTHER_ROLL = "rollother";
	public static final String SELF_ROLL = "roll";
	public static final String REVEAL = "reveal";
	public static final String ROLL_AGAIN = "roll?";
	
	public void exit();
	
	public class PigMsg implements Serializable {
		private static final long serialVersionUID = -7084128846562180341L;
		public String command;
		public int[] args;
		public PigStats stats;
		
		public PigMsg(String command, int[] args) {
			this.command = command;
			this.args = args;
		}
		
		public PigMsg(String command, int arg) {
			this.command = command;
			int[] temp = {arg};
			this.args = temp;
		}
		
		public PigMsg(String command, PigStats stats) {
			this.command = command;
			this.stats = stats;
		}
	}
}