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
	public static final String GET_STATS = "stats";
	public static final String ROLL_AGAIN = "roll?";
	
	public void exit();
	//public void rollAgain(boolean b);
	public PigStats getStats(int playerID);
}