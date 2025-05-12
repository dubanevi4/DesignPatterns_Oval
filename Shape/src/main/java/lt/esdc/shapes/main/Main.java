package lt.esdc.shapes.main;

import lt.esdc.shapes.entity.Oval;
import lt.esdc.shapes.entity.Point;
import lt.esdc.shapes.reader.InputDataReader;
import lt.esdc.shapes.repository.ShapeRepository;
import lt.esdc.shapes.validator.impl.OvalValidator;
import lt.esdc.shapes.validator.impl.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static void main(String[] args) {

        final Logger logger = LogManager.getLogger(Main.class);
        try {
            InputDataReader.readAndStoreShapes("shapes.txt");
                logger.info("Shapes processing completed successfully");
        } catch (Exception e) {
                logger.error("Application error: {}", e.getMessage(), e);
        }
        Oval oval = (Oval) ShapeRepository.getInstance().getById(1);
        oval.setPoint1(new Point(-4, 0));
    }
}