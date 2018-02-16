/**
 *
 */
package org.lucidant.springaop.model;

/**
 * @author chrisfaulkner
 *
 */
import java.time.LocalDate;

public class Person
{
	private String lastName;
	private String firstName;
	private LocalDate dateOfBirth;

	public Person(final String firstName, final String lastName, final LocalDate dateOfBirth)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(final LocalDate dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}
}
