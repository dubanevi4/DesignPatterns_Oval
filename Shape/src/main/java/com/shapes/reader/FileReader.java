package com.shapes.reader;

import com.shapes.entity.Shape;
import com.shapes.entity.Point;
import com.shapes.exception.OvalProjectException;
import com.shapes.factory.ShapeFactory;
import com.shapes.repository.ShapeRepository;
import com.shapes.validator.impl.InputStringValidator;
import com.shapes.validator.impl.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
    private static final Logger logger = LogManager.getLogger(FileReader.class);
    private static final InputStringValidator validator = new InputStringValidator();
    private static final ShapeRepository repository = ShapeRepository.getInstance();

    public static void readAndStoreShapes(String filePath) throws IOException {
        logger.info("Reading shapes from file: {}", filePath);

        Files.lines(Path.of(filePath))
                .filter(line -> !line.trim().isEmpty())
                .filter(line -> !line.startsWith("#"))
                .forEach(FileReader::processLine);
    }

    private static void processLine(String line) {
        try {
            ValidationResult validation = validator.validate(line);
            if (!validation.isValid()) {
                logger.warn("Validation failed: {}", validation.getErrorMessage());
                return;
            }

            double[] coords = InputStringValidator.parseOvalCoordinates(line);
            Shape oval = ShapeFactory.createShape(
                    ShapeFactory.ShapeType.OVAL,
                    new Point(coords[0], coords[1]),
                    new Point(coords[2], coords[3])
            );

            long id = repository.save(oval);
            logger.debug("Created oval with id: {}", id);

        } catch (OvalProjectException e) {
            logger.error("Error processing line: {} - {}", line, e.getMessage());
        }
    }
}