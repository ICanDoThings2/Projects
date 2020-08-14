package proj2Pack;

import java.util.*;
import java.io.*;

/**
* COP 3530: Project 2 – Binary Search Trees
* <p>
* Opens a country named Countries2.csv
* Formats them into a country per line, and generates a binary tree
* Each node is a country name and it's GDP.
* 
* <p>
* First it reads the countries
* Then it prints the tree it makes with the countries in order
* Deletes three countries, Australia, Czech Republic, and Norway
* Then it prints Preorder, finds Sri Lanka, North Cyprus, Czech Republic, and Norway
* Then it outputs their per capita GDP or -1 if they aren't found and how many nodes were visited to find them.
* 
* Lastly it deletes Malawi, Somalia, Canada, Tunisia, and New Zealand
* Then it prints the top and bottom ten countries in terms of GDP
*
* @author Hunter Blake
* @version 7/14/20
*/

public class Project2 {

	static BinarySearchTree countryTree = new BinarySearchTree();
	
	static File cntryFile = new File("Countries2.csv"); 
	
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
			
			countryTree.insert( countryName, capitaGDP );
		}
		
	}
	

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			fReader = new Scanner(cntryFile);
			readCountries();
			countryTree.printInorder();

			System.out.println("");
			
			countryTree.delete("Australia");
			System.out.println("Australia has been deleted from the tree");
			countryTree.delete("Czech Republic");
			System.out.println("Czech Republic has been deleted from the tree");
			countryTree.delete("Norway");
			System.out.println("Norway has been deleted from the tree");
			
			countryTree.printPreorder();
			
			String toFind[] = new String[] {"Sri Lanka","North Cyprus", "Czech Republic", "Norway"};
			double lastGDP = 0;
			
			for ( String S : toFind )
			{
				lastGDP = countryTree.find(S);
				
				if ( lastGDP == -1 )
				{
					System.out.println(S + " is not found");
					System.out.println(countryTree.nodesChecked + " nodes visited");
				}
				
				else
				{
					System.out.println(S + " is found with GDP per capita " + lastGDP);
					System.out.println(countryTree.nodesChecked + " nodes visited");
				}
				System.out.println("");
			}
			
			System.out.println("");
			
			
			countryTree.delete("Malawi");
			System.out.println( "Malawi has been deleted from the tree");
			countryTree.delete("Somalia");
			System.out.println( "Somalia has been deleted from the tree");
			countryTree.delete("Canada");
			System.out.println( "Canada has been deleted from the tree");
			countryTree.delete("Tunisia");
			System.out.println( "Tunisia has been deleted from the tree");
			countryTree.delete("New Zealand");
			System.out.println(" New Zealand has been deleted from the tree");
			
			countryTree.printPostorder();
			
			System.out.println();
			countryTree.printBottomTen();
	
			System.out.println();
			countryTree.printTopTen();
			
		}
		
		catch ( FileNotFoundException FileErr )
		{
			System.out.println("File not found!");
		}
	}

}
