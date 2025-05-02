package com.shapes.validator;

public interface Validator<T> {
    ValidationResult validate(T input);
}