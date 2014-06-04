/**
 * 
 */
package de.hfu.meetme.model;

/**
 * @author Simeon Sembach
 *
 */
public enum MMGender
{

	MALE, FEMALE;
	
	// MM-API:
	
	/**
	 * Returns whether the {@link MMGender} is MALE.
	 * @return true if the {@link MMGender} is MALE, false otherwise
	 */
	public boolean isMale()
	{
		return this == MALE;
	}
	
	/**
	 * Returns whether the {@link MMGender} is FEMALE.
	 * @return true if the {@link MMGender} is FEMALE, false otherwise
	 */
	public boolean isFemale()
	{
		return this == FEMALE;
	}
	
}
