package com.yzq.demo1.aop;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Service
public class WebLogAspect2 {

	private ThreadLocal<Long> startTimes = new ThreadLocal<Long>();

	@Pointcut("execution(public * com.yzq.demo1.controller..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	@Order(2)
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		System.out.println("URL2 : " + request.getRequestURL().toString());
		System.out.println("HTTP_METHOD2 : " + request.getMethod());
		System.out.println("IP2 : " + request.getRemoteAddr());
		System.out.println("CLASS_METHOD2 : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		System.out.println("ARGS2 : " + Arrays.toString(joinPoint.getArgs()));
		startTimes.set(new Date().getTime());
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	@Order(2)
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		System.out.println("RESPONSE2 : " + ret);
		System.out.println("COSTTIME2 : " + (new Date().getTime() - startTimes.get()));
	}
}
