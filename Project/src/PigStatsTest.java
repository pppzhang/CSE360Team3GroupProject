import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.junit.Test;


public class PigStatsTest {	

	@Test
	public void testGetPigStats() {
		// test case 1: no .sav file and create a new user profile
		// first remove all .passthepig.sav file under the current directory
		File dir1 = new File("."); 	
		File savFile1[] = dir1.listFiles(new FilenameFilter(){
			public boolean accept(File dir1, String filename) {
				return filename.endsWith(".passthepig.sav");
			}
		});
		for (int i=0; i<savFile1.length; i++)
			savFile1[i].delete();
		
		// without .sav file, the program will initiate a PigStats by asking for username
		PigStats player1 = PigStats.getPigStats();
		// check the username to match the user's input
		int check = JOptionPane.showConfirmDialog(null, "is your username ="+player1.getUserName());
		if (check != JOptionPane.YES_OPTION) 
			fail("Does not match!");
		
		
		//test case 2: existing player and extract info from .sav file
		//delete all .sav file first
		File dir2 = new File("."); 	
		File savFile2[] = dir2.listFiles(new FilenameFilter(){
			public boolean accept(File dir2, String filename) {
				return filename.endsWith(".passthepig.sav");
			}
		});
		for (int i=0; i<savFile2.length; i++)
			savFile2[i].delete();
		//create a .passthepig.sav file 
		try {
			FileOutputStream saveFile = new FileOutputStream("John.passthepig.sav");	//username=John
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(10); 	//total score
			save.writeObject(3.2);	//average score
			save.writeObject(3); 	//number of games played
			save.writeObject(1);	//number of games won
			save.writeObject(1); 	//number of ones rolled	
			save.close();			//closes ObjOutStream
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// the program shall extract info from the .sav file
		PigStats player2 = PigStats.getPigStats();
		// check the username to be John, total score to be 10
		assertEquals(player2.getUserName(),"John");
		assertEquals(player2.getTotalScore(),10);		
	}

	@Test
	public void testSave() {
		//create a user profile and load it, save roll/winner results and test
		// step 1: remove all .passthepig.sav files
		File dir = new File("."); 	
		File savFile[] = dir.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".passthepig.sav");
			}
		});
		for (int i=0; i<savFile.length; i++)
			savFile[i].delete();
		
		// step 2: create a profile for Tom
		try {
			FileOutputStream saveFile = new FileOutputStream("Tom.passthepig.sav");	//username=Tom
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(0); 	//total score
			save.writeObject(0.0);	//average score
			save.writeObject(0); 	//number of games played
			save.writeObject(0);	//number of games won
			save.writeObject(0); 	//number of ones rolled
			save.close();			//closes ObjOutStream
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//step 3: save a roll result and not-won case
		PigStats player = PigStats.getPigStats();
		ArrayList<Integer> rolls = new ArrayList<Integer>();
		rolls.add(4);
		rolls.add(2);
		rolls.add(3);
		rolls.add(6);
		player.save(rolls, false);
		
		//step 4: check the result
		assertEquals(player.getTotalScore(),15);
		assertEquals(player.getAverageScore(),15.0,0.001);
		assertEquals(player.getNumGamesPlayed(),1);
		assertEquals(player.getNumGamesWon(),0);
		assertEquals(player.getNumOnesRolled(),0);
		
		//step 5: save roll results ended with one
		rolls.clear();
		rolls.add(5);
		rolls.add(1);
		player.save(rolls, false);
		
		//step 6: check the result
		assertEquals(player.getTotalScore(),15);
		assertEquals(player.getAverageScore(),7.5,0.001);
		assertEquals(player.getNumGamesPlayed(),2);
		assertEquals(player.getNumGamesWon(),0);
		assertEquals(player.getNumOnesRolled(),1);
	
	}

	@Test
	public void testGetUserName() {
		//create a user profile and load it, save roll/winner results and test
		// step 1: remove all .passthepig.sav files
		File dir = new File("."); 	
		File savFile[] = dir.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".passthepig.sav");
			}
		});
		for (int i=0; i<savFile.length; i++)
			savFile[i].delete();
		
		// step 2: create a profile for Tom
		try {
			FileOutputStream saveFile = new FileOutputStream("Tom.passthepig.sav");	//username=Tom
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(0); 	//total score
			save.writeObject(0.0);	//average score
			save.writeObject(0); 	//number of games played
			save.writeObject(0);	//number of games won
			save.writeObject(0); 	//number of ones rolled
			save.close();			//closes ObjOutStream
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//step 3: save a roll result and not-won case
		PigStats player = PigStats.getPigStats();
		ArrayList<Integer> rolls = new ArrayList<Integer>();
		rolls.add(4);
		rolls.add(2);
		rolls.add(3);
		rolls.add(6);
		player.save(rolls, false);
		
		//step 4: check the result		
		assertEquals(player.getUserName(),"Tom");
	}

	@Test
	public void testGetTotalScore() {
		//create a user profile and load it, save roll/winner results and test
		// step 1: remove all .passthepig.sav files
		File dir = new File("."); 	
		File savFile[] = dir.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".passthepig.sav");
			}
		});
		for (int i=0; i<savFile.length; i++)
			savFile[i].delete();
		
		// step 2: create a profile for Tom
		try {
			FileOutputStream saveFile = new FileOutputStream("Tom.passthepig.sav");	//username=Tom
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(0); 	//total score
			save.writeObject(0.0);	//average score
			save.writeObject(0); 	//number of games played
			save.writeObject(0);	//number of games won
			save.writeObject(0); 	//number of ones rolled
			save.close();			//closes ObjOutStream
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//step 3: save a roll result and not-won case
		PigStats player = PigStats.getPigStats();
		ArrayList<Integer> rolls = new ArrayList<Integer>();
		rolls.add(4);
		rolls.add(2);
		rolls.add(3);
		rolls.add(6);
		player.save(rolls, false);
		
		//step 4: check the result
		assertEquals(player.getTotalScore(),15);
	}

	@Test
	public void testGetAverageScore() {
		//create a user profile and load it, save roll/winner results and test
		// step 1: remove all .passthepig.sav files
		File dir = new File("."); 	
		File savFile[] = dir.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".passthepig.sav");
			}
		});
		for (int i=0; i<savFile.length; i++)
			savFile[i].delete();
		
		// step 2: create a profile for Tom
		try {
			FileOutputStream saveFile = new FileOutputStream("Tom.passthepig.sav");	//username=Tom
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(0); 	//total score
			save.writeObject(0.0);	//average score
			save.writeObject(0); 	//number of games played
			save.writeObject(0);	//number of games won
			save.writeObject(0); 	//number of ones rolled
			save.close();			//closes ObjOutStream
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//step 3: save a roll result and not-won case
		PigStats player = PigStats.getPigStats();
		ArrayList<Integer> rolls = new ArrayList<Integer>();
		rolls.add(4);
		rolls.add(2);
		rolls.add(3);
		rolls.add(6);
		player.save(rolls, false);
		
		//step 4: check the result
		assertEquals(player.getAverageScore(),15.0,0.001);
	}

	@Test
	public void testGetNumGamesPlayed() {
		//create a user profile and load it, save roll/winner results and test
		// step 1: remove all .passthepig.sav files
		File dir = new File("."); 	
		File savFile[] = dir.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".passthepig.sav");
			}
		});
		for (int i=0; i<savFile.length; i++)
			savFile[i].delete();
		
		// step 2: create a profile for Tom
		try {
			FileOutputStream saveFile = new FileOutputStream("Tom.passthepig.sav");	//username=Tom
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(0); 	//total score
			save.writeObject(0.0);	//average score
			save.writeObject(0); 	//number of games played
			save.writeObject(0);	//number of games won
			save.writeObject(0); 	//number of ones rolled
			save.close();			//closes ObjOutStream
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//step 3: save a roll result and not-won case
		PigStats player = PigStats.getPigStats();
		ArrayList<Integer> rolls = new ArrayList<Integer>();
		rolls.add(4);
		rolls.add(2);
		rolls.add(3);
		rolls.add(6);
		player.save(rolls, false);
		
		//step 4: check the result
		assertEquals(player.getNumGamesPlayed(),1);
	}

	@Test
	public void testGetNumGamesWon() {
		//create a user profile and load it, save roll/winner results and test
		// step 1: remove all .passthepig.sav files
		File dir = new File("."); 	
		File savFile[] = dir.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".passthepig.sav");
			}
		});
		for (int i=0; i<savFile.length; i++)
			savFile[i].delete();
		
		// step 2: create a profile for Tom
		try {
			FileOutputStream saveFile = new FileOutputStream("Tom.passthepig.sav");	//username=Tom
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(0); 	//total score
			save.writeObject(0.0);	//average score
			save.writeObject(0); 	//number of games played
			save.writeObject(0);	//number of games won
			save.writeObject(0); 	//number of ones rolled
			save.close();			//closes ObjOutStream
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//step 3: save a roll result and not-won case
		PigStats player = PigStats.getPigStats();
		ArrayList<Integer> rolls = new ArrayList<Integer>();
		rolls.add(4);
		rolls.add(2);
		rolls.add(3);
		rolls.add(6);
		player.save(rolls, false);
		
		//step 4: check the result
		assertEquals(player.getNumGamesWon(),0);
	}

	@Test
	public void testGetNumOnesRolled() {
		//create a user profile and load it, save roll/winner results and test
		// step 1: remove all .passthepig.sav files
		File dir = new File("."); 	
		File savFile[] = dir.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".passthepig.sav");
			}
		});
		for (int i=0; i<savFile.length; i++)
			savFile[i].delete();
		
		// step 2: create a profile for Tom
		try {
			FileOutputStream saveFile = new FileOutputStream("Tom.passthepig.sav");	//username=Tom
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(0); 	//total score
			save.writeObject(0.0);	//average score
			save.writeObject(0); 	//number of games played
			save.writeObject(0);	//number of games won
			save.writeObject(0); 	//number of ones rolled
			save.close();			//closes ObjOutStream
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//step 3: save a roll result and not-won case
		PigStats player = PigStats.getPigStats();
		ArrayList<Integer> rolls = new ArrayList<Integer>();
		rolls.add(4);
		rolls.add(2);
		rolls.add(3);
		rolls.add(6);
		player.save(rolls, false);
		
		//step 4: check the result
		assertEquals(player.getNumOnesRolled(),0);
	}

}
