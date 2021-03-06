package nl.reproducer.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { FIELD, METHOD, ANNOTATION_TYPE } )
@Retention( RUNTIME )
@Documented
@Constraint( validatedBy = AlwaysInvalidValidator.class )
public @interface AlwaysInvalid {

    public static final String NONE = "none";

    String defaultState() default NONE;

    String message() default "{nl.reproducer.validation.AlwaysInvalid.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
