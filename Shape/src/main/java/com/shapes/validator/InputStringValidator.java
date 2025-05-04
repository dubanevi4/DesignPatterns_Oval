package com.shapes.validator;

import com.shapes.exception.OvalProjectException;

import java.util.regex.Pattern;

public class InputStringValidator implements Validator<String>{
    // Expected pattern: Oval: «1.9; 2.1; 3.2; 4.5»;
    private static final Pattern OVAL_PATTERN = Pattern.compile(
            "^Oval:\\s*«\\s*([+-]?\\d*\\.?\\d+)\\s*;\\s*([+-]?\\d*\\.?\\d+)\\s*;\\s*([+-]?\\d*\\.?\\d+)\\s*;\\s*([+-]?\\d*\\.?\\d+)\\s*»\\s*;$",
            Pattern.UNICODE_CHARACTER_CLASS
    );

    @Override
    public ValidationResult validate(String input) {
        if (input == null || input.trim().isEmpty()) {
            return ValidationResult.invalid("Input string is empty");
        }

        if (!OVAL_PATTERN.matcher(input).matches()) {
            return ValidationResult.invalid("Invalid oval format. Expected: Oval: «x1; y1; x2; y2»;");
        }

        return ValidationResult.valid();
    }

    public static double[] parseOvalCoordinates(String input) throws OvalProjectException {
        java.util.regex.Matcher matcher = OVAL_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new OvalProjectException("Invalid oval format");
        }

        return new double[] {
                Double.parseDouble(matcher.group(1)),
                Double.parseDouble(matcher.group(2)),
                Double.parseDouble(matcher.group(3)),
                Double.parseDouble(matcher.group(4))
        };
    }
}
