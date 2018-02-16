/**
 *
 */
package org.lucidant.springaop.model;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author chrisfaulkner
 *
 */
public class PersonService
{
	public String getFullName(final Person person)
	{
		return person.getLastName() + " " + person.getFirstName();
	}

	public int getAge(final Person person)
	{
		final Period period = Period.between(person.getDateOfBirth(),
				LocalDate.now());
		return period.getYears();
	}
}
