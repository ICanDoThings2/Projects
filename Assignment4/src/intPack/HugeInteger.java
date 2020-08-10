package intPack;

import java.util.*;



public class HugeInteger {
	
	private int intList[] = new int[40];
	
	public HugeInteger()
	{
		
	}
	
	public int getIntList( int indexOf )
	{
		return intList[indexOf];
	}
	
	public void setIntList( int indexOf, int asInt )
	{
		intList[indexOf] = asInt;
	}
	
	public void parse(String asInput)
	{
		
		int numLength = 40;
		
		if ( asInput.length() < 40 )
		{
			numLength = asInput.length();
		}
		
		for ( int t = 0; numLength < 39 && t < 40; t++ ) // I hope me using an additional boolean isn't too against standard practice.
		{
			intList[t] = 0;
			
			if ( t >= 40 - numLength )
			{
				setIntList( t,  Character.getNumericValue( asInput.charAt( t - ( 40 - numLength) ) ) );
			}
			
			
			
		}
		
		for ( int t = 0; numLength >= 40 && t < 40; t++ ) // I hope me using an additional boolean isn't too against standard practice.
		{
			setIntList( t, Character.getNumericValue( asInput.charAt( t  ) ) );
		}
	}
	
	public int nextNonZero( boolean forward, int startInd ) // For finding the next non zero, for adding and subtracting purposes when it goes over 9.
	{
		for ( int l = startInd + 1; l < 40 && forward; l++ )
		{
			if ( getIntList(l) > 0 )
			{
				return l;
			}
		}
		
		for ( int l = startInd - 1; l > -1 && !forward; l-- )
		{
			if ( getIntList(l) > 0 )
			{
				return l;
			}
		}
		
		return -1;
	}
	
	
	public String toString()
	{
		String returning = new String();
		
		for ( int t = 0; t < 40; t++ )
		{
			returning += intList[t];
		}
		
		return returning;
	}
	
	public void add( String adding )
	{
		
		for ( int e=0; e < 40; e++ )
		{
			int thisAdd = Character.getNumericValue( adding.charAt(e) );
			
			if ( getIntList(e) + thisAdd >= 10 )
			{
				
				setIntList(e, ( getIntList(e) + thisAdd ) - 10  );
				
				int tInd = e - 1;
				
				boolean overflowing = true;
				
				while ( overflowing && tInd > -1 )
				{
					if ( getIntList(tInd) + 1 < 10 )
					{
						setIntList(tInd, getIntList(tInd) + 1 );
						overflowing = false;
					}
					else
					{
						setIntList(tInd, (getIntList(tInd) + 1 - 10) );
						tInd -= 1;
					}
				}
			}
			else
			{
				setIntList(e, thisAdd + getIntList(e) );
			}
		}
		
	}
	
	public void subtract( String taking )
	{
		if ( isLessThan(taking) ) // I hope I understood what you meant by discounting correctly. If not, please tell me.
		{
			return;
		}
		
		if ( isEqualTo( taking ) ) // If they're equal it just comes out to 0.
		{
			for ( int t=0; t < 40; t++ )
			{
				setIntList(t, 0);
			}
			
			return;
		}
		
		for ( int e=0; e < 40; e++ )
		{
			int subtracting = Character.getNumericValue( taking.charAt(e) );
			
			if ( getIntList(e) >= subtracting  )
			{
				setIntList(e, getIntList(e) - subtracting );
			}
			else
			{
				int g = nextNonZero( false, e );
				setIntList(g, getIntList(g) - 1 );
				
				setIntList(e, ( getIntList(e) + 10 ) - subtracting );
				
				for ( int p = g + 1; p < e; g++ )
				{
					setIntList(p, getIntList(p) + 9 );
				}
				
				
			}
		}
	}
	
	public boolean isEqualTo( String comparing )
	{
		return toString().equals( comparing );
	}
	
	public boolean isNotEqualTo( String comparing )
	{
		return !isEqualTo( comparing ); // I hope you won't be offended by this, but I didn't understand why you wanted to write a whole new function when "!" works.
	}
	
	public boolean isGreaterThanOrEqualTo(String compare )
	{
		return isGreaterThan( compare ) || isEqualTo( compare );
	}
	
	public boolean isGreaterThan( String compare )
	{
		for ( int let = 0; let < 40; let++ )
		{
			if ( intList[let] > Character.getNumericValue(compare.charAt(let)) )
			{
				return true;
			}
			if ( intList[let] < Character.getNumericValue(compare.charAt(let)) )
			{
				return false;
			}
		}
		
		return false;
	}
	
	public boolean isLessThan( String compare )
	{
		for ( int let = 0; let < 40; let++ )
		{
			if ( intList[let] < Character.getNumericValue(compare.charAt(let)) )
			{
				return true;
			}
			if ( intList[let] > Character.getNumericValue(compare.charAt(let)) )
			{
				return false;
			}
		}
		
		return false;
	}
	
	public boolean isLessThanOrEqualTo( String compare )
	{
		return isLessThan( compare ) || isEqualTo( compare );
	}
	
	public boolean isZero()
	{
		for ( int i = 0; i < 40; i++ )
		{
			if ( intList[i] != 0 )
			{
				return false;
			}
		}
		
		return true;
	}
	
	

}


