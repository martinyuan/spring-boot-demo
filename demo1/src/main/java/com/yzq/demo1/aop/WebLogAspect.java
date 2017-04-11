package com.yzq.demo1.aop;

import java.util.Arrays;

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
public class WebLogAspect {

	@Pointcut("execution(public * com.yzq.demo1.controller..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	@Order(1)
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		System.out.println("URL : " + request.getRequestURL().toString());
		System.out.println("HTTP_METHOD : " + request.getMethod());
		System.out.println("IP : " + request.getRemoteAddr());
		System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	@Order(1)
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		System.out.println("RESPONSE : " + ret);
	}
}
