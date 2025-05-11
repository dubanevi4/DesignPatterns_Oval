package lt.esdc.shapes;

import lt.esdc.shapes.reader.InputDataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShapeApp {
    public static void main(String[] args) {

        final Logger logger = LogManager.getLogger(ShapeApp.class);
        try {
            InputDataReader.readAndStoreShapes("shapes.txt");
                logger.info("Shapes processing completed successfully");
        } catch (Exception e) {
                logger.error("Application error: {}", e.getMessage(), e);
        }
    }
}