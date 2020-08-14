package assignment6pack;

/*
 * Adjunct professor type. Gets paid base salary and per section they do
 * @author Hunter Blake
 * @since 1.0
 */

public class ZAdjunctProfessor extends ZEmployee {
	
	/*
	 * Constructor, takes no arguments.
	 */
	
	public ZAdjunctProfessor()
	{
		
	}
	
	private float payPerSection;
	private int sectionsDone;
	
	/*
	 * Returns how much they are paid per section as float
	 */

	public float ZGetPayPerSection()
	{
		return payPerSection;
	}
	
	/*
	 * Sets how much they are paid per section
	 * @param asPayPerSection	How much they should get paid per section, should be above 0 or won't be set
	 */
	
	public void ZSetPayPerSection( float asPayPerSection )
	{
		if ( asPayPerSection < 0 )
		{
			return;
		}
		
		payPerSection = asPayPerSection;
	}
	
	/*
	 * Returns the amount of sections done, taught this semester
	 */
	
	public int ZGetSectionsDone()
	{
		return sectionsDone;
	}
	
	/*
	 * Sets the amount of sections done/taught this semester
	 * @param asSectionsDone	An integer which is checked if it's non-negative
	 */
	
	public void ZSetSectionsDone( int asSectionsDone )
	{
		if ( sectionsDone < 0 )
		{
			return;
		}
		
		sectionsDone = asSectionsDone;
		
	}
	
	/*
	 * Does the same as parent class, but adds base salary and pay per section * sections done.
	 * @param currentMonth	The month they are receiving payroll
	 */
	
	@Override
	public float ZGetPayroll( int currentMonth )
	{
		return super.ZGetPayroll(currentMonth) + ZGetBaseSalary() + ( ZGetPayPerSection() * ZGetSectionsDone() );
	}

}
