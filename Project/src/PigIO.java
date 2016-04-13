public interface PigIO {
	
	public static final String PING = "ping";
	public static final String PLAYER_JOINED = "joined";
	public static final String PLAYER_LEFT = "left";
	public static final String SET_TURN = "turn";
	public static final String SET_ORDER = "order";
	public static final String OTHER_ROLL = "roll?";
	public static final String SELF_ROLL = "roll";
	public static final String REVEAL = "reveal";
	
	public void exit();
	//public void rollAgain(boolean b);
	public String[] getStats(int playerID);
}