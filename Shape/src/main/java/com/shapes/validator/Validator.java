package com.shapes.validator;

import com.shapes.validator.impl.ValidationResult;

public interface Validator<T> {
    ValidationResult validate(T input);
}