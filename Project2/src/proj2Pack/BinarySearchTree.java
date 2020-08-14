package proj2Pack;

/**
* A Binary Search Tree, holds an array of top and bottom ten countries, by per Capita GDP
* It also stores how many nodes it has checked during it's last Find call
* It has a start node used to accessing the tree.
*
* @author Hunter Blake
* @version 7/14/20
*/

import java.math;

public class BinarySearchTree {
	
	
	Node starterNode;
	static int nodesChecked;
	
	Node topTenCountries[] = new Node[10];
	Node bottomTenCountries[] = new Node[10];

	/**
	* Default Constructor
	*/
	
	public BinarySearchTree()
	{
		
	}
	
	
	
	/**
	* Description of the purpose of the method, the meaning of the
	* input parameters (if any) and the meaning of the return values * (if any).
	*
	* @param nameA First name to be checked if it should go before nameB
	* @param nameB Second name to be checked if it should go after nameA
	* @return True if name A would be before name B alphabetically.
	*/
	
	static public boolean nameBefore( String nameA, String nameB ) // Returns true if name A would be before Name B alphabetically.
	{
		boolean nameALonger = false;
		int minLength = nameA.length(); // In the eventuality one name is longer, only checks up to the length of the shorter name.
		// Also, if names are found to be the exact same their shared length, I believe the longer version of a name would be put after.
		
		if ( nameB.length() < minLength )
		{
			minLength = nameB.length();
			nameALonger = true;
		}
		
		int charACode, charBCode;
			
		for ( int ch = 0; ch < minLength; ch++ )
		{
			charACode = Character.toLowerCase(nameA.charAt(ch));
			charBCode = Character.toLowerCase(nameB.charAt(ch));
				
			if ( charACode < charBCode ) // Apologies, I hope this wasn't a too cringe worthy way of doing this.
			{
				return true;
			}
				
			else if ( charACode > charBCode )
			{
				return false;
			}
				
			else
			{
				continue;
			}
		}
		
		
		
		return !nameALonger;
	}
	
	
	
	private class Node 
	{
		String name;
		double gdpPerCapita;
		Node leftChild;
		Node rightChild;
		
		
		
		/**
		* Constructor of a Node class. 
		* 
		* @param name String of the country's name
		* @param gdpPerCapita perCapita of the country node.
		*/
		
		public Node(String name, double gdpPerCapita) 
		{
			 this.name = name;
			 this.gdpPerCapita = gdpPerCapita;
		}
		
		/**
		* Prints the node, starting with name then per capita GDP
		*/
		
		public void printNode()
		{
			System.out.printf("%-25s%,-20.2f\n", name, gdpPerCapita);
		}
		
	}
	
	/**
	* Iterates through the binary tree, going for the left or right child if before or after alphabetically
	* the node we're checking until it comes upon an empty space. Then, it places it in the empty space.
	*
	* @param name Country name for the country we're inserting.
	* @param gdpPerCapita GDP per capita of the country to insert
	*/

	public void insert(String name, double gdpPerCapita)
	{
		
		if ( starterNode == null )
		{
			starterNode = new Node(name, gdpPerCapita);
			return;
		}
		
		Node currentNode = starterNode;
		
		// Most likely more cost effective having a local variable remember
		// Than calling the function twice. Apologies if I made that error
		// in project 1.
		boolean before; 
		
		while ( currentNode != null )
		{
			
			before = nameBefore( name, currentNode.name );
			
			if ( before )
			{
				if ( currentNode.leftChild == null )
				{
					currentNode.leftChild = new Node(name, gdpPerCapita);
					return;
				}
				
				currentNode = currentNode.leftChild;
				continue;
			}
			else
			{
				if ( currentNode.rightChild == null )
				{
					currentNode.rightChild = new Node(name, gdpPerCapita);
					return;
				}
				
				currentNode = currentNode.rightChild;
				continue;
			}
		}
	}
	
	/**
	* Returns whether or not a child has the same name for the first parameter, with the second parameter
	* being the parent we're checking.
	*
	* @param name Name of the country we're checking is a child.
	* @param forNode Node we're checking if a country is a child of.
	* @return True if the second parameter has a child left or right with the name parameter as it's name.
	*/
	
	public boolean isChildNode( String name, Node forNode )
	{
		
		if ( forNode.leftChild != null )
		{
			if ( forNode.leftChild.name.compareTo(name) == 0 )
			{
				return true;
			}
		}
		
		if ( forNode.rightChild != null )
		{
			if ( forNode.rightChild.name.compareTo(name) == 0 )
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	* Searches for a node and returns it if is found, starting from the starterNode
	* if second parameter is null, or the node if it isn't. 
	* I hope this won't violate the rules of this assignment, I just figured it was a
	* little optimization. 
	* 
	* @param name The name of that which is sought.
	* @param startNode Node we begin the search from. 
	* @return The found node, or null if it isn't found.
	*/
	
	public Node seek( String name, Node startNode )
	{
		
		nodesChecked = 0;
		
		boolean notDone = true;
		
		Node curNode = starterNode;
		
		if ( startNode != null )
		{
			curNode = startNode;
		}
		
		while ( notDone )
		{
			if ( curNode.name.compareTo(name) != 0 )
			{
				if ( nameBefore( name, curNode.name ) )
				{
					if ( curNode.leftChild == null )
					{
						return null;
					}
					else
					{
						curNode = curNode.leftChild;
						nodesChecked++;
						continue;
					}
				}
				else
				{
					if ( curNode.rightChild == null )
					{
						return null;
					}
					else
					{
						curNode = curNode.rightChild;
						nodesChecked++;
						continue;
					}
				}
			}
			else
			{
				return curNode;
			}
		}
		
		return null;
	}
	
	/**
	* Searches for a node's parent and returns it if is found, starting from the starterNode
	* 
	* @param name The name of that which is sought.
	* @return The found node, or null if it isn't found.
	*/
	
	public Node seekParent( String name )
	{
		boolean notDone = true;
		Node curNode = starterNode;
		
		while ( notDone )
		{
			if ( !isChildNode( name, curNode ) )
			{
				if ( nameBefore( name, curNode.name ) )
				{
					if ( curNode.leftChild == null )
					{
						return null;
					}
					else
					{
						curNode = curNode.leftChild;
						continue;
					}
				}
				else
				{
					if ( curNode.rightChild == null )
					{
						return null;
					}
					else
					{
						curNode = curNode.rightChild;
						continue;
					}
				}
			}
			else
			{
				return curNode;
			}
		}
		
		return null;
	}
	
	/**
	* If a node has no children, which is to be proven when this function is called
	* or else it will return the left child
	* Otherwise it checks which child is valid and returns that.
	* 
	* @param forNode The node we are checking for the child of.
	* @return The found node
	*/
	
	public Node nodeOnlyChild( Node forNode )
	{
		if ( forNode.leftChild != null )
		{
			return forNode.leftChild;
		}
		else
		{
			return forNode.rightChild;
		}
	}
	
	/**
	* Determines how many children a node has based on if left and right child are not null.
	* 
	* @param forNode The node we are checking the children amount of.
	* @return The children amount.
	*/
	
	public int nodeChildrenAmt( Node forNode )
	{
		int childAmt = 0;
		
		if ( forNode.leftChild != null  )
		{
			childAmt++;
		}
		if ( forNode.rightChild != null )
		{
			childAmt++;
		}
		
		return childAmt;
	}
	
	/**
	* The per capita of a country node, by it's name.
	* 
	* @param name The country we search for by name.
	* @return The perCapitaGDP of the found node.
	*/
	
	public double find (String name)
	{
		Node found;
		
		found = seek ( name, null );
		
		if ( found != null )
		{
			return found.gdpPerCapita;
		}
		
		return -1;
	}
	
	/**
	 * This deletes a node, searching for it by it's name.
	 * If it is without any children, the node is simply removed from it's parent references.
	 * If it has only one, the parent's reference to it is removed with the to be deleted's child.
	 * If it has more than two, it finds the least further in alphabetical format and replaces it with that.
	 * It first however deletes the node it's using as a replacement, saving the values to temporary memory before replacing.
	 * 
	 */
	
	public void delete(String name)
	{
		Node deletingParent = seekParent(name);
		Node toDelete = null;
		
		boolean leftChild = false;
		
		if ( deletingParent.leftChild != null )
		{
			leftChild = ( deletingParent.leftChild.name.compareTo(name) == 0 );
		}
		
		
		String replacementName;
		double replacementCapitaGDP;

		
		if ( leftChild ) // if we're removing the left child of deletingParent
		{
			toDelete = deletingParent.leftChild;
			
			switch ( nodeChildrenAmt( toDelete ) )
			{
				case 0:
					deletingParent.leftChild = null;
					break;
				
				case 1:
					deletingParent.leftChild = nodeOnlyChild( toDelete );
					break;
					
				case 2: // Work on saturday
					Node replacement = toDelete.rightChild;

					if ( toDelete.rightChild.leftChild == null )
					{
						replacementName = toDelete.rightChild.name;
						replacementCapitaGDP = toDelete.rightChild.gdpPerCapita;
						delete( replacementName );
						toDelete.name = replacementName;
						toDelete.gdpPerCapita = replacementCapitaGDP;
						return;
					}
					
					while ( replacement.leftChild != null )
					{
						replacement = replacement.leftChild;
					}
					
					replacementName = replacement.name;
					replacementCapitaGDP = replacement.gdpPerCapita;
					delete(replacement.name);
					toDelete.name = replacementName;
					toDelete.gdpPerCapita = replacementCapitaGDP;
					break;
			}
		}
		else
		{
			toDelete = deletingParent.rightChild;
			
			switch ( nodeChildrenAmt( toDelete ) )
			{
				case 0:
					deletingParent.rightChild = null;
					break;
					
				case 1:
					deletingParent.rightChild = nodeOnlyChild( toDelete ); 
					break;
					
				case 2: // Work on this Saturday
					Node replacement = toDelete.rightChild;

					if ( toDelete.rightChild.leftChild == null )
					{
						replacementName = toDelete.rightChild.name;
						replacementCapitaGDP = toDelete.rightChild.gdpPerCapita;
						delete( replacementName );
						toDelete.name = replacementName;
						toDelete.gdpPerCapita = replacementCapitaGDP;
						return;
					}
					
					while ( replacement.leftChild != null )
					{
						replacement = replacement.leftChild;
					}
					
					replacementName = replacement.name;
					replacementCapitaGDP = replacement.gdpPerCapita;
					delete(replacement.name);
					toDelete.name = replacementName;
					toDelete.gdpPerCapita = replacementCapitaGDP;
					break;
			}
		}
		
	}
	
	/**
	 * A recursive function for printing inOrder
	 * 
	 * @param asNode the node we're doing for this step.
	 */
	
	public void doInorderNode( Node asNode )
	{
		if ( asNode != null )
		{
			doInorderNode( asNode.leftChild );
			asNode.printNode();
			doInorderNode( asNode.rightChild );
		}
		
	}
	
	/**
	 * A recursive function for printing preOrder
	 * 
	 * @param asNode the node we're doing for this step.
	 */
	
	public void doPreorderNode( Node asNode )
	{
		if ( asNode != null )
		{
			asNode.printNode();
			doPreorderNode( asNode.leftChild );
			doPreorderNode( asNode.rightChild );
		}
	}
	
	/**
	 * A recursive function for printing postOrder
	 * 
	 * @param asNode the node we're doing for this step.
	 */
	
	public int dailyCases(int num1, float R0, int i)
	{
		
		float baseNum = 1+R0;
		
		int powTimes = i-1;
		
		float curTotal = baseNum;
		
		while ( powTimes > 1 )
		{
			curTotal *= baseNum;
			powTimes--;
		}
		
		if ( i == 1 )
		{
			return (int) curTotal * num1;
		}

		return dailyCases(num1, R0, i-1) + (int) curTotal;
		
	}
	
	public void doPostorderNode( Node asNode )
	{
		if ( asNode != null )
		{
			doPreorderNode( asNode.leftChild );
			doPreorderNode( asNode.rightChild );
			asNode.printNode();
		}
	}
	
	/**
	 * A function that commences the recursion for printing the whole tree inOrder
	 * 
	 */
	
	public void printInorder()
	{
		System.out.println("Inorder Traversal:");
		System.out.println("");
		
		System.out.println("Name	GDP Per Capita\r\n" + 
				"-----------------------------------------");
		
		doInorderNode( starterNode );
	}
	
	/**
	 * A function that commences the recursion for printing the whole tree preOrder
	 * 
	 */
	
	public void printPreorder()
	{
		System.out.println("Preorder Traversal");
		
		System.out.println("");
		
		System.out.println("Name	GDP Per Capita\r\n" + 
				"-----------------------------------------");
		
		doPreorderNode( starterNode );
	}
	
	/**
	 * A function that commences the recursion for printing the whole tree postOrder
	 * 
	 */
	
	public void printPostorder()
	{
		System.out.println("Postorder Traversal");
		
		System.out.println("");
		
		System.out.println("Name	GDP Per Capita\r\n" + 
				"-----------------------------------------");
		
		doPostorderNode( starterNode );
	}
	
	boolean doingLow = false;
	
	/**
	 * Recursively iterates the tree in fashion a la inOrder
	 * 
	 * @param asNode The node we are working on recursively.
	 */
	
	public void loadNode( Node asNode )
	{
		if ( asNode == null )
		{
			return;
		}
		
		loadNode ( asNode.leftChild );
		
		if ( doingLow )
		{
			processNodeForLow( asNode );
		}
		
		if ( !doingLow )
		{
			processNodeForHigh( asNode );
		}
		
		loadNode ( asNode.rightChild );
		
	}
	
	/**
	 * Works with the above function when we are trying to assemble the table of top ten highest GDPs
	 * It inserts it into the list based on where it would best fit, and if the list is already full
	 * removes the highest GDP country.
	 * 
	 * @param asNode The node we are checking if it belongs in our list.
	 * 
	 */
	
	public void processNodeForHigh( Node asNode )
	{
		for ( int n = 0; n < 10; n++ )
		{
			if ( topTenCountries[n] == null )
			{
				topTenCountries[n] = asNode;
				return;
			}
			
			if ( asNode.gdpPerCapita > topTenCountries[n].gdpPerCapita )
			{
				for ( int g = 9; g > n; g-- )
				{
					topTenCountries[g] = topTenCountries[g-1];
				}
				
				topTenCountries[n] = asNode;
				break;
			}
		}
	}
	
	/**
	 * Works with the above function when we are trying to assemble the table of bottom ten GDP countries
	 * It inserts it into the list based on where it would best fit, and if the list is already full
	 * removes the lowest GDP country.
	 * 
	 * @param asNode The node we are checking if it belongs in our list.
	 * 
	 */
	
	public void processNodeForLow( Node asNode )
	{
		for ( int n = 0; n < 10; n++ )
		{
			if ( bottomTenCountries[n] == null )
			{
				bottomTenCountries[n] = asNode;
				return;
			}
			
			if ( asNode.gdpPerCapita < bottomTenCountries[n].gdpPerCapita )
			{
				for ( int g = 9; g > n; g-- )
				{
					bottomTenCountries[g] = bottomTenCountries[g-1];
				}
				
				bottomTenCountries[n] = asNode;
				break;
			}
		}
	}
	
	/**
	 * Compiles the list of bottom ten GDP per capita countries, then outputs it to the screen.
	 */
	
	public void printBottomTen()
	{
		doingLow = true;
		
		for ( int i = 0; i < 10; i++ )
		{
			bottomTenCountries[i] = null;
		}
		
		loadNode ( starterNode );
		
		System.out.println("Bottom ten countries regarding GDP Per Capita");
		System.out.println("");
		
		System.out.println("Name	GDP Per Capita\r\n" + 
				"-----------------------------------------");
		
		for ( int i = 0; i < 10; i++ )
		{
			bottomTenCountries[i].printNode();
		}
	}
	
	/**
	 * Compiles the list of top ten GDP per capita countries. Then it outputs it.
	 */
	
	public void printTopTen()
	{
		doingLow = false;
		
		for ( int i = 0; i < 10; i++ )
		{
			topTenCountries[i] = null;
		}
		
		loadNode ( starterNode );
		
		System.out.println("Top ten countries regarding GDP Per Capita");
		System.out.println("");
		
		System.out.println("Name	GDP Per Capita\r\n" + 
				"-----------------------------------------");
		
		
		for ( int i = 0; i < 10; i++ )
		{
			topTenCountries[i].printNode();
		}
	}
	
	
}
