package com.fastcampus.boardserver.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginCheck {
	
	public static enum UserType{
		USER, ADMIN
	}
	
	UserType type() ;

}
