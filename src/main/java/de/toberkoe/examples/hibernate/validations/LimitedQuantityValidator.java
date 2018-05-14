package de.toberkoe.examples.hibernate.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LimitedQuantityValidator implements ConstraintValidator<CompliedLimitedQuantity, BasketItem> {

	@Override
	public boolean isValid(BasketItem basketItem, ConstraintValidatorContext constraintValidatorContext) {
		if (basketItem == null || basketItem.getProduct() == null) {
			return true;
		}

		if (!basketItem.getProduct().isLimitedQuantity()) {
			return true;
		}

		return basketItem.getQuantity() == 1;
	}
}
