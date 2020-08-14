package assignment6pack;

import java.util.*;

/*
 * The base class from which the different types of employees derive. Has salary and birth month
 * Birth month is an integer, and base salary is a float
 * Makes sure the salary is 0 or higher, and birth month is between 1 and 12, inclusive
 * 
 * @author	Hunter Blake
 * @since 1.0
 */

public abstract class ZEmployee {

	private int birthMonth;
	private float baseSalary;
	
	/*
	 * Gets the base salary, as a float, directly
	 */
	
	public float ZGetBaseSalary()
	{
		return baseSalary;
	}
	
	/*
	 * Sets the base salary, if not zero
	 * @param asSalary	What we set the salary as, for a float. Preferably above 0
	 */
	
	public void ZSetBaseSalary( float asSalary )
	{
		if ( asSalary < 0 )
		{
			return;
		}
		
		baseSalary = asSalary;
	}
	
	/*
	 * Type of Full Time Professor employee
	 */
	
	public enum ZFullTimeProfType
	{
		Full_Prof,
		Assoc_Prof,
		Assi_Prof
	}
	
	/*
	 * Type of Admin employee
	 */
	
	public enum ZAdminEmployType
	{
		Secretary,
		Technician
	}
	
	/*
	 * Sets the birth month, between 1 and 12
	 * @param asMonth	The month of 1-12
	 */
	
	public void ZSetBirthMonth( int asMonth )
	{
		if ( asMonth > 12 || asMonth < 1 )
		{
			birthMonth = 1;
			return;
		}
		
		birthMonth = asMonth;
	}
	
	/*
	 * Returns the value of Birth Month
	 */
	
	public int ZGetBirthMonth()
	{
		return birthMonth;
	}
	
	/*
	 * Gets how much payroll is
	 * If the month is same as their birthday, gives them 100$
	 * @param currentMonth	The month it is when they are receiving payroll.
	 */
	
	public float ZGetPayroll( int currentMonth )
	{
		if ( currentMonth == ZGetBirthMonth() )
		{
			return 100.0f;
		}
		
		return 0f;
	}
	
}
