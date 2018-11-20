package com.ecc.exercise8;

import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorUtil {
	private static final ValidatorFactory factory;

	static {
		factory = Validation.buildDefaultValidatorFactory();
	}

	public static <T> Set<ConstraintViolation<T>> validate(T t) {
		Validator validator = factory.getValidator();
		return validator.validate(t);
	}

	public static <T> String getViolationMessage(Set<ConstraintViolation<T>> violations) {
		return violations.stream()
					  	 .map(v -> String.format("%s: %s", v.getPropertyPath(), v.getMessage()))
					  	 .collect(Collectors.joining("\n"));
	}
}