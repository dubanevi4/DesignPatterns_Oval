package lt.esdc.shapes.factory;

import lt.esdc.shapes.entity.Oval;
import lt.esdc.shapes.entity.Point;
import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.exception.OvalProjectException;
import lt.esdc.shapes.util.IDGenerator;
import lt.esdc.shapes.validator.impl.OvalValidator;
import lt.esdc.shapes.validator.impl.ValidationResult;
import lt.esdc.shapes.validator.Validator;

public class ShapeFactory {
    public enum ShapeType {
        OVAL
    }

    private static final Validator<Oval> ovalValidator = new OvalValidator();

    public static Shape createShape(ShapeType type, Point point1, Point point2) throws OvalProjectException {
        switch (type) {
            case OVAL:
                Oval oval = new Oval(point1, point2);
                ValidationResult validation = ovalValidator.validate(oval);
                if (!validation.isValid()) {
                    throw new OvalProjectException(validation.getErrorMessage());
                }
                long id = IDGenerator.generateId();
                oval.setID(id);
                return oval;
            default:
                throw new OvalProjectException("Illegal shape type :" + type);
        }
    }
}