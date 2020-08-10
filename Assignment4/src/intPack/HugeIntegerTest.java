package intPack;
import java.util.*;

public class HugeIntegerTest {

	
	private static HugeInteger hIntA, hIntB;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		hIntA = new HugeInteger();
		hIntB = new HugeInteger();
		
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Enter your first digit string");
		
		hIntA.parse( reader.next() );
		
		System.out.println("Enter your second digit string");
		
		hIntB.parse( reader.next() );
		
		System.out.println( hIntA.toString() );
		System.out.println( hIntB.toString() );
		
		System.out.println( hIntA.isGreaterThan( hIntB.toString() ) );
		
		hIntA.subtract( hIntB.toString() );
		System.out.println( hIntA.toString() );
		
		hIntA.add( hIntB.toString() );
		hIntA.add( hIntB.toString() );
		System.out.println( hIntA.toString() );
	}

}
