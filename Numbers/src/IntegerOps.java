import java.util.Scanner;

public class IntegerOps 
{

	public static void main(String[] args)
	{
	
		int num1 = 0, num2 = 0, num3 = 0;
		
		int sum;
		int product;
		int largest, smallest;
		float average;
		int median;
		
		
	
	 
	
		// Use Scanner class to read the three integer
	
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter 3 numbers");
		num1 = reader.nextInt();
		num2 = reader.nextInt();
		num3 = reader.nextInt();
	
		sum = getSum(num1, num2, num3); //The sum method calculates the sum
	
		// Call product method to calculate the product
	
		product = getProduct( num1, num2, num3 );
	
		// Call method to calculate the average (real number)
	
		average = averageOfNum( sum );
	
		// Call method to calculate the smallest number
		
		smallest = smallestNum( num1, num2, num3 );
		
		// Call method to calculate the largest number
		
		largest = biggestNum( num1, num2, num3 );
		
		// Call product method to calculate the median of the 3 numbers
	
		median = getMedian( num1, num2, num3, smallest, largest );
	
		System.out.println("Sum = " + sum);
		System.out.println("Product = " + product);
		System.out.println("Largest = " + largest);
		System.out.println("Smallest = " + smallest);
		System.out.println("Average = " + average);
		System.out.println("Median = " + median);
	
		// Print the product, average, smallest, largest, and median in separate lines
	
	}
	
	public static int getMedian( int a, int b, int c, int low, int high)
	{
		if ( a == b || a == c ) // Checks for duplicate numbers.
		{
			return a;
		}
		if ( b == c )
		{
			return b;
		}
		
		if ( a != low && a != high )
		{
			return a;
		}
		else if ( b != low && b != high )
		{
			return b;
		}
		else
		{
			return c;
		}
		
	}
	
	/*
	 * Takes a sum, and outputs the average of it by a second number
	 * 
	 * 
	 */
	
	public static float averageOfNum( int a )
	{
		return a / 3.0f;
	}
	
	/*
	 * Returns the smallest of 3 numbers.
	 * 
	 */
	
	public static int smallestNum( int a, int b, int c)
	{
		int little = a;
		
		if ( b < little )
		{
			little = b;
		}
		if ( c < little )
		{
			little = c;
		}
		
		return little;
		
	}
	
	/*
	 * This gets the biggest of 3 numbers.
	 */
	
	public static int biggestNum( int a, int b, int c)
	{
		int bigger = a;
		
		if ( b > bigger )
		{
			bigger = b;
		}
		if ( c > bigger )
		{
			bigger = c;
		}
		
		return bigger;
		
	}
	
	/*
	 * Gets the product of 3 numbers.
	 */
	
	public static int getProduct( int a, int b, int c)
	{
		
		return ( a * b * c );
	}
	
	/**
	
	* This method calculates the sum of 3 integers
	
	* @param x the first integer
	
	* @param y the second integer
	
	* @param z the third integer
	
	* @return the sum of the 3 integers
	
	*/
	
	public static int getSum(int x, int y, int z)
	{
	
		return x + y + z;
	
	}

 

}