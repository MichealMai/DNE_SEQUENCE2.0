package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)    //Annotation the target
@Retention(RetentionPolicy.RUNTIME)

public @interface column {
	public String field();// charater type
	public boolean primaryKey() default false;//is primary key
    public String type() default "VARCHAR(80)"; // character type
    public boolean defaultNull() default true;  // is null
}
