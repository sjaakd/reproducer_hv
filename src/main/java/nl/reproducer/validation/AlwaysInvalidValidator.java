package nl.reproducer.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

@ApplicationScoped
public class AlwaysInvalidValidator implements ConstraintValidator<AlwaysInvalid, Object> {

    @Inject
    AlwaysInvalidDao dao;

    private AlwaysInvalid ca;

    @Override
    public void initialize( AlwaysInvalid annotation ) {
        this.ca = annotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        boolean valid = false;

        String state;
        if ( AlwaysInvalid.NONE.equals( ca.message() ) ) {
            state = dao.getState();
        }
        else {
            state = ca.defaultState();
        }

        if ( !valid ) {
            HibernateConstraintValidatorContext ctx = context.unwrap( HibernateConstraintValidatorContext.class );
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate( "message: " + state ) //
               .addConstraintViolation(); //
        }

        // always trigger an validation failure.
        return valid;
    }
}
