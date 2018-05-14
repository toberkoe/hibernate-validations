package de.toberkoe.examples.hibernate.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = LimitedQuantityValidator.class)
@Documented
public @interface CompliedLimitedQuantity {

	String message() default "Quantity of product is too high due to limitations";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
