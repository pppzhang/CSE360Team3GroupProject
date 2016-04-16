import static org.junit.Assert.*;

import org.junit.Test;


public class PigStatsTest {

	@Test
	public void testPigStats() {
		PigStats player = new PigStats("John");
		assertNotNull(player);
		assertEquals(player.getScore(),0);
	}

	@Test
	public void testAddRollNumber() {
		PigStats player = new PigStats("John");
		player.addRollNumber(3);
		player.addRollNumber(4);
		assertEquals(player.getScore(),7); 	//total score = 3+4 =7;
		assertEquals(player.getRollNumber().size(),2); 	//2 roll results
		assertEquals(player.getRollNumber().get(1),(Integer)4); 	// the second roll result shall be 4
	}

	@Test
	public void testGetScore() {
		PigStats player = new PigStats("John");
		player.addRollNumber(3);
		player.addRollNumber(4);
		assertEquals(player.getScore(),7); 	//total score = 3+4 =7;
		player.addRollNumber(1);
		assertEquals(player.getScore(),0); 	//roll result of 1 reset the score to be 0		
	}

	@Test
	public void testGetRollNumber() {
		PigStats player = new PigStats("John");
		player.addRollNumber(3);
		player.addRollNumber(4);
		player.addRollNumber(1);
		assertEquals(player.getRollNumber().size(),3); 	//3 roll results
		assertEquals(player.getRollNumber().get(0), (Integer)3);	// 1st result is 3
		assertEquals(player.getRollNumber().get(1), (Integer)4);	// 1st result is 4
		assertEquals(player.getRollNumber().get(2), (Integer)1);	// 1st result is 1
		
	}

}
