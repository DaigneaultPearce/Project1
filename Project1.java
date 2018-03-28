import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Project1 {//rename

	public static void main(String[] args) throws IOException { 
		
		//Initialize scanner & file.
		Scanner file = new Scanner(new File("input1.txt"));
		
		//Pull first number in the file.
		int numPersons = file.nextInt();
		
		//VisualDebug
		System.out.println("There are " + numPersons +" men and " + numPersons + " women.");
		
		//Initialize preference lists,  processed lists and given matching.
		int[][] mensPreference = new int[numPersons][numPersons];
		int[][] mensProcessedList = new int[numPersons][numPersons];
		int[][] womensPreference = new int[numPersons][numPersons];
		int[][] womensProcessedList = new int[numPersons][numPersons];//delete l8r
		int[] mMatch = new int[numPersons];
		int[] wMatch = new int[numPersons];
		
		//Populate the mens preference lists.
		System.out.println("Mens preferences");
		for(int i = 0; i < numPersons; i++) {
			for(int j = 0; j < numPersons; j++) {
				mensPreference[i][j] = file.nextInt();
				System.out.println(mensPreference[i][j]);
			}
		}
		
		//Populate the womens preference lists.
		System.out.println("Womens preferences");
		for(int i = 0; i < numPersons; i++) {
			for(int j = 0; j < numPersons; j++) {
				womensPreference[i][j] = file.nextInt();
				System.out.println(womensPreference[i][j]);
			}		
		}
		
		//Populate the given match array.
		System.out.println("Matching is");
		for(int i = 0; i < numPersons; i++) {
			mMatch[i] = file.nextInt();
			System.out.println(mMatch[i]);
		}
		
		//populate womans matching
		for(int i = 0; i < numPersons; i++)
			wMatch[mMatch[i] - 1] = i + 1;
		
		//print womens matching list
		System.out.println("Womens Matching");
		for(int i = 0; i < numPersons; i++)
			System.out.println(wMatch[i]);

		//Preprocess preference lists.
		System.out.println("Mens Processed List");
		for(int i = 0; i < numPersons; i++) {
			for(int j = 0; j < numPersons; j++) {
				mensProcessedList[i][mensPreference[i][j]-1] = j+1;
			}
		}
		for(int i = 0; i < numPersons; i++) {
			for(int j = 0; j < numPersons; j++) {
				System.out.println(mensProcessedList[i][j]);
			}
		}
		System.out.println("Womens Processed List");
		for(int i = 0; i < numPersons; i++) {
			for(int j = 0; j < numPersons; j++) {
				womensProcessedList[i][womensPreference[i][j]-1] = j+1;
				//System.out.println(womensProcessedList[i][womensPreference[i][j]-1]);
			}
		}
		//Print processed preference list
		for(int i = 0; i < numPersons; i++) {
			for(int j = 0; j < numPersons; j++) {
				System.out.println(womensProcessedList[i][j]);
			}
		}
		//Check for instability.
		for(int i = 0; i < numPersons; i++) {
			for(int j = 0; j < numPersons; j++) {
				if(mensPreference[i][j] == mMatch[i])
					break;
				else if(mensPreference[i][j] < mMatch[i]) {
					if(womensProcessedList[mensPreference[i][j] - 1][i] < womensProcessedList[mensPreference[i][j] - 1][wMatch[mensPreference[i][j]]])
						System.out.println("No" + '(' + (i + 1) + ',' + mensPreference[i][j] + ')');
			}else{
				System.out.println("Yes");
			}
		}
	}
}
}
