package lt.esdc.shapes.validator;

import lt.esdc.shapes.validator.impl.ValidationResult;

public interface Validator<T> {
    ValidationResult validate(T input);
}