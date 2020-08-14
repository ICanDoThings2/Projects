package proj3Pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
* COP 3530: Project 3 – Hash Tables
* <p>
* Taking a file named Countries3.csv, hashes each of their name and puts them into the countryHashTable
* based on the hash of their names as indexes. It first reads said file, then it displays it.
* Next it deletes Cyprus, Kazakhstan, Hungary, Japan.
* 
* Then it finds the per capita GDP of Costa Rica, North Cyprus, and Hungary then prints them or -1 if not found.
* Lastly, it deletes Zambia, Canada, Egypt, Yemen, India, and Singapore. To finish it then displays the table
* and prints the number of unused indexes and the amount of indexes with hashLinkedLists.
*
* @author Hunter Blake
* @version 7/21/20
*/

public class Project3 {
	
	static HashTable countryHashTable = new HashTable();
	
	static File cntryFile = new File("Countries3.csv"); 
	
	static Scanner fReader;
	
	
	
	public static void readCountries()
	{
		fReader.nextLine();
		String[] splitString;
		String countryName; 
		float capitaGDP = 0;
		
		while ( fReader.hasNextLine() )
		{
			splitString = fReader.nextLine().split(",");
			countryName = splitString[0];
			capitaGDP = Float.parseFloat( splitString[4] ) / Integer.parseInt( splitString[3] );
			
			countryHashTable.insert( countryName, capitaGDP);
		}
		
	}

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		try
		{
			fReader = new Scanner(cntryFile);
			readCountries();
		}
		catch ( FileNotFoundException FileErr ) 
		{
			System.out.println("File not found!");
		}
		
		countryHashTable.display();
		countryHashTable.delete("Cyprus");
		countryHashTable.delete("Kazakhstan");
		countryHashTable.delete("Hungary");
		countryHashTable.delete("Japan");
		
		String[] toFind = new String[] {"Costa Rica","North Cyprus","Hungary"};
		
		for ( String s : toFind )
		{
			double foundGDP = countryHashTable.find(s);
			
			if ( foundGDP != -1 )
			{
				System.out.println(s + " is found with a GDP per capita of " + foundGDP );
			}
			else
			{
				System.out.println(s + " is not found");
			}
		}
		
		countryHashTable.delete("Zambia");
		countryHashTable.delete("Canada");
		countryHashTable.delete("Egypt");
		countryHashTable.delete("Yemen");
		countryHashTable.delete("India");
		countryHashTable.delete("Singapore");
		
		countryHashTable.display();
		
		countryHashTable.printFreeAndCollisions();
		
	}

}
