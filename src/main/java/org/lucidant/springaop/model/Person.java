/**
 *
 */
package org.lucidant.springaop.model;

import java.time.LocalDate;
import lombok.Data;

@Data
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

	public String getLastName()
	{
		return lastName;
	}

	public String getFirstName()
	{
		return firstName;
	}

}
