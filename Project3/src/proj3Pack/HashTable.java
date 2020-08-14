package proj3Pack;

/**
 * Stores an array of the HashLinkedList class, with the array having a length of 311, due to the nature of the hash
 * function, which acts upon the country's name.
 * 
 * Has contained classes for the linked list and node per each country.
 * <p>
 * 
 * @author Hunter Blake
 * 
 *
 */

public class HashTable {

	HashLinkedList[] hashList = new HashLinkedList[311];
	
	/**
	 * A node is used to represent a country, it has a value for the country's name as well
	 * as it's GDP per capita. It has a specialized function for outputting a node.
	 * 
	 * @author Hunter Blake
	 * @version 7/21/20
	 *
	 */
	
	private class Node {
		String name;
		double gdpPerCapita;
		Node nextNode;
		
		/**
		 * This is the standard constructor for a Node, and creates one using a value for it's name
		 * and per capita GDP.
		 * 
		 * @param name The name of the country the node is being created for.
		 * @param gdpPerCapita The per capita GDP of the country's node we're creating.
		 */
		
		public Node(String name, double gdpPerCapita) 
		{
			this.name = name;
			this.gdpPerCapita = gdpPerCapita;
		}
		
		/**
		 * Outputs the data of the node.
		 */
		
		public void printNode() 
		{
		 System.out.printf("%-25s%,-20.2f\n", name, gdpPerCapita);
		}
	}
	
	/**
	 * 
	 * This is my custom linked list class, to work in tandem with the table you specified
	 * Holds what node is the start and end of the list, which is the same if the linked list
	 * only holds the one country.
	 * 
	 * @author Hunter Blake
	 */
	
	private class HashLinkedList{
		Node Start, End;
		
		/**
		 * Empty constructor.
		 */
		
		public HashLinkedList()
		{
			
		}
		
		/**
		 * A constructor, which takes in one node as an argument, and makes it both the start and end
		 * which is used for an index if a linked list doesn't already exist there.
		 * 
		 * @param asStartNode The node that will make the linked list.
		 */
		
		public HashLinkedList(Node asStartNode)
		{
			Start = asStartNode;
			End = asStartNode;
		}
		
	}
	
	/**
	 * Taking in a String, this adds each letter to an integer and when it's added all characters
	 * within the string, it gets the modulus of that number by 311 and returns that as the
	 * string's hash.
	 * 
	 * @param forString The string we're trying to get the hash of.
	 * @return The integer the string comes out when hashed.
	 */
	
	static public int stringHash( String forString )
	{
		int asHash = 0;
		
		for ( int i = 0; i < forString.length(); i++ )
		{
			asHash += forString.charAt(i);
		}
		
		return ( asHash % 311 );
		
	}
	
	/**
	 * Taking a name of a country and it's gdpPerCapita, it creates a new node
	 * representing that country, after it gets the hash of said country to
	 * determine what index it belongs to within the hash table.
	 * 
	 * <p>
	 * Then when it finds the index it belongs, checks if there is a hashLinkedList reference at that index
	 * If not, it creates a new hashLinkedList at that index using the created node. If there is a inkedList there,
	 * it appends it to the end of the list. It assign's the current end's nextNode to the new node, and assigns the
	 * end to now be the newly inserted node.
	 * 
	 * @param country The name for the country node we're inserting.
	 * @param gdpPerCapita The GDP per capita of the country node being inserted.
	 */
	
	public void insert( String country, double gdpPerCapita )
	{
		int asInd = stringHash ( country );
		Node forLList = new Node( country, gdpPerCapita );
		
		if ( hashList[asInd] == null )
		{
			hashList[asInd] = new HashLinkedList( forLList );
			return;
		}
		else
		{
			hashList[asInd].End.nextNode = forLList;
			hashList[asInd].End = forLList;
			return;
		}
		
		
	}
	
	/**
	 * This gets the hash of a country's name, checks the hashTable's array at index of that hash
	 * If no country of that name is found, or there is no hashLinkedList -1 is returned.
	 * 
	 * @param country The title of the country we're trying to find the per capita GDP of.
	 * @return The double value for the country's per capita GDP if found, which is -1 if not found.
	 */
	
	public double find( String country )
	{
		int asInd = stringHash ( country );
		
		if ( hashList[asInd] == null )
		{
			return -1;
		}
		
		Node curNode = hashList[asInd].Start;
		
		while ( curNode.name.compareTo( country ) != 0 && curNode.nextNode != null )
		{
			curNode = curNode.nextNode;
		}
		
		if ( curNode.name.compareTo( country ) == 0 )
		{
			return curNode.gdpPerCapita;
		}
		
		return -1;
		
	}
	
	/**
	 * 
	 * Using a name of a country, it gets the hash of the country, then it gets the index
	 * by the hash and if it finds a linkedList that has it, it will delete it.
	 * 
	 * <p>
	 * 
	 * If the hashLinkedList is only that country, it change that index to point to a null reference
	 * If it isn't the only country, it removes the country. If it's found to be the start, it changes
	 * the start to be the start country's next node reference. Any other time, and it will set it's
	 * predecessor node to point to the to be deleted's next node. The exception to this if the next
	 * node for to be deleted is found to be null, which is presumably when it's the end and in said case
	 * end is made to be the deleted's predecessor.
	 * 
	 * @param country The name of the country we want deleted.
	 */
	
	public void delete( String country )
	{
		int atInd = stringHash( country );
		
		System.out.println(country + " has been deleted from hash table");
		
		boolean beginning = false, end = false;
		
		if ( hashList[atInd].Start.name.compareTo(country) == 0 )
		{
			beginning = true;
		}
		
		if ( hashList[atInd].End.name.compareTo( country ) == 0 )
		{
			end = true;
		}
		
		if ( beginning && end )
		{
			hashList[atInd] = null; // If this is all the list is, just dump the list.
			return;
		}
		
		if ( beginning )
		{
			hashList[atInd].Start = hashList[atInd].Start.nextNode; // Easy. Just had to watch out for it. Set start to successor then.
			return;
		}
		
		Node curNode = hashList[atInd].Start;
		
		while ( curNode.nextNode.name.compareTo( country ) != 0 )
		{
			curNode = curNode.nextNode;
		}
		
		if ( curNode.nextNode.nextNode != null )
		{
			curNode.nextNode = curNode.nextNode.nextNode;
		}
		
		if ( curNode.nextNode.nextNode == null )
		{
			if ( curNode.nextNode == hashList[atInd].End )
			{
				hashList[atInd].End = curNode;
			}
			
			curNode.nextNode = null;
		}

		
		
	}
	
	/**
	 * This outputs the contents of the hashTable's hashList content,
	 * it prints empty if there is no linked List, and for each node in the list
	 * it calls printNode on the node in the list.
	 */
	
	public void display()
	{
		System.out.println("Hash table content: ");
		System.out.println("");
		
		for ( int i = 0; i < 311; i++ )
		{
			if ( hashList[i] == null )
			{
				System.out.println( i + ".	Empty");
			}
			else
			{
				System.out.printf( i + ".\t" );
				hashList[i].Start.printNode();
				Node currentNode = hashList[i].Start.nextNode;
				while ( currentNode != null )
				{
					System.out.printf("\t");
					currentNode.printNode();
					currentNode = currentNode.nextNode;
				}
			}
		}
	}
	
	/**
	 * This iterates the array of hashList, and foreach index
	 * found to be to a null reference, tallies that as being an empty instance
	 * For each that has a reference to a hashLinkedList tallies that as being a collision
	 * Then when it's done it writes the count of collided and empty.
	 */
	
	public void printFreeAndCollisions()
	{
		int empty = 0, used = 0;
		
		for ( int i = 0; i < 311; i++ )
		{
			if ( hashList[i] != null )
			{
				used++;
				continue;
			}
			else
			{
				empty++;
				continue;
			}
		}
		
		System.out.println("There are " + empty + " spaces available and " + used + " collisions in the table.");
		
	}
	
}
