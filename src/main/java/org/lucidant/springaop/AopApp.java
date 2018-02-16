package org.lucidant.springaop;

import org.lucidant.springaop.model.Person;
import org.lucidant.springaop.model.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopApp
{
	public static void main(final String[] args)
	{

		final ApplicationContext context = new AnnotationConfigApplicationContext(AopConfiguration.class);
		final Person person = (Person) context.getBean("person");
		final PersonService personService = (PersonService) context.getBean("personService");

		System.out.println("Name is:" + personService.getFullName(person));
		System.out.println("Age is:" + personService.getAge(person));
	}
}