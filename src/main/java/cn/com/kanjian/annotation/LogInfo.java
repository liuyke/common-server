package cn.com.kanjian.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD})  
public @interface LogInfo {
	
	/**
	 * 操作说明 
	 */
    String value();
    
}