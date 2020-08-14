package assignment6pack;

import java.util.*;

/*
 * Runs the program, holds the list of employees, gets input, etc
 * @author Hunter Blake
 * @version	1.0
 */

public class ZPayrollCalc {
	
	/*
	 * Holds the scanner that reads incoming values.
	 */
	
	public static Scanner ZReader;
	
	/*
	 * Returns an integer between 1 and 12 as a month
	 * Wrong input will make it get called again
	 */
	
	public static int ZGetValidMonth()
	{
		int toRead = ZReader.nextInt();
		
		if ( toRead < 1 || toRead > 12 )
		{
			System.out.println("Please, enter a number between 1 and 12, inclusive.");
			return ZGetValidMonth();
		}
		else
		{
			return toRead;
		}
		
	}
	
	/*
	 * Gets an integer between 1 and 7 for next task
	 * calls and returns itself if given bad input
	 */
	
	public static int ZGetNextTask()
	{
		
		int next = ZReader.nextInt();
		
		if ( next < 1 || next > 7 )
		{
			System.out.println("Error. Please enter a number between 1 and 7, inclusive.");
			return ZGetNextTask();
		}
		
		return next;
	}
	
	/*
	 * Gets a positive integer
	 */
	
	public static int ZGetPositiveInt()
	{
		int asPositive = ZReader.nextInt();
		
		if ( asPositive >= 0 )
		{
			return asPositive;
		}
		
		System.out.println("Please enter something greater than or equal to 0.");
		return ZGetPositiveInt();
	}
	
	/*
	 * Gets a positive float
	 */
	
	public static float ZGetPositiveFloat()
	{
		float asPositive = ZReader.nextFloat();
		
		if ( asPositive >= 0.0f )
		{
			return asPositive;
		}
		
		System.out.println("Please enter something greater than or equal to 0.");
		return ZGetPositiveFloat();
	}
	
	/*
	 * Creates and returns a specific type of employee based on an integer
	 * The integer is from ZGetNextTask()
	 * It gets a birth month first
	 * Then assigns the baseSalary by prompting the user
	 * Then if further action is needed in the case of non administrators
	 * and non full time professors works with the user
	 * @param asEmployeeType	An integer of 1-5 representing the employee type created.
	 */
	
	static public ZEmployee ZAddEmployeeType( int asEmployeeType )
	{
		System.out.println("Please enter the birth month of this employee.");
		
		int asBirthMonth = ZGetValidMonth();
		
		
		float asBaseSal = 0.0f;
		
		switch ( asEmployeeType )
		{
			case 1:
				
			case 4:
				System.out.println("Please enter the monthly salary");
				break;
				
			case 2:
				System.out.println("Please enter the base salary of your adjunct professor");
				break;
				
			case 3:
				System.out.println("Please enter the salary per section of your instructor");
				break;
				
			
				
			case 5:
				System.out.println("Please enter the hourly wage of your Graduate Teaching Assistant");
				break;
				
			default:
				System.out.println("If you managed to do this somehow, I would like to know.");
				break;
		}
		
		asBaseSal = ZGetPositiveFloat();
		
		switch ( asEmployeeType )
		{
			case 1:
				ZEmployee newProf = new ZFullTimeProfessor();
				newProf.ZSetBirthMonth(asBirthMonth);
				newProf.ZSetBaseSalary(asBaseSal);
				return newProf;
				
			case 4:
				ZEmployee newAdmin = new ZAdminEmployee();
				newAdmin.ZSetBirthMonth(asBirthMonth);
				newAdmin.ZSetBaseSalary(asBaseSal);
				return newAdmin;
				
			case 2:
				ZAdjunctProfessor AdjPro = new ZAdjunctProfessor();
				AdjPro.ZSetBirthMonth(asBirthMonth);
				AdjPro.ZSetBaseSalary(asBaseSal);
				System.out.println("Please enter amount of sections taught");
				AdjPro.ZSetSectionsDone( ZGetPositiveInt() );
				System.out.println("Please enter payment per section");
				AdjPro.ZSetPayPerSection( ZGetPositiveFloat() );
				return AdjPro;
				
			case 3:
				ZInstructor newInstructor = new ZInstructor();
				newInstructor.ZSetBirthMonth(asBirthMonth);
				newInstructor.ZSetBaseSalary( asBaseSal );
				System.out.println("Please enter amount of sections taught");
				newInstructor.ZSetSectionsTaught( ZGetPositiveInt() );
				return newInstructor;
				
			case 5:
				ZGraduateTeachingAssistant newGTA = new ZGraduateTeachingAssistant();
				newGTA.ZSetBirthMonth( asBirthMonth );
				newGTA.ZSetBaseSalary( asBaseSal );
				System.out.println("Enter hours worked");
				newGTA.ZSetHoursWorked( ZGetPositiveInt() );
				System.out.println("Enter overtime pay per hour");
				newGTA.ZSetOvertimePay( ZGetPositiveFloat() );
				return newGTA;
		}
		
		return null; // Don't know how you'll feel about this.
		
		
		
	}
	
	/*
	 * Does operations to calculate payroll for a month
	 * Sets up the ZReader to a new scanner to read input
	 * Has an employee list of who is on payroll
	 * Gets the current month payroll is doing, for employees eligible
	 * for birthday bonuses then lets the user select options of what
	 * they want to do.
	 */
	
	public static void main ( String[] args )
	{
		ZReader = new Scanner(System.in);
		
		
		boolean operating = true; 
		
		ArrayList<ZEmployee> ZEmployeeList = new ArrayList<ZEmployee>();
		
		System.out.println("Please, enter the current month, as 1 to 12, inclusive.");
		int currentMonth = ZGetValidMonth();
		
		
		
		while ( operating )
		{
			
			System.out.println("1- Add a full-time professor");
			System.out.println("2- Add an adjunct professor");
			System.out.println("3- Add an instructor");
			System.out.println("4- Add an administrator");
			System.out.println("5- Add a graduate teaching assistant");
			System.out.println("6- Print payroll");
			System.out.println("7- Quit");
		
			int asNextTask = ZGetNextTask();
			switch ( asNextTask )
			{
				case 1:
					
				case 2:
					
				case 3:
					
				case 4:
					
				case 5:
					ZEmployeeList.add( ZAddEmployeeType(asNextTask ) );
					break;
					
					
				case 6:
					float curTotal = 0.0f;
					for ( ZEmployee curEmployee : ZEmployeeList )
					{
						curTotal += curEmployee.ZGetPayroll(currentMonth);
					}
					
					System.out.println("This month's total payroll is: $" + curTotal );
					break;
				case 7:
					return;
			}
		
		
		}
		
	}

}
