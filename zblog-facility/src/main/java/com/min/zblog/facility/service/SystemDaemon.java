package com.min.zblog.facility.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SystemDaemon {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
		ctx.registerShutdownHook();
	}

}
