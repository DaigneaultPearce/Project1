import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Project1 {//rename
/*
 * Project1
 * Daigneault Pardo Pearce
 * Comp482
 * Spring2018
 * Professor John Noga
 */
	public static void main(String[] args) throws IOException { 
		
		//Initialize scanner & file.
		Scanner file = new Scanner(new File("input1.txt"));
		
		//Pull first number in the file.
		int numPersons = file.nextInt();
		
		//Initialize preference lists,  processed lists, given matching by man and calculated matching by woman.
		int[][] mensPreference = new int[numPersons][numPersons];
		int[][] mensProcessedList = new int[numPersons][numPersons];
		int[][] womensPreference = new int[numPersons][numPersons];
		int[][] womensProcessedList = new int[numPersons][numPersons];
		int[] mMatch = new int[numPersons];
		int[] wMatch = new int[numPersons];
		
		//Populate the mens preference lists.
		for(int i = 0; i < numPersons; i++) {
			for(int j = 0; j < numPersons; j++) {
				mensPreference[i][j] = file.nextInt();
			}
		}
		
		//Populate the womens preference lists.
		for(int i = 0; i < numPersons; i++) {
			for(int j = 0; j < numPersons; j++) {
				womensPreference[i][j] = file.nextInt();
			}		
		}
		
		//Populate the given mens matching.
		for(int i = 0; i < numPersons; i++) {
			mMatch[i] = file.nextInt();
		}
		
		//Calculate womens matching.
		for(int i = 0; i < numPersons; i++) {
			wMatch[mMatch[i] - 1] = i + 1;
		}
		
		//Re-process mens preference lists by rank. This step maintains O(n^2) solution.
		for(int i = 0; i < numPersons; i++) {
			for(int j = 0; j < numPersons; j++) {
				mensProcessedList[i][mensPreference[i][j]-1] = j+1;
			}
		}
		
		//Re-process womens preference lists by rank. This step maintains O(n^2) solution.
		for(int i = 0; i < numPersons; i++) {
			for(int j = 0; j < numPersons; j++) {
				womensProcessedList[i][womensPreference[i][j]-1] = j+1;
			}
		}
		
		//Check for instability.
		boolean mStable = true;	//Does man prefer his match?
		boolean wStable = true;	//Does woman prefer her match?
		int sadMan = -1; 		//index 0 of instability.
		int sadWoman = -1; 		//index 1 of instability.
		for(int i = 0; i < numPersons; i++) {//For every man...
			if(mStable && wStable) {//Guard: Still looking for an instability?
				for(int j = 0; j < numPersons; j++) {
					if(mensPreference[i][j] == mMatch[i]) {//Guard: Does man have his preferred woman?
						break;
					}
					if(mensPreference[i][j] != mMatch[i]) {//Does man prefer a woman other than his current match?
						//local scope variables for readability.
						int preference = mensPreference[i][j];
						mStable = false;
						sadMan = (i + 1);
						if(womensProcessedList[preference - 1][i] < wMatch[i]) {
							wStable = false;
							sadWoman = preference;
						}
					}
				}
			}
		}
		//Print result.
		if(!mStable && !wStable) {
			System.out.println("No " + '(' + sadMan + ',' + sadWoman + ')');
		}else {
			System.out.println("Yes");
		}
		//Close the scanner.
		file.close();
	}
}
