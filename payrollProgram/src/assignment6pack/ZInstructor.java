package assignment6pack;

/*
 * Instructor, paid per section taught
 * @author Hunter Blake
 * @since 1.0
 */

public class ZInstructor extends ZEmployee {
	
	/*
	 * Constructor, takes no arguments.
	 */
	
	public ZInstructor()
	{
		
	}
	
	private int sectionsTaught;
	
	/*
	 * Returns the amount of sections taught this semester, as int
	 */
	
	public int ZGetSectionsTaught()
	{
		return sectionsTaught;
	}
	
	/*
	 * Sets the amount of sections taught this semester, as int
	 * @param asSectionsTaught	The amount of semesters taught, positive.
	 */
	
	public void ZSetSectionsTaught( int asSectionsTaught )
	{
		if ( sectionsTaught < 0 )
		{
			return;
		}
		
		sectionsTaught = asSectionsTaught;
	}
	
	/*
	 * Does the same as parent class, but multiplies ZGetSectionsTaught() * ZGetBaseSalary() and adds that
	 * to what the parent class returns
	 * @param currentMonth	The month they are receiving payroll
	 */
	
	@Override
	public float ZGetPayroll( int currentMonth )
	{
		return ( super.ZGetPayroll( currentMonth ) + ( ZGetSectionsTaught() * ZGetBaseSalary() ) );
	}

}
