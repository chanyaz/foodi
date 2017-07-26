package com.artinrayan.foodi.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

	/*@Before("execution(* com.mkyong.customer.bo.CustomerBo.addCustomer(..))")
	public void logBefore(JoinPoint joinPoint) {

		System.out.println("logBefore() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");
	}*/

	/*@After("execution(* com.mkyong.customer.bo.CustomerBo.addCustomer(..))")
	public void logAfter(JoinPoint joinPoint) {

		System.out.println("logAfter() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("******");

	}*/
	
	/*@AfterReturning(
			pointcut = "execution(* com.mkyong.customer.bo.CustomerBo.addCustomerReturnValue(..))",
			returning= "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {

		System.out.println("logAfterReturning() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("Method returned value is : " + result);
		System.out.println("******");

	}*/
	
	/*@AfterThrowing(
			pointcut = "execution(* com.mkyong.customer.bo.CustomerBo.addCustomerThrowException(..))",
			throwing= "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

		System.out.println("logAfterThrowing() is running!");
		System.out.println("hijacked : " + joinPoint.getSignature().getName());
		System.out.println("Exception : " + error);
		System.out.println("******");

	}*/
	
	
	@Around("execution(* com.artinrayan.foodi.core.HostService.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		System.out.println("logAround() is running!");
		long start = System.currentTimeMillis();
		System.out.println("hijacked method : " + joinPoint.getSignature().getName());
		System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));
		
		System.out.println("Around before is running!");
		Object ret = joinPoint.proceed();
		System.out.println("Around after is running!");
		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println("Method execution time: " + elapsedTime + " milliseconds.");
		System.out.println("******");

		return ret;
	}

	/**
	 * If you have to catch absolutely everything, use "catch Throwable"
	 * The java.lang.Throwable class is the superclass of all errors and exceptions in the Java language.
	 * Only objects that are instances of this class (or one of its subclasses) are thrown by the Java Virtual Machine
	 * or can be thrown by the Java throw statement.
	 * @param ex
	 * @throws Throwable
     */
	@AfterThrowing(pointcut = "execution(* com.artinrayan.foodi.core.HostService.*(..))", throwing = "ex")
	public void logAfterThrowingAllMethods(JoinPoint joinPoint, Exception ex) throws Throwable
	{
		System.out.println("****LoggingAspect.logAfterThrowingAllMethods() " + ex);
		System.out.println("****Method name " + joinPoint.getSignature());
		System.out.println("****Argument " + joinPoint.getArgs()[0]);
	}

//	@Before("execution(* com.howtodoinjava.app.service.impl.EmployeeManagerImpl.getEmployeeById(..))")
//	public void logBeforeGetEmployee(JoinPoint joinPoint)
//	{
//		System.out.println("****LoggingAspect.logBeforeGetEmployee() : " + joinPoint.getSignature().getName());
//	}
//
//	@Before("execution(* com.howtodoinjava.app.service.impl.EmployeeManagerImpl.createEmployee(..))")
//	public void logBeforeCreateEmployee(JoinPoint joinPoint)
//	{
//		System.out.println("****LoggingAspect.logBeforeCreateEmployee() : " + joinPoint.getSignature().getName());
//	}
	
}