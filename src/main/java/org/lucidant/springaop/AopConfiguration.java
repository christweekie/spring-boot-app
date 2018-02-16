/**
 *
 */
package org.lucidant.springaop;

import java.time.LocalDate;
import java.time.Month;

import org.aspectj.lang.annotation.Pointcut;
import org.lucidant.springaop.model.MyPerformanceMonitorInterceptor;
import org.lucidant.springaop.model.Person;
import org.lucidant.springaop.model.PersonService;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author chrisfaulkner
 *
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConfiguration
{

	@Pointcut("execution(public String org.lucidant.springaop.model.PersonService.getFullName(..))")
	public void monitor()
	{
	}

	@Pointcut("execution(public int org.lucidant.springaop.model.PersonService.getAge(..))")
	public void myMonitor()
	{
	}

	@Bean
	public PerformanceMonitorInterceptor performanceMonitorInterceptor()
	{
		return new PerformanceMonitorInterceptor(true);
	}

	@Bean
	public Advisor performanceMonitorAdvisor()
	{
		final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("org.lucidant.springaop.AopConfiguration.monitor()");
		return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor());
	}

	@Bean
	public Person person()
	{
		return new Person("John", "Smith", LocalDate.of(1980, Month.JANUARY, 12));
	}

	@Bean
	public PersonService personService()
	{
		return new PersonService();
	}

	@Bean
	public MyPerformanceMonitorInterceptor myPerformanceMonitorInterceptor()
	{
		return new MyPerformanceMonitorInterceptor(true);
	}

	@Bean
	public Advisor myPerformanceMonitorAdvisor()
	{
		final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("org.lucidant.springaop.AopConfiguration.myMonitor()");
		return new DefaultPointcutAdvisor(pointcut, myPerformanceMonitorInterceptor());
	}

}