package lt.esdc.shapes.reader;

import lt.esdc.shapes.entity.Shape;
import lt.esdc.shapes.entity.Point;
import lt.esdc.shapes.exception.OvalProjectException;
import lt.esdc.shapes.factory.ShapeFactory;
import lt.esdc.shapes.repository.ShapeRepository;
import lt.esdc.shapes.storage.Warehouse;
import lt.esdc.shapes.validator.impl.InputStringValidator;
import lt.esdc.shapes.validator.impl.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputDataReader {
    private static final Logger logger = LogManager.getLogger(InputDataReader.class);
    private static final InputStringValidator validator = new InputStringValidator();
    private static final ShapeRepository repository = ShapeRepository.getInstance();

    public static void readAndStoreShapes(String filePath) throws IOException {
        logger.info("Reading shapes from file: {}", filePath);

        InputStream inputStream = InputDataReader.class.getClassLoader().getResourceAsStream(filePath);
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            reader.lines()
                    .filter(line -> !line.trim().isEmpty())
                    .filter(line -> !line.startsWith("#")) // Фильтр комментариев
                    .forEach(InputDataReader::processLine);
            reader.close();
        } else {
            logger.error("Файл не найден в classpath");
        }
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

            repository.save(oval); // Сохраняем в репозитории
            Warehouse.getInstance().update(oval.getID(), oval.calculateArea(), oval.calculatePerimeter()); // Используем ID, который уже есть в объекте

            logger.debug("Created oval with id: {}", oval.getID());

        } catch (OvalProjectException e) {
            logger.error("Error processing line: {} - {}", line, e.getMessage());
        }
    }
}