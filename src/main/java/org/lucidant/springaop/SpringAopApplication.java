/**
 *
 */
package org.lucidant.springaop;

import java.util.Arrays;

import org.lucidant.springaop.model.Person;
import org.lucidant.springaop.model.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author chrisfaulkner
 *
 */
@SpringBootApplication
public class SpringAopApplication
{
	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		SpringApplication.run(SpringAopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(final ApplicationContext ctx)
	{
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			final String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (final String beanName : beanNames)
			{
				System.out.println(beanName);
			}

			final Person person = (Person) ctx.getBean("person");
			final PersonService personService = (PersonService) ctx.getBean("personService");

			System.out.println("Name is:" + personService.getFullName(person));
			System.out.println("Age is:" + personService.getAge(person));

		};
	}
}
