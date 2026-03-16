package carbontracker.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;                    // defines the period for how long to be defined 

@Retention(RetentionPolicy.RUNTIME)

public @interface HighEmission {                    // custom annotations are declared using @interface

    String message() default "High Carbon Emission Activity";

}
