/**
 *
 */
package org.lucidant.springaop.model;

import java.util.Date;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;

/**
 * @author chrisfaulkner
 *
 */
public class MyPerformanceMonitorInterceptor extends AbstractMonitoringInterceptor
{

	public MyPerformanceMonitorInterceptor()
	{
	}

	public MyPerformanceMonitorInterceptor(final boolean useDynamicLogger)
	{
		setUseDynamicLogger(useDynamicLogger);
	}

	@Override
	protected Object invokeUnderTrace(final MethodInvocation invocation, final Log log) throws Throwable
	{

		final String name = createInvocationTraceName(invocation);
		final long start = System.currentTimeMillis();
		log.info("Method " + name + " execution started at:" + new Date());
		try
		{
			return invocation.proceed();
		} finally
		{
			final long end = System.currentTimeMillis();
			final long time = end - start;
			log.info("Method " + name + " execution lasted:" + time + " ms");
			log.info("Method " + name + " execution ended at:" + new Date());

			if (time > 10)
			{
				log.warn("Method execution longer than 10 ms!");
			}

		}
	}
}
