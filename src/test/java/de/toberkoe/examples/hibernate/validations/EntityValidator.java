package de.toberkoe.examples.hibernate.validations;

import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public final class EntityValidator {

	private final Object entity;
	private final Set<ConstraintViolation<Object>> violations = new HashSet<>();

	private EntityValidator(Object entity) {
		this.entity = entity;
		validate();
	}

	public static EntityValidator assertThat(Object entity) {
		return new EntityValidator(entity);
	}

	private void validate() {
		violations.clear();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		violations.addAll(validator.validate(entity));
	}

	public EntityValidator isValid() {
		if (!violations.isEmpty()) {
			throw new AssertionError("Expected entity to be valid");
		}
		return this;
	}

	public EntityValidator isInvalid() {
		if (violations.isEmpty()) {
			throw new AssertionError("Expected entity to be invalid");
		}
		return this;
	}

	public EntityValidator containsInvalidAttribute(String attributeName) {
		if (violations.stream()
				.map(ConstraintViolation::getPropertyPath)
				.map(Path::toString)
				.anyMatch(n -> n.equals(attributeName))) {
			return this;
		}
		throw new AssertionError("Expected invalid attributes to contain " + attributeName);
	}

	public void printMessages() {
		violations.stream()
				.map(ConstraintViolation::getMessage)
				.forEach(System.out::println);
	}
}
